package com.toyodo.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.toyodo.dao.ExternalDAO;
import com.toyodo.model.Invoice;
import com.toyodo.model.Order;
import com.toyodo.model.Products;
import com.toyodo.utils.DBConnection;

/*
 * @author JAVATAR
 * */

public class ExternalDAOImpl implements ExternalDAO {
	private static ExternalDAOImpl externalDAOImpl;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	private List<Products> listProducts = new ArrayList<Products>();

	public static ExternalDAOImpl createObject() {
		if (externalDAOImpl == null) {
			synchronized (ExternalDAOImpl.class) {
				externalDAOImpl = new ExternalDAOImpl();
			}
		}
		return externalDAOImpl;
	}

	@Override
	public void createConnection() {
		con = DBConnection.createConnection();
	}

	@Override
	public boolean isCustomerID(String customerNameID) {
		// regular expression pattern to determine whether the input provided is
		// customer id or customer name
		// if customerNameID contains any number, then the input is clearly Customer ID
		String regex = "(.)*(\\d)(.)*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(customerNameID);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	@Override
	public Products getProductsDetails(String productID) {
		createConnection();
		Products products = null;

		final String strsql = "SELECT * FROM `products` WHERE `product_id` = ?";
		try {
			ps = con.prepareStatement(strsql);
			ps.setString(1, productID);
			rs = ps.executeQuery();
			if (rs.next()) {
				products = new Products();
				products.setProductID(rs.getString("product_id"));
				products.setName(rs.getString("name"));
				products.setPrice(rs.getDouble("price"));
				products.setCategory(rs.getString("category"));

			}
		} catch (SQLException sqlex) {
			System.out.println(sqlex);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return products;
	}

	@Override
	public List<Products> listProducts(String productID) {
		createConnection();
		// clear the previous record on every request to avoid appending list of orders
		if (!listProducts.isEmpty())
			listProducts.clear();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Products product = null;
		System.out.println("TEST PRODUCT ID>> " + productID);
		// trim the list of product IDs before splitting
		// separate the product ID to fetch details from the product table
		String[] listOfProductIDs = productID.trim().split(" ");
		System.out.println("listOfProductIDs: " + listOfProductIDs);

		for (String id : listOfProductIDs) {
			System.out.println("LIST [] " + id);
			final String strsql = "SELECT * FROM `products` WHERE `product_id` = ?";
			try {

				ps = con.prepareStatement(strsql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					product = new Products(rs.getString("product_id"), rs.getString("name"), rs.getDouble("price"),
							rs.getString("category"));
					listProducts.add(product);
				}
			} catch (SQLException sqlex) {
				System.out.println(sqlex);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println(listProducts);
		}
		return listProducts;

	}

	@Override
	public double calculateGSTRate(double price, int quantity) {
		// GST rates for the product
		// flat rate of 10% for all types of products
		return (price * quantity * 0.1);
	}

	@Override
	public double calculateTotalGSTAmmount(double totalGSTAmount, double gst) {
		// total GST amount is the sum of GST rates for products
		return (totalGSTAmount + gst);
	}

	@Override
	public double calculateTotalInvoiceValue(double productPrice, double shippingCost, double gst) {
		// total invoice value is the sum of product prices, shipping cost and GST
		return (productPrice + shippingCost + gst);
	}

	@Override
	public Invoice viewInvoice(Timestamp orderDatetime, Date orderDate) {
		createConnection();
		Invoice invoice = null;
		Order order = null;
		List <Products> product = new ArrayList<Products>();
		final String strsql = "select * from `invoice` inner join `order` inner join `order_product_util` on `invoice`.`order_datetime` = `order`.`order_datetime` AND `order`.`order_id` = `order_product_util`.`order_id` WHERE `invoice`.`order_datetime` = ?";
//		final String strsql = "SELECT * FROM `invoice` WHERE `order_datetime` = ?";
		try {
			ps = con.prepareStatement(strsql);
			ps.setTimestamp(1, orderDatetime);
			rs = ps.executeQuery();
			System.out.println(ps);
			
			while (rs.next()) {

				String status = rs.getString("status");
				java.util.Date today = new java.util.Date();
				System.out.println("Test today with orderDate " + today);
				if ((status.equals("Approved") || status.equals("Completed")) && !(orderDate.equals(today))) {
					invoice = new Invoice();
					invoice.setInvoiceID(rs.getInt("invoice_id"));
					invoice.setInvoiceDate(rs.getDate("invoice_date"));
					invoice.setOrderDatetime(rs.getTimestamp("order_datetime"));
					invoice.setCustomerID(rs.getString("customer_id"));
					invoice.setGst(rs.getDouble("gst"));
					invoice.setTypeOfGST(rs.getString("type_of_gst"));
					invoice.setTotalGSTAmount(rs.getDouble("total_gst_amount"));
					invoice.setTotalInvoiceValue(rs.getDouble("total_invoice_value"));
					invoice.setStatus(rs.getString("status"));
					invoice.setOrder(new Order(rs.getString("order_id"), rs.getDouble("shipping_cost"), rs.getString("shipping_agency")));
					product.add(new Products(rs.getString("product_id")));
					
//					invoice.setProduct((rs.getString("product_id"));
				}
			}
			invoice.setProduct(product);
		} catch (SQLException sqlex) {
			System.out.println(sqlex);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return invoice;
	}

}

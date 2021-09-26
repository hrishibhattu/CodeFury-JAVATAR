package com.toyodo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.toyodo.dao.EmployeeDAO;
import com.toyodo.model.Employee;
import com.toyodo.model.Invoice;
import com.toyodo.model.Order;
import com.toyodo.model.Products;
import com.toyodo.utils.DBConnection;

/*
 * @author JAVATAR
 * */

public class EmployeeDAOImpl implements EmployeeDAO {
	private static EmployeeDAOImpl employeeDAOImpl;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	private List<Order> listOrder = new ArrayList<Order>();
	private List<Products> listProducts = new ArrayList<Products>();

	public static EmployeeDAOImpl createObject() {
		if (employeeDAOImpl == null) {
			synchronized (EmployeeDAOImpl.class) {
				employeeDAOImpl = new EmployeeDAOImpl();
			}
		}
		return employeeDAOImpl;
	}

	@Override
	public void createConnection() {
		con = DBConnection.createConnection();
	}

	@Override
	public String employeeLogin(Employee employeeLogin) {
		createConnection();
		String credential = "invalid";
		try {
			String strlogin = "SELECT * FROM `employee` WHERE `employee_id` = ? AND `password` = ?";
			ps = con.prepareStatement(strlogin);
			ps.setString(1, employeeLogin.getEmployee_id());
			ps.setString(2, employeeLogin.getPassword());
			rs = ps.executeQuery();
			if (rs.next())
				credential = "valid";
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
		return credential;
	}

	@Override
	public List<Order> listOrder() {
		createConnection();
		//	clear the previous record on every request to avoid appending previous list of orders
		if (!listOrder.isEmpty())
			listOrder.clear();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Order order = null;
		final String strsql = "SELECT * FROM `order` INNER JOIN `customer` ON `order`.`customer_id` = `customer`.`customer_id`";
		try {
			ps = con.prepareStatement(strsql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String currOrderId = rs.getString("order_id");
				String getProductsQuery = "SELECT * FROM `order_product_util` WHERE `order_id`=" + currOrderId;
				Statement stmt = con.createStatement();
				ResultSet rs1 = stmt.executeQuery(getProductsQuery);
				String listOfProducts = "";
				while (rs1.next()) {
					listOfProducts += " " + rs1.getString("product_id");
				}
				order = new Order(rs.getString("order_id"), rs.getDate("order_date"), rs.getTimestamp("order_datetime"),
						rs.getString("customer_id"), rs.getString("name"), rs.getString("address"), listOfProducts,
						rs.getDouble("total_order_value"), rs.getDouble("shipping_cost"),
						rs.getString("shipping_agency"), rs.getString("status"));
				listOrder.add(order);
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
		return listOrder;
	}

	@Override
	public int createQuote(Order order) {
		createConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1;
		final String strsql = "INSERT INTO `order`(`order_date`, `order_datetime`, `customer_id`, `total_order_value`, `shipping_cost`, `shipping_agency`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(strsql);
			ps.setDate(1, order.getOrderDate());
			ps.setTimestamp(2, order.getOrderDatetime());
			ps.setString(3, order.getCustomerID());
			ps.setDouble(4, order.getTotalOrderValue());
			ps.setDouble(5, order.getShippingCost());
			ps.setString(6, order.getShippingAgency());
			ps.setString(7, order.getStatus());
			System.out.println(ps);
			if (ps.executeUpdate() > 0) {
				System.out.println("Done");
			}
			
			final String getOrderId = "SELECT `order_id` FROM `order` WHERE `order_datetime` = ?";
			Timestamp orderDateTime = order.getOrderDatetime();
			ps1 = con.prepareStatement(getOrderId);
			ps1.setTimestamp(1, orderDateTime);
			ResultSet rs2 = ps1.executeQuery();
			
			int order_id = 0;
			while (rs2.next()) {
				order_id = rs2.getInt("order_id");
			}
			Map<String, Integer> mapOfProducts = order.getMapOfProducts();
			for (String product : mapOfProducts.keySet()) {
				final String insertProduct = "INSERT INTO `order_product_util` (order_id, product_id, quantity) VALUES (?, ?, ?)";
				ps1 = con.prepareStatement(insertProduct);
				ps1.setInt(1, order_id);
				ps1.setString(2, product);
				ps1.setInt(3, mapOfProducts.get(product));
				ps1.executeUpdate();
			}
            return 1;
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
		return 0;
	}

	@Override
	public List<Products> listProducts() {
		createConnection();
		// clear the previous record on every request to avoid appending list of orders
		if (!listProducts.isEmpty())
			listProducts.clear();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Products product = null;
		final String strsql = "SELECT * FROM `products`";
		try {
			ps = con.prepareStatement(strsql);
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
		return listProducts;
	}

	@Override
	public String getEmployeeDetailsByEmpId(String empId) {
		createConnection();
		String employeeName = null;
		try {
			String query = "select name from `employee` WHERE `employee_id` = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, empId);
			rs = ps.executeQuery();
			if (rs.next()) {
				employeeName = rs.getString("name");
				System.out.println(employeeName + " in dao");// debug
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
		return employeeName;
	}

	@Override
	public String getLastAccessTime(String employeeId, String currentAccess) {
		String lastLoginTime = "Accessing for the first time";
		createConnection();
		try {
			String query = "select logintime from `last_login_details` WHERE `login_id` = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, employeeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				lastLoginTime = rs.getString("logintime");
				// debug
				System.out.println(lastLoginTime + " in dao");
				String updateQuery = "UPDATE `last_login_details` SET logintime=? WHERE login_id = ?";
				ps = con.prepareStatement(updateQuery);
				ps.setString(1, currentAccess);
				ps.setString(2, employeeId);
				ps.executeUpdate();
			} else {
				String currentAccessTime = currentAccess;
				String insQuery = "INSERT INTO `last_login_details` VALUES (?, ?)";
				ps = con.prepareStatement(insQuery);
				ps.setString(1, employeeId);
				ps.setString(2, currentAccessTime);
				ps.execute();
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
		return lastLoginTime;
	}


	@Override
	public int createInvoice(Invoice invoice) {
		createConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String strsql = "INSERT INTO `invoice` (`invoice_date`, `order_datetime`, `customer_id`, `gst`, `type_of_gst`, `total_gst_amount`, `total_invoice_value`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(strsql);
			ps.setDate(1, invoice.getInvoiceDate());
			ps.setTimestamp(2, invoice.getOrderDatetime());
			ps.setString(3, invoice.getCustomerID());
			ps.setDouble(4, invoice.getGst());
			ps.setString(5, invoice.getTypeOfGST());
			ps.setDouble(6, invoice.getTotalGSTAmount());
			ps.setDouble(7, invoice.getTotalInvoiceValue());
			ps.setString(8, invoice.getStatus());
			System.out.println(ps);
			if (ps.executeUpdate() > 0) {
				return 1;
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
		return 0;
	}

	@Override
	public void importProducts(List<Products> products) {
		createConnection();
		PreparedStatement ps = null;
		String insertSql = "INSERT INTO products VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE name=?, price=?, category=?";
		try {
			ps = con.prepareStatement(insertSql);
			for (Products p : products) {
				ps.setString(1, p.getProductID());
				ps.setString(2, p.getName());
				ps.setDouble(3, p.getPrice());
				ps.setString(4, p.getCategory());
				ps.setString(5, p.getName());
				ps.setDouble(6, p.getPrice());
				ps.setString(7, p.getCategory());
				ps.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeConnection() {
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

}

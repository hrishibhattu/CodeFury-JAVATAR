package com.toyodo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.toyodo.dao.CustomerDAO;
import com.toyodo.model.Customer;
import com.toyodo.model.Invoice;
import com.toyodo.model.Order;
import com.toyodo.utils.DBConnection;

/*
 * @author JAVATAR
 * */

public class CustomerDAOImpl implements CustomerDAO {
	private static CustomerDAOImpl customerDAOImpl;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	private List<Order> listOrder = new ArrayList<Order>();

	public static CustomerDAOImpl createObject() {
		if (customerDAOImpl == null) {
			synchronized (CustomerDAOImpl.class) {
				customerDAOImpl = new CustomerDAOImpl();
			}
		}
		return customerDAOImpl;
	}

	@Override
	public void createConnection() {
		con = DBConnection.createConnection();
	}

	@Override
	public String customerLogin(Customer customerLogin) {
		createConnection();
		String credential = "invalid";
		try {
			String strlogin = "SELECT * FROM `customer_login_credential` WHERE (`customer_id` = ? OR `name` = ?) AND `password` = ?";
			ps = con.prepareStatement(strlogin);
			ps.setString(1, customerLogin.getCustomerID());
			ps.setString(2, customerLogin.getName());
			ps.setString(3, customerLogin.getPassword());
			rs = ps.executeQuery();
			if (rs.next())
				credential = "valid";
		} catch (SQLException sqlex) {
			System.out.println(sqlex);
		} finally {
			closeConnection();
		}
		return credential;

	}

	@Override
	public Customer searchCustomer(String customerID) {
		createConnection();
		Customer customer = null;
		final String strlogin = "SELECT * FROM `customer` WHERE `customer_id` = ?";
		try {
			ps = con.prepareStatement(strlogin);
			ps.setString(1, customerID);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer();
				customer.setCustomerID(rs.getString("customer_id"));
				customer.setName(rs.getString("name"));
				customer.setGstNumber(rs.getString("gst"));
				customer.setCity(rs.getString("city"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				customer.setPincode(rs.getInt("pincode"));
			}
		} catch (SQLException sqlex) {
			System.out.println(sqlex);
		} finally {
			closeConnection();
		}
		return customer;
	}

	@Override
	public String getLastAccessTime(String customerId, String currentAccess) {
		String lastLoginTime = "Accessing for first time";
		createConnection();
		try {
			String query = "select logintime from `last_login_details` WHERE `login_id` = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			if (rs.next()) {
				lastLoginTime = rs.getString("logintime");
				System.out.println(lastLoginTime + " in dao");// debug
				String updateQuery = "update `last_login_details` set logintime=? where login_id = ?";
				ps = con.prepareStatement(updateQuery);
				ps.setString(1, currentAccess);
				ps.setString(2, customerId);
				ps.executeUpdate();
			} else {
				String currentAccessTime = currentAccess;
				String insQuery = "insert into `last_login_details` values(?,?)";
				ps = con.prepareStatement(insQuery);
				ps.setString(1, customerId);
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
	public List<Order> listOrder(String customerNameID) {

		createConnection();
		// clear the previous record on every request to avoid appending previous list
		// of orders
		if (!listOrder.isEmpty())
			listOrder.clear();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Order order = null;
		final String strsql = "SELECT * FROM `order` INNER JOIN `customer` ON `order`.`customer_id` = `customer`.`customer_id` WHERE `customer`.`customer_id` = ?";
		try {
			ps = con.prepareStatement(strsql);
			ps.setString(1, customerNameID);
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
	public void updateStatus(Order order) {
		createConnection();
		PreparedStatement psv = null;
		java.util.Date today = new java.util.Date();
		try {
			long timeDifference = today.getTime() - order.getOrderDatetime().getTime();
			long daysDifference = (timeDifference / (1000 * 60 * 60 * 24)) % 365;
			//	if current date is less than or equal to 30 days
			//	the order gets approved
			if (daysDifference <= 30) {
				String query = "UPDATE `order` SET `status` = \"Approved\" WHERE `order_datetime`= ?";
				String queryInv = "UPDATE `invoice` SET `status` = \"Approved\" WHERE `order_datetime`= ?";
				ps = con.prepareStatement(query);
				psv = con.prepareStatement(queryInv);
				ps.setTimestamp(1, order.getOrderDatetime());
				psv.setTimestamp(1, order.getOrderDatetime());
				ps.executeUpdate();
				psv.executeUpdate();
			} 
			//	if current date is more than 30 days
			//	the order gets expired
			else {
				String query = "UPDATE `order` SET `status` = \"Expired\" WHERE `order_datetime`= ?";
				String queryInv = "UPDATE `invoice` SET `status` = \"Expired\" WHERE `order_datetime`= ?";
				ps = con.prepareStatement(query);
				psv = con.prepareStatement(queryInv);
				ps.setTimestamp(1, order.getOrderDatetime());
				psv.setTimestamp(1, order.getOrderDatetime());
				ps.executeUpdate();
				psv.executeUpdate();
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
	}

	@Override
	public String getCustomerDetailsByEmpId(String custId) {
		createConnection();
		String customerName = null;
		try {
			String query = "select name from `customer_login_credential` WHERE `customer_id` = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, custId);
			rs = ps.executeQuery();
			if (rs.next()) {
				customerName = rs.getString("name");
				// System.out.println("[DAO] CustomerName: " + customerName);
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
		return customerName;
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

package core.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;


import core.database.model.Coupon;
import core.database.model.CustomerDO;
import core.database.model.Type;

public class CustomerDBDAO implements CustomerDAO {

	private Connection connection;

	public CustomerDBDAO() {
		this.connection = ConnectionManagerPool.getInstance().returnConnection();
	}

	@Override
	public int createCustomer(CustomerDO customerDO) {
		try {
			Statement statement = connection.createStatement();
			String insertQuery = buildCustomerInsertQuery(customerDO);
			int numberOfInsertRecords = statement.executeUpdate(insertQuery);
			statement.close();
			return numberOfInsertRecords;
		} catch (SQLException e) {
			throw new RuntimeException(
					"Wasn't able to inset customer: " + "\n" + customerDO.toString() + "\n" + e.getMessage());
		}
	}

	@Override
	public int removeCustomer(CustomerDO customerDO) {
		try {
			Statement statement = connection.createStatement();
			int numberOfRemoveRecords = statement.executeUpdate("DELETE FROM Customer WHERE customer_id = " + customerDO.getCustomerId());
			statement.close();
			return numberOfRemoveRecords;
			} catch (SQLException e) {
			throw new RuntimeException(
					"wasn't able to remove customer ..." + customerDO.toString() + "\n" + e.getMessage());
		}
	}

	@Override
	public int updateCustomer(CustomerDO customerDO) {
		try {
			Statement statement = connection.createStatement();
			String updateQuery = updateCustomerInsertQuery(customerDO.getCustomerId(), customerDO.getCustomerName(),
					customerDO.getPassword(), customerDO.getEmail());
			int numberOfUpdateRecords = statement.executeUpdate(updateQuery);
			statement.close();
			return numberOfUpdateRecords;
		} catch (SQLException e) {
			String errorMessage = "Wasn't able to update customer ..." + customerDO.toString() + "\n" + e.getMessage();
			System.err.println(errorMessage);
			throw new RuntimeException(errorMessage);
		}
	}

	@Override
	public CustomerDO getCustomer(long customerId) {
		Statement statement = null;
		CustomerDO customerDO = null;
		try {
			statement = connection.createStatement();
			String customerQuery = "SELECT * FROM customer where customer_id = " + customerId;
			ResultSet resultSet = statement.executeQuery(customerQuery);
			while (resultSet.next()) {
				customerDO = new CustomerDO(Integer.parseInt(resultSet.getString("CUSTOMER_ID")),
						resultSet.getString("CUSTOMER_NAME"), resultSet.getString("PASSWORD"),
						resultSet.getString("EMAIL"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Wasn't able to find customerID ..." + customerId + "\n" + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customerDO;
	}

	@Override
	public Collection<CustomerDO> getAllCustomers() {
		Statement statement = null;
		Collection<CustomerDO> customers = new ArrayList<>();
		try {
			statement = connection.createStatement();

			String customerQuery = "SELECT customer_Id, customer_Name, password, email FROM customer";
			ResultSet resultSet = statement.executeQuery(customerQuery);
			while (resultSet.next()) {
				CustomerDO tempCustomerDO = new CustomerDO(Integer.parseInt(resultSet.getString("CUSTOMER_ID")),resultSet.getString("CUSTOMER_NAME"), resultSet.getString("PASSWORD"), resultSet.getString("EMAIL"));
				customers.add(tempCustomerDO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Wasn't able to find any customer list ...\n" + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customers;
	}

	@Override
	public Collection<Coupon> getCoupons(long customerId) {
		Statement statement = null;
		Collection<Coupon> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();

			String couponQuery = "SELECT coupon_ID, title, startDate, endDate, amount, type, messege, image  FROM coupon";
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {

				Coupon tempCoupon = new Coupon(Long.parseLong(resultSet.getString("coupon_ID")),
						resultSet.getString("title"), resultSet.getString("startDate"), resultSet.getString("endDate"),
						Integer.parseInt(resultSet.getString("amount")), Type.valueOf(resultSet.getString("type")),
						resultSet.getString("messege"), Double.parseDouble(resultSet.getString("price")),
						resultSet.getString("image"));
				coupons.add(tempCoupon);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Wasn't able to find any coupons list ...\n" + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return coupons;
	}

	@Override
	public boolean login(long customerId, String password) {
		Statement statement = null;
		String tempCustomerName = null;
		String tempPassword = null;
		try {
			statement = connection.createStatement();

			String loginQuery = "SELECT Customer_Id, password from customer where Customer_Id = '" + customerId
					+ "' and password = '" + password + "'";
			ResultSet resultSet = statement.executeQuery(loginQuery);
			while (resultSet.next()) {

				tempCustomerName = resultSet.getString("Customer_Id");
				tempPassword = resultSet.getString("Password");
			}
		} catch (SQLException e) {
			throw new RuntimeException(
					"Customer id or/and password are not valid! Please try again... \n" + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (tempCustomerName != null & tempPassword != null) {
			return true;
		} else {
			return false;
		}
	}

	private String buildCustomerInsertQuery(CustomerDO customerDO) {
		StringBuilder sb = new StringBuilder("INSERT INTO Customer VALUES(");
		sb.append(String.valueOf(customerDO.getCustomerId()));
		sb.append(",");
		sb.append(surroundWithCommas(customerDO.getCustomerName()));
		sb.append(",");
		sb.append(surroundWithCommas(customerDO.getPassword()));
		sb.append(",");
		sb.append(surroundWithCommas(customerDO.getEmail()));
		sb.append(")");
		return sb.toString();
	}

	private String surroundWithCommas(String string) {
		return "'" + string + "'";
	}

	private String updateCustomerInsertQuery(long customerId, String customerName, String password, String email) {
		StringBuilder sb = new StringBuilder("Update Customer SET Customer_Name = ");
		sb.append(surroundWithCommas(customerName));
		sb.append(", Password = ");
		sb.append(surroundWithCommas(password));
		sb.append(", Email = ");
		sb.append(surroundWithCommas(email));
		sb.append("WHERE Customer_Id = " + customerId);
		return sb.toString();
	}

}

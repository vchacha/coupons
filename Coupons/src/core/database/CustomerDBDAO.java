package core.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;

public class CustomerDBDAO implements CustomerDAO {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
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
	public int purchaseCoupon(CouponDO couponDO, long customerID) {
		Statement statement = null;
		try {
			statement = connection.createStatement();

			String query = ("INSERT INTO Customer_Coupon VALUES(" + customerID + "," + couponDO.getCouponId() + ")");
			int numberOfPurchaseCoupon = statement.executeUpdate(query); 
			statement.close();
		return numberOfPurchaseCoupon;
		} catch (SQLException e) {
			throw new RuntimeException(
					"Wasn't able to purchase coupon: " + "\n" + couponDO.toString() + "\n" + e.getMessage());
			}
	}
	
	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByCustomer(long customerId) {
		Statement statement = null;
		List<String> couponIds = new ArrayList<>();
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();
			String couponIdsQuery = ("SELECT distinct coupon_id FROM customer_coupon where customer_Id = " + customerId + ";");
			ResultSet resultSetForCouponIds = statement.executeQuery(couponIdsQuery);
			while (resultSetForCouponIds.next()) {
				couponIds.add(resultSetForCouponIds.getString("Coupon_ID"));
				}
			String couponQuery = ("SELECT * FROM Coupon WHERE COUPON_ID IN(" + String.join(",", couponIds) + ")");
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {
				CouponDO tempCouponDO = new CouponDO(Long.parseLong(resultSet.getString("Coupon_ID")),
						Long.parseLong(resultSet.getString("Company_ID")), resultSet.getString("Title"),
						convertStringToDate(resultSet.getString("Start_Date")),
						convertStringToDate(resultSet.getString("End_Date")),
						Integer.parseInt(resultSet.getString("Amount")), Type.valueOf(resultSet.getString("Type")),
						resultSet.getString("Message"), Double.parseDouble(resultSet.getString("Price")),
						resultSet.getString("Image"));
				coupons.add(tempCouponDO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Wasn't able to find any coupon list ...\n" + e.getMessage());
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
	public Collection<CouponDO> getAllPurchaseCouponsByType(long customerId, Type type) {
		Statement statement = null;
		List<String> couponIds = new ArrayList<>();
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();
			String couponIdsQuery = ("SELECT distinct coupon_id FROM customer_coupon where customer_Id = " + customerId + ";");
			ResultSet resultSetForCouponIds = statement.executeQuery(couponIdsQuery);
			while (resultSetForCouponIds.next()) {
				couponIds.add(resultSetForCouponIds.getString("Coupon_ID"));
				}
			String couponQuery = ("SELECT * FROM Coupon WHERE Type = " + '"' + type + '"' + "and COUPON_ID IN(" + String.join(",", couponIds) + ")");
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {
				CouponDO tempCouponDO = new CouponDO(Long.parseLong(resultSet.getString("Coupon_ID")),
						Long.parseLong(resultSet.getString("Company_ID")), resultSet.getString("Title"),
						convertStringToDate(resultSet.getString("Start_Date")),
						convertStringToDate(resultSet.getString("End_Date")),
						Integer.parseInt(resultSet.getString("Amount")), Type.valueOf(resultSet.getString("Type")),
						resultSet.getString("Message"), Double.parseDouble(resultSet.getString("Price")),
						resultSet.getString("Image"));
				coupons.add(tempCouponDO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Wasn't able to find any coupon list ...\n" + e.getMessage());
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
	public Collection<CouponDO> getAllPurchaseCouponsByPrice(long customerId, double price) {
		Statement statement = null;
		List<String> couponIds = new ArrayList<>();
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();
			String couponIdsQuery = ("SELECT distinct coupon_id FROM customer_coupon where customer_Id = " + customerId + ";");
			ResultSet resultSetForCouponIds = statement.executeQuery(couponIdsQuery);
			while (resultSetForCouponIds.next()) {
				couponIds.add(resultSetForCouponIds.getString("Coupon_ID"));
				}
			String couponQuery = ("SELECT * FROM Coupon WHERE Price = " + price + "and COUPON_ID IN(" + String.join(",", couponIds) + ")");
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {
				CouponDO tempCouponDO = new CouponDO(Long.parseLong(resultSet.getString("Coupon_ID")),
						Long.parseLong(resultSet.getString("Company_ID")), resultSet.getString("Title"),
						convertStringToDate(resultSet.getString("Start_Date")),
						convertStringToDate(resultSet.getString("End_Date")),
						Integer.parseInt(resultSet.getString("Amount")), Type.valueOf(resultSet.getString("Type")),
						resultSet.getString("Message"), Double.parseDouble(resultSet.getString("Price")),
						resultSet.getString("Image"));
				coupons.add(tempCouponDO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Wasn't able to find any coupon list ...\n" + e.getMessage());
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

	private Date convertStringToDate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("cannot parse date: " + date + " from coupon table: " + "\n" + e.getMessage()); 
		}		
	}

	
	
}

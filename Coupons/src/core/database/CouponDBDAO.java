package core.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import core.database.model.CouponDO;
import core.database.model.Type;

public class CouponDBDAO implements CouponDAO {

	private Connection connection;

	public CouponDBDAO() {
		this.connection = ConnectionManagerPool.getInstance().returnConnection();
	}

	@Override
	public int createCoupon(CouponDO couponDO) {
		try {
			Statement statement = connection.createStatement();
			String insertQuery = buildCouponInsertQuery(couponDO);
			int numberOfInsertRecords = statement.executeUpdate(insertQuery);
			statement.close();
			return numberOfInsertRecords;
		} catch (SQLException e) {
			throw new RuntimeException(
					"Wasn't able to create coupon: " + "\n" + couponDO.toString() + "\n" + e.getMessage());
		}
	}

	@Override
	public int removeCoupon(CouponDO couponDO) {
		try {
			Statement statement = connection.createStatement();
			int numberOfRemoveRecords = statement.executeUpdate("DELETE FROM Coupon WHERE coupon_id = " + couponDO.getCouponId());
			statement.close();
			return numberOfRemoveRecords;
			} catch (SQLException e) {
			throw new RuntimeException(
					"wasn't able to remove coupon ..." + couponDO.toString() + "\n" + e.getMessage());
		}
	}

	@Override
	public int updateCoupon(CouponDO couponDO) {
		try {
			Statement statement = connection.createStatement();
			String updateQuery = updateCouponInsertQuery(couponDO.getCouponId(), couponDO.getTitle(),
					couponDO.getStartDate(), couponDO.getEndDate(), couponDO.getAmount(), couponDO.getType(), 
					couponDO.getMessege(), couponDO.getPrice(), couponDO.getImage());
			int numberOfUpdateRecords = statement.executeUpdate(updateQuery);
			statement.close();
			return numberOfUpdateRecords;
		} catch (SQLException e) {
			String errorMessage = "Wasn't able to update coupon ..." + couponDO.toString() + "\n" + e.getMessage();
			System.err.println(errorMessage);
			throw new RuntimeException(errorMessage);
		}
	}

	@Override
	public CouponDO getCoupon(long companyId, long couponId) {
		Statement statement = null;
		CouponDO couponDO = null;
		try {
			statement = connection.createStatement();
			String couponQuery = "SELECT * FROM coupon where company_Id = " + companyId + " and coupon_Id = " + couponId;
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {
				couponDO = new CouponDO(Long.parseLong(resultSet.getString("Company_ID")), Long.parseLong(resultSet.getString("Coupon_ID")),
						resultSet.getString("Title"), resultSet.getString("Start_Date"), resultSet.getString("End_Date"),
						Integer.parseInt(resultSet.getString("Amount")), Type.valueOf(resultSet.getString("Type")),
						resultSet.getString("Messege"), Double.parseDouble(resultSet.getString("Price")),
						resultSet.getString("Image"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Wasn't able to find couponID ..." + couponId + "\n" + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return couponDO;
	}
	
	
	@Override
	public Collection<CouponDO> getAllCoupons() {
		Statement statement = null;
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();

			String couponQuery = "SELECT Coupon_Id, Title, Start_Date, End_Date, Amount, Type, Messege, Price, Image FROM Coupon";
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {
				CouponDO tempCouponDO = new CouponDO(Long.parseLong(resultSet.getString("Company_ID")), Long.parseLong(resultSet.getString("Coupon_ID")),
						resultSet.getString("Title"), resultSet.getString("Start_Date"), resultSet.getString("End_Date"),
						Integer.parseInt(resultSet.getString("Amount")), Type.valueOf(resultSet.getString("Type")),
						resultSet.getString("Messege"), Double.parseDouble(resultSet.getString("Price")),
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
	public Collection<CouponDO> getAllCouponsByCompany(long companyId) {
		Statement statement = null;
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();

			String couponQuery = "SELECT Coupon_Id, Title, Start_Date, End_Date, Amount, Type, Messege, Price, Image FROM Coupon where Company_Id = " + companyId;
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {
				CouponDO tempCouponDO = new CouponDO(Long.parseLong(resultSet.getString("Company_ID")), Long.parseLong(resultSet.getString("Coupon_ID")),
						resultSet.getString("Title"), resultSet.getString("Start_Date"), resultSet.getString("End_Date"),
						Integer.parseInt(resultSet.getString("Amount")), Type.valueOf(resultSet.getString("Type")),
						resultSet.getString("Messege"), Double.parseDouble(resultSet.getString("Price")),
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
	public Collection<CouponDO> getAllCouponsByCustomer(long customerId) {
		Statement statement = null;
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();

			String couponQuery = "SELECT comapny_id, coupon_ID, title, start_Date, end_Date, amount, type, messege, image FROM coupon where Customer_Id = " + customerId;
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {

				CouponDO tempCoupon = new CouponDO(Long.parseLong(resultSet.getString("Company_ID")), Long.parseLong(resultSet.getString("Coupon_ID")),
						resultSet.getString("Title"), resultSet.getString("Start_Date"), resultSet.getString("End_Date"),
						Integer.parseInt(resultSet.getString("Amount")), Type.valueOf(resultSet.getString("Type")),
						resultSet.getString("Messege"), Double.parseDouble(resultSet.getString("Price")),
						resultSet.getString("Image"));
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
	public Collection<CouponDO> getAllCouponsByType(long companyId, Type type) {
		Statement statement = null;
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();

			String couponQuery = "SELECT Coupon_Id, Title, Start_Date, End_Date, Amount, Type, Messege, Price, Image FROM Coupon where company_Id = " + companyId + " and Type = " + type;
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {
				CouponDO tempCouponDO = new CouponDO(Long.parseLong(resultSet.getString("Company_ID")), Long.parseLong(resultSet.getString("Coupon_ID")),
						resultSet.getString("Title"), resultSet.getString("Start_Date"), resultSet.getString("End_Date"),
						Integer.parseInt(resultSet.getString("Amount")), Type.valueOf(resultSet.getString("Type")),
						resultSet.getString("Messege"), Double.parseDouble(resultSet.getString("Price")),
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
	
	
	private String buildCouponInsertQuery(CouponDO couponDO) {
		StringBuilder sb = new StringBuilder("INSERT INTO Coupon VALUES(");
		sb.append(String.valueOf(couponDO.getCompanyId()));
		sb.append(",");
		sb.append(surroundWithCommas(String.valueOf(couponDO.getCouponId())));
		sb.append(",");
		sb.append(surroundWithCommas(couponDO.getTitle()));
		sb.append(",");
		sb.append(surroundWithCommas(couponDO.getStartDate()));
		sb.append(",");
		sb.append(surroundWithCommas(couponDO.getEndDate()));
		sb.append(",");
		sb.append(surroundWithCommas(String.valueOf(couponDO.getAmount())));
		sb.append(",");
		sb.append(surroundWithCommas(String.valueOf(couponDO.getType())));
		sb.append(",");
		sb.append(surroundWithCommas(couponDO.getMessege()));
		sb.append(",");
		sb.append(surroundWithCommas(String.valueOf(couponDO.getPrice())));
		sb.append(",");
		sb.append(surroundWithCommas(couponDO.getImage()));
		sb.append(")");
		return sb.toString();
	}

	private String surroundWithCommas(String string) {
		return "'" + string + "'";
	}

	private String updateCouponInsertQuery(long couponId, String title, String startDate, String endDate,
			int amount, Type type, String messege, double price, String image) {
		StringBuilder sb = new StringBuilder("Update Coupon SET Title = ");
		sb.append(surroundWithCommas(title));
		sb.append(", StartDate = ");
		sb.append(surroundWithCommas(startDate));
		sb.append(", EndDate = ");
		sb.append(surroundWithCommas(endDate));
		sb.append(", Amount = ");
		sb.append(surroundWithCommas(String.valueOf(amount)));
		sb.append(", Type = ");
		sb.append(surroundWithCommas(String.valueOf(type)));
		sb.append(", Messege = ");
		sb.append(surroundWithCommas(messege));
		sb.append(", Price = ");
		sb.append(surroundWithCommas(String.valueOf(price)));
		sb.append(", Image = ");
		sb.append(surroundWithCommas(image));
		sb.append("WHERE Coupo_Id = " + couponId);
		return sb.toString();
	}



}

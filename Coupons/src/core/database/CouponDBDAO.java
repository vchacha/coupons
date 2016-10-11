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
import java.util.stream.Collectors;

import core.database.model.CouponDO;
import core.database.model.PurchasedCouponDO;
import core.database.model.Type;

public class CouponDBDAO implements CouponDAO {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
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
	public void removeCoupon(CouponDO couponDO) {
		try {
			Statement statement = connection.createStatement();
			String query = deletePurchasedCouponsByCouponId(fetchPurchasedCouponsByCouponId(couponDO.getCouponId()));
			int deletedPurchasedCoupons = statement.executeUpdate(query);
			int deletedCoupon = statement.executeUpdate("DELETE FROM Coupon WHERE coupon_id = " + couponDO.getCouponId());
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(
					"wasn't able to remove coupon ..." + couponDO.toString() + "\n" + e.getMessage());
		}
	}

	private String deletePurchasedCouponsByCouponId(List<PurchasedCouponDO> fetchPurchasedCouponsByCouponId) {
		StringBuilder sb = new StringBuilder("DELETE FROM Customer_Coupon WHERE ");
		sb.append(String.join(" OR ",
				fetchPurchasedCouponsByCouponId.stream()
						.map(i -> "(coupon_id = " + i.getCouponId() + " and CUSTOMER_ID = " + i.getCustomerId() + ")")
						.collect(Collectors.toList())));
		return sb.toString();
	}

	private List<PurchasedCouponDO> fetchPurchasedCouponsByCouponId(long couponId) {
		List<PurchasedCouponDO> purchasedCouponDOs = new ArrayList<>();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * from Customer_Coupon WHERE coupon_id = " + couponId);
			while (resultSet.next()) {
				purchasedCouponDOs.add(new PurchasedCouponDO(Long.valueOf(resultSet.getString("CUSTOMER_ID")),
						Long.valueOf(resultSet.getString("COUPON_ID"))));
			}
		} catch (SQLException e) {
			throw new RuntimeException("wasn't able to fetch purchased coupons records..." + e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new RuntimeException("get error in attempt to close statement..." + e.getMessage());
			}
		}
		return purchasedCouponDOs;
	}

	@Override
	public int updateCoupon(CouponDO couponDO) {
		try {
			Statement statement = connection.createStatement();
			String updateQuery = updateCouponInsertQuery(couponDO.getCouponId(), couponDO.getCompanyId(),
					couponDO.getTitle(), couponDO.getStartDate(), couponDO.getEndDate(), couponDO.getAmount(),
					couponDO.getType(), couponDO.getMessage(), couponDO.getPrice(), couponDO.getImage());
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
			String couponQuery = "SELECT * FROM coupon where company_Id = " + companyId + " and coupon_Id = "
					+ couponId;
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {
				couponDO = new CouponDO(Long.parseLong(resultSet.getString("Coupon_ID")),
						Long.parseLong(resultSet.getString("Company_ID")), resultSet.getString("Title"),
						convertStringToDate(resultSet.getString("Start_Date")),
						convertStringToDate(resultSet.getString("End_Date")),
						Integer.parseInt(resultSet.getString("Amount")), Type.valueOf(resultSet.getString("Type")),
						resultSet.getString("Message"), Double.parseDouble(resultSet.getString("Price")),
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

			String couponQuery = "SELECT * FROM Coupon";
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
	public Collection<CouponDO> getAllCouponsByType(Type type) {
		Statement statement = null;
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();

			String couponQuery = "SELECT * FROM Coupon where Type = " + "'" + type + "'";
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
			throw new RuntimeException("Wasn't able to find any coupon with type: " + type + "\n" + e.getMessage());
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
	public Collection<CouponDO> getAllPurchaseCoupons() {
		Statement statement = null;
		List<String> couponIds = new ArrayList<>();
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();
			String couponIdsQuery = ("SELECT distinct coupon_id FROM customer_coupon;");
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

	private String buildCouponInsertQuery(CouponDO couponDO) {
		StringBuilder sb = new StringBuilder("INSERT INTO Coupon VALUES(");
		sb.append(String.valueOf(couponDO.getCouponId()));
		sb.append(",");
		sb.append(surroundWithCommas(String.valueOf(couponDO.getCompanyId())));
		sb.append(",");
		sb.append(surroundWithCommas(couponDO.getTitle()));
		sb.append(",");
		sb.append(surroundWithCommas(convertDateToSimpleString(couponDO.getStartDate())));
		sb.append(",");
		sb.append(surroundWithCommas(convertDateToSimpleString(couponDO.getEndDate())));
		sb.append(",");
		sb.append(surroundWithCommas(String.valueOf(couponDO.getAmount())));
		sb.append(",");
		sb.append(surroundWithCommas(String.valueOf(couponDO.getType())));
		sb.append(",");
		sb.append(surroundWithCommas(couponDO.getMessage()));
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

	private String updateCouponInsertQuery(long couponId, long companyId, String title, Date startDate, Date endDate,
			int amount, Type type, String message, double price, String image) {
		StringBuilder sb = new StringBuilder("Update Coupon SET Company_Id = ");
		sb.append(surroundWithCommas(String.valueOf(companyId)));
		sb.append(", Title = ");
		sb.append(surroundWithCommas(title));
		sb.append(", Start_Date = ");
		sb.append(surroundWithCommas(convertDateToSimpleString(startDate)));
		sb.append(", End_Date = ");
		sb.append(surroundWithCommas(convertDateToSimpleString(endDate)));
		sb.append(", Amount = ");
		sb.append(surroundWithCommas(String.valueOf(amount)));
		sb.append(", Type = ");
		sb.append(surroundWithCommas(String.valueOf(type)));
		sb.append(", Message = ");
		sb.append(surroundWithCommas(message));
		sb.append(", Price = ");
		sb.append(surroundWithCommas(String.valueOf(price)));
		sb.append(", Image = ");
		sb.append(surroundWithCommas(image));
		sb.append("WHERE Coupon_Id = " + couponId);
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

	private String convertDateToSimpleString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		return simpleDateFormat.format(date);
	}
}

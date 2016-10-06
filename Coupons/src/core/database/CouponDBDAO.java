package core.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import core.database.model.CompanyDO;
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
			String couponQuery = "SELECT * FROM coupon where company_Id = " + companyId + " and coupon_id = " + couponId;
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {
				couponDO = new CouponDO(Long.parseLong(resultSet.getString("company_ID")), Long.parseLong(resultSet.getString("coupon_ID")),
						resultSet.getString("title"), resultSet.getString("startDate"), resultSet.getString("endDate"),
						Integer.parseInt(resultSet.getString("amount")), Type.valueOf(resultSet.getString("type")),
						resultSet.getString("messege"), Double.parseDouble(resultSet.getString("price")),
						resultSet.getString("image"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Wasn't able to find companyID ..." + companyId + "\n" + e.getMessage());
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
	public Collection<CouponDO> getAllCouponsByCompany(long companyId) {
		Statement statement = null;
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();

			String companyQuery = "SELECT coupon_Id, company_Name, password, email FROM company";
			ResultSet resultSet = statement.executeQuery(companyQuery);
			while (resultSet.next()) {
				CompanyDO tempCompanyDO = new CompanyDO(Integer.parseInt(resultSet.getString("COMPANY_ID")),
						resultSet.getString("COMPANY_NAME"), resultSet.getString("PASSWORD"),
						resultSet.getString("EMAIL"));
				companies.add(tempCompanyDO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Wasn't able to find any company list ...\n" + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return companies;
	}

	@Override
	public Collection<CouponDO> getCoupons(long companyId) {
		Statement statement = null;
		Collection<CouponDO> coupons = new ArrayList<>();
		try {
			statement = connection.createStatement();

			String couponQuery = "SELECT comapny_id, coupon_ID, title, startDate, endDate, amount, type, messege, image  FROM coupon";
			ResultSet resultSet = statement.executeQuery(couponQuery);
			while (resultSet.next()) {

				CouponDO tempCoupon = new CouponDO(Long.parseLong(resultSet.getString("company_ID")), Long.parseLong(resultSet.getString("coupon_ID")),
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
	public boolean login(long companyId, String password) {
		Statement statement = null;
		String tempCompanyName = null;
		String tempPassword = null;
		try {
			statement = connection.createStatement();

			String loginQuery = "SELECT Company_Id, password from company where Company_Id = '" + companyId
					+ "' and password = '" + password + "'";
			ResultSet resultSet = statement.executeQuery(loginQuery);
			while (resultSet.next()) {

				tempCompanyName = resultSet.getString("Company_Id");
				tempPassword = resultSet.getString("Password");
			}
		} catch (SQLException e) {
			throw new RuntimeException(
					"Company id or/and password are not valid! Please try again... \n" + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (tempCompanyName != null & tempPassword != null) {
			return true;
		} else {
			return false;
		}
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

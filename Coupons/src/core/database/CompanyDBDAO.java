package core.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.JOptionPane;

import core.database.model.CompanyDO;
import core.database.model.Coupon;
import core.database.model.Type;

public class CompanyDBDAO implements CompanyDAO {

	private Connection connection;

	public CompanyDBDAO() {
		this.connection = ConnectionManagerPool.getInstance().returnConnection();
	}

	@Override
	public int createCompany(CompanyDO companyDO) {
		try {
			Statement statement = connection.createStatement();
			String insertQuery = buildCompanyInsertQuery(companyDO);
			int numberOfInsertRecords = statement.executeUpdate(insertQuery);
			statement.close();
			return numberOfInsertRecords;
		} catch (SQLException e) {
			throw new RuntimeException(
					"Wasn't able to inset company: " + "\n" + companyDO.toString() + "\n" + e.getMessage());
		}
	}

	@Override
	public int removeCompany(CompanyDO companyDO) {
		try {
			Statement statement = connection.createStatement();
			int numberOfRemoveRecords = statement.executeUpdate("DELETE FROM Company WHERE company_id = " + companyDO.getCompanyId());
			statement.close();
			return numberOfRemoveRecords;
			} catch (SQLException e) {
			throw new RuntimeException(
					"wasn't able to remove company ..." + companyDO.toString() + "\n" + e.getMessage());
		}
	}

	@Override
	public int updateCompany(CompanyDO companyDO) {
		try {
			Statement statement = connection.createStatement();
			String updateQuery = updateCompanyInsertQuery(companyDO.getCompanyId(), companyDO.getCompanyName(),
					companyDO.getPassword(), companyDO.getEmail());
			int numberOfUpdateRecords = statement.executeUpdate(updateQuery);
			statement.close();
			return numberOfUpdateRecords;
		} catch (SQLException e) {
			String errorMessage = "Wasn't able to update company ..." + companyDO.toString() + "\n" + e.getMessage();
			System.err.println(errorMessage);
			throw new RuntimeException(errorMessage);
		}
	}

	@Override
	public CompanyDO getCompany(long companyId) {
		Statement statement = null;
		CompanyDO companyDO = null;
		try {
			statement = connection.createStatement();
			String companyQuery = "SELECT * FROM company where company_id = " + companyId;
			ResultSet resultSet = statement.executeQuery(companyQuery);
			while (resultSet.next()) {
				companyDO = new CompanyDO(Integer.parseInt(resultSet.getString("COMPANY_ID")),
						resultSet.getString("COMPANY_NAME"), resultSet.getString("PASSWORD"),
						resultSet.getString("EMAIL"));
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
		return companyDO;
	}

	@Override
	public Collection<CompanyDO> getAllCompanies() {
		Statement statement = null;
		Collection<CompanyDO> companies = new ArrayList<>();
		try {
			statement = connection.createStatement();

			String companyQuery = "SELECT company_Id, company_Name, password, email FROM company";
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
	public Collection<Coupon> getCoupons(long companyId) {
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

	private String buildCompanyInsertQuery(CompanyDO companyDO) {
		StringBuilder sb = new StringBuilder("INSERT INTO Company VALUES(");
		sb.append(String.valueOf(companyDO.getCompanyId()));
		sb.append(",");
		sb.append(surroundWithCommas(companyDO.getCompanyName()));
		sb.append(",");
		sb.append(surroundWithCommas(companyDO.getPassword()));
		sb.append(",");
		sb.append(surroundWithCommas(companyDO.getEmail()));
		sb.append(")");
		return sb.toString();
	}

	private String surroundWithCommas(String string) {
		return "'" + string + "'";
	}

	private String updateCompanyInsertQuery(long companyId, String CompanyName, String CompanyPassword, String CompanyEmail) {
		StringBuilder sb = new StringBuilder("Update Company SET Company_Name = ");
		sb.append(surroundWithCommas(CompanyName));
		sb.append(", Password = ");
		sb.append(surroundWithCommas(CompanyPassword));
		sb.append(", Email = ");
		sb.append(surroundWithCommas(CompanyEmail));
		sb.append("WHERE Company_Id = " + companyId);
		return sb.toString();
	}

}

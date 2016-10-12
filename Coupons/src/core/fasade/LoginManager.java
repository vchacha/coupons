package core.fasade;

import core.database.CompanyDAO;
import core.database.CompanyDBDAO;
import core.database.CouponDBDAO;
import core.database.CustomerDAO;
import core.database.CustomerDBDAO;
import logic.exceptions.LoginException;

public class LoginManager {

	public CouponClientFacade login(String id, String password, ClientType clientType) {
		if (isLoginSucceed(id, password, clientType)) {
			if (clientType.equals(ClientType.ADMIN)) {
				return new AdminFacade(new CompanyDBDAO(), new CustomerDBDAO(), new CouponDBDAO());
			}

			if (clientType.equals(ClientType.COMPANY)) {
				return new CompanyFacade(new CouponDBDAO(), new CompanyDBDAO());
			}

			if (clientType.equals(ClientType.CUSTOMER)) {
				return new CustomerFacade(new CustomerDBDAO());
			}
		}
		throw new LoginException(
				"id or/and password are not valid! Please try again");
	}

	private boolean isLoginSucceed(String id, String password, ClientType clientType) {
		boolean result = false;
		switch (clientType) {
		case ADMIN:
			if (id.equals("Admin") && password.equals("1234")) {
				result = true;
			}
			break;

		case COMPANY:
			CompanyDAO companyDAO = new CompanyDBDAO();
			result = companyDAO.login(Long.parseLong(id), password);
			break;

		case CUSTOMER:
			CustomerDAO customerDAO = new CustomerDBDAO();
			return customerDAO.login(Long.parseLong(id), password);

		default:
			result = false;
		}
		return result;
	}
}

package runner;

import javax.swing.JOptionPane;

import core.database.ConnectionManagerPool;
import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.Type;
import core.fasade.ClientType;
import core.fasade.CouponClientFacade;
import core.fasade.LoginManager;
import logic.exceptions.CompanyValidationException;
import logic.exceptions.LoginException;


public class MainTestFasade {

	public static void main(String[] args) {
		try {

			LoginManager loginManager = new LoginManager();
			CouponClientFacade couponClientFacade = loginManager.login("Admin", "1234", ClientType.ADMIN);
			
			//whenCompanyIdIsLessThen1000ExpectCompanyValidationException
			try {
				couponClientFacade.createCompany(new CompanyDO(12, "reebok", "1234", "reebok@gmail.com"));				
			} catch (CompanyValidationException e) {
				System.err.println(e.getMessage());
			}
			
			//Creation a new Coupon (without exception)
			//couponClientFacade.createCoupon(new CouponDO(61234, 1234, "HEALTH", "10-Oct-16", "14-Oct-16", 100, Type.HEALTH, "messege", 50, "image"));

		} catch (LoginException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			ConnectionManagerPool.getInstance().closeAllConnection();
		}

	}

}

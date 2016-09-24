package runner;

import javax.swing.JOptionPane;

import core.database.ConnectionManagerPool;
import core.database.model.CompanyDO;
import core.fasade.ClientType;
import core.fasade.CouponClientFacade;
import core.fasade.LoginManager;
import logic.exceptions.LoginException;
import logic.validation.CompanyValidator;
import logic.validation.DataValidator;
import logic.validation.ValidationResponse;

public class MainTestFasade {

	public static void main(String[] args) {
		try {

			LoginManager loginManager = new LoginManager();
			CouponClientFacade couponClientFacade = loginManager.login("Admin", "123", ClientType.ADMIN);

			CompanyDO companyDO = new CompanyDO(5L, "arsenal", "6543", "arsenal@gmail.com");
			DataValidator<CompanyDO> companyValidator = new CompanyValidator();
			ValidationResponse companyValidatorResponse = companyValidator.validateData(companyDO);
			if (companyValidatorResponse.isOk()) {
				couponClientFacade.createCompany(companyDO);
				JOptionPane.showMessageDialog(null,"successfully created company: \n" + companyDO.toString());
			} else {
				JOptionPane.showMessageDialog(null,companyValidatorResponse.getErrorMessage());
			}

		} catch (LoginException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			ConnectionManagerPool.getInstance().closeAllConnection();
		}

	}

}

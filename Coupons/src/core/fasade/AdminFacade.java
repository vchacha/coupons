package core.fasade;

import java.util.Collection;

import core.database.CompanyDAO;
import core.database.CouponDAO;
import core.database.CustomerDAO;
import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;
import logic.exceptions.CompanyValidationException;
import logic.exceptions.CustomerValidationException;
import logic.validation.CompanyValidator;
import logic.validation.CustomerValidator;
import logic.validation.DataValidator;
import logic.validation.ValidationResponse;

public class AdminFacade implements CouponClientFacade {

	private CompanyDAO companyDAO;
	private CustomerDAO customerDAO;
	private CouponDAO couponDAO;

	public AdminFacade(CompanyDAO companyDAO, CustomerDAO customerDAO, CouponDAO couponDAO) {
		this.companyDAO = companyDAO;
		this.customerDAO = customerDAO;
		this.couponDAO = couponDAO;
	}

	@Override
	public void createCompany(CompanyDO companyDO) {
		DataValidator<CompanyDO> companyValidator = new CompanyValidator();
		ValidationResponse companyValidatorResponse = companyValidator.validateData(companyDO);
		if (companyValidatorResponse.isOk()) {
			companyDAO.createCompany(companyDO);
			return;
		}
		throw new CompanyValidationException(companyValidatorResponse.getErrorMessage());
	}

	@Override
	public void removeCompany(CompanyDO companyDO) {
		companyDAO.removeCompany(companyDO);
	}

	@Override
	public void updateCompany(CompanyDO companyDO) {
		DataValidator<CompanyDO> companyValidator = new CompanyValidator();
		ValidationResponse companyValidatorResponse = companyValidator.validateData(companyDO);
		if (companyValidatorResponse.isOk()) {
			companyDAO.updateCompany(companyDO);
			return;
		}
		throw new CompanyValidationException(companyValidatorResponse.getErrorMessage());
	}

	@Override
	public CompanyDO getCompany(long companyId) {
		return companyDAO.getCompany(companyId);
	}

	@Override
	public Collection<CompanyDO> getAllCompanies() {
		return companyDAO.getAllCompanies();
	}

	@Override
	public void createCustomer(CustomerDO customerDO) {
		DataValidator<CustomerDO> customerValidator = new CustomerValidator();
		ValidationResponse customerValidatorResponse = customerValidator.validateData(customerDO);
		if (customerValidatorResponse.isOk()) {
			customerDAO.createCustomer(customerDO);
			return;
		}
		throw new CustomerValidationException(customerValidatorResponse.getErrorMessage());
	}

	@Override
	public void removeCustomer(CustomerDO customerDO) {
		customerDAO.removeCustomer(customerDO);
	}

	@Override
	public void updateCustomer(CustomerDO customerDO) {
		DataValidator<CustomerDO> customerValidator = new CustomerValidator();
		ValidationResponse customerValidatorResponse = customerValidator.validateData(customerDO);
		if (customerValidatorResponse.isOk()) {
		customerDAO.updateCustomer(customerDO);
		return;
		}
		throw new CustomerValidationException(customerValidatorResponse.getErrorMessage());
	}

	@Override
	public CustomerDO getCustomer(long customerId) {
		return customerDAO.getCustomer(customerId);
	}

	@Override
	public Collection<CustomerDO> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}

	@Override
	public void createCoupon(CouponDO coupon) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeCoupon(CouponDO coupon) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCoupon(CouponDO coupon) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CouponDO getCoupon(long companyId, long couponId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllCoupons() {
		return couponDAO.getAllCoupons();
	}

	@Override
	public Collection<CouponDO> getAllCouponsByCompanyAndType(long companyId, Type type) {
		return companyDAO.getAllCouponsByCompanyAndType(companyId, type);
	}

	@Override
	public void purchaseCoupon(CouponDO coupon, long customerId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByCustomer(long customerId) {
		return customerDAO.getAllPurchaseCouponsByCustomer(customerId);
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByType(long customerId, Type type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByPrice(long customerId, int price) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllCouponsByCompany(long companyId) {
		return companyDAO.getAllCouponsByCompany(companyId);
	}

	@Override
	public Collection<CouponDO> getAllCouponsByType(Type type) {
		return couponDAO.getAllCouponsByType(type);
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCoupons() {
		return couponDAO.getAllPurchaseCoupons();
	}

}

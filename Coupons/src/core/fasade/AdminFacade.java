package core.fasade;

import java.util.Collection;

import core.database.CompanyDAO;
import core.database.CustomerDAO;
import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;

public class AdminFacade implements CouponClientFacade {

	private CompanyDAO companyDAO;
	private CustomerDAO customerDAO;

	public AdminFacade(CompanyDAO companyDao) {
		this.companyDAO = companyDao;
	}

	@Override
	public int createCompany(CompanyDO companyDO) {
		return companyDAO.createCompany(companyDO);
	}

	@Override
	public int removeCompany(CompanyDO companyDO) {
		return companyDAO.removeCompany(companyDO);
	}

	@Override
	public int updateCompany(CompanyDO companyDO) {
		return companyDAO.updateCompany(companyDO);
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
	public int createCustomer(CustomerDO customerDO) {
		return customerDAO.createCustomer(customerDO);
	}

	@Override
	public int removeCustomer(CustomerDO customerDO) {
		return customerDAO.removeCustomer(customerDO);
	}

	@Override
	public int updateCustomer(CustomerDO customerDO) {
		return customerDAO.updateCustomer(customerDO);
	}

	@Override
	public CustomerDO getCustomer(long customerId) {
		return customerDAO.getCustomer(customerId);
	}

	@Override
	public Collection<CustomerDO> getAllCustomer() {
		return customerDAO.getAllCustomers();
	}

	@Override
	public int createCoupon(CouponDO coupon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCoupon(CouponDO coupon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCoupon(CouponDO coupon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CouponDO getCoupon(long couponId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CouponDO> getAllCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CouponDO> getCouponsByType(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int purchase(CouponDO coupon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByType(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByPrice(int price) {
		// TODO Auto-generated method stub
		return null;
	}

}

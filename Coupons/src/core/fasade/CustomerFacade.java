package core.fasade;

import java.util.Collection;

import core.database.CustomerDAO;
import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;

public class CustomerFacade implements CouponClientFacade {
	
	private CustomerDAO customerDAO;

	@Override
	public int createCompany(CompanyDO companyDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int removeCompany(CompanyDO companyDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int updateCompany(CompanyDO companyDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CompanyDO getCompany(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int createCustomer(CustomerDO customerDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int removeCustomer(CustomerDO customerDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int updateCustomer(CustomerDO customerDO) {
		return customerDAO.createCustomer(customerDO);
	}

	@Override
	public CustomerDO getCustomer(long customerId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CustomerDO> getAllCustomer() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int createCoupon(CouponDO couponDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int removeCoupon(CouponDO couponDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int updateCoupon(CouponDO couponDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CouponDO getCoupon(long companyId, long couponId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllCoupons() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getCouponsByType(long companyId, Type type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int purchaseCoupon(CouponDO couponDO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCoupons(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByType(long customerId, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByPrice(long customerId, int price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CompanyDO> getAllCompanies() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllCouponsByCompany(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllCouponsByCustomer(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}

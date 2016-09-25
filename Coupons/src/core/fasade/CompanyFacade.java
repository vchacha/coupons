package core.fasade;

import java.util.Collection;

import core.database.model.CompanyDO;
import core.database.model.Coupon;
import core.database.model.CustomerDO;
import core.database.model.Type;

public class CompanyFacade implements CouponClientFacade {

	@Override
	public int createCompany(CompanyDO company) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int removeCompany(CompanyDO company) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int updateCompany(CompanyDO company) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CompanyDO getCompany(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CompanyDO> getAllCompanies() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int createCustomer(CustomerDO customer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int removeCustomer(CustomerDO customer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int updateCustomer(CustomerDO customer) {
		throw new UnsupportedOperationException();
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
	public int createCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Coupon getCoupon(long couponId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Coupon> getAllCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Coupon> getCouponsByType(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int purchase(Coupon coupon) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Coupon> getAllPurchaseCoupons() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Coupon> getAllPurchaseCouponsByType(Type type) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Coupon> getAllPurchaseCouponsByPrice(int price) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	
}

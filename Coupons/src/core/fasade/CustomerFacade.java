package core.fasade;

import java.util.Collection;

import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;

public class CustomerFacade implements CouponClientFacade {

	@Override
	public int createCompany(CompanyDO company) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCompany(CompanyDO company) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCompany(CompanyDO company) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CompanyDO getCompany(long companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createCustomer(CustomerDO customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCustomer(CustomerDO customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCustomer(CustomerDO customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CustomerDO getCustomer(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CustomerDO> getAllCustomer() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public Collection<CompanyDO> getAllCompanies() {
		// TODO Auto-generated method stub
		return null;
	}

}

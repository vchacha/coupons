package core.fasade;

import java.util.Collection;

import core.database.CouponDAO;
import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;

public class CompanyFacade implements CouponClientFacade {
	
	private CouponDAO couponDAO;

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
	public int createCoupon(CouponDO couponDO) {
		return couponDAO.createCoupon(couponDO);
	}

	@Override
	public int removeCoupon(CouponDO couponDO) {
		return couponDAO.removeCoupon(couponDO);
	}

	@Override
	public int updateCoupon(CouponDO couponDO) {
		return couponDAO.updateCoupon(couponDO);
	}

	@Override
	public CouponDO getCoupon(long companyId, long couponId) {
		return couponDAO.getCoupon(companyId, couponId);
	}

	@Override
	public Collection<CouponDO> getAllCoupons() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllCouponsByCompany(long companyId){
		return couponDAO.getAllCouponsByCompany(companyId);
	}
	
	@Override
	public Collection<CouponDO> getAllCouponsByCustomer(long customerId){
		throw new UnsupportedOperationException();
				
	}
	
	@Override
	public Collection<CouponDO> getCouponsByType(long companyId, Type type) {
		return couponDAO.getAllCouponsByType(companyId, type);
	}

	@Override
	public int purchaseCoupon(CouponDO coupon) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCoupons(long customerId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByType(long customerId, Type type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByPrice(long customerId, int price) {
		throw new UnsupportedOperationException();
	}

	
}

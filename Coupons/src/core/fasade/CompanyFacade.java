package core.fasade;

import java.util.Collection;

import core.database.CompanyDAO;
import core.database.CouponDAO;
import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;

public class CompanyFacade implements CouponClientFacade {
	
	private CouponDAO couponDAO;
	private CompanyDAO companyDAO;

	@Override
	public void createCompany(CompanyDO company) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeCompany(CompanyDO company) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCompany(CompanyDO company) {
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
	public void createCustomer(CustomerDO customer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeCustomer(CustomerDO customer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCustomer(CustomerDO customer) {
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
	public void createCoupon(CouponDO couponDO) {
		couponDAO.createCoupon(couponDO);
	}

	@Override
	public void removeCoupon(CouponDO couponDO) {
		couponDAO.removeCoupon(couponDO);
	}

	@Override
	public void updateCoupon(CouponDO couponDO) {
		couponDAO.updateCoupon(couponDO);
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
		return companyDAO.getAllCouponsByCompany(companyId);
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

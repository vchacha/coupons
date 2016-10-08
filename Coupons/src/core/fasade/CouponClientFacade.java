package core.fasade;

import java.util.Collection;

import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;

public interface CouponClientFacade {
	
	int createCompany(CompanyDO company);
	
	int removeCompany(CompanyDO company);
	
	int updateCompany(CompanyDO company);
	
	CompanyDO getCompany( long companyId);
	
	Collection <CompanyDO> getAllCompanies();

	int createCustomer(CustomerDO customer);
	
	int removeCustomer(CustomerDO customer);
	
	int updateCustomer(CustomerDO customer);
	
	CustomerDO getCustomer(long customerId);
	
	Collection <CustomerDO> getAllCustomer();
	
	int createCoupon(CouponDO coupon);
	
	int removeCoupon(CouponDO coupon);
	
	int updateCoupon(CouponDO coupon);
	
	CouponDO getCoupon(long companyId, long couponId);
	
	Collection <CouponDO> getAllCoupons();
	
	Collection<CouponDO> getAllCouponsByCompany(long companyId);
	
	Collection<CouponDO> getAllCouponsByCustomer(long customerId);
	
	Collection <CouponDO> getCouponsByType(long companyId, Type type);
	
	int purchaseCoupon(CouponDO coupon);
	
	Collection <CouponDO> getAllPurchaseCoupons(long customerId);
	
	Collection <CouponDO> getAllPurchaseCouponsByType(long customerId, Type type);
	
	Collection <CouponDO> getAllPurchaseCouponsByPrice(long customerId, int price);


}

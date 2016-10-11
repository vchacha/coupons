package core.fasade;

import java.util.Collection;

import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;

public interface CouponClientFacade {
	
	void createCompany(CompanyDO company);
	
	void removeCompany(CompanyDO company);
	
	void updateCompany(CompanyDO company);
	
	CompanyDO getCompany( long companyId);
	
	Collection <CompanyDO> getAllCompanies();

	void createCustomer(CustomerDO customer);
	
	void removeCustomer(CustomerDO customer);
	
	void updateCustomer(CustomerDO customer);
	
	CustomerDO getCustomer(long customerId);
	
	Collection <CustomerDO> getAllCustomer();
	
	void createCoupon(CouponDO coupon);
	
	void removeCoupon(CouponDO coupon);
	
	void updateCoupon(CouponDO coupon);
	
	CouponDO getCoupon(long companyId, long couponId);
	
	Collection <CouponDO> getAllCoupons();
	
	Collection<CouponDO> getAllCouponsByCompany(long companyId);
	
	Collection <CouponDO> getAllCouponsByCompanyAndType(long companyId, Type type);
	
	void purchaseCoupon(CouponDO coupon, long customerId);
	
	Collection <CouponDO> getAllPurchaseCouponsByCustomer(long customerId);
	
	Collection <CouponDO> getAllPurchaseCouponsByType(long customerId, Type type);
	
	Collection <CouponDO> getAllPurchaseCouponsByPrice(long customerId, int price);


}

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

	public int createCustomer(CustomerDO customer);
	
	public int removeCustomer(CustomerDO customer);
	
	public int updateCustomer(CustomerDO customer);
	
	public CustomerDO getCustomer(long customerId);
	
	public Collection <CustomerDO> getAllCustomer();
	
	public int createCoupon(CouponDO coupon);
	
	public int removeCoupon(CouponDO coupon);
	
	public int updateCoupon(CouponDO coupon);
	
	public CouponDO getCoupon(long couponId );
	
	public Collection <CouponDO> getAllCoupons();
	
	public Collection <CouponDO> getCouponsByType(Type type);
	
	public int purchase(CouponDO coupon);
	
	public Collection <CouponDO> getAllPurchaseCoupons();
	
	public Collection <CouponDO> getAllPurchaseCouponsByType(Type type);
	
	public Collection <CouponDO> getAllPurchaseCouponsByPrice(int price);


}

package core.fasade;

import java.util.Collection;

import core.database.model.CompanyDO;
import core.database.model.Coupon;
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
	
	public int createCoupon(Coupon coupon);
	
	public int removeCoupon(Coupon coupon);
	
	public int updateCoupon(Coupon coupon);
	
	public Coupon getCoupon(long couponId );
	
	public Collection <Coupon> getAllCoupons();
	
	public Collection <Coupon> getCouponsByType(Type type);
	
	public int purchase(Coupon coupon);
	
	public Collection <Coupon> getAllPurchaseCoupons();
	
	public Collection <Coupon> getAllPurchaseCouponsByType(Type type);
	
	public Collection <Coupon> getAllPurchaseCouponsByPrice(int price);


}

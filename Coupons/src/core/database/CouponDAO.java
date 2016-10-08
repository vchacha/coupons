package core.database;

import java.util.Collection;

import core.database.model.CouponDO;
import core.database.model.Type;

public interface CouponDAO {

	int createCoupon(CouponDO coupon);

	int removeCoupon(CouponDO coupon);

	int updateCoupon(CouponDO coupon);
	
	CouponDO getCoupon(long companyId, long couponId);
	
	Collection<CouponDO> getAllCoupons();

	Collection<CouponDO> getAllCouponsByCompany(long companyId);
	
	Collection<CouponDO> getAllCouponsByCustomer(long customerId);
	
	Collection<CouponDO> getAllCouponsByType(long companyId, Type type);

}

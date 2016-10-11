package core.database;

import java.util.Collection;

import core.database.model.CouponDO;
import core.database.model.Type;

public interface CouponDAO {

	int createCoupon(CouponDO couponDO);

	void removeCoupon(CouponDO couponDO);

	int updateCoupon(CouponDO couponDO);
	
	CouponDO getCoupon(long companyId, long couponId);
	
	Collection<CouponDO> getAllCoupons();

	Collection<CouponDO> getAllCouponsByType(Type type);
	
	Collection<CouponDO> getAllPurchaseCoupons();

}

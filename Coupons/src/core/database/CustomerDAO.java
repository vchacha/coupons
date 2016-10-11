package core.database;

import java.util.Collection;

import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;

public interface CustomerDAO {
	
	int createCustomer(CustomerDO customerDO);
	
	int removeCustomer(CustomerDO customerDO);
	
	int updateCustomer(CustomerDO customerDO);
	
	CustomerDO getCustomer(long customerId);
	
	Collection<CustomerDO> getAllCustomers();
	
	Collection<CouponDO> getAllPurchaseCouponsByCustomer(long customerId);
	
	Collection<CouponDO> getAllPurchaseCouponsByType(long customerId, Type type);
	
	Collection<CouponDO> getAllPurchaseCouponsByPrice(long customerId, double price);
	
	int purchaseCoupon (CouponDO couponDO, long customerId);
	
	boolean login(long customerId, String password);
	
}
 
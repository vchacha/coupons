package core.database;

import java.util.Collection;

import core.database.model.CouponDO;
import core.database.model.CustomerDO;

public interface CustomerDAO {
	
	int createCustomer(CustomerDO customerDO);
	
	int removeCustomer(CustomerDO customerDO);
	
	int updateCustomer(CustomerDO customerDO);
	
	CustomerDO getCustomer(long customerId);
	
	Collection<CustomerDO> getAllCustomers();
	
	Collection<CouponDO> getCoupons(long customerId);
	
	boolean login(long customerId, String password);
	
}
 
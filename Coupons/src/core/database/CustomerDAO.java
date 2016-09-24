package core.database;

import java.util.Collection;

import core.database.model.Coupon;
import core.database.model.CustomerDO;

public interface CustomerDAO {
	
	int createCustomer(CustomerDO customerDO);
	
	int removeCustomer(CustomerDO customerDO);
	
	int updateCustomer(CustomerDO customerDO);
	
	CustomerDO getCustomer(long customerId);
	
	Collection<CustomerDO> getAllCustomers();
	
	Collection<Coupon> getCoupons(long CustomerId);
	
	boolean login(long customerId, String password);
	
}
 
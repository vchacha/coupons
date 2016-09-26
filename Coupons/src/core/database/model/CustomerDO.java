package core.database.model;

import java.util.Collection;

public class CustomerDO {

	private long customerId;
	private String customerName;
	private String password;
	private String email;
	private Collection <CouponDO> coupon;

	public CustomerDO(long customerId, String customerName, String password, String email) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.password = password;
		this.email = email;
	}

	public long getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Collection<CouponDO> getCoupon() {
		return coupon;
	}

	public void setCoupon(Collection<CouponDO> coupon) {
		this.coupon = coupon;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		String customer = 
		"Customer Id:  " + getCustomerId() + "\n" +
		"Company Name: " + getCustomerName() + "\n" +
		"Password:     " + getPassword() + "\n" +
		"Email:        " + getEmail() + "\n";
			return customer;
	}
}

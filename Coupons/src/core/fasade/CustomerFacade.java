package core.fasade;

import java.util.Collection;

import core.database.CustomerDAO;
import core.database.model.CompanyDO;
import core.database.model.CouponDO;
import core.database.model.CustomerDO;
import core.database.model.Type;

public class CustomerFacade implements CouponClientFacade {
	
	private CustomerDAO customerDAO;
	
	public CustomerFacade(CustomerDAO customerDAO) {
		super();
		this.customerDAO = customerDAO;
	}

	@Override
	public void createCompany(CompanyDO companyDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeCompany(CompanyDO companyDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCompany(CompanyDO companyDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CompanyDO getCompany(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void createCustomer(CustomerDO customerDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeCustomer(CustomerDO customerDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCustomer(CustomerDO customerDO) {
		customerDAO.createCustomer(customerDO);
	}

	@Override
	public CustomerDO getCustomer(long customerId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CustomerDO> getAllCustomers() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void createCoupon(CouponDO couponDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeCoupon(CouponDO couponDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCoupon(CouponDO couponDO) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CouponDO getCoupon(long companyId, long couponId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllCoupons() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllCouponsByCompanyAndType(long companyId, Type type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void purchaseCoupon(CouponDO couponDO, long customerId) {
		if (couponDO.getAmount() < 1){
			System.out.println("Counter of this coupon is empty , please try to buy an another coupon");
		}
		customerDAO.purchaseCoupon(couponDO, customerId);
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByCustomer(long customerId) {
		return customerDAO.getAllPurchaseCouponsByCustomer(customerId);
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByType(long customerId, Type type) {
		return customerDAO.getAllPurchaseCouponsByType(customerId, type);
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCouponsByPrice(long customerId, int price) {
		return customerDAO.getAllPurchaseCouponsByPrice(customerId, price);
	}

	@Override
	public Collection<CompanyDO> getAllCompanies() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Collection<CouponDO> getAllCouponsByCompany(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllCouponsByType(Type type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CouponDO> getAllPurchaseCoupons() {
		throw new UnsupportedOperationException();
	}


}

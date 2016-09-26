package core.database.model;

public class CouponDO {
	
	private long companyId;
	private long couponId;
	private String title;
	private String startDate;
	private String endDate;
	private int amount;
	private Type type;
	private String messege;
	private double price;
	private String image;
	
	


	public CouponDO(long companyId, long couponId, String title, String startDate, String endDate, int amount, Type type, String messege,
			double price, String image) {
		super();
		this.companyId = companyId;
		this.couponId = couponId;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.messege = messege;
		this.price = price;
		this.image = image;
	}



	public long getCouponId() {
		return couponId;
	}



	public void setTitle(String title) {
		this.title = title;
	}




	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}




	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}




	public void setAmount(int amount) {
		this.amount = amount;
	}




	public void setType(Type type) {
		this.type = type;
	}




	public void setMessege(String messege) {
		this.messege = messege;
	}




	public void setPrice(double price) {
		this.price = price;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public String getTitle() {
		return title;
	}




	public String getStartDate() {
		return startDate;
	}




	public String getEndDate() {
		return endDate;
	}




	public int getAmount() {
		return amount;
	}




	public Type getType() {
		return type;
	}




	public String getMessege() {
		return messege;
	}




	public double getPrice() {
		return price;
	}




	public String getImage() {
		return image;
	}




	@Override
	public String toString() {
		String coupon = 
		"Coupon Id:" + getCouponId() + "\n" +
		"Title:    " + getTitle() + "\n" +
		"Start Day:" + getStartDate() + "\n" +
		"End Day:  " + getEndDate() + "\n" + 
		"Amount:   " + getAmount() + "\n" +
		"Type:     " + getType() + "\n" +
		"Messege:  " + getMessege() + "\n" + 
		"Price:    " + getPrice() + "\n" +
		"Image:    " + getImage() + "\n"; 
			return coupon;
	}
}

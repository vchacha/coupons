package core.database.model;

public class Coupon {

	private Long couponID;
	private String title;
	private String startDate;
	private String endDate;
	private int amount;
	private Type type;
	private String messege;
	private double price;
	private String image;
	
	


	public Coupon(Long couponID, String title, String startDate, String endDate, int amount, Type type, String messege,
			double price, String image) {
		super();
		this.couponID = couponID;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.messege = messege;
		this.price = price;
		this.image = image;
	}




	public Long getCouponID() {
		return couponID;
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
		"Coupon Id:" + getCouponID() + "\n" +
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

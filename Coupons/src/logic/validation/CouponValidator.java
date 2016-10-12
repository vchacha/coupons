package logic.validation;

import core.database.model.CouponDO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CouponValidator implements DataValidator<CouponDO> {

	private static final int MINIMUN_ALLOWED_STRING_LENGTH = 4;
	private static final long MINIMUN_NUMBER_ID = 1000;

	@Override
	public ValidationResponse validateData(CouponDO couponDO) {
		boolean isCouponIdMeetRules = validateId(couponDO.getCouponId());
		boolean isCompanyIdMeetRules = validateId(couponDO.getCompanyId());
		boolean isTitleMeetRules = validateString(couponDO.getTitle(), MINIMUN_ALLOWED_STRING_LENGTH);
		boolean isDateMeetRules = validateDate(couponDO.getStartDate(), couponDO.getEndDate());
		boolean isAmountMeetRules = validateAmountAndPrice(couponDO.getAmount());
		boolean isMessageMeetRules = validateString(couponDO.getMessage(), MINIMUN_ALLOWED_STRING_LENGTH); 
		boolean isPriceMeetRules = validateAmountAndPrice(couponDO.getPrice());
		boolean isImageMeetRules = validateString(couponDO.getImage(), MINIMUN_ALLOWED_STRING_LENGTH);
		StringBuilder aggregateErrorMessage = new StringBuilder();
		
		if (!isCouponIdMeetRules) {
			aggregateErrorMessage.append("Coupon ID does not meet required standards \n");
		}
		if (!isCompanyIdMeetRules) {
			aggregateErrorMessage.append("Company ID does not meet required standards \n");
		}
		if (!isTitleMeetRules) {
			aggregateErrorMessage.append("Title of coupon does not meet required standards \n");
		}		
		if (!isDateMeetRules) {
			aggregateErrorMessage.append("Start Date or/and End Date does not meet required standards \n");
		}
		if (!isAmountMeetRules) {
			aggregateErrorMessage.append("Amount of coupons does not meet required standards \n");
		}
		if (!isMessageMeetRules) {
			aggregateErrorMessage.append("Message/Information of coupon does not meet required standards \n");
		}
		if (!isPriceMeetRules) {
			aggregateErrorMessage.append("Price does not meet required standards \n");
		}
		if (!isImageMeetRules) {
			aggregateErrorMessage.append("Image does not meet required standards \n");
		}
		
		if (!isCouponIdMeetRules || !isCompanyIdMeetRules || !isTitleMeetRules || !isDateMeetRules 
			|| !isAmountMeetRules || !isMessageMeetRules || !isPriceMeetRules || !isImageMeetRules){
			return new ValidationResponse(false, aggregateErrorMessage.toString());
		}
		return new ValidationResponse(true, null);
	}

	private boolean validateId(long id) {
		if (id >= MINIMUN_NUMBER_ID) {
			return true;
		}
		return false;
	}

	private boolean validateString(String string, int minAllowedLength) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		return string.length() >= minAllowedLength;
	}

	private boolean validateDate(Date startDate, Date endDate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   Date currentDate = new Date();
		if (startDate.before(currentDate) || endDate.before(currentDate)){
			return false;
		}	
		if (endDate.before(startDate)){
			return false;
		}
		
		return true;
	}

	private boolean validateAmountAndPrice(double price) {
		if (price >= 1) {
			return true;
		}
		return false;
	}
	
	private boolean validateAmountAndPrice(int amount) {
		if (amount >= 1) {
			return true;
		}
		return false;
	}
}

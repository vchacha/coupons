package runner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import core.database.ConnectionManagerPool;
import core.database.model.CompanyDO;
import core.fasade.ClientType;
import core.fasade.CouponClientFacade;
import core.fasade.LoginManager;
import logic.exceptions.CompanyValidationException;
import logic.exceptions.DAOException;
import logic.exceptions.LoginException;

public class MiscRunner {
	
	public static void main(String[] args) {
	try {

		LoginManager loginManagerAdmin = new LoginManager();
		//TODO Login with user Admin (username 'Admin', password '1234'), if password is incorrect, expect exception
		CouponClientFacade couponClientFacadeAdmin = loginManagerAdmin.login("Admin", "1234", ClientType.ADMIN);
	
		//Creation a new Company (without exception)
		try {
			couponClientFacadeAdmin.createCompany(new CompanyDO(12345, "ebay", "eB765", "ebay@gmail.com"));				
		} catch (DAOException e) {
			System.err.println(e.getMessage());
		}
	} catch (LoginException e) {
		System.out.println("Error!!!" + e.getMessage());
	}	
	
	
	finally {
		ConnectionManagerPool.getInstance().closeAllConnection();
	}
}}
	
	
	
	
	
	
	
	
	
	

	/*public static void main(String[] args) throws ParseException {
		List<String> strings = new ArrayList<>();
		strings.add("10");
		strings.add("12");
		strings.add("3");
		strings.add("5");
		
		System.out.println(String.join(",", strings));*/
		
		
		/*Date date = new Date();
		System.out.println(date.toString());
		
//		LocalDate localDate = LocalDate.
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parsedDate = simpleDateFormat.parse("2016-10-11 09:46:00");
		System.out.println(parsedDate);
		System.out.println(simpleDateFormat.format(parsedDate));*/
	//	System.out.println(parsedDate.toString());
		
		/*LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(date, time);*/
		
//		LocalDateTime dateTimeFromDateAndTime = LocalDateTime.parse("2016-10-11 09:46:00");
		
//		System.out.println(dateTimeFromDateAndTime.toString());
//	}

//}

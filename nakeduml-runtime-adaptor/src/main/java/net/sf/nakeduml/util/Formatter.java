package net.sf.nakeduml.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Formatter{
	public static int parseInteger(String string){
		return Integer.parseInt(string);
	}
	public static String parseName(String string){
		return string;
	}
	public static String parseFile(String string){
		return string;
	}
	public static InternetAddress[] parseEmailAddress(String string){
		try{
			return InternetAddress.parse(string);
		}catch(AddressException e){
			throw new RuntimeException(e);
		}
	}
	public static String parseCode(String string){
		return string;
	}
	public static boolean parseYesNo(String string){
		return "true".equals(string);
	}
	public static float parseDecimal(String string){
		return Float.parseFloat(string);
	}
	public static String parseNote(String string){
		return string;
	}
	public static String parsePassword(String string){
		return string;
	}
	public static String parsePhoneNumber(String string){
		return string;
	}
	public static String parseUsername(String string){
		return string;
	}
	public static java.sql.Date parseDateInFuture(String string){
		return java.sql.Date.valueOf(string);
	}
	public static float parseMoneyInLocalCurrency(String string){
		return Float.parseFloat(string);
	}
	public static float parsePercentage(String string){
		return Float.parseFloat(string);
	}
}

package org.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//TODO make this threadvar or something
public class TinkerFormatter {

	public static Date parse(String date) {
		try {
			return new SimpleDateFormat().parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String format(Date date) {
		return new SimpleDateFormat().format(date);
	}

}

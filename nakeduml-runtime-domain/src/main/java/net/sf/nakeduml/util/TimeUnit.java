package net.sf.nakeduml.util;

import java.io.Serializable;

public enum TimeUnit implements Serializable, AbstractEnum {
	CALENDAR_YEAR("calendarYear"), BUSINESS_YEAR("businessYear"), BUSINESS_MONTH("businessMonth"), CALENDAR_MONTH("calendarMonth"), BUSINESS_WEEK(
			"businessWeek"), CALENDAR_WEEK("calendarWeek"), CALENDAR_DAY("calendarDay"), BUSINESS_DAY("businessDay"), BUSINESS_MINUTE(
			"businessMinute"), ACTUAL_MINUTE("actualMinute"), BUSINESS_HOUR("businessHour"), ACTUAL_HOUR("actualHour"), ACTUAL_SECOND("acutalSecond");
	private String name;

	TimeUnit(String code) {
		this.name = code;
	}

	public static TimeUnit lookup(String name) {
		for (TimeUnit tu :values()) {
			if (tu.getName().equals(name) || tu.name().equals(name)) {
				return tu;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(BUSINESS_DAY);
	}

	public String getName() {
		return this.name;
	}

	public boolean isBusinessTime() {
		switch (this) {
		case BUSINESS_YEAR:
		case BUSINESS_MONTH:
		case BUSINESS_WEEK:
		case BUSINESS_DAY:
		case BUSINESS_HOUR:
		case BUSINESS_MINUTE:
			return true;
		default:
			return false;
		}
	}
}

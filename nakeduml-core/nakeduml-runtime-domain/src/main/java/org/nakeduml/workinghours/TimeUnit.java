package org.nakeduml.workinghours;
import org.nakeduml.runtime.domain.AbstractEnum;
import org.nakeduml.runtime.domain.MetaIdentifiable;
public class TimeUnit implements MetaIdentifiable, AbstractEnum {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7891898661873010721L;
	static final public TimeUnit DEFAULT = new TimeUnit();
	static final public String[] ALLNAMES = { "DEFAULT", "businessWeek", "businessMonth", "actualMinute",
			"calendarDay", "actualHour", "businessMinute", "calendarWeek", "calendarYear", "businessYear",
			"businessHour", "businessDay", "calendarMonth" };
	final private int value;
	final private String name;
	static final public int _BUSINESSWEEK = 39;
	static final public TimeUnit BUSINESSWEEK = new TimeUnit(_BUSINESSWEEK, "businessWeek",
			"_9_5_c530259_1132754550640_375512_1015");
	static final public int _BUSINESSMONTH = 43;
	static final public TimeUnit BUSINESSMONTH = new TimeUnit(_BUSINESSMONTH, "businessMonth",
			"_11_5_625020d_1154673751296_948740_518");
	static final public int _ACTUALMINUTE = 42;
	static final public TimeUnit ACTUALMINUTE = new TimeUnit(_ACTUALMINUTE, "actualMinute",
			"_11_5_625020d_1154673369062_367529_497");
	static final public int _CALENDARDAY = 40;
	static final public TimeUnit CALENDARDAY = new TimeUnit(_CALENDARDAY, "calendarDay",
			"_11_5_625020d_1154672526656_897270_474");
	static final public int _ACTUALHOUR = 41;
	static final public TimeUnit ACTUALHOUR = new TimeUnit(_ACTUALHOUR, "actualHour",
			"_11_5_625020d_1154672969015_299580_482");
	static final public int _BUSINESSMINUTE = 38;
	static final public TimeUnit BUSINESSMINUTE = new TimeUnit(_BUSINESSMINUTE, "businessMinute",
			"_9_5_c530259_1130136124437_475107_1395");
	static final public int _CALENDARWEEK = 44;
	static final public TimeUnit CALENDARWEEK = new TimeUnit(_CALENDARWEEK, "calendarWeek",
			"_11_5_625020d_1154673764515_491306_525");
	static final public int _CALENDARYEAR = 45;
	static final public TimeUnit CALENDARYEAR = new TimeUnit(_CALENDARYEAR, "calendarYear",
			"_11_5_625020d_1154673785234_14548_528");
	static final public int _BUSINESSYEAR = 34;
	static final public TimeUnit BUSINESSYEAR = new TimeUnit(_BUSINESSYEAR, "businessYear",
			"_9_5_c530259_1130136124421_243768_1391");
	static final public int _BUSINESSHOUR = 37;
	static final public TimeUnit BUSINESSHOUR = new TimeUnit(_BUSINESSHOUR, "businessHour",
			"_9_5_c530259_1130136124421_327503_1394");
	static final public int _BUSINESSDAY = 36;
	static final public TimeUnit BUSINESSDAY = new TimeUnit(_BUSINESSDAY, "businessDay",
			"_9_5_c530259_1130136124421_569139_1393");
	static final public int _CALENDARMONTH = 35;
	static final public TimeUnit CALENDARMONTH = new TimeUnit(_CALENDARMONTH, "calendarMonth",
			"_9_5_c530259_1130136124421_470101_1392");
	private String mofIdForLiteral;
	static final private TimeUnit[] ALL = { BUSINESSWEEK, BUSINESSMONTH, ACTUALMINUTE, CALENDARDAY, ACTUALHOUR,
			BUSINESSMINUTE, CALENDARWEEK, CALENDARYEAR, BUSINESSYEAR, BUSINESSHOUR, BUSINESSDAY, CALENDARMONTH };
	/**
	 * Constructor for TimeUnit
	 */
	public TimeUnit() {
		this.value = 0;
		this.name = "DEFAULT";
		this.mofIdForLiteral = "";
	}
	/**
	 * Constructor for TimeUnit
	 * 
	 * @param value
	 * @param name
	 * @param mofIdForLiteral
	 */
	public TimeUnit(int value, String name, String mofIdForLiteral) {
		this.value = value;
		this.name = name;
		this.mofIdForLiteral = mofIdForLiteral;
	}
	static public TimeUnit lookup(int key) {
		switch (key) {
		case _BUSINESSWEEK:
			return BUSINESSWEEK;
		case _BUSINESSMONTH:
			return BUSINESSMONTH;
		case _ACTUALMINUTE:
			return ACTUALMINUTE;
		case _CALENDARDAY:
			return CALENDARDAY;
		case _ACTUALHOUR:
			return ACTUALHOUR;
		case _BUSINESSMINUTE:
			return BUSINESSMINUTE;
		case _CALENDARWEEK:
			return CALENDARWEEK;
		case _CALENDARYEAR:
			return CALENDARYEAR;
		case _BUSINESSYEAR:
			return BUSINESSYEAR;
		case _BUSINESSHOUR:
			return BUSINESSHOUR;
		case _BUSINESSDAY:
			return BUSINESSDAY;
		case _CALENDARMONTH:
			return CALENDARMONTH;
		}
		;
		return null;
	}
	static public TimeUnit lookup(String name) {
		for (int i = 0; i < ALL.length; i++) {
			if (name.equals(ALL[i].getName())) {
				return ALL[i];
			}
		}
		throw new IllegalArgumentException("No TimeUnit exists for \'" + name + "\'.");
	}
	public int getValue() {
		return this.value;
	}
	public String getName() {
		return this.name;
	}
	@Override
	public String toString() {
		return getName();
	}
	public boolean isBusinessWeek() {
		return this.value == _BUSINESSWEEK;
	}
	public boolean isBusinessMonth() {
		return this.value == _BUSINESSMONTH;
	}
	public boolean isActualMinute() {
		return this.value == _ACTUALMINUTE;
	}
	public boolean isCalendarDay() {
		return this.value == _CALENDARDAY;
	}
	public boolean isActualHour() {
		return this.value == _ACTUALHOUR;
	}
	public boolean isBusinessMinute() {
		return this.value == _BUSINESSMINUTE;
	}
	public boolean isCalendarWeek() {
		return this.value == _CALENDARWEEK;
	}
	public boolean isCalendarYear() {
		return this.value == _CALENDARYEAR;
	}
	public boolean isBusinessYear() {
		return this.value == _BUSINESSYEAR;
	}
	public boolean isBusinessHour() {
		return this.value == _BUSINESSHOUR;
	}
	public boolean isBusinessDay() {
		return this.value == _BUSINESSDAY;
	}
	public boolean isCalendarMonth() {
		return this.value == _CALENDARMONTH;
	}
	public void setMofIdForLiteral(String mofIdForLiteral) {
		this.mofIdForLiteral = mofIdForLiteral;
	}
	public String getMofIdForLiteral() {
		return this.mofIdForLiteral;
	}
	static public TimeUnit[] getAllLiterals() {
		return ALL;
	}
	public String getMetaId() {
		return "_9_5_c530259_1130136092609_795275_1390";
	}
	public void setClassId(int dummy) {
	}
	public int getClassId() {
		return 33;
	}
}
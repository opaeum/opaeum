package org.nakeduml.workinghours;

import org.nakeduml.runtime.domain.IEnum;

public class WorkDayType implements IEnum{
	private static final long serialVersionUID = 5546864012892815979L;
	static final public WorkDayType DEFAULT = new WorkDayType();
	static final public String[] ALLNAMES = {
			"DEFAULT","weekday","saturday","sunday"
	};
	final private int value;
	final private String name;
	static final public int _WEEKDAY = 22;
	static final public WorkDayType WEEKDAY = new WorkDayType(_WEEKDAY, "weekday", "_9_5_c530259_1132751800812_490923_15");
	static final public int _SATURDAY = 20;
	static final public WorkDayType SATURDAY = new WorkDayType(_SATURDAY, "saturday", "_9_5_c530259_1132751800812_441446_9");
	static final public int _SUNDAY = 21;
	static final public WorkDayType SUNDAY = new WorkDayType(_SUNDAY, "sunday", "_9_5_c530259_1132751800812_420014_12");
	private String mofIdForLiteral;
	static final private WorkDayType[] ALL = {
			WEEKDAY,SATURDAY,SUNDAY
	};
	/**
	 * Constructor for WorkDayType
	 * 
	 * @param value
	 * @param name
	 * @param mofIdForLiteral
	 */
	public WorkDayType(int value,String name,String mofIdForLiteral){
		this.value = value;
		this.name = name;
		this.mofIdForLiteral = mofIdForLiteral;
	}
	/**
	 * Constructor for WorkDayType
	 */
	public WorkDayType(){
		this.value = 0;
		this.name = "DEFAULT";
		this.mofIdForLiteral = "";
	}
	static public WorkDayType lookup(int key){
		switch(key){
		case _WEEKDAY:
			return WEEKDAY;
		case _SATURDAY:
			return SATURDAY;
		case _SUNDAY:
			return SUNDAY;
		}
		;
		return null;
	}
	static public WorkDayType lookup(String name){
		for(int i = 0;i < ALL.length;i++){
			if(name.equals(ALL[i].getName())){
				return ALL[i];
			}
		}
		throw new IllegalArgumentException("No WorkDayType exists for \'" + name + "\'.");
	}
	public int getValue(){
		return this.value;
	}
	public String getName(){
		return this.name;
	}
	@Override
	public String toString(){
		return getName();
	}
	public boolean isWeekday(){
		return this.value == _WEEKDAY;
	}
	public boolean isSaturday(){
		return this.value == _SATURDAY;
	}
	public boolean isSunday(){
		return this.value == _SUNDAY;
	}
	public void setMofIdForLiteral(String mofIdForLiteral){
		this.mofIdForLiteral = mofIdForLiteral;
	}
	public String getMofIdForLiteral(){
		return this.mofIdForLiteral;
	}
	static public WorkDayType[] getAllLiterals(){
		return ALL;
	}
	public String getMetaId(){
		return "_9_5_c530259_1132751800812_670037_16";
	}
	public void setClassId(int dummy){
	}
	public int getClassId(){
		return 19;
	}
	@Override
	public String getUuid(){
		return mofIdForLiteral;
	}
}
package org.opaeum.runtime.environment.marshall;


public class EnumValue extends Value{
	private static final long serialVersionUID = 9086348287296846913L;
	Long enumId;
	public Long getEnumId(){
		return enumId;
	}
	public EnumValue(String classId,Long enumId){
		super(classId);
		this.enumId = enumId;
	}
}

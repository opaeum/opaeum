package org.nakeduml.runtime.environment.marshall;


public class EnumValue extends Value{
	private static final long serialVersionUID = 9086348287296846913L;
	Integer enumId;
	public Integer getEnumId(){
		return enumId;
	}
	public EnumValue(String classId,Integer enumId){
		super(classId);
		this.enumId = enumId;
	}
}

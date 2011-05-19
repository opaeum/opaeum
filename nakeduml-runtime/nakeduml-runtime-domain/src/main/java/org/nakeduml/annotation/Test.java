package org.nakeduml.annotation;


public enum Test {
@SecurityOnEdit(requiredRoles={"asdf","asdf"},requiresGroupOwnership=true,requiresUserOwnership=false)
	ONE,
	@SecurityOnEdit(requiredRoles={"asdf","asdf"},requiresGroupOwnership=true,requiresUserOwnership=false)
	TWO;
//	public static void main(String[] args) throws Exception{
//		System.out.println(Test.class.getField("ONE").getAnnotation(SecurityOnEdit.class).requiresGroupOwnership());
//	}
}

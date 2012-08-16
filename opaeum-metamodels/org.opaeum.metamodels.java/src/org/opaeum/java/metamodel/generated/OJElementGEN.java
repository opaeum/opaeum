package org.opaeum.java.metamodel.generated;

import java.util.HashMap;
import java.util.Map;

import org.opaeum.java.metamodel.OJElement;

abstract public class OJElementGEN{
	protected String name = "";
	private String comment = "";
	public static Map<Class<?>,Long> counts = new HashMap<Class<?>,Long>();
	public void finalize(){
		counts.put(getClass(), getCount() - 1);
	}
	protected Long getCount(){
		Long long1 = counts.get(getClass());
		if(long1 == null){
			return 0l;
		}else if(long1 % 100 == 0){
//			System.out.println(getClass().getName() + " count :" + long1);
		}
		return long1;
	}
	protected OJElementGEN(){
		counts.put(getClass(), getCount() + 1);
	}
	public String getName(){
		return name;
	}
	public String getComment(){
		return comment;
	}
	public void setComment(String element){
		if(comment != element){
			comment = element;
		}
	}
	public String toString(){
		String result = "";
		if(this.getName() != null){
			result = result + " name:" + this.getName() + ", ";
		}
		if(this.getComment() != null){
			result = result + " comment:" + this.getComment();
		}
		return result;
	}
	public String getIdString(){
		String result = "";
		if(this.getName() != null){
			result = result + this.getName();
		}
		return result;
	}
	/**
	 * Copies all attributes and associations of this instance into 'copy'. True parts, i.e. associations marked 'aggregate' or 'composite',
	 * and attributes, are copied as well. References to other objects, i.e. associations not marked 'aggregate' or 'composite', will not be
	 * copied. The 'copy' will refer to the same objects as the original (this) instance.
	 * 
	 * @param copy
	 */
	public void copyInfoInto(OJElement copy){
		copy.name=getName();
		copy.setComment(getComment());
	}
}
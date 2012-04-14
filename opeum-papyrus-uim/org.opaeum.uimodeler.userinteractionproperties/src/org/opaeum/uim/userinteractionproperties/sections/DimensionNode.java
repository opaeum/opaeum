package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Class;

public class DimensionNode{
	private Property property;
	private Class fromClass;
	DimensionNode detail;
	DimensionNode master;
	public DimensionNode(Class fromClass,Property p){
		this.property = p;
		this.fromClass = fromClass;
	}
	public DimensionNode(){
	}
	public Class getFromClass(){
		return fromClass;
	}
	public DimensionNode linkToInnermostDetail(){
		if(detail == null){
			return this;
		}else{
			detail.master = this;
			return detail.linkToInnermostDetail();
		}
	}
	public DimensionNode getCopy(){
		DimensionNode result = new DimensionNode(fromClass, property);
		if(detail != null){
			result.detail = detail.getCopy();
		}
		return result;
	}
	@Override
	public String toString(){
		return getName();
	}
	private DimensionNode getDetail(){
		return detail;
	}
	Property getProperty(){
		return property;
	}
	public String getName(){
		if(master == null){
			return property.getType().getName();
		}else{
			return property.getType().getName() + "->" + master.getName();
		}
	}
}
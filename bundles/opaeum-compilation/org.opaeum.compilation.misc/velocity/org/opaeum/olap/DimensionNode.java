package org.opaeum.olap;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

public class DimensionNode{
	private Property property;
	private Classifier fromClass;
	DimensionNode detail;
	DimensionNode master;
	public DimensionNode(Classifier fromClass,Property p){
		this.property = p;
		this.fromClass = fromClass;
	}
	public DimensionNode(){
	}
	public Classifier getFromClass(){
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
		return fromClass.getName() + "->" + property.getName();
	}
	Property getProperty(){
		return property;
	}
	//NB!!! Needs to be synchronized with org.opaeum.uim.userinteractionproperties.sections.DimensionNode.getName()
	public String getName(){
		if(master == null){
			return property.getName() + ":" + property.getType().getName();
		}else{
			return property.getName() + "." + master.getName();
		}
	}
	public boolean hasRecursion(){
		Set<Property> props = new HashSet<Property>();
		if(occursIn(props)){
			return true;
		}else{
			return false;
		}
	}
	private boolean occursIn(Set<Property> props){
		if(props.contains(property)){
			return true;
		}else if(detail!=null){
			props.add(property);
			return detail.occursIn(props);
		}else{
			return false;
		}
	}
	public Type getBaseType(){
		return property.getType();
	}
}
package org.opaeum.olap;

import java.util.HashSet;
import java.util.Set;

import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedProperty;

public class DimensionNode{
	private INakedProperty property;
	private INakedClassifier fromClass;
	DimensionNode detail;
	DimensionNode master;
	public DimensionNode(INakedClassifier fromClass,INakedProperty p){
		this.property = p;
		this.fromClass = fromClass;
	}
	public DimensionNode(){
	}
	public INakedClassifier getFromClass(){
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
	INakedProperty getProperty(){
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
		Set<INakedProperty> props = new HashSet<INakedProperty>();
		if(occursIn(props)){
			return true;
		}else{
			return false;
		}
	}
	private boolean occursIn(Set<INakedProperty> props){
		if(props.contains(property)){
			return true;
		}else if(detail!=null){
			props.add(property);
			return detail.occursIn(props);
		}else{
			return false;
		}
	}
}
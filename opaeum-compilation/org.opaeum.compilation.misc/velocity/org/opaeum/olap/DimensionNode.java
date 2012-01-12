package org.opaeum.olap;

import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedProperty;

public class DimensionNode{
	private INakedProperty property;
	private ICompositionParticipant fromClass;
	DimensionNode detail;
	DimensionNode master;
	public DimensionNode(ICompositionParticipant fromClass,INakedProperty p){
		this.property = p;
		this.fromClass = fromClass;
	}
	public DimensionNode(){
	}
	public ICompositionParticipant getFromClass(){
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
	private DimensionNode getDetail(){
		return detail;
	}
	INakedProperty getProperty(){
		return property;
	}
	public String getName(){
		if(master == null){
			return property.getBaseType().getName();
		}else{
			return property.getBaseType().getName() + "->" + master.getName();
		}
	}
}
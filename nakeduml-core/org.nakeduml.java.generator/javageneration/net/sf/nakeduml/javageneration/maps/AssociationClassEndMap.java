package net.sf.nakeduml.javageneration.maps;

import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public class AssociationClassEndMap{
	private NakedStructuralFeatureMap map;
	private NakedStructuralFeatureMap otherMap;
	private NakedStructuralFeatureMap assocationClassToEndMap;
	private NakedStructuralFeatureMap assocationClassToOtherEndMap;
	private NakedStructuralFeatureMap endToAssocationClassMap;
	private NakedStructuralFeatureMap otherEndToAssocationClassMap;
	public AssociationClassEndMap(INakedProperty p){
		INakedAssociation asc = (INakedAssociation) p.getAssociation();
		map = OJUtil.buildStructuralFeatureMap(p);
		otherMap = OJUtil.buildStructuralFeatureMap(p.getOtherEnd());
		assocationClassToEndMap = OJUtil.buildStructuralFeatureMap(asc.getPropertyToEnd(p));
		assocationClassToOtherEndMap = OJUtil.buildStructuralFeatureMap(asc.getPropertyToEnd(p.getOtherEnd()));
		if(p.isNavigable()){
			endToAssocationClassMap = OJUtil.buildStructuralFeatureMap(asc.getPropertyToEnd(p).getOtherEnd());
		}
		if(p.getOtherEnd().isNavigable()){
			otherEndToAssocationClassMap = OJUtil.buildStructuralFeatureMap(asc.getPropertyToEnd(p.getOtherEnd()).getOtherEnd());
		}
	}
	public NakedStructuralFeatureMap getMap(){
		return map;
	}
	public NakedStructuralFeatureMap getOtherMap(){
		return otherMap;
	}
	public NakedStructuralFeatureMap getEndToAssocationClassMap(){
		return endToAssocationClassMap;
	}
	public NakedStructuralFeatureMap getOtherEndToAssocationClassMap(){
		return otherEndToAssocationClassMap;
	}
	public NakedStructuralFeatureMap getAssocationClassToEndMap(){
		return assocationClassToEndMap;
	}
	public NakedStructuralFeatureMap getAssocationClassToOtherEndMap(){
		return assocationClassToOtherEndMap;
	}
}

package org.opaeum.javageneration.maps;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.opaeum.javageneration.util.OJUtil;

public class AssociationClassEndMap{
	private StructuralFeatureMap map;
	private StructuralFeatureMap otherMap;
	private StructuralFeatureMap assocationClassToEndMap;
	private StructuralFeatureMap assocationClassToOtherEndMap;
	private StructuralFeatureMap endToAssocationClassMap;
	private StructuralFeatureMap otherEndToAssocationClassMap;
	public AssociationClassEndMap(OJUtil ojUtil, Property p){
		Association asc = (Association) p.getAssociation();
		map = ojUtil.buildStructuralFeatureMap(p);
		otherMap = ojUtil.buildStructuralFeatureMap(p.getOtherEnd());
		assocationClassToEndMap = ojUtil.buildStructuralFeatureMap(asc.getPropertyToEnd(p));
		assocationClassToOtherEndMap = ojUtil.buildStructuralFeatureMap(asc.getPropertyToEnd(p.getOtherEnd()));
		if(p.isNavigable()){
			endToAssocationClassMap = ojUtil.buildStructuralFeatureMap(asc.getPropertyToEnd(p).getOtherEnd());
		}
		if(p.getOtherEnd().isNavigable()){
			otherEndToAssocationClassMap = ojUtil.buildStructuralFeatureMap(asc.getPropertyToEnd(p.getOtherEnd()).getOtherEnd());
		}
	}
	public StructuralFeatureMap getMap(){
		return map;
	}
	public StructuralFeatureMap getOtherMap(){
		return otherMap;
	}
	public StructuralFeatureMap getEndToAssocationClassMap(){
		return endToAssocationClassMap;
	}
	public StructuralFeatureMap getOtherEndToAssocationClassMap(){
		return otherEndToAssocationClassMap;
	}
	public StructuralFeatureMap getAssocationClassToEndMap(){
		return assocationClassToEndMap;
	}
	public StructuralFeatureMap getAssocationClassToOtherEndMap(){
		return assocationClassToOtherEndMap;
	}
}

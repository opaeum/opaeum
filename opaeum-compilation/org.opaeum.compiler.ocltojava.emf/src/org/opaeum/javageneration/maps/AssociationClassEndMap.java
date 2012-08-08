package org.opaeum.javageneration.maps;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForAssociation;
import org.opaeum.javageneration.util.OJUtil;

public class AssociationClassEndMap{
	private StructuralFeatureMap map;
	private StructuralFeatureMap assocationClassToOtherEndMap;
	private StructuralFeatureMap endToAssocationClassMap;
	private StructuralFeatureMap otherEndToAssocationClassMap;
	public AssociationClassEndMap(OJUtil ojUtil, Property p){
		map = ojUtil.buildStructuralFeatureMap(p);
		EmulatedPropertyHolderForAssociation ephfa=  (EmulatedPropertyHolderForAssociation) ojUtil.getLibrary().getEmulatedPropertyHolder(p.getAssociation());
		assocationClassToOtherEndMap = ojUtil.buildStructuralFeatureMap(ephfa.getEmulatedAttribute(p));
		if(p.isNavigable()){
			endToAssocationClassMap = ojUtil.buildStructuralFeatureMap(ephfa.getEndToAssociation(p));
		}
		if(p.getOtherEnd().isNavigable()){
			otherEndToAssocationClassMap = ojUtil.buildStructuralFeatureMap(ephfa.getEndToAssociation(p.getOtherEnd()));
		}
	}
	public StructuralFeatureMap getMap(){
		return map;
	}

	public StructuralFeatureMap getEndToAssocationClassMap(){
		return endToAssocationClassMap;
	}
	public StructuralFeatureMap getOtherEndToAssocationClassMap(){
		return otherEndToAssocationClassMap;
	}

	public StructuralFeatureMap getAssocationClassToOtherEndMap(){
		return assocationClassToOtherEndMap;
	}
}

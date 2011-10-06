/*
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.maps;


import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IStructuralFeature;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.StringHelpers;

import org.opaeum.java.metamodel.OJPathName;

/**
 * StructuralfeatureMap : This class holds all the information on how a UML structural feature is transformed
 * into Java.
 * 
 * It is able to give information on the following items.
 * 		- fieldname
 * 		- getter
 * 		- setter
 * 		- adder
 * 		- remover
 * 		- removeAll
 * 		- internal adder
 * 		- internal remover
 * 
 * (See ClassifierMap)
 * 		- java type path of this feature
 * 		- java facade path of this feature
 * 		- java type path of the value that is used to initialize this feature
 * 		- java value that is used to initialize this feature
 * 		- first three also for the element type if this feature's type is a collection
 * 
 * 		- unmodifiable collection method
 * 
 */
public class StructuralFeatureMap extends PackageableElementMap {

	protected IStructuralFeature	feature;
	protected ClassifierMap   		featureTypeMap 	= null;
	protected ClassifierMap   		baseTypeMap 		= null;
	
	public StructuralFeatureMap(IStructuralFeature feature) {
		super(feature);
		this.feature = feature;
		featureTypeMap = new ClassifierMap(feature.getType());
		if (feature instanceof IAssociationEnd) {
			IClassifier baseType = ((IAssociationEnd)feature).getBaseType();
			baseTypeMap = new ClassifierMap(baseType);
		}  else if (feature.getType().isCollectionKind()) {
			IClassifier baseType = ((ICollectionType)feature.getType()).getElementType();
			baseTypeMap = new ClassifierMap(baseType);
		} else {
			baseTypeMap = featureTypeMap;
		}

	}
	
	/* SET OF OPERATIONS THAT RETURN SIMPLE INFO ON THIS STRUCTURAL FEATURE */
	
	public IStructuralFeature getFeature() {
		return feature;
	}
	
	public String umlName(){
		return feature.getName();
	}
	
	public boolean isUnique() {
		return feature.isUnique();
	}

	public boolean isStatic() {
		return feature.hasClassScope();
	}
	
	public String isDerived(){
		return String.valueOf( !feature.isDerived());
	}
	
	public boolean isOptional(){
		return feature.getMultiplicity().getLower() == 0;
	}
	
	public String umlType(){
		return featureTypeMap.umlType();
	}
	
	public boolean isCollection(){
		return featureTypeMap.isCollection();
	}
	
	public boolean isNestedCollection(){
		return featureTypeMap.isNestedCollection();	
	}

	public boolean isUmlPrimitive(){
		return featureTypeMap.isUmlPrimitive();
	}
	
	public boolean isJavaPrimitive(){
		return featureTypeMap.isJavaPrimitive();
	}
	
	public boolean elementTypeIsUmlPrimitive(){
		Check.pre("StructuralFeatureTransformer.elementTypeIsUmlPrimitive called for non-collection attribute", isCollection() );
		ICollectionType type = (ICollectionType)feature.getType();
		return new ClassifierMap(type.getElementType()).isUmlPrimitive();
	}

	public boolean elementTypeIsJavaPrimitive(){
		Check.pre("StructuralFeatureTransformer.elementTypeIsJavaPrimitive called for non-collection attribute", isCollection() );
		ICollectionType type = (ICollectionType)feature.getType();
		return new ClassifierMap(type.getElementType()).isJavaPrimitive();
	}

	/* SET OF OPERATIONS THAT RETURN JAVA INFO ON THIS STRUCTURAL FEATURE */
	
	public String fieldname(){
		return "f_" + StringHelpers.firstCharToLower(feature.getName());
	}

	public String getter() {
		String name = feature.getName();
		if (name.indexOf("is") == 0) {
			return name;
		}
		return "get" + StringHelpers.firstCharToUpper(name);
	}

	public String setter() {
		String name = feature.getName();
		if (name.indexOf("is") == 0) {
			name = name.substring(2, name.length());
		}
		return "set" + StringHelpers.firstCharToUpper(name);
	}
	
	public String adder(){
		return "addTo" + StringHelpers.firstCharToUpper(feature.getName());
	}

	public String remover(){
		return "removeFrom" + StringHelpers.firstCharToUpper(feature.getName());
	}

	public String removeAll() {
		return "removeAllFrom" + StringHelpers.firstCharToUpper(feature.getName());
	}

	public String internalAdder(){
		return "z_internalAddTo" + StringHelpers.firstCharToUpper(feature.getName());
	}

	public String internalRemover(){
		return "z_internalRemoveFrom" + StringHelpers.firstCharToUpper(feature.getName());
	}
	
	/* SET OF OPERATIONS THAT RETURN TYPE INFO ON THIS STRUCTURAL FEATURE */	

	public OJPathName javaTypePath(){
		return featureTypeMap.javaTypePath();
	}

	public OJPathName javaObjectTypePath(){
		return featureTypeMap.javaObjectTypePath();
	}

	public OJPathName javaFacadeTypePath() {
		return featureTypeMap.javaFacadeTypePath();
	}
	
	public OJPathName javaDefaultTypePath() {
		return featureTypeMap.javaDefaultTypePath();
	}

	public String javaDefaultValue() {
		return featureTypeMap.javaDefaultValue();
	}

	public OJPathName javaBaseTypePath(){
		Check.pre("StructuralFeatureMap.javaBaseTypePath called for non-collection attribute or single-value association end", baseTypeMap != null );
		return baseTypeMap.javaTypePath();
	}

	public OJPathName javaBaseFacadeTypePath() {
		Check.pre("StructuralFeatureMap.javaBaseFacadeTypePath called for non-collection attribute or single-value association end", baseTypeMap != null );
		return baseTypeMap.javaFacadeTypePath();
	}
	
	public OJPathName javaBaseDefaultTypePath() {
		Check.pre("StructuralFeatureMap.javaBaseDefaultTypePath called for non-collection attribute or single-value association end", baseTypeMap != null );
		return baseTypeMap.javaDefaultTypePath();
	}

	public OJPathName javaBaseObjectTypePath() {
		Check.pre("StructuralFeatureMap.javaBaseObjectTypePath called for non-collection attribute or single-value association end", baseTypeMap != null );
		return baseTypeMap.javaObjectTypePath();
	}

	public String javaBaseDefaultValue() {
		// TODO check!!!
		Check.pre("StructuralFeatureMap.javaBaseDefaultValue called for non-collection attribute or single-value association end", baseTypeMap != null );
		return baseTypeMap.javaDefaultValue();
	}

	public String unmodifiableOper(){
		return featureTypeMap.javaUnmodifiableCollectionOper();
	}

	public OJPathName unmodifiableOperType(){
		return featureTypeMap.javaUnmodifiableCollectionOperType();
	}

	public String javaBaseFacadeDefaultValue() {
		// TODO check!!!
		Check.pre("StructuralFeatureMap.javaBaseFacadeDefaultValue called for non-collection attribute or single-value association end", baseTypeMap != null );
		return baseTypeMap.javaDefaultValue();
	}

	/* SET OF CONVENIENCE OPERATIONS */
	
	public String javaType(){
		return javaTypePath().getCollectionTypeName();
	}

	public String javaObjectType(){
		return javaObjectTypePath().getCollectionTypeName();
	}

	public String javaBaseType(){
		return javaBaseTypePath().getCollectionTypeName();
	}

	public String javaFacadeType() {
		return javaFacadeTypePath().getCollectionTypeName();
	}

	public String javaBaseFacadeType() {
		return javaBaseFacadeTypePath().getCollectionTypeName();
	}

	public String javaBaseDefaultType() {
		return javaBaseDefaultTypePath().getCollectionTypeName();
	}


}

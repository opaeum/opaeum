package nl.klasse.octopus.codegen.umlToJava.maps;

import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.StructuralFeature;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.java.metamodel.OJPathName;

public class StructuralFeatureMap extends PackageableElementMap {

	protected StructuralFeature feature;
	protected ClassifierMap featureTypeMap = null;
	protected ClassifierMap baseTypeMap = null;
	
	public StructuralFeatureMap(StructuralFeature feature) {
		super(feature);
		this.feature = feature;
		baseTypeMap = new ClassifierMap((Classifier) feature.getType());
		if(EmfPropertyUtil.isMany(feature)){
			featureTypeMap=new ClassifierMap((Classifier) feature.getType(),getCollectionKind(feature));
		}else{
			featureTypeMap=baseTypeMap;
		}

	}

	private CollectionKind getCollectionKind(StructuralFeature feature) {
		return null;
	}

	public StructuralFeature getFeature() {
		return feature;
	}

	public String umlName() {
		return feature.getName();
	}

	public boolean isUnique() {
		return feature.isUnique();
	}

	public boolean isStatic() {
		return feature.isStatic();
	}

	public boolean isOptional() {
		return feature.getLower() == 0;
	}

	public String umlType() {
		return featureTypeMap.umlType();
	}

	public boolean isCollection() {
		return featureTypeMap.isCollection();
	}

	public boolean isUmlPrimitive() {
		return featureTypeMap.isUmlPrimitive();
	}

	public boolean isJavaPrimitive() {
		return featureTypeMap.isJavaPrimitive();
	}

	public boolean elementTypeIsUmlPrimitive() {
		return baseTypeMap.isUmlPrimitive();
	}

	public boolean elementTypeIsJavaPrimitive() {
		return baseTypeMap.isJavaPrimitive();
	}


	public String fieldname() {
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

	public String adder() {
		return "addTo" + StringHelpers.firstCharToUpper(feature.getName());
	}

	public String remover() {
		return "removeFrom" + StringHelpers.firstCharToUpper(feature.getName());
	}

	public String removeAll() {
		return "removeAllFrom"
				+ StringHelpers.firstCharToUpper(feature.getName());
	}

	public String internalAdder() {
		return "z_internalAddTo"
				+ StringHelpers.firstCharToUpper(feature.getName());
	}

	public String internalRemover() {
		return "z_internalRemoveFrom"
				+ StringHelpers.firstCharToUpper(feature.getName());
	}

	/* SET OF OPERATIONS THAT RETURN TYPE INFO ON THIS STRUCTURAL FEATURE */

	public OJPathName javaTypePath() {
		return featureTypeMap.javaTypePath();
	}

	public OJPathName javaObjectTypePath() {
		return featureTypeMap.javaObjectTypePath();
	}

	public OJPathName javaFacadeTypePath() {
		return featureTypeMap.javaTypePath();
	}

	public OJPathName javaDefaultTypePath() {
		return featureTypeMap.javaDefaultTypePath();
	}

	public String javaDefaultValue() {
		return featureTypeMap.javaDefaultValue();
	}

	public OJPathName javaBaseTypePath() {
		Check.pre(
				"StructuralFeatureMap.javaBaseTypePath called for non-collection attribute or single-value association end",
				baseTypeMap != null);
		return baseTypeMap.javaTypePath();
	}

	public OJPathName javaBaseFacadeTypePath() {
		Check.pre(
				"StructuralFeatureMap.javaBaseFacadeTypePath called for non-collection attribute or single-value association end",
				baseTypeMap != null);
		return baseTypeMap.javaTypePath();
	}

	public OJPathName javaBaseDefaultTypePath() {
		Check.pre(
				"StructuralFeatureMap.javaBaseDefaultTypePath called for non-collection attribute or single-value association end",
				baseTypeMap != null);
		return baseTypeMap.javaDefaultTypePath();
	}

	public OJPathName javaBaseObjectTypePath() {
		Check.pre(
				"StructuralFeatureMap.javaBaseObjectTypePath called for non-collection attribute or single-value association end",
				baseTypeMap != null);
		return baseTypeMap.javaObjectTypePath();
	}

	public String javaBaseDefaultValue() {
		// TODO check!!!
		Check.pre(
				"StructuralFeatureMap.javaBaseDefaultValue called for non-collection attribute or single-value association end",
				baseTypeMap != null);
		return baseTypeMap.javaDefaultValue();
	}

	public OJPathName unmodifiableOperType() {
		return featureTypeMap.javaUnmodifiableCollectionOperType();
	}

	public String javaBaseFacadeDefaultValue() {
		// TODO check!!!
		Check.pre(
				"StructuralFeatureMap.javaBaseFacadeDefaultValue called for non-collection attribute or single-value association end",
				baseTypeMap != null);
		return baseTypeMap.javaDefaultValue();
	}



	public String javaType() {
		return javaTypePath().getCollectionTypeName();
	}

	public String javaObjectType() {
		return javaObjectTypePath().getCollectionTypeName();
	}

	public String javaBaseType() {
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

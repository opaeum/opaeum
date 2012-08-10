package nl.klasse.octopus.codegen.umlToJava.maps;

import nl.klasse.tools.common.StringHelpers;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuralFeature;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.name.NameConverter;

public class StructuralFeatureMap extends PackageableElementMap{
	protected StructuralFeature feature;
	protected ClassifierMap featureTypeMap = null;
	protected ClassifierMap baseTypeMap = null;
	public StructuralFeatureMap(OJUtil ojUtil,StructuralFeature feature){
		super(ojUtil, feature);
		Property property = (Property) feature;
		if(property.getAssociationEnd() != null){
			// qualifier - might have backing attribute
			Classifier c= (Classifier) EmfElementFinder.getContainer(property.getAssociationEnd());
			Property attribute = c.getAttribute(property.getName(),null);
			if(attribute!=null){
				property=attribute;
			}
		}
		this.feature = property;
		Classifier type = (Classifier) property.getType();
		if(type == null){
				type = ojUtil.getLibrary().getStringType();
		}
		baseTypeMap = ojUtil.buildClassifierMap(type);
		if(EmfPropertyUtil.isMany(feature)){
			featureTypeMap = ojUtil.buildClassifierMap(type, getCollectionKind(property));
		}else{
			featureTypeMap = baseTypeMap;
		}
	}
	public Classifier getBaseType(){
		return baseTypeMap.modelClass;
	}
	public OJPathName javaDefaultTypePath(){
		if(isMany()){
			OJPathName baseType = javaBaseDefaultTypePath();
			OJPathName copy = featureTypeMap.javaDefaultTypePath().getCopy();
			copy.addToElementTypes(baseType);
			return copy;
		}else{
			return featureTypeMap.javaDefaultTypePath();
		}
	}
	public OJPathName javaTypePath(){
		if(isMany()){
			OJPathName copy = featureTypeMap.javaTypePath().getCopy();
			copy.addToElementTypes(javaBaseTypePath());
			return copy;
		}else if(isJavaPrimitive()){
			return featureTypeMap.javaObjectTypePath();
		}else{
			return featureTypeMap.javaTypePath();
		}
	}
	private CollectionKind getCollectionKind(StructuralFeature feature){
		return super.getCollectionKind(feature);
	}
	public StructuralFeature getFeature(){
		return feature;
	}
	public String umlName(){
		return feature.getName();
	}
	public boolean isUnique(){
		return feature.isUnique();
	}
	public boolean isStatic(){
		return feature.isStatic();
	}
	public boolean isOptional(){
		return feature.getLower() == 0;
	}
	public String umlType(){
		return featureTypeMap.umlType();
	}
	public boolean isCollection(){
		return featureTypeMap.isCollection();
	}
	public boolean isUmlPrimitive(){
		return featureTypeMap.isUmlPrimitive();
	}
	public boolean isJavaPrimitive(){
		return featureTypeMap.isJavaPrimitive();
	}
	public boolean elementTypeIsUmlPrimitive(){
		return baseTypeMap.isUmlPrimitive();
	}
	public boolean elementTypeIsJavaPrimitive(){
		return baseTypeMap.isJavaPrimitive();
	}
	public String fieldname(){
		String asIs = NameConverter.decapitalize(getProperty().getName());
		if(OJUtil.isJavaKeyword(asIs)){
			asIs = "_" + asIs;
		}
		return asIs;
	}
	public String getter(){
		String name = feature.getName();
		if(name.indexOf("is") == 0){
			return name;
		}
		return "get" + StringHelpers.firstCharToUpper(name);
	}
	public String setter(){
		String name = feature.getName();
		if(name.indexOf("is") == 0){
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
	public String removeAll(){
		return "removeAllFrom" + StringHelpers.firstCharToUpper(feature.getName());
	}
	public String internalAdder(){
		return "z_internalAddTo" + StringHelpers.firstCharToUpper(feature.getName());
	}
	public String internalRemover(){
		return "z_internalRemoveFrom" + StringHelpers.firstCharToUpper(feature.getName());
	}
	public OJPathName javaObjectTypePath(){
		return featureTypeMap.javaObjectTypePath();
	}
	public OJPathName javaFacadeTypePath(){
		return featureTypeMap.javaTypePath();
	}
	public String javaDefaultValue(){
		return featureTypeMap.javaDefaultValue();
	}
	public OJPathName javaBaseFacadeTypePath(){
		return baseTypeMap.javaTypePath();
	}
	public OJPathName javaBaseDefaultTypePath(){
		return baseTypeMap.javaDefaultTypePath();
	}
	public OJPathName javaBaseObjectTypePath(){
		return baseTypeMap.javaObjectTypePath();
	}
	public String javaBaseDefaultValue(){
		return baseTypeMap.javaDefaultValue();
	}
	public OJPathName unmodifiableOperType(){
		return featureTypeMap.javaUnmodifiableCollectionOperType();
	}
	public String javaBaseFacadeDefaultValue(){
		return baseTypeMap.javaDefaultValue();
	}
	public String javaType(){
		return javaTypePath().getCollectionTypeName();
	}
	public String javaObjectType(){
		return javaObjectTypePath().getCollectionTypeName();
	}
	public String javaBaseType(){
		return javaBaseTypePath().getCollectionTypeName();
	}
	public String javaFacadeType(){
		return javaFacadeTypePath().getCollectionTypeName();
	}
	public String javaBaseFacadeType(){
		return javaBaseFacadeTypePath().getCollectionTypeName();
	}
	public String javaBaseDefaultType(){
		return javaBaseDefaultTypePath().getCollectionTypeName();
	}
	public boolean isOne(){
		return !isMany();
	}
	public boolean isOneToMany(){
		return otherEndIsOne() && isMany();
	}
	public boolean isManyToMany(){
		return !otherEndIsOne() && isMany();
	}
	public String allAdder(){
		return "addAllTo" + StringHelpers.firstCharToUpper(feature.getName());
	}
	public String clearer(){
		return "clear" + StringHelpers.firstCharToUpper(feature.getName());
	}
	public OJPathName javaBaseTypePath(){
		if(baseTypeMap.isJavaPrimitive()){
			return baseTypeMap.javaObjectTypePath();
		}else{
			return baseTypeMap.javaTypePath();
		}
	}
	public String qualifierProperty(){
		return "z_keyOf" + NameConverter.capitalize(getProperty().getName()) + "On" + ((Classifier) getProperty().getOwner()).getName();
	}
	public String qualifierPropertySetter(){
		return "setZ_keyOf" + NameConverter.capitalize(getProperty().getName()) + "On" + ((Classifier) getProperty().getOwner()).getName();
	}
	public NameWrapper getPersistentName(){
		return PersistentNameUtil.getPersistentName(feature);
	}
	public boolean isManyToOne(){
		return !otherEndIsOne() && isOne();
	}
	public boolean isOneToOne(){
		return otherEndIsOne() && isOne();
	}
	public boolean isMany(){
		if(feature instanceof Property){
			Property property = (Property) feature;
			int qualifierCount = property.getQualifiers().size();
			return property.isMultivalued() || qualifierCount > 0;
		}else{
			return feature.isMultivalued();
		}
	}
	/**
	 * IF the other end is not navigable or there is no other end, an assumption of otherEnd=Many is made
	 * 
	 * @return
	 */
	protected boolean otherEndIsOne(){
		if(getProperty().getOtherEnd() != null){
			Property otherEnd = getProperty().getOtherEnd();
			return otherEnd.isNavigable() && otherEnd.getUpper() == 1 && otherEnd.getQualifiers().size() == 0;
		}else{
			return false;
		}
	}
	public Property getProperty(){
		return((Property) feature);
	}
	public Classifier getDefiningClassifier(){
		return (Classifier) EmfElementFinder.getContainer(feature);
	}
	public boolean isInverse(){
		return EmfPropertyUtil.isInverse(getProperty());
	}
}

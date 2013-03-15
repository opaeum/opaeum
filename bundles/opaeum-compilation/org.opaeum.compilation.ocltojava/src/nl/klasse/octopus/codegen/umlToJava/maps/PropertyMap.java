package nl.klasse.octopus.codegen.umlToJava.maps;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuralFeature;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.eclipse.emulated.AssociationClassToEnd;
import org.opaeum.eclipse.emulated.EndToAssociationClass;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.name.NameConverter;

public final class PropertyMap extends PackageableElementMap{
	private Property property;
	protected ClassifierMap actualTypeMap = null;
	protected ClassifierMap baseTypeMap = null;
	public PropertyMap(OJUtil ojUtil,Property property){
		super(ojUtil, property);
		if(property.getAssociationEnd() != null){
			// qualifier - might have backing attribute
			Classifier c = (Classifier) EmfElementFinder.getContainer(property.getAssociationEnd());
			Property attribute = c.getAttribute(property.getName(), null);
			if(attribute != null){
				property = attribute;
			}
		}
		this.setProperty(property);
		Classifier type = (Classifier) property.getType();
		if(type == null){
			type = ojUtil.getLibrary().getStringType();
		}
		baseTypeMap = ojUtil.buildClassifierMap(type);
		if(EmfPropertyUtil.isMany(property)){
			actualTypeMap = ojUtil.buildClassifierMap(type, getCollectionKind(property));
		}else{
			actualTypeMap = baseTypeMap;
		}
	}
	public Classifier getBaseType(){
		return baseTypeMap.elementType;
	}
	public OJPathName javaDefaultTypePath(){
		if(isMany()){
			OJPathName baseType = javaBaseDefaultTypePath();
			OJPathName copy = actualTypeMap.javaDefaultTypePath().getCopy();
			copy.addToElementTypes(baseType);
			return copy;
		}else{
			return actualTypeMap.javaDefaultTypePath();
		}
	}
	public OJPathName javaTypePath(){
		if(isMany()){
			OJPathName copy = actualTypeMap.javaTypePath().getCopy();
			OJPathName javaBaseTypePath = javaBaseTypePath();
			copy.addToElementTypes(javaBaseTypePath);
			if(property.isDerivedUnion()){
				copy.markAsExtendingElement(javaBaseTypePath);
			}
			return copy;
		}else if(isJavaPrimitive()){
			return actualTypeMap.javaObjectTypePath();
		}else{
			return actualTypeMap.javaTypePath();
		}
	}
	private CollectionKind getCollectionKind(StructuralFeature feature){
		return super.getCollectionKind(feature);
	}
	public String umlName(){
		return property.getName();
	}
	public boolean isUnique(){
		return property.isUnique();
	}
	public boolean isStatic(){
		return property.isStatic();
	}
	public boolean isOptional(){
		return property.getLower() == 0;
	}
	public boolean isCollection(){
		return actualTypeMap.isCollection();
	}
	public boolean isUmlPrimitive(){
		return actualTypeMap.isUmlPrimitive();
	}
	public boolean isJavaPrimitive(){
		return actualTypeMap.isJavaPrimitive();
	}
	public boolean elementTypeIsUmlPrimitive(){
		return baseTypeMap.isUmlPrimitive();
	}
	public boolean elementTypeIsJavaPrimitive(){
		return baseTypeMap.isJavaPrimitive();
	}
	public String fieldname(){
		String asIs = NameConverter.decapitalize(getJavaCompatibleName());
		if(OJUtil.isJavaKeyword(asIs)){
			asIs = "_" + asIs;
		}
		return asIs;
	}
	private String getJavaCompatibleName(){
		return NameConverter.toJavaVariableName(property.getName());
	}
	public String getter(){
		String name = getJavaCompatibleName();
		if(name.indexOf("is") == 0){
			return name;
		}
		return "get" + NameConverter.capitalize(name);
	}
	public String setter(){
		String name = getJavaCompatibleName();
		if(name.indexOf("is") == 0){
			name = name.substring(2, name.length());
		}
		return "set" + NameConverter.capitalize(name);
	}
	public String adder(){
		return "addTo" + NameConverter.capitalize(getJavaCompatibleName());
	}
	public String remover(){
		return "removeFrom" + NameConverter.capitalize(getJavaCompatibleName());
	}
	public String removeAll(){
		return "removeAllFrom" + NameConverter.capitalize(getJavaCompatibleName());
	}
	public String internalAdder(){
		return "z_internalAddTo" + NameConverter.capitalize(getJavaCompatibleName());
	}
	public String internalRemover(){
		return "z_internalRemoveFrom" + NameConverter.capitalize(getJavaCompatibleName());
	}
	public OJPathName javaObjectTypePath(){
		return actualTypeMap.javaObjectTypePath();
	}
	public OJPathName javaFacadeTypePath(){
		return actualTypeMap.javaTypePath();
	}
	public String javaDefaultValue(){
		return actualTypeMap.javaDefaultValue();
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
		return actualTypeMap.javaUnmodifiableCollectionOperType();
	}
	public String javaBaseFacadeDefaultValue(){
		return baseTypeMap.javaDefaultValue();
	}
	public String javaType(){
		return javaTypePath().getTypeNameWithTypeArguments();
	}
	public String javaObjectType(){
		return javaObjectTypePath().getTypeNameWithTypeArguments();
	}
	public String javaBaseType(){
		return javaBaseTypePath().getTypeNameWithTypeArguments();
	}
	public String javaFacadeType(){
		return javaFacadeTypePath().getTypeNameWithTypeArguments();
	}
	public String javaBaseFacadeType(){
		return javaBaseFacadeTypePath().getTypeNameWithTypeArguments();
	}
	public String javaBaseDefaultType(){
		return javaBaseDefaultTypePath().getTypeNameWithTypeArguments();
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
		return "addAllTo" + NameConverter.capitalize(getJavaCompatibleName());
	}
	public String clearer(){
		return "clear" + NameConverter.capitalize(getJavaCompatibleName());
	}
	public OJPathName javaBaseTypePath(){
		if(baseTypeMap.isJavaPrimitive()){
			return baseTypeMap.javaObjectTypePath();
		}else{
			return baseTypeMap.javaTypePath();
		}
	}
	public String qualifierProperty(){
		Property property = (Property) (this.property instanceof EndToAssociationClass ? ((EndToAssociationClass) this.property).getOriginalProperty()
				: this.property);
		Classifier owner = EmfPropertyUtil.getOwningClassifier(property);
		return "z_keyOf" + NameConverter.capitalize(NameConverter.toJavaVariableName(property.getName())) + "On" + owner.getName();
	}
	public String qualifierPropertySetter(){
		Property property = (Property) (this.property instanceof EndToAssociationClass ? ((EndToAssociationClass) this.property).getOriginalProperty()
				: this.property);
		Classifier owner = EmfPropertyUtil.getOwningClassifier(property);
		return "setZ_keyOf" + NameConverter.capitalize(NameConverter.toJavaVariableName(property.getName())) + "On" + (owner).getName();
	}
	public NameWrapper getPersistentName(){
		return PersistentNameUtil.getPersistentName(property);
	}
	public boolean isManyToOne(){
		return !otherEndIsOne() && isOne();
	}
	public boolean isOneToOne(){
		return otherEndIsOne() && isOne();
	}
	public boolean isMany(){
		if(property instanceof Property){
			int qualifierCount = property.getQualifiers().size();
			return property.isMultivalued() || qualifierCount > 0;
		}else{
			return property.isMultivalued();
		}
	}
	/**
	 * IF the other end is not navigable or there is no other end, an assumption of otherEnd=Many is made
	 * 
	 * @return
	 */
	protected boolean otherEndIsOne(){
		if(property.getOtherEnd() != null){
			Property otherEnd = property.getOtherEnd();
			return otherEnd.getUpper() == 1 && otherEnd.getQualifiers().size() == 0;
		}else{
			return false;
		}
	}
	public Property getProperty(){
		return (Property) property;
	}
	public Classifier getDefiningClassifier(){
		return (Classifier) EmfElementFinder.getContainer(property);
	}
	public boolean isInverse(){
		return EmfPropertyUtil.isInverse(property);
	}
	public boolean isQualifier(){
		return EmfPropertyUtil.isQualifier(property);
	}
	protected void setProperty(Property property){
		this.property = property;
	}
	public List<OJPathName> qualifiedArgumentsForWriter(){
		List<OJPathName> result = qualifiedArgsForReader();
		result.add(javaBaseTypePath());
		return result;
	}
	public List<OJPathName> qualifiedArgsForReader(){
		List<OJPathName> result = new ArrayList<OJPathName>();
		for(Property q:property.getQualifiers()){
			Property bp = EmfPropertyUtil.getBackingPropertyForQualifier(q);
			if(bp == null){
				bp = q;
			}
			if(bp.getType() == null){
				result.add(StdlibMap.javaStringType);
			}else{
				result.add(ojUtil.classifierPathname(bp.getType()));
			}
		}
		return result;
	}
	public boolean isEndToAssociationClass(){
		return property instanceof EndToAssociationClass;
	}
	public AssociationClassEndMap getAssocationClassMap(){
		if(isEndToAssociationClass()){
			return ojUtil.buildAssociationClassEndMap(((EndToAssociationClass) property).getOriginalProperty());
		}else if(isAssociationClassToEnd()){
			return ojUtil.buildAssociationClassEndMap(((AssociationClassToEnd) property).getOriginalProperty());
		}
		return ojUtil.buildAssociationClassEndMap(property);
	}
	public boolean isAssociationClassToEnd(){
		return property instanceof AssociationClassToEnd;
	}
}

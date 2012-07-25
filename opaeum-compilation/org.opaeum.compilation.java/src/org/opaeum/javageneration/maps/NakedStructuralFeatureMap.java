package org.opaeum.javageneration.maps;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.name.NameConverter;

public class NakedStructuralFeatureMap extends StructuralFeatureMap{
	public String fieldname(){
		String asIs= NameConverter.decapitalize(getProperty().getName());
		if(OJUtil.isJavaKeyword(asIs)){
			asIs = "_" + asIs;
		}
		return asIs;
	}
	public NakedStructuralFeatureMap(Property feature){
		super(feature);
		baseTypeMap = OJUtil.buildClassifierMap((Classifier) feature.getType(),(CollectionKind)null);
		featureTypeMap = OJUtil.buildClassifierMap((Classifier) feature.getType(),feature);
	}
	public String qualifierProperty(){
		return "z_keyOf" + NameConverter.capitalize(getProperty().getName()) + "On" +((Classifier)getProperty().getOwner()).getName();
	}
	public String qualifierPropertySetter(){
		return "setZ_keyOf" + NameConverter.capitalize(getProperty().getName()) + "On" +((Classifier)getProperty().getOwner()).getName();
	}
	public NameWrapper getPersistentName(){
		return PersistentNameUtil.getPersistentName(feature);
	}

	@Override
	public OJPathName javaTypePath(){
		if(isMany()){
			OJPathName copy = super.javaTypePath().getCopy();
			copy.addToElementTypes(javaBaseTypePath());
			return copy;
		}else if(isJavaPrimitive()){
			return featureTypeMap.javaObjectTypePath();
		}else{
			return super.javaTypePath();
		}
	}
	@Override
	public OJPathName javaDefaultTypePath(){
		if(isMany()){
			OJPathName baseType = super.javaBaseDefaultTypePath();
			OJPathName copy = super.javaDefaultTypePath().getCopy();
			copy.addToElementTypes(baseType);
			return copy;
		}else{
			return super.javaDefaultTypePath();
		}
	}
	@Override
	public OJPathName javaBaseTypePath(){
		if(baseTypeMap.isJavaPrimitive()){
			return baseTypeMap.javaObjectTypePath();
		}else{
			return baseTypeMap.javaTypePath();
		}
	}
	public String allAdder(){
		return "addAllTo" + StringHelpers.firstCharToUpper(feature.getName());
	}
	public String clearer(){
		return "clear" + StringHelpers.firstCharToUpper(feature.getName());
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
	public boolean isOne(){
		return !isMany();
	}
	public boolean isOneToMany(){
		return otherEndIsOne() && isMany();
	}
	public boolean isManyToMany(){
		return !otherEndIsOne() && isMany();
	}
	public boolean isManyToOne(){
		return !otherEndIsOne() && isOne();
	}
	public boolean isOneToOne(){
		return otherEndIsOne() && isOne();
	}
	/**
	 * IF the other end is not navigable or there is no other end, an assumption of otherEnd=Many is made
	 * 
	 * @return
	 */
	protected boolean otherEndIsOne(){
		if(getProperty().getOtherEnd() != null){
			Property otherEnd = getProperty().getOtherEnd();
			return otherEnd.isNavigable() && otherEnd.getUpper()==1 && otherEnd.getQualifiers().size() == 0;
		}else{
			return false;
		}
	}
	public Property getProperty(){
		return((Property) feature);
	}
	public Classifier getDefiningClassifier(){
		return (Classifier)EmfElementFinder.getContainer(feature);
	}
}

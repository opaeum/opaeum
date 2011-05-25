package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.javageneration.auditing.AuditImplementationStep;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.tools.common.StringHelpers;

import org.nakeduml.java.metamodel.OJPathName;


public class NakedStructuralFeatureMap extends StructuralFeatureMap{

	public NakedStructuralFeatureMap(INakedProperty feature){
		super(feature);
		baseTypeMap = new NakedClassifierMap( feature.getNakedBaseType());
		featureTypeMap = new NakedClassifierMap(feature.getType());
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
	public OJPathName javaAuditTypePath(){
		if (getProperty().getBaseType() instanceof INakedEnumeration) {
			return javaTypePath();
		} else {
			if(isMany()){
				if(!isJavaPrimitive() && !javaBaseType().equals("String") && !javaBaseType().equals("Integer")) {
					OJPathName copy = super.javaTypePath().getCopy();
					copy.addToElementTypes(new OJPathName(javaBaseTypePath().toJavaString()+AuditImplementationStep.AUDIT));
					return copy;
				} else {
					return javaTypePath();
				}
				//TODO this string jol must be wrong
			}else if(isJavaPrimitive() || javaBaseType().equals("String")){
				return featureTypeMap.javaObjectTypePath();
			}else{
				return new OJPathName(super.javaTypePath().toJavaString()+AuditImplementationStep.AUDIT);
			}
		}
	}

	//TODO this string jol must be wrong
	public OJPathName javaAuditBaseTypePath(){
		if (javaBaseType().equals("String")) {
			return new OJPathName("String");
		} else if(baseTypeMap.isJavaPrimitive()){
			return baseTypeMap.javaObjectTypePath();
		}else{
			return new OJPathName(baseTypeMap.javaTypePath().toJavaString()+AuditImplementationStep.AUDIT);
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
	
	public String javaAuditDefaultValue() {
		OJPathName baseType = javaBaseTypePath();
		String javaDefaultValue = featureTypeMap.javaDefaultValue();
		return javaDefaultValue.replace(baseType.getLast(), baseType.getLast()+AuditImplementationStep.AUDIT);
	}

	public OJPathName javaAuditDefaultTypePath(){
		if(isMany()){
			OJPathName baseType = super.javaBaseDefaultTypePath();
			OJPathName auditBaseType = new OJPathName(baseType.toJavaString()+AuditImplementationStep.AUDIT);
			OJPathName copy = super.javaDefaultTypePath().getCopy();
			copy.addToElementTypes(auditBaseType);
			return copy;
		}else{
			return new OJPathName(super.javaDefaultTypePath().toJavaString()+AuditImplementationStep.AUDIT);
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
		if(feature instanceof INakedProperty){
			INakedProperty property = (INakedProperty) feature;
			int qualifierCount = property.getQualifiers().size();
			return property.getNakedMultiplicity().isMany() || qualifierCount > 0;
		}else{
			return feature.getMultiplicity().getUpper() > 1;
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
			INakedProperty otherEnd = getProperty().getOtherEnd();
			return otherEnd.isNavigable() && otherEnd.getNakedMultiplicity().isOne() && otherEnd.getQualifiers().size() == 0;
		}else{
			return false;
		}
	}
	public INakedProperty getProperty(){
		return((INakedProperty) feature);
	}
}

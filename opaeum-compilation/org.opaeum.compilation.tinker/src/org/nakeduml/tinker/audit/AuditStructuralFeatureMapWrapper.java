package org.nakeduml.tinker.audit;

import org.eclipse.uml2.uml.INakedEnumeration;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;

public class AuditStructuralFeatureMapWrapper extends NakedStructuralFeatureMap {

	public AuditStructuralFeatureMapWrapper(NakedStructuralFeatureMap map) {
		super(map.getProperty());
	}
	
	public OJPathName javaTypePath(){
		if (getProperty().getBaseType() instanceof INakedEnumeration) {
			return super.javaTypePath();
		} else {
			if(isMany()){
				if(!isJavaPrimitive() && !javaBaseType().equals("String") && !javaBaseType().equals("Integer")) {
					OJPathName copy = super.javaTypePath().getCopy();
					copy.addToElementTypes(new OJPathName(super.javaBaseTypePath().toJavaString()+TinkerAuditGenerationUtil.AUDIT));
					return copy;
				} else {
					return super.javaTypePath();
				}
				//TODO this string jol must be wrong
			}else if(isJavaPrimitive() || isUmlPrimitive()){
				return featureTypeMap.javaObjectTypePath();
			}else{
				return new OJPathName(super.javaTypePath().toJavaString()+TinkerAuditGenerationUtil.AUDIT);
			}
		}
	}	
	
	//TODO this string jol must be wrong
	public OJPathName javaBaseTypePath(){
		if (super.javaBaseTypePath().getCollectionTypeName().equals("String")) {
			return new OJPathName("String");
		} else if(baseTypeMap.isJavaPrimitive() || baseTypeMap.isUmlPrimitive()){
			return baseTypeMap.javaObjectTypePath();
		}else{
			return new OJPathName(baseTypeMap.javaTypePath().toJavaString()+TinkerAuditGenerationUtil.AUDIT);
		}
	}	
	
	public OJPathName javaDefaultTypePath(){
		if(isMany()){
			OJPathName baseType = super.javaBaseDefaultTypePath();
			OJPathName auditBaseType = new OJPathName(baseType.toJavaString()+TinkerAuditGenerationUtil.AUDIT);
			OJPathName copy = super.javaDefaultTypePath().getCopy();
			copy.addToElementTypes(auditBaseType);
			return copy;
		}else{
			return new OJPathName(super.javaDefaultTypePath().toJavaString()+TinkerAuditGenerationUtil.AUDIT);
		}
	}
	
}

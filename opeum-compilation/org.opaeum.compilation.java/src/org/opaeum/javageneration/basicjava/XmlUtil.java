package org.opeum.javageneration.basicjava;

import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedHelper;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSimpleType;
import org.opeum.metamodel.core.internal.AssociationClassToEnd;
import org.opeum.metamodel.core.internal.EndToAssociationClass;

public class XmlUtil{
	public static boolean isXmlAttribute(INakedProperty f){
		return (f.getNakedBaseType() instanceof INakedSimpleType || f.getNakedBaseType() instanceof INakedEnumeration) && !(OJUtil.isBuiltIn(f) || f.isDerived());
	}
	public static boolean isXmlSubElement(INakedProperty f){
		if(isXmlElement(f)){
			if(f instanceof EndToAssociationClass){
				return ((EndToAssociationClass) f).getIndexInAssocation() == 0;
			}else{
				return f.isComposite();
			}
		}else{
			return false;
		}
	}
	private static boolean isContainmentFeature(INakedProperty f){
		return f.getOtherEnd() != null && f.getOtherEnd().isComposite() && !(f instanceof AssociationClassToEnd);
	}
	private static boolean isPersistent(INakedClassifier nakedBaseType){
		return nakedBaseType instanceof INakedComplexStructure && ((INakedComplexStructure) nakedBaseType).isPersistent();
	}
	public static boolean isXmlReference(INakedProperty f){
		if(isXmlElement(f)){
			if(f instanceof EndToAssociationClass){
				return ((EndToAssociationClass) f).getIndexInAssocation() == 1;
			}else{
				return !(f.isComposite() || f.isInverse());
			}
		}else{
			return false;
		}
	}
	private static boolean isXmlElement(INakedProperty f){
		boolean realizedThroughAssocationClass = OJUtil.hasOJClass((INakedClassifier) f.getAssociation());
		boolean classIsElement = isPersistent(f.getNakedBaseType())
				|| (f.getNakedBaseType() instanceof INakedInterface && !(f.getNakedBaseType() instanceof INakedHelper));
		return classIsElement && !(f.isDerived() || isContainmentFeature(f) || realizedThroughAssocationClass);
	}
}

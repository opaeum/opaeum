package org.opaeum.javageneration.basicjava;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.javageneration.util.OJUtil;

public class XmlUtil{
	public static boolean isXmlAttribute(Property f){
		return (EmfClassifierUtil.isSimpleType(f.getType()) || f.getType() instanceof Enumeration) && !(OJUtil.isBuiltIn(f) || f.isDerived());
	}
	public static boolean isXmlSubElement(Property f){
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
	private static boolean isContainmentFeature(Property f){
		return f.getOtherEnd() != null && f.getOtherEnd().isComposite() && !(f instanceof AssociationClassToEnd);
	}
	private static boolean isPersistent(Type nakedBaseType){
		return nakedBaseType instanceof ComplexStructure && ((ComplexStructure) nakedBaseType).isPersistent();
	}
	public static boolean isXmlReference(Property f){
		if(isXmlElement(f)){
			if(f instanceof EndToAssociationClass){
				return ((EndToAssociationClass) f).getIndexInAssocation() == 1;
			}else{
				return !(f.isComposite() || EmfPropertyUtil.isInverse( f));
			}
		}else{
			return false;
		}
	}
	private static boolean isXmlElement(Property f){
		boolean realizedThroughAssocationClass = OJUtil.hasOJClass((Classifier) f.getAssociation());
		boolean classIsElement = isPersistent(f.getType()) || (f.getType() instanceof Interface && !(EmfClassifierUtil.isHelper(f.getType())));
		return classIsElement && !(f.isDerived() || isContainmentFeature(f) || realizedThroughAssocationClass);
	}
}

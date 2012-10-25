package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.AssociationClassToEnd;
import org.opaeum.eclipse.emulated.EndToAssociationClass;
import org.opaeum.javageneration.util.OJUtil;

public class XmlUtil{
	public static boolean isXmlAttribute(PropertyMap map){
		return (EmfClassifierUtil.isSimpleType(map.getBaseType()) || map.getBaseType() instanceof Enumeration)
				&& !(OJUtil.isBuiltIn(map.getProperty()) || EmfPropertyUtil.isDerived(map.getProperty()));
	}
	public static boolean isXmlSubElement(PropertyMap map){
		if(isXmlElement(map)){
			if(map.getProperty() instanceof EndToAssociationClass){
				return ((EndToAssociationClass) map.getProperty()).getIndexInAssocation() == 0;
			}else{
				return map.getProperty().isComposite();
			}
		}else{
			return false;
		}
	}
	private static boolean isContainmentFeature(Property f){
		return f.getOtherEnd() != null && f.getOtherEnd().isComposite() && !(f instanceof AssociationClassToEnd);
	}
	private static boolean isPersistent(Type nakedBaseType){
		return EmfClassifierUtil.isComplexStructure(nakedBaseType) && EmfClassifierUtil.isPersistent(nakedBaseType);
	}
	public static boolean isXmlReference(PropertyMap map){
		if(isXmlElement(map)){
			Property f = map.getProperty();
			if(f instanceof EndToAssociationClass){
				return ((EndToAssociationClass) f).getIndexInAssocation() == 1;
			}else{
				return !(f.isComposite() || EmfPropertyUtil.isInverse(f));
			}
		}else{
			return false;
		}
	}
	private static boolean isXmlElement(PropertyMap map){
		Property f = map.getProperty();
		boolean realizedThroughAssocationClass = EmfAssociationUtil.isClass(f.getAssociation());
		boolean classIsElement = isPersistent(map.getBaseType())
				|| (map.getBaseType() instanceof Interface && !(EmfClassifierUtil.isHelper(map.getBaseType())));
		return classIsElement && !(EmfPropertyUtil.isDerived( f) || isContainmentFeature(f) || realizedThroughAssocationClass);
	}
}

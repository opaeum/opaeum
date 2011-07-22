package net.sf.nakeduml.javageneration;

import java.util.Collection;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.expressions.internal.types.PathName;

import org.nakeduml.java.metamodel.OJElement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedElement;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;

public class StereotypeAnnotator extends AbstractJavaProducingVisitor {
	protected void annotate(INakedElement umlElement, OJAnnotatedClass ojClass, String stereotypeName, OJElement javaElement) {
		if (umlElement.hasStereotype(stereotypeName)) {
			INakedInstanceSpecification is = umlElement.getStereotype(stereotypeName);
			applyStereotypeAsAnnotation(javaElement, is);
		}
	}

	protected void applyStereotypesAsAnnotations(INakedElement umlElement, OJElement javaElement) {
		applyStereotypesAsAnnotations(javaElement, umlElement.getStereotypes());
	}

	protected void applyStereotypesAsAnnotations(OJElement javaElement,Collection<? extends INakedInstanceSpecification> stereotypes){
		for (INakedInstanceSpecification is : stereotypes) {
			if (isBuiltIn(is.getClassifier())) {
				applyStereotypeAsAnnotation(javaElement, is);
			}
		}
	}

	private void applyStereotypeAsAnnotation(OJElement javaElement, INakedInstanceSpecification stereotypeApplication) {
		OJAnnotationValue an = buildAnnotation(stereotypeApplication);
		putAnnotation(javaElement, an);
	}

	private OJAnnotationValue buildAnnotation(INakedInstanceSpecification stereotypeApplication) {
		ClassifierMap map = new NakedClassifierMap(stereotypeApplication.getClassifier());
		OJAnnotationValue an = new OJAnnotationValue(map.javaTypePath());
		for (INakedSlot slot : stereotypeApplication.getSlots()) {
			NakedStructuralFeatureMap sfm = new NakedStructuralFeatureMap(slot.getDefiningFeature());
			OJAnnotationAttributeValue aa = new OJAnnotationAttributeValue(sfm.umlName());
			an.putAttribute(aa);
			if (slot.getValues().isEmpty() && sfm.isOne()) {
				// TODO rethink this
				// 1. initialValue caused duplicate elements ????
				// 2. THere might not be a slot for the feature
				if (slot.getDefiningFeature().getInitialValue() != null) {
					addValue(stereotypeApplication, aa, slot, slot.getDefiningFeature().getInitialValue());
				} else {
					if (sfm.javaType().endsWith("int")) {
						aa.addNumberValue(new Integer(0));
					} else if (sfm.javaType().endsWith("float")) {
						aa.addNumberValue(new Float(0));
					} else if (sfm.javaType().endsWith("boolean")) {
						aa.addBooleanValue(Boolean.FALSE);
					} else {
						aa.addStringValue("");
					}
				}
			} else {
				for (INakedValueSpecification vs : slot.getValues()) {
					addValue(stereotypeApplication, aa, slot,vs);
				}
			}
		}
		return an;
	}

	private void putAnnotation(OJElement javaElement, OJAnnotationValue an) {
		if (javaElement instanceof OJAnnotatedElement) {
			((OJAnnotatedElement) javaElement).putAnnotation(an);
		}
	}

	private void addValue(INakedInstanceSpecification is, OJAnnotationAttributeValue aa, INakedSlot slot, INakedValueSpecification vs) {
		if (vs.getValue() instanceof Boolean) {
			aa.addBooleanValue(vs.booleanValue());
		} else if (vs.getValue() instanceof Number) {
			aa.addNumberValue((Number) vs.getValue());
		} else if (vs.getValue() instanceof String) {
			aa.addStringValue((String) vs.getValue());
		} else if (vs.getValue() instanceof INakedEnumerationLiteral) {
			INakedClassifier stereotype = is.getClassifier();
			INakedEnumerationLiteral l = (INakedEnumerationLiteral) vs.getValue();
			INakedEnumeration en = (INakedEnumeration) l.getEnumeration();
			if (slot.getDefiningFeature().getBaseType() instanceof INakedPrimitiveType) {
				//String
				aa.addStringValue(l.getName());
			} else {
				ClassifierMap ecm = new NakedClassifierMap(en);
				OJEnumValue ev = new OJEnumValue(ecm.javaTypePath(), l.getName().toUpperCase());
				aa.addEnumValue(ev);
			}
		} else if (vs.getValue() instanceof INakedClassifier) {
			
			OJPathName path = new OJPathName( ((INakedClassifier) vs.getValue()).getMappingInfo().getQualifiedJavaName() );
			aa.addClassValue(path);
			
		} else if (vs.getValue() instanceof INakedInstanceSpecification) {
			aa.addAnnotationValue(buildAnnotation((INakedInstanceSpecification) vs.getValue()));
		} else if (vs.getValue() instanceof INakedElement) {
			aa.addStringValue(((INakedElement) vs.getValue()).getName());
		} else {
			System.out.println("unknow value type:" + vs.getValue());
		}
	}

	private boolean isBuiltIn(INakedClassifier stereotype) {
		PathName pn = getPathNameInModel(stereotype);
		boolean result = workspace.getNakedUmlLibrary().getTypeMap().containsKey(pn.toString());
		return result;
	}

}

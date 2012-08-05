package org.opaeum.javageneration;

import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralReal;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedElement;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;

public class StereotypeAnnotator extends AbstractJavaProducingVisitor{

	protected void annotate(Element umlElement,OJAnnotatedClass ojClass,String stereotypeName,OJElement javaElement){
		if(StereotypesHelper.hasStereotype(umlElement, stereotypeName)){
			if(javaElement instanceof OJAnnotatedElement){
				applyStereotypeAsAnnotation(umlElement, (OJAnnotatedElement) javaElement,
						StereotypesHelper.getStereotype(umlElement, stereotypeName));
			}
		}
	}
	private void applyStereotypeAsAnnotation(Element umlElement,OJAnnotatedElement javaElement,Stereotype stereotypeApplication){
		OJAnnotationValue an = buildAnnotation(umlElement, stereotypeApplication);
		putAnnotation(javaElement, an);
	}
	private OJAnnotationValue buildAnnotation(Element element,Stereotype stereotype){
		ClassifierMap map = ojUtil.buildClassifierMap(stereotype,(CollectionKind) null);
		OJAnnotationValue an = new OJAnnotationValue(map.javaTypePath());
		for(Property property:stereotype.getAllAttributes()){
			StructuralFeatureMap sfm = ojUtil.buildStructuralFeatureMap(property);
			OJAnnotationAttributeValue aa = new OJAnnotationAttributeValue(sfm.umlName());
			an.putAttribute(aa);
			Object o = element.getValue(stereotype, property.getName());
			if(o == null && sfm.isOne()){
				if(property.getDefaultValue() != null){
					addSpecifiedValue(aa, property.getDefaultValue());
				}else{
					if(sfm.javaType().endsWith("int")){
						aa.addNumberValue(new Integer(0));
					}else if(sfm.javaType().endsWith("float")){
						aa.addNumberValue(new Float(0));
					}else if(sfm.javaType().endsWith("boolean")){
						aa.addBooleanValue(Boolean.FALSE);
					}else{
						aa.addStringValue("");
					}
				}
			}else{
				if(o instanceof Collection){
					Collection<?> coll = (Collection<?>) o;
					for(Object object:coll){
						addValue(aa, object);
					}
				}else{
					addValue(aa,  o);
				}
			}
		}
		return an;
	}
	private void addValue(OJAnnotationAttributeValue aa,Object object){
		if(object instanceof Boolean){
			aa.addBooleanValue((Boolean) object);
		}else if(object instanceof Number){
			aa.addNumberValue((Number) object);
		}else if(object instanceof String){
			aa.addStringValue((String) object);
		}else if(object instanceof EnumerationLiteral){
				EnumerationLiteral l = (EnumerationLiteral) object;
				Enumeration en = (Enumeration) l.getEnumeration();
					ClassifierMap ecm = ojUtil.buildClassifierMap(en,(CollectionKind)null);
					OJEnumValue ev = new OJEnumValue(ecm.javaTypePath(), l.getName().toUpperCase());
					aa.addEnumValue(ev);
		}else if(object instanceof InstanceSpecification){
				aa.addAnnotationValue(buildAnnotation((InstanceSpecification)object));
		}else if(object instanceof Classifier){
			aa.addClassValue(ojUtil.classifierPathname((Classifier) object));
		}else if(object instanceof NamedElement){
			aa.addStringValue(((NamedElement) object).getName());
		}else{
			System.out.println("unknow value type:" + object);
		}
		
	}
	protected void applyStereotypesAsAnnotations(Element umlElement,OJAnnotatedElement javaElement){
		applyStereotypesAsAnnotations(umlElement, javaElement, umlElement.getAppliedStereotypes());
	}
	protected void applyStereotypesAsAnnotations(Element umlElement,OJAnnotatedElement javaElement,Collection<Stereotype> stereotypes){
		for(Stereotype is:stereotypes){
			if(isBuiltIn(is)){
				applyStereotypeAsAnnotation(umlElement,javaElement, is);
			}
		}
	}

	private OJAnnotationValue buildAnnotation(InstanceSpecification stereotypeApplication){
		if(stereotypeApplication.getClassifiers().size() >= 1){
			ClassifierMap map = ojUtil.buildClassifierMap(stereotypeApplication.getClassifiers().get(0),(CollectionKind) null);
			OJAnnotationValue an = new OJAnnotationValue(map.javaTypePath());
			for(Slot slot:stereotypeApplication.getSlots()){
				Property property = (Property) slot.getDefiningFeature();
				StructuralFeatureMap sfm = ojUtil.buildStructuralFeatureMap(property);
				OJAnnotationAttributeValue aa = new OJAnnotationAttributeValue(sfm.umlName());
				an.putAttribute(aa);
				if(slot.getValues().isEmpty() && sfm.isOne()){
					// TODO rethink this
					// 1. initialValue caused duplicate elements ????
					// 2. THere might not be a slot for the feature
					if(property.getDefault() != null){
						addSpecifiedValue(aa, property.getDefaultValue());
					}else{
						if(sfm.javaType().endsWith("int")){
							aa.addNumberValue(new Integer(0));
						}else if(sfm.javaType().endsWith("float")){
							aa.addNumberValue(new Float(0));
						}else if(sfm.javaType().endsWith("boolean")){
							aa.addBooleanValue(Boolean.FALSE);
						}else{
							aa.addStringValue("");
						}
					}
				}else{
					for(ValueSpecification vs:slot.getValues()){
						addSpecifiedValue(aa, vs);
					}
				}
			}
			return an;
		}else{
			return new OJAnnotationValue();
		}
	}
	private void putAnnotation(OJAnnotatedElement javaElement,OJAnnotationValue an){
		javaElement.putAnnotation(an);
	}
	private void addSpecifiedValue(OJAnnotationAttributeValue aa,ValueSpecification vs){
		if(vs instanceof LiteralBoolean){
			aa.addBooleanValue(vs.booleanValue());
		}else if(vs instanceof LiteralInteger){
			aa.addNumberValue(vs.integerValue());
		}else if(vs instanceof LiteralReal){
			aa.addNumberValue(vs.realValue());
		}else if(vs instanceof LiteralString){
			aa.addStringValue(vs.stringValue());
		}else if(vs instanceof InstanceValue){
			InstanceValue iv = (InstanceValue) vs;
			if(iv.getInstance() instanceof EnumerationLiteral){
				EnumerationLiteral l = (EnumerationLiteral) iv.getInstance();
				Enumeration en = (Enumeration) l.getEnumeration();
				ClassifierMap ecm = ojUtil.buildClassifierMap(en,(CollectionKind)null);
				OJEnumValue ev = new OJEnumValue(ecm.javaTypePath(), l.getName().toUpperCase());
				aa.addEnumValue(ev);
			}else{
				aa.addAnnotationValue(buildAnnotation(((InstanceValue) vs).getInstance()));
			}
		}
	}
	private boolean isBuiltIn(Classifier stereotype){
		boolean result = workspace.getOpaeumLibrary().getTypeMap().containsKey(stereotype.getQualifiedName());
		return result;
	}
}

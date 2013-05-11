package org.opaeum.reverse.popup.actions;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.IMemberValuePairBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

public class Stereotyper{
	public static void stereotype(Element e,IAnnotationBinding[] abs,ClassifierFactory cf){
		for(IAnnotationBinding ab:abs){
			Stereotype st = (Stereotype) cf.getClassifierFor(ab.getAnnotationType());
			if(st != null && e.isStereotypeApplicable(st)){
				if(!e.isStereotypeApplied(st)){
					e.applyStereotype(st);
				}
				DynamicEObjectImpl sa = (DynamicEObjectImpl) e.getStereotypeApplication(st);
				populateDynamicEObject(cf, ab, sa);
			}
		}
	}
	private static void populateDynamicEObject(ClassifierFactory cf,IAnnotationBinding ab,DynamicEObjectImpl sa){
		for(IMemberValuePairBinding v:ab.getDeclaredMemberValuePairs()){
			EStructuralFeature feat=sa.eClass().getEStructuralFeature(v.getName());
			if(v.getValue() instanceof Object[]){
				Object[] values = (Object[]) v.getValue();
				EList<Object> eObjects = (EList<Object>) sa.eGet(feat);
				eObjects.clear();
				for(Object object:values){
					eObjects.add(convertSingleValue(cf, sa, v, object));
				}
			}else{
				Object value = convertSingleValue(cf, sa, v, v.getValue());
				sa.eSet(feat, value);
			}
		}
	}
	private static Object convertSingleValue(ClassifierFactory cf,DynamicEObjectImpl st,IMemberValuePairBinding v,Object fromValue){
		Object value=null;
		if(fromValue instanceof String || fromValue instanceof Number || fromValue instanceof Boolean){
			value=fromValue;
		}else if(fromValue instanceof IVariableBinding){
			IVariableBinding vb =(IVariableBinding) fromValue;
			if(vb.isEnumConstant()){
				//Remember annotations wont have associations to enums nor inheritance - this will work:
				EEnum en= (EEnum) st.eClass().getEStructuralFeature(v.getName()).getEType();
				value=en.getEEnumLiteral(vb.getName());
			}
		}else if(fromValue instanceof ITypeBinding){
			value=cf.getClassifierFor((ITypeBinding)fromValue);
		}else if(fromValue instanceof IAnnotationBinding){
			EClass eClass= (EClass) st.eClass().getEStructuralFeature(v.getName()).getEType();
			value =new DynamicEObjectImpl(eClass);
			populateDynamicEObject(cf,(IAnnotationBinding)fromValue,(DynamicEObjectImpl)value);
		}
		return value;
	}
}

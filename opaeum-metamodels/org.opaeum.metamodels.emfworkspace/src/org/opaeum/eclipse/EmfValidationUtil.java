package org.opaeum.eclipse;

import org.opaeum.metamodel.validation.BrokenRule;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EmfValidationUtil{
	public static String replaceArguments(EObject object,BrokenRule brokenRule,String messagePattern){
		String simpleName = object.getClass().getSimpleName();
		String message = messagePattern.replace("{0}", simpleName + "[" + getNameFor(object) + "]");
		Object[] value = brokenRule.getParameters();
		for(int i = 0;i < value.length;i++){
			message = message.replace("{" + (i + 1) + "}", value[i]==null?"Exception":value[i].toString());
		}
		return message;
	}
	private static String getNameFor(EObject object){
		EList<EStructuralFeature> sfs = object.eClass().getEAllStructuralFeatures();
		for(EStructuralFeature eStructuralFeature:sfs){
			if(eStructuralFeature.getName().equals("name")){
				String name = (String) object.eGet(eStructuralFeature);
				if(name == null || name.trim().length() == 0){
					return getNameFor(object.eContainer());
				}else{
					return name;
				}
			}
		}
		return object.toString();
	}
	public static final String TYPE_EXPRESSION_HERE = "Type expression here";
	public static final String OCL_EXPRESSION_REQUIRED = "Ocl expression required";
}

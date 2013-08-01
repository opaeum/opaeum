package org.opaeum.eclipse;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.metamodel.validation.BrokenFeature;
import org.opaeum.metamodel.validation.BrokenRule;

public class EmfValidationUtil{
	public static String replaceArguments(EObject object,BrokenRule brokenRule,String messagePattern){
		String simpleName = object.getClass().getSimpleName();
		String message = messagePattern.replace("{0}", simpleName + "[" + getNameFor(object) + "]");
		Object[] value = brokenRule.getParameters();
		for(int i = 0;i < value.length;i++){
			message = message.replace("{" + (i + 1) + "}", value[i] == null ? "Exception" : getNameFor(value[i]));
		}
		return message;
	}
	private static String getNameFor(Object o){
		if(o instanceof EStructuralFeature){
			return ((EStructuralFeature) o).getName();
		}else if(o instanceof EObject){
			EObject object = (EObject) o;
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
		}else if(o instanceof BrokenFeature){
			return " the " + getNameFor(((BrokenFeature) o).getFeature()) +" of "+ getNameFor(((BrokenFeature) o).getOwner());
		}
		if(o==null){
			System.out.println();
		}
		return o.toString();
	}
	public static final String TYPE_EXPRESSION_HERE = "Type expression here";
	public static final String OCL_EXPRESSION_REQUIRED = "Ocl expression required";
}

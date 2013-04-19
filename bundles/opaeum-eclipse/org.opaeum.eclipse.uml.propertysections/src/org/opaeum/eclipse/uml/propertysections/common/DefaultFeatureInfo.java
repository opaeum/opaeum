package org.opaeum.eclipse.uml.propertysections.common;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumFeatureDialog.IFeatureInfo;

public class DefaultFeatureInfo implements IFeatureInfo{
	EObject object;
	EStructuralFeature feature;
	public DefaultFeatureInfo(EObject eObject,EStructuralFeature feature2){
		this.object=eObject;
		this.feature=feature2;
	}
	@Override
	public Object getObject(){
		return object;
	}
	@Override
	public EClassifier getType(){
		return feature.getEType();
	}
	@Override
	public List<?> getCurrentValues(){
		if(object==null){
			return Collections.emptyList();
		}
		return (List<?>) object.eGet(feature);
	}
}

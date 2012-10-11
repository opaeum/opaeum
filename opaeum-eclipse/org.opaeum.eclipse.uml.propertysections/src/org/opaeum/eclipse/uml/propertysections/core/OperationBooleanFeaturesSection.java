package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.subsections.BooleanSubsection;

public class OperationBooleanFeaturesSection extends AbstractMultiFeaturePropertySection{
	private BooleanSubsection isQuery;
	private BooleanSubsection isStatic;
	private BooleanSubsection isAbstract;
	public OperationBooleanFeaturesSection(){
		isQuery = createBoolean(UMLPackage.eINSTANCE.getOperation_IsQuery(), "Is Query", 140);
		isStatic = createBoolean(UMLPackage.eINSTANCE.getFeature_IsStatic(), "Is Static", 140);
		isAbstract = createBoolean(UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract(), "Is Abstract", 140);
	}
	@Override
	public String getLabelText(){
		return "Config:";
	}
}

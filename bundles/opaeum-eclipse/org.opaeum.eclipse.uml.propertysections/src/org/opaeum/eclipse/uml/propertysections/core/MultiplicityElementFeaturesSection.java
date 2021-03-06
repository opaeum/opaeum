package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.subsections.BooleanSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.PotentiallyUnlimitedNaturalIntegerSubsection;

public class MultiplicityElementFeaturesSection extends AbstractMultiFeaturePropertySection{
	private PotentiallyUnlimitedNaturalIntegerSubsection from;
	private PotentiallyUnlimitedNaturalIntegerSubsection to;
	private BooleanSubsection isUnique;
	private BooleanSubsection isOrdered;
	public MultiplicityElementFeaturesSection(){
		from = createLiteralInteger(UMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(), "Number of values:", 140, 40);
		from.setDefaultValue(1);
		to = createLiteralInteger(UMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(), "to:", 140, 40);
		to.setDefaultValue(1);
		isUnique = createBoolean(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique(), "Every value is unique", 140);
		isOrdered = createBoolean(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered(), "Values are ordered", 140);
	}
	@Override
	public String getLabelText(){
		return "Multiplicity";
	}
}

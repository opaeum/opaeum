package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.commands.SetStereotypeValueCommand;
import org.opaeum.eclipse.commands.StereotypeValueInformation;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.name.NameConverter;

public class BooleanOnStereotypeSubsection extends BooleanSubsection{
	private StereotypeValueInformation info;
	public BooleanOnStereotypeSubsection(IMultiPropertySection section,StereotypeValueInformation stereotypeValueInformation){
		super(section);
		this.info = stereotypeValueInformation;
	}
	@Override
	protected Command buildCommand(EObject selection,EObject featureOwner){
		SetStereotypeValueCommand result = new SetStereotypeValueCommand(section.getEditingDomain(), (Element) selection, info, getNewValue());
		return result;
	}
	@Override
	public EStructuralFeature getFeature(){
		return info.getFeature(section.getSelectedObject());
	}
	public EObject getFeatureOwner(EObject e){
		Element e2 = (Element) e;
		return e2.getStereotypeApplication(StereotypesHelper.getStereotype(e2, info.getStereotypeName()));
	}
}

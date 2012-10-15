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

public class ComboOnStereotypeSubsection extends ComboSubsection{
	private StereotypeValueInformation info;
	IChoiceProvider enumChoiceProvider = new IChoiceProvider(){
		@Override
		public Object[] getChoices(){
			if(section.getSelectedObject() == null){
				return new Object[0];
			}else{
				EEnum en = (EEnum) info.getExpectedType(section.getSelectedObject());
				return en.getELiterals().toArray();
			}
		}
	};
	public ComboOnStereotypeSubsection(IMultiPropertySection section,StereotypeValueInformation stereotypeValueInformation){
		super(section);
		this.info = stereotypeValueInformation;
	}
	@Override
	public IChoiceProvider getChoiceProvider(){
		if(super.getChoiceProvider() == null){
			setChoiceProvider(enumChoiceProvider);
		}
		return super.getChoiceProvider();
	}
	@Override
	public ILabelProvider getLabelProvider(){
		if(labelProvider == null && getChoiceProvider()==enumChoiceProvider){
			labelProvider = new LabelProvider(){
				@Override
				public String getText(Object element){
					ENamedElement l = (ENamedElement) element;
					return NameConverter.separateWords(NameConverter.capitalize(l.getName()));
				}
			};
		}
		return super.getLabelProvider();
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

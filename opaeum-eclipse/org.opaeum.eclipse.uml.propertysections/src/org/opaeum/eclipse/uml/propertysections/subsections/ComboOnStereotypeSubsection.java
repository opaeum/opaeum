package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.commands.SetStereotypeValueCommand;
import org.opaeum.eclipse.commands.StereotypeValueInformation;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.name.NameConverter;

public class ComboOnStereotypeSubsection extends ComboSubsection{
	private StereotypeValueInformation info;
	public ComboOnStereotypeSubsection(IMultiPropertySection section,StereotypeValueInformation stereotypeValueInformation){
		super(section);
		this.info = stereotypeValueInformation;
	}
	@Override
	public IChoiceProvider getChoiceProvider(){
		if(super.getChoiceProvider() == null){
			setChoiceProvider(new IChoiceProvider(){
				@Override
				public Object[] getChoices(){
					if(section.getSelectedObject() == null){
						return new Object[0];
					}else{
						EEnum en = (EEnum) info.getExpectedType(section.getSelectedObject());
						return en.getELiterals().toArray();
					}
				}
			});
			super.setLabelProvider(new LabelProvider(){
				@Override
				public String getText(Object element){
					EEnumLiteral l=(EEnumLiteral) element;
					return NameConverter.separateWords(NameConverter.capitalize(l.getName()));
				}
			});
		}
		return super.getChoiceProvider();
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
}

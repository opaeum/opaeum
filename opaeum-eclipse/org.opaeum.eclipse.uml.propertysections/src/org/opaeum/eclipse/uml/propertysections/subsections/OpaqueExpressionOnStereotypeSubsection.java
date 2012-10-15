package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.commands.SetStereotypeValueCommand;
import org.opaeum.eclipse.commands.StereotypeValueInformation;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
/**
 * NB!! this subsection assumes that the parent already has the correct Stereotype Application. It will simply look for the 
 * specified feature in the StereotypeApplication 
 * @author ampie
 *
 */
public class OpaqueExpressionOnStereotypeSubsection extends OpaqueExpressionSubsection{
	private StereotypeValueInformation info;
	public OpaqueExpressionOnStereotypeSubsection(IMultiPropertySection section,StereotypeValueInformation info){
		super(section);
		this.info=info;
	}
	@Override
	protected Command buildCommand(EObject selection,EObject featureOwner){
		Command cmd = new SetStereotypeValueCommand(section.getEditingDomain(), (Element) selection, info, getNewValue());
		return cmd;
	}
	@Override
	public EStructuralFeature getFeature(){
		return info.getFeature(section.getSelectedObject());
	}
}
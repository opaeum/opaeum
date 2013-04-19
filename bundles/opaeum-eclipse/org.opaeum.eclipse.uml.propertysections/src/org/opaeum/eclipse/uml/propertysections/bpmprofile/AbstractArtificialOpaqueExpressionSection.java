package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.custom.TextChangeListener;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.commands.SetStereotypeValueCommand;
import org.opaeum.eclipse.uml.propertysections.base.RecreatingOpaqueExpressionSection;
import org.opaeum.name.NameConverter;

public abstract class AbstractArtificialOpaqueExpressionSection extends RecreatingOpaqueExpressionSection implements TextChangeListener{
	@Override
	protected void maybeAppendCommand(EditingDomain editingDomain,CompoundCommand compoundCommand,Object selectedObject,EObject featureOwner,
			EStructuralFeature f,Object oldValue,Object newValue){
		compoundCommand.append(new SetStereotypeValueCommand(editingDomain, (Element) selectedObject, getExpressionName(), newValue));
	}
	@Override
	public String getLabelText(){
		return NameConverter.separateWords(NameConverter.capitalize(getExpressionName()));
	}
	@Override
	protected EObject getFeatureOwner(EObject e){
		Element el = (Element) e;
		for(EObject sa:el.getStereotypeApplications()){
			if(sa.eClass().getEStructuralFeature(getExpressionName()) != null){
				return sa;
			}
		}
		return null;
	}
	@Override
	protected EStructuralFeature getFeature(EObject nextObject){
		return nextObject.eClass().getEStructuralFeature(getExpressionName());
	}
	@Override
	protected EStructuralFeature getFeature(){
		return getFeature(getFeatureOwner(getSelectedObject()));
	}
	protected abstract String getExpressionName();
}
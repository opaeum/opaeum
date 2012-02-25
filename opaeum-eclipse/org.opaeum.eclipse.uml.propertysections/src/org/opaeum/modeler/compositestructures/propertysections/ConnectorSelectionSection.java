package org.opaeum.modeler.compositestructures.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.util.UMLUtil.StereotypeApplicationHelper;
import org.opaeum.eclipse.EmfActivityUtil.TypeAndMultiplicity;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.AttachedOpaqueBehaviorSection;
import org.opaeum.topcased.propertysections.EObjectNavigationSource;

public class ConnectorSelectionSection extends AttachedOpaqueBehaviorSection implements EObjectNavigationSource{
	@Override
	protected String getLabelText(){
		return "Selection";
	}
	@Override
	public EObject getEObjectToGoTo(){
		return getBehavior();
	}
	protected void removeBehavior(Behavior behavior){
		Stereotype stereotype = getStereotype();
		EObject sa = getConnector().getStereotypeApplication(stereotype);
		getEditingDomain().getCommandStack()
				.execute(SetCommand.create(getEditingDomain(), sa, stereotype.getDefinition().getEStructuralFeature("selector"), null));
	}
	protected Behavior createBehavior(){
		Behavior tf = null;
		TypeAndMultiplicity sourceType = EmfCompositeStructuresUtil.findTargetType(getConnector());
		TypeAndMultiplicity targetType = EmfCompositeStructuresUtil.findTargetType(getConnector());
		OpaqueBehavior behavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
		behavior.setName("selectionFor" + getConnector().getName());
		createParam(behavior, sourceType, "source", ParameterDirectionKind.IN_LITERAL).setUpper(-1);
		createParam(behavior, targetType, "result", ParameterDirectionKind.RETURN_LITERAL).setUpper(-1);
		Stereotype stereotype = getStereotype();
		EObject sa = getConnector().getStereotypeApplication(stereotype);
		if(sa == null){
			sa = StereotypeApplicationHelper.INSTANCE.applyStereotype(getConnector(), stereotype.getDefinition());
		}
		Command add = SetCommand.create(getEditingDomain(), sa, stereotype.getDefinition().getEStructuralFeature("selector"), behavior);
		getEditingDomain().getCommandStack().execute(add);
		tf = behavior;
		return tf;
	}
	private Connector getConnector(){
		return (Connector) getEObject();
	}
	protected Behavior getBehavior(){
		Stereotype st = getStereotype();
		return (Behavior) getConnector().getValue(st, "selector");
	}
	private Stereotype getStereotype(){
		Profile p = ProfileApplier.applyProfile(getConnector().getModel(), StereotypeNames.OPAEUM_BPM_PROFILE);
		return p.getOwnedStereotype(StereotypeNames.RESPONSIBILITY_DELEGATION);
	}
	protected void addVariables(){
		TypeAndMultiplicity sourceType = EmfCompositeStructuresUtil.findTargetType(getConnector());
		if(sourceType != null){
			if(getBehavior() == null || getBehavior().getOwnedParameters().size() != 2){
				oclComposite.addVariable("source", sourceType.getType());
			}else{
				for(Parameter parameter:getBehavior().getOwnedParameters()){
					if(parameter.getDirection() == ParameterDirectionKind.IN_LITERAL){
						oclComposite.addVariable(parameter.getName(), sourceType.getType());
					}
				}
			}
		}
	}
	@Override
	public boolean isRelationshipComplete(){
		TypeAndMultiplicity sourceType = EmfCompositeStructuresUtil.findSourceType(getConnector());
		TypeAndMultiplicity targetType = EmfCompositeStructuresUtil.findTargetType(getConnector());
		return sourceType != null && targetType != null;
	}
	@Override
	protected NamedElement getOclContext(){
		return EmfElementFinder.getNearestClassContext(getConnector());
	}
}

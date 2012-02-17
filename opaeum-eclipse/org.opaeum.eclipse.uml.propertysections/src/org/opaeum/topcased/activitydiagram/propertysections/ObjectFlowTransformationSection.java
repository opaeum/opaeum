package org.opaeum.topcased.activitydiagram.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfActivityUtil.TypeAndMultiplicity;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.topcased.propertysections.AttachedOpaqueBehaviorSection;
import org.opaeum.topcased.propertysections.EObjectNavigationSource;

public class ObjectFlowTransformationSection extends AttachedOpaqueBehaviorSection implements EObjectNavigationSource{
	@Override
	protected String getLabelText(){
		return "Transformation";
	}
	@Override
	public EObject getEObjectToGoTo(){
		return getObjectFlow().getTransformation();
	}
	protected ObjectFlow getObjectFlow(){
		ObjectFlow objectFlow = (ObjectFlow) getEObject();
		return objectFlow;
	}

	protected void removeBehavior(Behavior tf){
		getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getObjectFlow(), UMLPackage.eINSTANCE.getObjectFlow_Transformation(), null));
		getEditingDomain().getCommandStack().execute(
				RemoveCommand.create(getEditingDomain(), tf.getOwner(), UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior(), tf));
	}
	protected Behavior createBehavior(){
		Behavior tf = null;
		Classifier clss = EmfElementFinder.getNearestClassContext(getObjectFlow());
		TypeAndMultiplicity sourceType = EmfActivityUtil.findSourceType(getObjectFlow());
		TypeAndMultiplicity targetType = EmfActivityUtil.findTargetType(getObjectFlow());
		OpaqueBehavior behavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
		behavior.setName("transformationFor" + getObjectFlow().getName());
		createParam(behavior, sourceType, "source", ParameterDirectionKind.IN_LITERAL).setUpper(1);
		createParam(behavior, targetType, "result", ParameterDirectionKind.RETURN_LITERAL);
		Command add = AddCommand.create(getEditingDomain(), clss, UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior(), behavior);
		getEditingDomain().getCommandStack().execute(add);
		Command setCommand = SetCommand.create(getEditingDomain(), getObjectFlow(), UMLPackage.eINSTANCE.getObjectFlow_Transformation(), behavior);
		getEditingDomain().getCommandStack().execute(setCommand);
		tf = behavior;
		return tf;
	}
	protected Behavior getBehavior(){
		return getObjectFlow().getTransformation();
	}
	protected void addVariables(){
		TypeAndMultiplicity sourceType = EmfActivityUtil.findSourceType(getObjectFlow());
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
		TypeAndMultiplicity sourceType = EmfActivityUtil.findSourceType(getObjectFlow());
		TypeAndMultiplicity targetType = EmfActivityUtil.findSourceType(getObjectFlow());
		return sourceType!=null && targetType!=null;
	}
	@Override
	protected NamedElement getOclContext(){
		return EmfElementFinder.getNearestClassContext(getObjectFlow());
	}
}

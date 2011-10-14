package org.opaeum.topcased.activitydiagram.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfActivityUtil.TypeAndMultiplicity;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.EObjectNavigationSource;

public class ObjectFlowSelectionSection extends AttachedOpaqueBehaviorSection implements EObjectNavigationSource{
	@Override
	protected String getLabelText(){
		return "Selector";
	}

	@Override
	public EObject getEObjectToGoTo(){
		return getObjectFlow().getSelection();
	}
	protected void removeBehavior(Behavior tf){
		getEditingDomain().getCommandStack().execute(
				SetCommand.create(getEditingDomain(), getObjectFlow(), UMLPackage.eINSTANCE.getObjectFlow_Selection(), null));
		getEditingDomain().getCommandStack().execute(
				RemoveCommand.create(getEditingDomain(), tf.getOwner(), UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior(), tf));
	}
	protected Behavior createBehavior(){
		Behavior tf;
		Classifier clss = EmfElementFinder.getNearestClassifier(getObjectFlow());
		if(clss instanceof Activity && ((Activity) clss).getContext()!=null && !StereotypesHelper.hasKeyword(clss, StereotypeNames.BUSINES_PROCESS)) {
			clss=((Behavior) clss).getContext();
		}
		OpaqueBehavior behavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
		behavior.setName("selectionFor" + getObjectFlow().getName());
		TypeAndMultiplicity sourceType = EmfActivityUtil.findSourceType(getObjectFlow());
		Parameter source = createParam(behavior, sourceType, "source", ParameterDirectionKind.IN_LITERAL);
		source.setIsUnique(false);
		source.setIsOrdered(false);
		source.setUpper(-1);
		TypeAndMultiplicity targetType = EmfActivityUtil.findTargetType(getObjectFlow());
		createParam(behavior, targetType, "result", ParameterDirectionKind.RETURN_LITERAL);
		Command add = AddCommand.create(getEditingDomain(), clss, UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior(), behavior);
		getEditingDomain().getCommandStack().execute(add);
		Command setCommand = SetCommand.create(getEditingDomain(), getObjectFlow(), UMLPackage.eINSTANCE.getObjectFlow_Selection(), behavior);
		getEditingDomain().getCommandStack().execute(setCommand);
		tf=behavior;
		return tf;
	}
	protected Behavior getBehavior(){
		return getObjectFlow().getSelection();
	}
	protected void addVariables(){
		oclComposite.addVariable("source", EmfActivityUtil.findSourceType(getObjectFlow()).getType());
	}
}

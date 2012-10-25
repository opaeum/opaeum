package org.opaeum.eclipse.commands;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfValidationUtil;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class SetEventCommand extends CompoundCommand{
	private EClass type;
	private boolean isRelative;
	public SetEventCommand(EditingDomain editingDomain,Trigger trigger,EClass type){
		this(editingDomain, trigger, type, false);
	}
	public SetEventCommand(EditingDomain editingDomain,Trigger trigger,EClass type,boolean isRelative){
		this.type = type;
		this.isRelative = isRelative;
		EAnnotation ann = StereotypesHelper.getNumlAnnotation(trigger);
		if(ann == null){
			ann = EcoreFactory.eINSTANCE.createEAnnotation();
			ann.setSource(StereotypeNames.NUML_ANNOTATION);
			append(AddCommand.create(editingDomain, trigger, EcorePackage.eINSTANCE.getEModelElement_EAnnotations(), ann));
		}
		Event event = findEvent(ann);
		if(event == null){
			event = (Event) UMLFactory.eINSTANCE.create(type);
			event.setName(((NamedElement) trigger.getOwner()).getName() + type.getName());
			//Papyrus breaks if these are not specified
			if(event instanceof TimeEvent){
				((TimeEvent) event).setIsRelative(isRelative);
				((TimeEvent) event).setWhen(EmfValueSpecificationUtil.buildTimeExpression(event, "When",EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
			}else if(event instanceof ChangeEvent){
				((ChangeEvent) event).setChangeExpression(EmfValueSpecificationUtil.buildOpaqueExpression(event, "ChangeExpression", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
			}
			append(AddCommand.create(editingDomain, ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), event));
			// NB!! creation of changeExpressions and whenExpression will be done automatically by OpaeumElementLinker
		}
		if(trigger.getEvent() != event){
			append(SetCommand.create(editingDomain, trigger, UMLPackage.eINSTANCE.getTrigger_Event(), event));
		}
	}
	@Override
	public boolean canExecute(){
		return true;
	}
	private Event findEvent(EAnnotation ann){
		for(EObject eObject:ann.getContents()){
			if(type.isInstance(eObject)){
				if(eObject instanceof TimeEvent){
					if(((TimeEvent) eObject).isRelative() == isRelative){
						return (Event) eObject;
					}
				}else{
					return (Event) eObject;
				}
			}
		}
		return null;
	}
}

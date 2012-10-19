package org.opaeum.uimodeler.util;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.uml2uim.PerspectiveCreator;

public class UmlToUimSwitch extends UMLSwitch<Object>{
	private Notification notification;
	PerspectiveCreator perspectiveCreator;
	FormSynchronizer2 formSynchronizer;
	public UmlToUimSwitch(PerspectiveCreator perspectiveCreator,FormSynchronizer2 formSynchronizer){
		super();
		this.perspectiveCreator = perspectiveCreator;
		this.formSynchronizer = formSynchronizer;
	}
	public void goForIt(Notification n){
		this.notification = n;
		if(n.getNotifier() instanceof Element){
			doSwitch((Element) notification.getNotifier());
		}
	}
	@Override
	public Object casePackage(Package object){
		if(notification.getNewValue() instanceof Classifier){
			Classifier classifier = (Classifier) notification.getNewValue();
			perspectiveCreator.visitClassifier(classifier);
			perspectiveCreator.visitClassifier(classifier);
		}
		return super.casePackage(object);
	}
	@Override
	public Object caseClassifier(Classifier object){
		if(notification.getNewValue() instanceof Property){
			perspectiveCreator.visitProperty((Property) notification.getNewValue());
		}
		if(notification.getNewValue() instanceof Operation){
			perspectiveCreator.visitOperation((Operation) notification.getNewValue());
			formSynchronizer.beforeOperation((Operation) notification.getNewValue());
		}
		if(notification.getNewValue() instanceof Classifier){
			perspectiveCreator.visitClassifier((Classifier) notification.getNewValue());
		}
		if(notification.getNewValue() instanceof Action){
			formSynchronizer.beforeAction((Action) notification.getNewValue());
		}
		if(notification.getNewValue() instanceof TypedElement){
			TypedElement typedElement = (TypedElement) notification.getNewValue();
			refreshDataElements(typedElement);
		}
		return super.caseClassifier(object);
	}
	@Override
	public Object caseStructuredActivityNode(StructuredActivityNode object){
		if(notification.getNewValue() instanceof Action){
			formSynchronizer.beforeAction((Action) notification.getNewValue());
		}
		return super.caseStructuredActivityNode(object);
	}
	@Override
	public Object caseBehavior(Behavior object){
		return super.caseBehavior(object);
	}
	@Override
	public Object caseOperation(Operation object){
		if(notification.getNewValue() instanceof Parameter){
			Parameter typedElement = (Parameter) notification.getNewValue();
			refreshDataElements(typedElement);
		}
		return super.caseOperation(object);
	}
	@Override
	public Object caseOpaqueAction(OpaqueAction object){
		return super.caseOpaqueAction(object);
	}
	@Override
	public Object caseTypedElement(TypedElement object){
		if(notification.getFeatureID(TypedElement.class) == UMLPackage.TYPED_ELEMENT__TYPE
				|| notification.getFeatureID(MultiplicityElement.class) == UMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE){
			refreshDataElements((TypedElement) notification.getNotifier());
		}
		return super.caseTypedElement(object);
	}
	protected void refreshDataElements(TypedElement typedElement){
		EObject container = EmfElementFinder.getContainer(typedElement);
		if(typedElement instanceof Property && ((Property) typedElement).getAssociationEnd() == null){
			formSynchronizer.beforeClass((Classifier) container);
			// TODO refresh all the pages (Tables,Panels) that may refer to this classifier,i.e. containment properties, generalizations,
			// interfacerealizations, paramaters,pins
		}else if(typedElement instanceof Parameter){
			if(container instanceof Operation){
				formSynchronizer.beforeOperation((Operation) container);
			}else{
				formSynchronizer.beforeClass((Classifier) container);
			}
		}else if(typedElement instanceof Pin){
			formSynchronizer.beforeAction((Action) container);
		}
	}
}

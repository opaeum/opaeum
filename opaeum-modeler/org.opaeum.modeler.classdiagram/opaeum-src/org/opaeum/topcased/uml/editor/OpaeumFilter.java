package org.opaeum.topcased.uml.editor;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.outline.AdditionalResources;

public class OpaeumFilter extends ViewerFilter{
	public static Set<EClassifier> DISALLOWED_CLASSES = new HashSet<EClassifier>();
	public static Set<EClassifier> ALLOWED_CLASSES = new HashSet<EClassifier>();
	static{
		init();
	}
	protected static void init(){
		DISALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getFunctionBehavior());
		DISALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getProtocolStateMachine());
		DISALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getFunctionBehavior());
		DISALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getTimeConstraint());
		DISALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getDurationConstraint());
		DISALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getDevice());
		DISALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getNode());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getGeneralization());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getInterfaceRealization());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getUsage());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getElementImport());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getPackageImport());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getPackageMerge());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getAssociation());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getTransition());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getPseudostate());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getActivityEdge());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getActivity());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getStateMachine());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getPackage());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getComponent());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getConnector());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getConnectorEnd());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getProperty());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getOperation());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getParameter());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getState());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getAction());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getControlNode());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getPin());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getPort());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getRegion());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getActivityPartition());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getClass_());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getEnumeration());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getEnumerationLiteral());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getSignal());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getDataType());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getVariable());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getInterface());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getConstraint());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getOpaqueExpression());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getInstanceSpecification());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getInstanceValue());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getLiteralInteger());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getLiteralString());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getLiteralBoolean());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getSlot());
		ALLOWED_CLASSES.add(UMLPackage.eINSTANCE.getInterruptibleActivityRegion());
	}
	public boolean select(Viewer viewer,Object parentElement,Object element){
		if(element instanceof AdditionalResources || element instanceof Resource){
			return true;
		}else if(element instanceof EObject){
			if(element instanceof EAnnotation){
				return false;
			}else if(element instanceof Element){
				return isAllowedElement((Element) element);
			}else{
				return true;
			}
		}
		return false;
	}
	public static boolean isAllowedElement(EObject element){
		init();//TODO Remove. THis is just temporary too allow us to change it at debug time
		if(element.eClass().getEPackage().getName().equalsIgnoreCase("uml")){
			for(EClassifier e:ALLOWED_CLASSES){
				if(e.isInstance(element)){
					for(EClassifier e2:DISALLOWED_CLASSES){
						if(e2.isInstance(element)){
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
}

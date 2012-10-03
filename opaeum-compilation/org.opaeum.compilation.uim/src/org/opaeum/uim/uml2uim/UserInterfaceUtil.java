package org.opaeum.uim.uml2uim;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterEffectKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfParameterUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.IgnoredElement;
import org.opaeum.uim.Page;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.component.UimContainer;
import org.opaeum.uim.util.ControlUtil;
import org.opaeum.uim.wizard.InvocationWizard;

public class UserInterfaceUtil{
	public static boolean requiresTablePage(UserInterfaceRoot uiRoot,TypedElement typedElement){
		return EmfPropertyUtil.isMany(typedElement) && requiresOwnPage(uiRoot, typedElement);
	}
	private static boolean requiresOwnPage(UserInterfaceRoot uiRoot,TypedElement typedElement){
		boolean result=false;
		if(EmfClassifierUtil.isPersistentComplexStructure(typedElement.getType())){
			if(typedElement instanceof Property){
				Property property=(Property) typedElement;
				//TODO consider derived/readOnly/oneToMany???
				result= property.isComposite();
			}else if(typedElement instanceof Parameter){
				Parameter parameter = (Parameter) typedElement;
				if(uiRoot instanceof InvocationWizard){
					// Creation input parameters need to be created as the behavior/operation is invoked
					result=EmfParameterUtil.isArgument(parameter.getDirection()) && parameter.getEffect() == ParameterEffectKind.CREATE_LITERAL;
				}else{
					// StandaloneTaskEditor/Behavior Editor/ResponsibilityViewer
					// Assume that objects passed to the task should be manipulated by the user
					// Objects resulting from the task should merely be selected by the user, UNLESS they are Creation Output Parameters
					result= EmfParameterUtil.isArgument(parameter.getDirection())
							|| (EmfParameterUtil.isResult(parameter.getDirection()) && parameter.getEffect() == ParameterEffectKind.CREATE_LITERAL);
				}
			}else if(typedElement instanceof Pin){
				// EmbeddedTaskEditor
				// Assume that objects passed to the task should be manipulated by the user, but objects resulting from the task should merely be
				// selected by the user
				result=typedElement instanceof InputPin;
			}else{
				result=false;
			}
		}
		return result;
	}
	public static boolean requiresDetailsPage(UserInterfaceRoot uiRoot,TypedElement typedElement){
		return !EmfPropertyUtil.isMany(typedElement) && requiresOwnPage(uiRoot, typedElement);
	}
	public static UserInteractionElement findRepresentingElement(Element e,UimContainer container){
		String id = EmfWorkspace.getId(e);
		TreeIterator<EObject> eAllContents = container.eAllContents();
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof UmlReference){
				if(id.equals(((UmlReference) eObject).getUmlElementUid())){
					while(!(eObject instanceof UserInteractionElement)){
						// Find nearest UserInteractionElement
						eObject = eObject.eContainer();
					}
					if(eObject instanceof UserInteractionElement){
						return (UserInteractionElement) eObject;
					}
				}
			}
		}
		return null;
	}
	public static boolean isIgnored(Element e, UserInterfaceRoot uiRoot){
		EList<IgnoredElement> ignoredElements = uiRoot.getIgnoredElements();
		for(IgnoredElement ie:ignoredElements){
			if(ie.getUmlElementUid().equals(EmfWorkspace.getId(e))){
				return true;
			}
		}
		return false;
	}
	public static Page findRepresentingPage(Element e,UserInterfaceRoot container){
		List<? extends Page> pages = container.getPages();
		for(Page page:pages){
			if(EmfWorkspace.getId(e).equals(page.getUmlElementUid())){
				return page;
			}
		}
		return null;
	}
	public static boolean isUnderUserControl(EObject container){
		EObject element = container;
		while(element != null){
			if(element instanceof UserInteractionElement && ((UserInteractionElement) element).isUnderUserControl()){
				return true;
			}else if(element instanceof Page){
				//A page is also considered under user control if its single panel is under user control
				Page page = (Page) element;
				if(page.getPanel() != null && page.getPanel().isUnderUserControl()){
					return true;
				}
			}else{
				element = element.eContainer();
			}
		}
		return false;
	}
}

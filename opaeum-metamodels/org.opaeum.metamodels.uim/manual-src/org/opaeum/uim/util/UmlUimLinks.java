package org.opaeum.uim.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.action.AbstractLink;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.action.LinkToQuery;
import org.opaeum.uim.action.TransitionButton;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.binding.LookupBinding;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.binding.UimBinding;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.editor.ObjectEditor;
import org.opaeum.uim.model.EmbeddedTaskEditor;
import org.opaeum.uim.model.OperationInvocationWizard;
import org.opaeum.uim.model.QueryInvoker;

public class UmlUimLinks{
	private EmfWorkspace primaryEmfWorkspace;
	static Map<Resource,UmlUimLinks> linksMap = new WeakHashMap<Resource,UmlUimLinks>();
	public UmlUimLinks(Resource uimResource,EmfWorkspace map){
		linksMap.put(uimResource, this);
		this.primaryEmfWorkspace = map;
	}
	public Element getUmlElement(UmlReference uIMBinding){
		if(uIMBinding.getUmlElementUid() == null){
			return null;
		}
		return getLink(uIMBinding);
	}
	public TypedElement getTypedElement(UimBinding uIMBinding){
		return (TypedElement) getLink(uIMBinding);
	}
	public Property getProperty(PropertyRef uIMBinding){
		return (Property) getLink(uIMBinding);
	}
	private Element getLink(UmlReference uIMBinding){
		EmfWorkspace map = primaryEmfWorkspace;
		Element element = map.getModelElement(uIMBinding.getUmlElementUid());
		if(element != null){
			return element;
		}
		return null;
	}
	public Operation getOperation(InvocationButton eObject){
		return (Operation) getUmlElement(eObject);
	}
	public Operation getOperation(LinkToQuery eObject){
		return (Operation) getUmlElement(eObject);
	}
	public Transition getTransition(TransitionButton eObject){
		return (Transition) getUmlElement(eObject);
	}
	public Operation getOperation(QueryInvoker form){
		return (Operation) getUmlElement(form);
	}
	public Operation getOperation(OperationInvocationWizard oif){
		return (Operation) getUmlElement(oif);
	}
	public OpaqueAction getAction(EmbeddedTaskEditor oif){
		return (OpaqueAction) getUmlElement(oif);
	}
	public Class getClass(ObjectEditor nearestForm){
		return (Class) getUmlElement(nearestForm);
	}
	public String getId(Element e){
		return primaryEmfWorkspace.getId(e);
	}
	public TypedElement getResultingType(final UimBinding uIMBinding){
		TypedElement typedElement = null;
		if(uIMBinding.getNext() == null && getTypedElement(uIMBinding) != null){
			typedElement = getTypedElement(uIMBinding);
		}else if(uIMBinding.getNext() != null){
			PropertyRef pr = uIMBinding.getNext();
			while(pr.getNext() != null){
				pr = pr.getNext();
			}
			if(getProperty(pr) != null){
				typedElement = getProperty(pr);
			}
		}
		return typedElement;
	}
	public Classifier getNearestClass(EObject uc){
		//TODO investigate if this was ever necessary
//		if(uc instanceof UimAction){
//			return getRepresentedClass(getNearestForm((UimAction)uc));
//		}else{
			UimDataTable nearestTable = getNearestTable(uc);
			if(nearestTable == null){
				UserInterfaceRoot uf = getNearestForm(uc);
				return getRepresentedClass(uf);
			}else if(nearestTable.getBinding() != null && getTypedElement(nearestTable.getBinding()) != null){
				return (Classifier) getBindingType(nearestTable.getBinding());
			}
			return null;
//		}
	}
	private Classifier getBindingType(UimBinding b){
		if(b.getNext() == null || getProperty(b.getNext()) == null){
			return (Classifier) getTypedElement(b).getType();
		}else{
			return getPropertyType(b.getNext());
		}
	}
	private Classifier getPropertyType(PropertyRef ref){
		if(ref.getNext() == null && getProperty(ref.getNext()) == null){
			return (Classifier) getProperty(ref).getType();
		}else{
			return getPropertyType(ref.getNext());
		}
	}
	public UimDataTable getNearestTable(EObject uc){
		while(!(uc instanceof UimDataTable)){
			if(uc.eContainer() == null){
				return null;
			}else if(uc.eContainer() instanceof UimDataTable){
				return (UimDataTable) uc.eContainer();
			}else if(uc.eContainer() instanceof UimComponent){
				uc = (UimComponent) uc.eContainer();
			}else{
				return null;
			}
		}
		return null;
	}
	public static UserInterfaceRoot getNearestForm(EObject uc){
		while(!(uc.eContainer() instanceof UserInterfaceRoot)){
			uc = uc.eContainer();
		}
		return (UserInterfaceRoot) uc.eContainer();
	}
	public static UmlReference getNearestForm(AbstractActionButton ab){
		EObject uc = ab;
		while(!(uc.eContainer() instanceof UserInterfaceRoot || uc.eContainer() instanceof AbstractEditor)){
			uc = uc.eContainer();
		}
		return (UmlReference) uc.eContainer();
	}
	public static UmlReference getNearestForm(AbstractLink ab){
		EObject uc = ab;
		while(!(uc.eContainer() instanceof UserInterfaceRoot || uc.eContainer() instanceof AbstractEditor)){
			uc = uc.eContainer();
		}
		return (UmlReference) uc.eContainer();
	}
	public List<Operation> getValidOperationsFor(UserInterfaceRoot ui){
		if(ui.eContainer() instanceof ObjectEditor){
			ObjectEditor cf = (ObjectEditor) ui.eContainer();
			Classifier representedClass = getRepresentedClass(cf);
			if(representedClass != null){
				if(representedClass instanceof Behavior){
					Behavior sm = (Behavior) representedClass;
					if(sm.getContext() != null && sm.getContext().getClassifierBehavior() == sm){
						return sm.getContext().getAllOperations();
					}
				}
				EList<Operation> allOps = representedClass.getAllOperations();
				return allOps;
			}
		}
		return new ArrayList<Operation>();
	}
	public Classifier getRepresentedClass(UmlReference uf){
		Element rc = getUmlElement(uf);
		if(rc instanceof Classifier){
			return delegateToContextIfRequired((Classifier) rc);
		}else{
			return null;
		}
	}
	public UimComponent getComponent(UimBinding pr){
		if(pr instanceof FieldBinding){
			return ((FieldBinding) pr).getField();
		}else if(pr instanceof TableBinding){
			return ((TableBinding) pr).getTable();
		}else if(pr instanceof LookupBinding){
			return ((LookupBinding) pr).getLookup().getField();
		}else{
			return (UimComponent) pr.eContainer();
		}
	}
	private Classifier delegateToContextIfRequired(Classifier rc){
		if((rc instanceof Behavior)){
			Behavior b = (Behavior) rc;
			if(b.getContext() != null && b.getContext().getClassifierBehavior() == b){
				return (Classifier) b.getContext();
			}
		}
		return rc;
	}
	public Classifier getType(UimBinding binding){
		if(binding == null || getTypedElement(binding) == null){
			return null;
		}else if(binding.getNext() == null || getProperty(binding.getNext()) == null){
			return (Classifier) getTypedElement(binding).getType();
		}else{
			return getType(binding.getNext());
		}
	}
	private Classifier getType(PropertyRef current){
		if(current.getNext() == null || getProperty(current.getNext()) == null){
			return (Classifier) getProperty(current).getType();
		}else{
			return getType(current.getNext());
		}
	}
	public Collection<? extends TypedElement> getOwnedTypedElements(AbstractEditor nearestForm){
		Element e = getUmlElement(nearestForm);
		return EmfPropertyUtil.getOwnedTypedElements(e);
	}
	public static UmlUimLinks getCurrentUmlLinks(EObject e){
		return linksMap.get(e.eResource());
	}
	public Element getNearestUmlElement(UserInteractionElement ui){
		while(ui != null){
			if(ui instanceof UmlReference){
				Element umlElement = getUmlElement((UmlReference) ui);
				if(umlElement == null){
					ui = (UserInteractionElement) ui.eContainer();
				}else{
					return umlElement;
				}
			}else{
				ui = (UserInteractionElement) ui.eContainer();
			}
		}
		return null;
	}
	public EmfWorkspace getEmfWorkspace(){
		return primaryEmfWorkspace;
	}
	public Behavior getBehavior(BehaviorExecutionEditor eContainer){
		return (Behavior) getLink(eContainer);
	}
}

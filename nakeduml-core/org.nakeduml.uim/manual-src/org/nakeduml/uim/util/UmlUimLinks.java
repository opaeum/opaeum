package org.nakeduml.uim.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.eclipse.EmfElementFinder;
import org.nakeduml.eclipse.EmfPropertyUtil;
import org.nakeduml.eclipse.EmfStateMachineUtil;
import org.nakeduml.uim.UimComponent;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.action.OperationAction;
import org.nakeduml.uim.action.TransitionAction;
import org.nakeduml.uim.binding.FieldBinding;
import org.nakeduml.uim.binding.LookupBinding;
import org.nakeduml.uim.binding.NavigationBinding;
import org.nakeduml.uim.binding.PropertyRef;
import org.nakeduml.uim.binding.TableBinding;
import org.nakeduml.uim.binding.UimBinding;
import org.nakeduml.uim.folder.OperationContainingFolder;
import org.nakeduml.uim.form.ActionTaskForm;
import org.nakeduml.uim.form.ClassForm;
import org.nakeduml.uim.form.FormPanel;
import org.nakeduml.uim.form.OperationInvocationForm;
import org.nakeduml.uim.form.OperationTaskForm;
import org.nakeduml.uim.form.StateForm;

public class UmlUimLinks{
	Collection<EmfWorkspace> emfWorkspaces = new ArrayList<EmfWorkspace>();
	private EmfWorkspace primaryEmfWorkspace;
	public UmlUimLinks(EmfWorkspace map){
		emfWorkspaces.add(map);
		this.primaryEmfWorkspace=map;
	}
	public Element getUmlElement(UmlReference uIMBinding){
		return getLink(uIMBinding);
	}
	public TypedElement getTypedElement(UimBinding uIMBinding){
		return (TypedElement) getLink(uIMBinding);
	}
	public Property getProperty(PropertyRef uIMBinding){
		return (Property) getLink(uIMBinding);
	}
	private Element getLink(UmlReference uIMBinding){
		for(EmfWorkspace map:emfWorkspaces){
			Element element = map.getElementMap().get(uIMBinding.getUmlElementUid());
			if(element != null){
				return element;
			}
		}
		return null;
	}
	public Class getRepresentedClass(OperationContainingFolder folder){
		return (Class) getLink(folder);
	}
	public State getState(StateForm sui){
		return (State) getUmlElement(sui);
	}
	public Operation getOperation(OperationAction eObject){
		return (Operation) getUmlElement(eObject);
	}
	public Transition getTransition(TransitionAction eObject){
		return (Transition) getUmlElement(eObject);
	}
	public Operation getOperation(OperationInvocationForm form){
		return (Operation) getUmlElement(form);
	}
	public Operation getOperation(OperationTaskForm oif){
		return (Operation) getUmlElement(oif);
	}
	public OpaqueAction getAction(ActionTaskForm oif){
		return (OpaqueAction) getUmlElement(oif);
	}
	public Class getClass(ClassForm nearestForm){
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
	public Classifier getNearestClass(UimComponent uc){
		UimDataTable nearestTable = getNearestTable(uc);
		if(nearestTable == null){
			FormPanel uf = getNearestForm(uc);
			return getRepresentedClass(uf);
		}else if(nearestTable.getBinding() != null && getTypedElement(nearestTable.getBinding()) != null){
			return (Classifier) getBindingType(nearestTable.getBinding());
		}
		return null;
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
	public UimDataTable getNearestTable(UimComponent uc){
		while(!(uc.getParent() instanceof UimDataTable)){
			if(uc.getParent() instanceof FormPanel){
				return null;
			}else{
				uc = (UimComponent) uc.getParent();
			}
		}
		return (UimDataTable) uc.getParent();
	}
	public FormPanel getNearestForm(EObject uc){
		while(!(uc.eContainer() instanceof FormPanel)){
			uc = uc.eContainer();
		}
		return (FormPanel) uc.eContainer();
	}
	public List<Operation> getValidOperationsFor(FormPanel ui){
		if(ui instanceof ClassForm){
			ClassForm cf = (ClassForm) ui;
			Class representedClass = getRepresentedClass(cf.getFolder());
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
		}else if(ui instanceof StateForm){
			StateForm sui = (StateForm) ui;
			State state = getState(sui);
			if(state != null){
				List<Operation> results = EmfStateMachineUtil.getTriggerOperations(state);
				results.addAll(EmfStateMachineUtil.getNonTriggerOperations(EmfStateMachineUtil.getStateMachine(state)));
				return results;
			}
		}
		return new ArrayList<Operation>();
	}
	public Class getRepresentedClass(FormPanel uf){
		Element rc = getUmlElement(uf);
		if(rc instanceof Class){
			return delegateToContextIfRequired((Class) rc);
		}else{
			return null;
		}
	}
	public UimComponent getComponent(UimBinding pr){
		if(pr instanceof FieldBinding){
			return ((FieldBinding) pr).getField();
		}else if(pr instanceof TableBinding){
			return ((TableBinding) pr).getTable();
		}else if(pr instanceof NavigationBinding){
			return ((NavigationBinding) pr).getNavigation();
		}else if(pr instanceof LookupBinding){
			return ((LookupBinding) pr).getLookup().getField();
		}
		return null;
	}
	private Class delegateToContextIfRequired(Class rc){
		if((rc instanceof Behavior)){
			Behavior b = (Behavior) rc;
			if(b.getContext() != null && b.getContext().getClassifierBehavior() == b){
				return (Class) b.getContext();
			}
		}
		return rc;
	}
	public Classifier getType(NavigationBinding binding){
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
	public Collection<? extends TypedElement> getOwnedTypedElements(FormPanel nearestForm){
		Element e = getUmlElement(nearestForm);
		return EmfPropertyUtil.getOwnedTypedElements(e);
	}

}

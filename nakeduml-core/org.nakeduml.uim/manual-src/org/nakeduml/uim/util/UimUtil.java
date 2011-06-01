package org.nakeduml.uim.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.nakeduml.eclipse.EmfBehaviorUtil;
import org.nakeduml.eclipse.EmfStateMachineUtil;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.FieldBinding;
import org.nakeduml.uim.FormPanel;
import org.nakeduml.uim.LookupBinding;
import org.nakeduml.uim.NavigationBinding;
import org.nakeduml.uim.OperationContainingFolder;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.TableBinding;
import org.nakeduml.uim.UimBinding;
import org.nakeduml.uim.UimComponent;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UimForm;

public class UimUtil{
	public static TypedElement getResultingType(final UimBinding uIMBinding){
		TypedElement typedElement = null;
		if(uIMBinding.getNext() == null && UmlUimLinks.getInstance(uIMBinding).getTypedElement(uIMBinding) != null){
			typedElement = UmlUimLinks.getInstance(uIMBinding).getTypedElement(uIMBinding);
		}else if(uIMBinding.getNext() != null){
			PropertyRef pr = uIMBinding.getNext();
			while(pr.getNext() != null){
				pr = pr.getNext();
			}
			if(UmlUimLinks.getInstance(uIMBinding).getProperty(pr) != null){
				typedElement = UmlUimLinks.getInstance(uIMBinding).getProperty(pr);
			}
		}
		return typedElement;
	}
	public static Classifier getNearestClass(UimComponent uc){
		UimDataTable nearestTable = getNearestTable(uc);
		if(nearestTable == null){
			FormPanel uf = getNearestForm(uc);
			return getRepresentedClass(uf);
		}else if(nearestTable.getBinding() != null && UmlUimLinks.getInstance(uc).getTypedElement(nearestTable.getBinding()) != null){
			return (Classifier) getBindingType(nearestTable.getBinding());
		}
		return null;
	}
	public static boolean isTask(Operation op){
		return isStereotypeApplied(op, "userresponsibility", "task", "responsibility");
	}
	private static boolean isStereotypeApplied(Operation op,String...s){
		for(Stereotype st:op.getAppliedStereotypes()){
			for(String string:s){
				if(st.getName().equalsIgnoreCase(string)){
					return true;
				}
			}
		}
		for(String kw:op.getKeywords()){
			for(String string:s){
				if(kw.equalsIgnoreCase(string)){
					return true;
				}
			}
		}
		return false;
	}
	private static Classifier getBindingType(UimBinding b){
		if(b.getNext() == null || UmlUimLinks.getInstance(b).getProperty(b.getNext()) == null){
			return (Classifier) UmlUimLinks.getInstance(b).getTypedElement(b).getType();
		}else{
			return getPropertyType(b.getNext());
		}
	}
	public static boolean isMany(TypedElement typedElement){
		MultiplicityElement me = (MultiplicityElement) typedElement;
		if(typedElement instanceof Property){
			if(((Property) typedElement).getQualifiers().size() > 0){
				return true;
			}
		}
		boolean many = me.getUpper() == -1 || me.getUpper() > 1;
		return many;
	}
	private static Classifier getPropertyType(PropertyRef ref){
		if(ref.getNext() == null && UmlUimLinks.getInstance(ref).getProperty(ref.getNext()) == null){
			return (Classifier) UmlUimLinks.getInstance(ref).getProperty(ref).getType();
		}else{
			return getPropertyType(ref.getNext());
		}
	}
	public static UimDataTable getNearestTable(UimComponent uc){
		while(!(uc.getParent() instanceof UimDataTable)){
			if(uc.getParent() instanceof FormPanel){
				return null;
			}else{
				uc = (UimComponent) uc.getParent();
			}
		}
		return (UimDataTable) uc.getParent();
	}
	public static FormPanel getNearestForm(EObject uc){
		while(!(uc.eContainer() instanceof FormPanel)){
			uc = uc.eContainer();
		}
		return(FormPanel) uc.eContainer();
	}
	public static List<Operation> getValidOperationsFor(FormPanel ui){
		if(ui instanceof ClassForm){
			ClassForm cf = (ClassForm) ui;
			Class representedClass = UmlUimLinks.getInstance(ui).getRepresentedClass(cf.getFolder());
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
			State state = UmlUimLinks.getInstance(ui).getState(sui);
			if(state != null){
				List<Operation> results = EmfStateMachineUtil.getTriggerOperations(state);
				results.addAll(EmfStateMachineUtil.getNonTriggerOperations(EmfStateMachineUtil.getStateMachine(state)));
				return results;
			}
		}
		return new ArrayList<Operation>();
	}
	public static Class getRepresentedClass(FormPanel uf){
		Element rc = UmlUimLinks.getInstance(uf).getUmlElement(uf);
		if(rc instanceof Class){
			return delegateToContextIfRequired((Class) rc);
		}else{
			return null;
		}
	}
	public static UimComponent getComponent(UimBinding pr){
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
	private static Class delegateToContextIfRequired(Class rc){
		if((rc instanceof Behavior)){
			Behavior b = (Behavior) rc;
			if(b.getContext() != null && b.getContext().getClassifierBehavior() == b){
				return (Class) b.getContext();
			}
		}
		return rc;
	}
	public static Classifier getType(NavigationBinding binding){
		if(binding == null || UmlUimLinks.getInstance(binding).getTypedElement(binding) == null){
			return null;
		}else if(binding.getNext() == null || UmlUimLinks.getInstance(binding).getProperty(binding.getNext()) == null){
			return (Classifier) UmlUimLinks.getInstance(binding).getTypedElement(binding).getType();
		}else{
			return getType(binding.getNext());
		}
	}
	private static Classifier getType(PropertyRef current){
		if(current.getNext() == null || UmlUimLinks.getInstance(current).getProperty(current.getNext()) == null){
			return (Classifier) UmlUimLinks.getInstance(current).getProperty(current).getType();
		}else{
			return getType(current.getNext());
		}
	}
	public static Collection<Activity> getAllOwnedActivities(Class representedClass){
		ArrayList<Behavior> behaviors = new ArrayList<Behavior>(representedClass.getOwnedBehaviors());
		EmfBehaviorUtil.addBehaviorsRecursively(behaviors, representedClass.getGenerals());
		Collection<Activity> results = new ArrayList<Activity>();
		for(Behavior b:behaviors){
			if(b instanceof Activity){
				results.add((Activity) b);
			}
		}
		return results;
	}
	public static Collection<? extends TypedElement> getOwnedTypedElements(FormPanel nearestForm){
		Element e = SafeUmlUimLinks.getInstance(nearestForm).getUmlElement(nearestForm);
		return getOwnedTypedElements(e);
	}
	public static Collection<TypedElement> getOwnedTypedElements(Element e){
		UmlUimLinks links = SafeUmlUimLinks.getInstance(e);
		if(e instanceof Class){
			Class class1 = (Class) e;
			if(class1 != null){
				return new ArrayList<TypedElement>( links.getOwnedAttributes(class1));
			}
		}else if(e instanceof State){
			State class1 = (State) e;
			if(class1 != null){
				return new ArrayList<TypedElement>( EmfStateMachineUtil.getStateMachine(class1).getAllAttributes());
			}
		}else if(e instanceof OperationInvocationForm){
			Operation operation = (Operation) e;
			if(operation != null){
				return new ArrayList<TypedElement>( operation.getOwnedParameters());
			}
		}else if(e instanceof OperationTaskForm){
			Operation operation = (Operation) e;
			if(operation != null){
				return new ArrayList<TypedElement>( operation.getOwnedParameters());
			}
		}else if(e instanceof OpaqueAction){
			OpaqueAction action = (OpaqueAction) e;
			if(action != null){
				Collection<Pin> result = new HashSet<Pin>(action.getInputs());
				result.addAll(action.getOutputs());
			}
		}
		return Collections.emptySet();
	}
	public static boolean isComposite(TypedElement t){
		if(t instanceof Property){
			Property p = (Property) t;
			return p.isComposite();
		}
		return false;
	}
}

package org.nakeduml.uim.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.Vertex;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.ControlKind;
import org.nakeduml.uim.FieldBinding;
import org.nakeduml.uim.FormPanel;
import org.nakeduml.uim.LookupBinding;
import org.nakeduml.uim.NavigationBinding;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.TableBinding;
import org.nakeduml.uim.UIMBinding;
import org.nakeduml.uim.UIMComponent;
import org.nakeduml.uim.UIMControl;
import org.nakeduml.uim.UIMDataTable;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UIMForm;

public class UimUtil {

	public static TypedElement getResultingType(final UIMBinding uIMBinding) {
		TypedElement typedElement = null;
		if (uIMBinding.getNext() == null && uIMBinding.getElement() != null) {
			typedElement = uIMBinding.getElement();
		} else if (uIMBinding.getNext() != null) {
			PropertyRef pr = uIMBinding.getNext();
			while (pr.getNext() != null) {
				pr = pr.getNext();
			}
			if (pr.getProperty() != null) {
				typedElement = (Property) pr.getProperty();
			}
		}
		return typedElement;
	}

	public static Classifier getNearestClass(UIMComponent uc) {
		UIMDataTable nearestTable = getNearestTable(uc);
		if (nearestTable == null) {
			UIMForm uf = getNearestForm(uc);
			return getRepresentedClass(uf);
		} else if (nearestTable.getBinding() != null
				&& nearestTable.getBinding().getElement() != null) {
			return (Classifier) getBindingType(nearestTable.getBinding());
		}
		return null;
	}

	public static boolean isTask(Operation op) {
		// TODO expand to stereotypes

		return op.getKeywords().contains("task");
	}

	private static Classifier getBindingType(UIMBinding b) {
		if (b.getNext() == null || b.getNext().getProperty() == null) {
			return (Classifier) b.getElement().getType();
		} else {
			return getPropertyType(b.getNext());
		}
	}

	public static boolean isMany(TypedElement typedElement) {
		MultiplicityElement me = (MultiplicityElement) typedElement;
		boolean many = me.getUpper() == -1 || me.getUpper() > 1;
		return many;
	}

	private static Classifier getPropertyType(PropertyRef ref) {
		if (ref.getNext() == null && ref.getNext().getProperty() == null) {
			return (Classifier) ref.getProperty().getType();
		} else {
			return getPropertyType(ref.getNext());
		}
	}

	public static UIMDataTable getNearestTable(UIMComponent uc) {
		while (!(uc.getParent() instanceof UIMDataTable)) {
			if (uc.getParent() instanceof FormPanel) {
				return null;
			} else {
				uc = (UIMComponent) uc.getParent();
			}
		}
		return (UIMDataTable) uc.getParent();
	}

	public static UIMForm getNearestForm(UIMComponent uc) {
		while (!(uc.getParent() instanceof FormPanel)) {
			uc = (UIMComponent) uc.getParent();
		}
		return ((FormPanel) uc.getParent()).getForm();
	}

	public static List<Operation> getValidOperationsFor(UIMForm ui) {
		if (ui instanceof ClassForm) {
			ClassForm cf = (ClassForm) ui;
			Class representedClass = cf.getFolder().getRepresentedClass();
			if (representedClass != null) {
				if (representedClass instanceof Behavior) {
					Behavior sm = (Behavior) representedClass;
					if (sm.getContext() != null
							&& sm.getContext().getClassifierBehavior() == sm) {
						return sm.getContext().getAllOperations();
					}
				}
				EList<Operation> allOps = representedClass.getAllOperations();
				return allOps;
			}
		} else if (ui instanceof StateForm) {
			StateForm sui = (StateForm) ui;
			State state = sui.getState();
			if (state != null) {
				List<Operation> results = StateMachineUtil
						.getTriggerOperations(state);
				results.addAll(StateMachineUtil
						.getNonTriggerOperations(StateMachineUtil
								.getStateMachine(state)));
				return results;
			}
		}
		return new ArrayList<Operation>();
	}

	public static Class getRepresentedClass(UIMForm uf) {
		if (uf instanceof ClassForm) {
			ClassForm cf = (ClassForm) uf;
			Class rc = cf.getFolder().getRepresentedClass();
			return delegateToContextIfRequired(rc);
		} else if (uf instanceof StateForm) {
			StateForm sui = (StateForm) uf;
			return delegateToContextIfRequired(sui.getFolder()
					.getStateMachine());
		} else if (uf instanceof OperationInvocationForm) {
			OperationInvocationForm oif = (OperationInvocationForm) uf;
			return delegateToContextIfRequired(oif.getFolder()
					.getRepresentedClass());
		} else if (uf instanceof OperationTaskForm) {
			OperationTaskForm oif = (OperationTaskForm) uf;
			return oif.getFolder().getEntity();
		}
		return null;

	}

	public static UIMComponent getComponent(UIMBinding pr) {
		if (pr instanceof FieldBinding) {
			return ((FieldBinding) pr).getField();
		} else if (pr instanceof TableBinding) {
			return ((TableBinding) pr).getTable();
		} else if (pr instanceof NavigationBinding) {
			return ((NavigationBinding) pr).getNavigation();
		} else if (pr instanceof LookupBinding) {
			return ((LookupBinding) pr).getLookup().getField();
		}
		return null;
	}

	private static Class delegateToContextIfRequired(Class rc) {
		if ((rc instanceof Behavior)) {
			Behavior b = (Behavior) rc;
			if (b.getContext() != null
					&& b.getContext().getClassifierBehavior() == b) {
				return (Class) b.getContext();
			}
		}
		return rc;
	}

	public static Classifier getType(NavigationBinding binding) {
		if (binding == null || binding.getElement() == null) {
			return null;
		} else if (binding.getNext() == null
				|| binding.getNext().getProperty() == null) {
			return (Classifier) binding.getElement().getType();
		} else {
			return getType(binding.getNext());
		}
	}

	private static Classifier getType(PropertyRef current) {
		if (current.getNext() == null
				|| current.getNext().getProperty() == null) {
			return (Classifier) current.getProperty().getType();
		} else {
			return getType(current.getNext());
		}
	}

	public static Collection<Activity> getAllOwnedActivities(
			Class representedClass) {
		ArrayList<Behavior> behaviors = new ArrayList<Behavior>(
				representedClass.getOwnedBehaviors());
		addBehaviorsRecursively(behaviors, representedClass.getGenerals());
		Collection<Activity> results = new ArrayList<Activity>();
		for (Behavior b : behaviors) {
			if (b instanceof Activity) {
				results.add((Activity) b);
			}
		}
		return results;
	}

	static void addBehaviorsRecursively(ArrayList<Behavior> behaviors,
			EList<Classifier> generals) {
		for (Classifier c : generals) {
			if (c instanceof Class) {
				behaviors.addAll(((Class) c).getOwnedBehaviors());
				addBehaviorsRecursively(behaviors, c.getGenerals());
			}
		}
	}

}

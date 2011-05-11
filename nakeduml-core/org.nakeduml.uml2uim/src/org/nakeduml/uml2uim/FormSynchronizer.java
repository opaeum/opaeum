package org.nakeduml.uml2uim;

import java.util.ArrayList;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.name.NameConverter;
import org.nakeduml.uim.ActionKind;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.ActivityFolder;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.EntityFolder;
import org.nakeduml.uim.OperationContainingFolder;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.StateMachineFolder;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UIMForm;
import org.nakeduml.uim.UserInteractionModel;
import org.nakeduml.uim.util.StateMachineUtil;
import org.nakeduml.uim.util.UimUtil;
import org.topcased.modeler.diagrams.model.Diagrams;
@StepDependency(phase=UserInteractionSynchronizationPhase.class, requires=FormFolderSynchronizer.class,after=FormFolderSynchronizer.class)
public class FormSynchronizer extends AbstractUimSynchronizer {
	public FormSynchronizer(){
		
	}
	public FormSynchronizer(UserInteractionModel model, Diagrams d,
			boolean regenerate) {
		super(model, d, regenerate);

	}

//	@VisitBefore(matchSubclasses = false)
//	public void beforeState(State s) {
//		StateForm sf = (StateForm) map.get(s);
//		if (sf == null) {
//			sf = UIMFactory.eINSTANCE.createStateForm();
//			sf.setState(s);
//			initForm(s, sf);
//		}
//		StateMachineFolder parentFolder = (StateMachineFolder) map.get(StateMachineUtil
//				.getStateMachine(s));
//		if (parentFolder != sf.getFolder()) {
//			parentFolder.getStateForms().add(sf);
//		}
//		sf.setName(s.getName());
//		if (regenerate || sf.getPanel() == null) {
//			removeOldDiagrams(sf);
//			FormCreator fc = new FormCreator(diagrams);
//			EList<Property> allAttributes = UimUtil.getRepresentedClass(sf)
//					.getAllAttributes();
//			fc.prepareFormPanel(sf, NameConverter.separateWords(s.getName()),
//					allAttributes);
//			fc.addButtonBar(ActionKind.UPDATE, ActionKind.DELETE,
//					ActionKind.BACK);
//		}
//	}
//
//	private void removeOldDiagrams(UIMForm sf) {
//		if (diagramMap.containsKey(sf)) {
//			Diagrams oldDiagrams = super.diagramMap.get(sf);
//			diagramMap.remove(sf);
//			oldDiagrams.setParent(null);
//		}
//	}
//
//	private void initForm(NamedElement s, UIMForm sf) {
//		map.putLinkForForm(s, sf);
//		sf.setName(s.getName());
//	}
//
//	@VisitBefore(matchSubclasses = false)
//	public void beforeAction(OpaqueAction a) {
//		ActionTaskForm atf = (ActionTaskForm) map.get(a);
//		if (atf == null) {
//			atf = UIMFactory.eINSTANCE.createActionTaskForm();
//			atf.setAction(a);
//			initForm(a, atf);
//		}
//		ActivityFolder parentFolder = (ActivityFolder) map.get(a.getActivity());
//		if (parentFolder != atf.getFolder()) {
//			parentFolder.getActionTaskForms().add(atf);
//		}
//		if (regenerate || atf.getPanel() == null) {
//			removeOldDiagrams(atf);
//
//			// TODO make input entities editable through inputs
//			FormCreator fc = new FormCreator(diagrams);
//			ArrayList<TypedElement> pins = new ArrayList<TypedElement>(a
//					.getInputs());
//			pins.addAll(a.getOutputs());
//			fc.prepareFormPanel(atf, "Task: "
//					+ NameConverter.separateWords(a.getName()), pins);
//			fc.addButtonBar(ActionKind.COMPLETE_TASK, ActionKind.POSTPONE_TASK,
//					ActionKind.RETURN_TASK);
//		}
//	}
//
//	@VisitBefore(matchSubclasses = false)
//	public void beforeClass(Class c) {
//		EntityFolder eFolder = (EntityFolder) this.map.get(c);
//		if (regenerate) {
//			for (ClassForm cf : eFolder.getClassForm()) {
//				removeOldDiagrams(cf);
//			}
//			eFolder.getClassForm().clear();
//		}
//		if (eFolder.getClassForm().isEmpty()) {
//			ClassForm editForm = UIMFactory.eINSTANCE.createClassForm();
//			eFolder.getClassForm().add(editForm);
//			editForm.setName(c.getName() + "Editor");
//			FormCreator fc = new FormCreator(diagrams);
//			EList<Property> allAttributes = c.getAllAttributes();
//			fc.prepareFormPanel(editForm, "Edit "
//					+ NameConverter.separateWords(c.getName()), allAttributes);
//			fc.addButtonBar(ActionKind.UPDATE, ActionKind.DELETE,
//					ActionKind.BACK);
//
//			ClassForm createForm = UIMFactory.eINSTANCE.createClassForm();
//			eFolder.getClassForm().add(createForm);
//			createForm.setName(c.getName() + "Creator");
//			fc.prepareFormPanel(createForm, "Create "
//					+ NameConverter.separateWords(c.getName()), allAttributes);
//			fc.addButtonBar(ActionKind.CREATE, ActionKind.BACK);
//		}
//	}
//
//	@VisitBefore(matchSubclasses = false)
//	public void beforeOperation(Operation o) {
//		// TODO support task forms in UML model
//		boolean isTaskForm = false;
//		if (isTaskForm) {
//			OperationTaskForm otf = (OperationTaskForm) map.get(o);
//			if (otf == null) {
//				otf = UIMFactory.eINSTANCE.createOperationTaskForm();
//				otf.setOperation(o);
//				initForm(o, otf);
//			}
//			EntityFolder parentFolder = (EntityFolder) map.get(o.getOwner());
//			if (parentFolder != otf.getFolder()) {
//				parentFolder.getOperationTaskForms().add(otf);
//			}
//			if (regenerate || otf.getPanel() == null) {
//				removeOldDiagrams(otf);
//				// TODO generate inputs for properties of input entities
//				FormCreator fc = new FormCreator(diagrams);
//				fc.prepareFormPanel(otf, "Task: "
//						+ NameConverter.separateWords(o.getName()), o
//						.getOwnedParameters());
//				fc.addButtonBar(ActionKind.COMPLETE_TASK,
//						ActionKind.POSTPONE_TASK, ActionKind.RETURN_TASK);
//			}
//		} else {
//			//TODO generate table Panels for multi output parameters and detail panels for single output parameters
//			OperationInvocationForm oif = (OperationInvocationForm) map.get(o);
//			if (oif == null) {
//				oif = UIMFactory.eINSTANCE.createOperationInvocationForm();
//				oif.setOperation(o);
//				initForm(o, oif);
//			}
//			OperationContainingFolder parentFolder = (OperationContainingFolder) map
//					.get(o.getOwner());
//			if (parentFolder != oif.getFolder()) {
//				parentFolder.getOperationInvocationForms().add(oif);
//			}
//			if (regenerate || oif.getPanel() == null) {
//				removeOldDiagrams(oif);
//
//				FormCreator fc = new FormCreator(diagrams);
//				fc.prepareFormPanel(oif, "Invoke: "
//						+ NameConverter.separateWords(o.getName()), o
//						.getOwnedParameters());
//				fc.addButtonBar(ActionKind.EXECUTE_OPERATION, ActionKind.BACK);
//			}
//		}
//	}
//
//	@VisitAfter
//	public void afterModel(Model model) {
//		setActiveDiagram(this.diagrams);
//
//	}
//
//	private void setActiveDiagram(Diagrams diagrams) {
//		diagrams.setActiveDiagram(diagrams.getDiagrams().isEmpty() ? null
//				: diagrams.getDiagrams().get(0));
//		for (Diagrams child : diagrams.getSubdiagrams()) {
//			setActiveDiagram(child);
//		}
//	}
}

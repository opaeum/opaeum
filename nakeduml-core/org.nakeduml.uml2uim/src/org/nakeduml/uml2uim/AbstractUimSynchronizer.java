package org.nakeduml.uml2uim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.emf.extraction.EmfElementVisitor;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitSpec;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.ActivityFolder;
import org.nakeduml.uim.EntityFolder;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.PackageFolder;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.StateMachineFolder;
import org.nakeduml.uim.UserInteractionElement;
import org.nakeduml.uim.UserInteractionModel;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.diagrams.model.Diagrams;

public class AbstractUimSynchronizer extends EmfElementVisitor implements TransformationStep{
	UmlUimLinks links;
	EmfWorkspace emfWorkspace;
	UserInteractionModel model;
	Map<UserInteractionElement, Diagrams> diagramMap = new HashMap<UserInteractionElement, Diagrams>();
	Diagrams diagrams;
	protected boolean regenerate;
	public AbstractUimSynchronizer(){
		
	}
	public AbstractUimSynchronizer(UserInteractionModel model, Diagrams diagrams, boolean regenerate) {
		init(model, diagrams, regenerate);
	}

	public void init(UserInteractionModel model,Diagrams diagrams,boolean regenerate){
		this.model = model;
		this.regenerate = regenerate;
		this.diagrams = diagrams;
		TreeIterator<EObject> dgmIter = diagrams.eAllContents();
		List<Diagrams> unwantedDiagrams = new ArrayList<Diagrams>();
		while (dgmIter.hasNext()) {
			EObject next = dgmIter.next();
			if (next instanceof Diagrams) {
				Diagrams child = (Diagrams) next;
				if (child.getDiagrams().size() >= 1) {
					EMFSemanticModelBridge esm = (EMFSemanticModelBridge) child.getDiagrams().get(0).getSemanticModel();
					UserInteractionElement uime = (UserInteractionElement) esm.getElement();
					if (diagramMap.containsKey(uime)) {
						// TODO move elsewhere
						unwantedDiagrams.add(child);
					} else {
						diagramMap.put(uime, child);
					}
				} else {
					unwantedDiagrams.add(child);
				}
			}
		}
		for (Diagrams dgsms : unwantedDiagrams) {
			dgsms.setParent(null);
		}
	}

	protected void safeguardParentVisitBefore(Element parent) {
		if (parent != null) {

			safeguardParentVisitBefore(parent.getOwner());
			for (VisitSpec v : beforeMethods) {
				maybeVisit(parent, v);
			}
		}
	}

	public void visitSafelyRecursively(Element element) {
		safeguardParentVisitBefore(element.getOwner());
		visitRecursively(element);
		safeguardParentVisitAfter(element.getOwner());
	}

	private void safeguardParentVisitAfter(Element owner) {
		if (owner != null) {
			for (VisitSpec v : afterMethods) {
				maybeVisit(owner, v);
			}
			safeguardParentVisitAfter(owner.getOwner());
		}

	}

	@Override
	public Collection<? extends Element> getChildren(Element root) {
		if (root instanceof EmfWorkspace) {
			return ((EmfWorkspace) root).getGeneratingModelsOrProfiles();
		} else {
			return super.getChildren(root);
		}
	}

}

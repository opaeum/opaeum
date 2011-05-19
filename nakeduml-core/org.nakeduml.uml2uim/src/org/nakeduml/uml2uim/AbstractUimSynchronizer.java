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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.State;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.ActivityFolder;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.EntityFolder;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.PackageFolder;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.StateMachineFolder;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UIMForm;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.UserInteractionElement;
import org.nakeduml.uim.UserInteractionModel;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.DiagramsFactory;

public class AbstractUimSynchronizer extends EmfElementVisitor implements TransformationStep{
	UmlUimLinks links;
	ResourceSet resourceSet;
	EmfWorkspace emfWorkspace;
	Map<UserInteractionElement,Diagrams> diagramMap = new HashMap<UserInteractionElement,Diagrams>();
	protected boolean regenerate;
	public AbstractUimSynchronizer(){
	}
	public AbstractUimSynchronizer(ResourceSet resourceSet,boolean regenerate){
		init(resourceSet, regenerate);
	}
	public void init(ResourceSet resourceSet,boolean regenerate){
		this.regenerate = regenerate;
		this.resourceSet = resourceSet;
		TreeIterator<EObject> dgmIter = diagrams.eAllContents();
		List<Diagrams> unwantedDiagrams = new ArrayList<Diagrams>();
		while(dgmIter.hasNext()){
			EObject next = dgmIter.next();
			if(next instanceof Diagrams){
				Diagrams child = (Diagrams) next;
				if(child.getDiagrams().size() >= 1){
					EMFSemanticModelBridge esm = (EMFSemanticModelBridge) child.getDiagrams().get(0).getSemanticModel();
					UserInteractionElement uime = (UserInteractionElement) esm.getElement();
					if(diagramMap.containsKey(uime)){
						// TODO move elsewhere
						unwantedDiagrams.add(child);
					}else{
						diagramMap.put(uime, child);
					}
				}else{
					unwantedDiagrams.add(child);
				}
			}
		}
		for(Diagrams dgsms:unwantedDiagrams){
			dgsms.setParent(null);
		}
	}
	protected void safeguardParentVisitBefore(Element parent){
		if(parent != null){
			safeguardParentVisitBefore(parent.getOwner());
			for(VisitSpec v:beforeMethods){
				maybeVisit(parent, v);
			}
		}
	}
	public void visitSafelyRecursively(Element element){
		safeguardParentVisitBefore(element.getOwner());
		visitRecursively(element);
		safeguardParentVisitAfter(element.getOwner());
	}
	private void safeguardParentVisitAfter(Element owner){
		if(owner != null){
			for(VisitSpec v:afterMethods){
				maybeVisit(owner, v);
			}
			safeguardParentVisitAfter(owner.getOwner());
		}
	}
	@Override
	public Collection<? extends Element> getChildren(Element root){
		if(root instanceof EmfWorkspace){
			return ((EmfWorkspace) root).getGeneratingModelsOrProfiles();
		}else{
			return super.getChildren(root);
		}
	}
}

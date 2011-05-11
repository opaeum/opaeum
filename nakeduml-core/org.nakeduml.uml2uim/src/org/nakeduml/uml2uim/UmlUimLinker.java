package org.nakeduml.uml2uim;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.nakeduml.uim.UIMBinding;
import org.nakeduml.uim.UIMForm;
import org.nakeduml.uim.UmlReference;

@StepDependency(phase = UserInteractionSynchronizationPhase.class)
public class UmlUimLinker extends AbstractUimSynchronizer{
	UmlUimLinks links;
	UIMForm form;
	public void putFormElements(UIMForm form){
		TreeIterator<EObject> eAllContents = form.eAllContents();
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof UmlReference){
				links.putLinkForForm(form, (UIMBinding)eObject);
			}
		}
	}
	@VisitBefore
	public void visitModel(Model model){
		links.putLinkForModel(model);
		TreeIterator<EObject> eAllContents = model.eAllContents();
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof org.eclipse.uml2.uml.Package || eObject instanceof Classifier){
				links.putLinkForModel((Namespace) eObject);
			}
			if(eObject instanceof Classifier || eObject instanceof Property || eObject instanceof Operation || eObject instanceof Parameter
					|| eObject instanceof OpaqueAction || eObject instanceof Pin || eObject instanceof State || eObject instanceof Transition){
				links.putLinkForForm(form, (Element) eObject);
			}
		}
	}
}

package org.opaeum.uim.uml2uim;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.opaeum.feature.StepDependency;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.action.TransitionButton;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.component.UimContainer;

@StepDependency(phase = UimSynchronizationPhase.class)
public class UimCleanup extends AbstractUimSynchronizer{
	public void putFormElements(ResourceSet rst){
		TreeIterator<Notifier> allContents = rst.getAllContents();
		while(allContents.hasNext()){
			Notifier ur = (Notifier) allContents.next();
			if(ur instanceof UmlReference && workspace.getModelElement(((UmlReference) ur).getUmlElementUid())==null){
				if(ur instanceof FieldBinding){
					FieldBinding fb = (FieldBinding) ur;
					UimContainer parent = fb.getField().getParent();
					parent.getChildren().remove(fb.getField());
				}
				if(ur instanceof TableBinding){
					TableBinding fb = (TableBinding) ur;
					UimContainer parent = fb.getTable().getParent();
					parent.getChildren().remove(fb.getTable());
				}
				if(ur instanceof InvocationButton || ur instanceof TransitionButton){
					AbstractActionButton oa = (AbstractActionButton) ur;
					UimContainer parent = oa.getParent();
					parent.getChildren().remove(oa);
				}
			}
		}
	}
}

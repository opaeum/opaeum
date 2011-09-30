package org.opeum.uml2uim;

import org.opeum.feature.StepDependency;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.opeum.uim.UmlReference;
import org.opeum.uim.action.OperationAction;
import org.opeum.uim.action.TransitionAction;
import org.opeum.uim.action.UimAction;
import org.opeum.uim.binding.FieldBinding;
import org.opeum.uim.binding.NavigationBinding;
import org.opeum.uim.binding.TableBinding;
import org.opeum.uim.layout.UimLayout;

@StepDependency(phase = UimSynchronizationPhase.class)
public class UimCleanup extends AbstractUimSynchronizer{
	public void putFormElements(ResourceSet rst){
		TreeIterator<Notifier> allContents = rst.getAllContents();
		while(allContents.hasNext()){
			Notifier ur = (Notifier) allContents.next();
			if(ur instanceof UmlReference && workspace.getElement(((UmlReference) ur).getUmlElementUid())==null){
				if(ur instanceof FieldBinding){
					FieldBinding fb = (FieldBinding) ur;
					UimLayout parent = fb.getField().getParent();
					parent.getChildren().remove(fb.getField());
				}
				if(ur instanceof TableBinding){
					TableBinding fb = (TableBinding) ur;
					UimLayout parent = fb.getTable().getParent();
					parent.getChildren().remove(fb.getTable());
				}
				if(ur instanceof NavigationBinding){
					NavigationBinding fb = (NavigationBinding) ur;
					UimLayout parent = fb.getNavigation().getParent();
					parent.getChildren().remove(fb.getNavigation());
				}
				if(ur instanceof OperationAction || ur instanceof TransitionAction){
					UimAction oa = (UimAction) ur;
					UimLayout parent = oa.getParent();
					parent.getChildren().remove(oa);
				}
			}
		}
	}
}

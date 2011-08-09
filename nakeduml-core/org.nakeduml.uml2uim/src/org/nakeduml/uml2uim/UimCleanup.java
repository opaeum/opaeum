package org.nakeduml.uml2uim;

import net.sf.nakeduml.feature.StepDependency;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.action.OperationAction;
import org.nakeduml.uim.action.TransitionAction;
import org.nakeduml.uim.action.UimAction;
import org.nakeduml.uim.binding.FieldBinding;
import org.nakeduml.uim.binding.NavigationBinding;
import org.nakeduml.uim.binding.TableBinding;
import org.nakeduml.uim.layout.UimLayout;

@StepDependency(phase = UimSynchronizationPhase.class)
public class UimCleanup extends AbstractUimSynchronizer{
	public void putFormElements(ResourceSet rst){
		TreeIterator<Notifier> allContents = rst.getAllContents();
		while(allContents.hasNext()){
			Notifier ur = (Notifier) allContents.next();
			if(ur instanceof UmlReference && workspace.getElementMap().get(((UmlReference) ur).getUmlElementUid())==null){
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

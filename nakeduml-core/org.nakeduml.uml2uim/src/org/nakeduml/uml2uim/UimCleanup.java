package org.nakeduml.uml2uim;

import java.util.Collection;

import net.sf.nakeduml.feature.StepDependency;

import org.nakeduml.uim.FieldBinding;
import org.nakeduml.uim.NavigationBinding;
import org.nakeduml.uim.OperationAction;
import org.nakeduml.uim.TableBinding;
import org.nakeduml.uim.TransitionAction;
import org.nakeduml.uim.UIMAction;
import org.nakeduml.uim.UIMContainer;
import org.nakeduml.uim.UIMForm;
import org.nakeduml.uim.UmlReference;

@StepDependency(phase = UserInteractionSynchronizationPhase.class)
public class UimCleanup extends AbstractUimSynchronizer{
	UmlUimLinks links;
	UIMForm form;
	public void putFormElements(UIMForm form){
		Collection<UmlReference> brokenLinks = links.getBrokenLinks(form);
		for(UmlReference ur:brokenLinks){
			if(ur instanceof FieldBinding){
				FieldBinding fb = (FieldBinding) ur;
				UIMContainer parent = fb.getField().getParent();
				parent.getChildren().remove(fb.getField());
			}
			if(ur instanceof TableBinding){
				TableBinding fb = (TableBinding) ur;
				UIMContainer parent = fb.getTable().getParent();
				parent.getChildren().remove(fb.getTable());
			}
			if(ur instanceof NavigationBinding){
				NavigationBinding fb = (NavigationBinding) ur;
				UIMContainer parent = fb.getNavigation().getParent();
				parent.getChildren().remove(fb.getNavigation());
			}
			if(ur instanceof OperationAction ||ur instanceof TransitionAction){
				UIMAction oa = (UIMAction) ur;
				UIMContainer parent = oa.getParent();
				parent.getChildren().remove(oa);
			}
		}
	}
}

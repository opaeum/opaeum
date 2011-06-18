package org.nakeduml.uml2uim;

import java.util.Collection;

import net.sf.nakeduml.feature.StepDependency;

import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.action.OperationAction;
import org.nakeduml.uim.action.TransitionAction;
import org.nakeduml.uim.action.UimAction;
import org.nakeduml.uim.binding.FieldBinding;
import org.nakeduml.uim.binding.NavigationBinding;
import org.nakeduml.uim.binding.TableBinding;
import org.nakeduml.uim.form.UimForm;
import org.nakeduml.uim.layout.UimLayout;
import org.nakeduml.uim.util.UmlUimLinks;

@StepDependency(phase = UserInteractionSynchronizationPhase.class)
public class UimCleanup extends AbstractUimSynchronizer{
	UmlUimLinks links;
	UimForm form;
	public void putFormElements(UimForm form){
		Collection<UmlReference> brokenLinks = links.getBrokenLinks();
		for(UmlReference ur:brokenLinks){
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
			if(ur instanceof OperationAction ||ur instanceof TransitionAction){
				UimAction oa = (UimAction) ur;
				UimLayout parent = oa.getParent();
				parent.getChildren().remove(oa);
			}
		}
	}
}

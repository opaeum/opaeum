package net.sf.nakeduml.uigeneration;

import java.util.Collection;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionElement;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

public class UserInteractionCleanup extends VisitorAdapter<UserInteractionElement,UserInteractionWorkspace>{
	@Override
	public Collection<? extends UserInteractionElement> getChildren(UserInteractionElement root){
		return root.getOwnedElement();
	}
	@VisitAfter
	public void visitInteraction(ClassifierUserInteraction ui){
		boolean listOrCreate = ui.getUserInteractionKind() == UserInteractionKind.CREATE
				|| ui.getUserInteractionKind() == UserInteractionKind.LIST;
		if(listOrCreate){
			if(ui.getOriginatingPropertyNavigation() == null){
				ui.getFolder().getEntityUserInteraction().remove(ui);
			}
		}
	}
}

package net.sf.nakeduml.seamgeneration.jsf;

import java.util.Properties;

import net.sf.nakeduml.seamgeneration.UserInteractionElementVisitor;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

public class AbstractBuilder extends UserInteractionElementVisitor {
	public static final String VIEW_DIR = "gen-view";
	protected Properties namespaceProperties;
	protected boolean isFromList(ClassifierUserInteraction ui) {
		return ui.getOriginatingPropertyNavigation() != null
				&& ui.getOriginatingPropertyNavigation().getResultingUserInteraction().getUserInteractionKind() == UserInteractionKind.LIST;
	}

}

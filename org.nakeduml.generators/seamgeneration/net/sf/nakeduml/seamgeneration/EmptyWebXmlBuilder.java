package net.sf.nakeduml.seamgeneration;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.jboss.seam.core.Expressions;
import org.jboss.seam.navigation.Navigation;
import org.jboss.seam.navigation.Page;
import org.jboss.seam.navigation.Param;
import org.jboss.seam.navigation.Rule;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.seamgeneration.page.SeamPageBuilder;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.textmetamodel.TextSource;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

@StepDependency(phase = SeamTransformationPhase.class, after = SeamPageBuilder.class)
public class EmptyWebXmlBuilder extends UserInteractionElementVisitor {

	public static final String VIEW_DIR = "gen-view";
	
	@Override
	public void initialize(UserInteractionWorkspace workspace,NakedUmlConfig config,TextWorkspace textWorkspace){
		super.initialize(workspace, config, textWorkspace);
	}
	
	@VisitAfter
	public void visitAfterWorkspace(UserInteractionWorkspace e) {
		TextOutputRoot outputRoot = textWorkspace.findOrCreateTextOutputRoot(VIEW_DIR);
		List<String> path = new ArrayList<String>();
		path.add("WEB-INF");
		path.add("web.xml");
		outputRoot.findOrCreateTextFile(path, new TextSource() {
			
			@Override
			public char[] toCharArray() {
				// TODO Auto-generated method stub
				return "<!-- empty, required for maven war overlay jol -->".toCharArray();
			}
			
			@Override
			public boolean hasContent() {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}	
}

package net.sf.nakeduml.seamgeneration.jsf;

import java.util.List;

import javax.faces.component.UIViewRoot;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.seamgeneration.jsf.source.JsfMenuSource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

@StepDependency(phase = SeamTransformationPhase.class,after=JsfMenuBuilder.class)
public class JsfContainmentBuilder extends AbstractJsfBuilder {

	private UIViewRoot jsfContainment;
	
	@VisitBefore
	public void populateJSFBody(ClassifierUserInteraction ui) {
		jsfContainment = new UIViewRoot();
		if (ui.getUserInteractionKind()!=UserInteractionKind.LIST) {
			generateContaintmentNavigations(ui.getClassifier());
		} else {
		}
	}

	@VisitAfter
	public void generateEntityXhtml(ClassifierUserInteraction ui) {
		TextOutputRoot outputRoot=textWorkspace.findOrCreateTextOutputRoot(VIEW_DIR);
		List<String> path = resolveViewId(ui,".containment.xhtml");
		outputRoot.findOrCreateTextFile(path, new JsfMenuSource(jsfContainment,path.size()-1, namespaceProperties));
	}

	private void generateContaintmentNavigations(DomainClassifier classifier) {
	}

	@Override
	protected String getEditRenderedRoot(DomainClassifier dc) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
 
package net.sf.nakeduml.seamgeneration.jsf;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.seamgeneration.jsf.source.JsfSource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;

@StepDependency(phase = SeamTransformationPhase.class,before=JsfEditBuilder.class,requires=JsfEditBuilder.class)
public class JsfBuilder extends AbstractBuilder {
	@VisitAfter
	public void generateEntityXhtml(ClassifierUserInteraction ui) {
		String body = NameConverter.decapitalize(ui.getName());
		String menu = NameConverter.decapitalize(ui.getName());
		TextOutputRoot outputRoot=textWorkspace.findOrCreateTextOutputRoot(VIEW_DIR);
		List<String> path = resolveViewId(ui,".xhtml");
		outputRoot.findOrCreateTextFile(
				path, 
				new JsfSource(
						menu, 
						body,
						path.size()-1, 
						namespaceProperties));
	}
	
}

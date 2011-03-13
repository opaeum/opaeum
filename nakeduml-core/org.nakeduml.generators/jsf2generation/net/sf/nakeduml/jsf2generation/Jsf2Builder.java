package net.sf.nakeduml.jsf2generation;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.jsf2generation.source.Jsf2Source;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.AbstractBuilder;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;

@StepDependency(phase=Jsf2TransformationPhase.class,before=Jsf2EditBuilder.class,requires=Jsf2EditBuilder.class)
public class Jsf2Builder extends AbstractBuilder {
	@VisitAfter
	public void generateEntityXhtml(ClassifierUserInteraction ui) {
		String body = NameConverter.decapitalize(ui.getName());
		String menu = NameConverter.decapitalize(ui.getName());
		TextOutputRoot outputRoot=textWorkspace.findOrCreateTextOutputRoot(VIEW_DIR);
		List<String> path = resolveViewId(ui,".xhtml");
		outputRoot.findOrCreateTextFile(
				path, 
				new Jsf2Source(
						menu, 
						body,
						path.size()-1, 
						namespaceProperties));
	}
	
}

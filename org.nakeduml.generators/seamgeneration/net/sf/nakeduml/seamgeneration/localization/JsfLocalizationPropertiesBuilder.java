package net.sf.nakeduml.seamgeneration.localization;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.seamgeneration.jsf.AbstractBuilder;
import net.sf.nakeduml.seamgeneration.page.SeamCreatePageBuilder;
import net.sf.nakeduml.textmetamodel.PropertiesSource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

@StepDependency(phase = SeamTransformationPhase.class, after = SeamCreatePageBuilder.class)
public class JsfLocalizationPropertiesBuilder extends AbstractBuilder {
	
	private Properties p = new Properties();
	
	@VisitBefore
	public void beforeClassifierUserInteraction(ClassifierUserInteraction ui){
		
		List<PropertyNavigation> properties = ui.getPropertyNavigation();
		for (PropertyNavigation propertyNavigation : properties) {
			p.put(propertyNavigation.getName(), propertyNavigation.getHumanName());
		}
		
		List<PropertyField> propertyFields = ui.getPropertyField();
		for (PropertyField propertyField : propertyFields) {
			p.put(propertyField.getName(), propertyField.getHumanName());
		}		
	}

	@VisitAfter
	public void afterWorkspace(UserInteractionWorkspace e) {
		TextOutputRoot outputRoot = textWorkspace.findOrCreateTextOutputRoot(CharArrayTextSource.WEB_RESOURCE);
		List<String> path = Arrays.asList("application_messages.properties");
		outputRoot.findOrCreateTextFile(path, new PropertiesSource(p));
	}	
}

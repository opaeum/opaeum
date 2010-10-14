package net.sf.nakeduml.seamgeneration.jsf;

import javax.faces.component.UIViewRoot;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.linkage.SourcePopulationResolver;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.seamgeneration.jsf.component.JsfFactoryInputOutput;
import net.sf.nakeduml.seamgeneration.page.SeamEditPageBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;

import org.jboss.seam.ui.component.html.HtmlDiv;
import org.jboss.seam.ui.component.html.HtmlFragment;

@StepDependency(phase = SeamTransformationPhase.class, requires = {JsfContainmentBuilder.class, JsfListBuilder.class,JsfMenuBuilder.class, SeamEditPageBuilder.class, SourcePopulationResolver.class })
public class JsfEditBuilder extends AbstractJsfBuilder {
	@VisitBefore
	public void beforeClassifierUserInteraction(ClassifierUserInteraction ui) {
		jsfBody = new UIViewRoot();
		switch (ui.getUserInteractionKind()) {
		case EDIT:
			createBodyHeader(ui);
			// iterate here to ensure correct order
			for (PropertyField f : ui.getPropertyField()) {
				if (f.getParticipationKind()==TypedElementParticipationKind.HIDDEN || f.getParticipationKind()==TypedElementParticipationKind.NAVIGATION) {
					continue;
				}
				HtmlFragment htmlFragment = JsfFactoryInputOutput.instance().getJsfInputOutputBuilder(ui, ui.getClassifier(), f).createUIComponent();
				bodyDiv.getChildren().add(htmlFragment);
			}
			break;
		}
	}

	@VisitAfter
	public void afterClassifierUserInteraction(ClassifierUserInteraction ui) {
		switch (ui.getUserInteractionKind()) {
		case EDIT:
			doAfterEditClassifierUserInteraction(ui);
			toSource(ui);
			break;
		}
	}

	private void doAfterEditClassifierUserInteraction(ClassifierUserInteraction ui) {
		HtmlDiv div = createDiv("buttonBox");
		bodyDiv.getChildren().add(div);
		if (isFromList(ui)) {
			addBackButton(div);
		}
		addUpdateButton(div, ui.getClassifier());
		addDeleteButton(ui.getClassifier(), div);
		addResetButton(div, ui, ui.getClassifier());
	}

	@Override
	protected String getEditRenderedRoot(DomainClassifier dc) {
		return NameConverter.decapitalize(dc.getName());
	}

}

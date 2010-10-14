package net.sf.nakeduml.jsf2generation;

import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlPanelGroup;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.jsf2generation.component.dummy.UIFragment;
import net.sf.nakeduml.jsf2generation.factory.Jsf2FactoryInputOutput;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;

@StepDependency(phase = Jsf2TransformationPhase.class, requires = {/*JsfContainmentBuilder.class, JsfListBuilder.class,JsfMenuBuilder.class, SeamEditPageBuilder.class, SourcePopulationResolver.class*/ })
public class Jsf2EditBuilder extends AbstractJsf2Builder {
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
				UIFragment htmlFragment = Jsf2FactoryInputOutput.instance().getJsf2InputOutputBuilder(ui.getClassifier(), f).createUIComponent();
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
		HtmlPanelGroup div = createDiv("buttonBox");
		bodyDiv.getChildren().add(div);
		if (isFromList(ui)) {
			addBackButton(div);
		}
		addUpdateButton(div, ui.getClassifier());
		addDeleteButton(ui.getClassifier(), div);
		addResetButton(div, ui.getClassifier());
	}

	@Override
	protected String getEditRenderedRoot(DomainClassifier dc) {
		return NameConverter.decapitalize(dc.getName());
	}

}

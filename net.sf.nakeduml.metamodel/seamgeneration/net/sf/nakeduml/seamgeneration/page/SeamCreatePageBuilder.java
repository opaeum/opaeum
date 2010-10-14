package net.sf.nakeduml.seamgeneration.page;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.seam.SeamSupport;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.EmptyWebXmlBuilder;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

import org.jboss.seam.core.Expressions;
import org.jboss.seam.navigation.Navigation;
import org.jboss.seam.navigation.Param;
import org.jboss.seam.navigation.Rule;

@StepDependency(phase = SeamTransformationPhase.class, after = SeamListPageBuilder.class, requires= {SeamSupport.class, SeamLoginPageBuilder.class, SeamPageBuilder.class, EmptyWebXmlBuilder.class})
public class SeamCreatePageBuilder extends AbstractSeamPageBuilder {

	@VisitBefore
	public void visitBeforeEntityNavigations(ClassifierUserInteraction ui) {
		super.visitBeforeEntityNavigations(ui);
		UserInteractionKind userInteractionKind = ui.getUserInteractionKind();
		switch (userInteractionKind) {
		case CREATE:
			addCreateNavigation(ui);
			break;
		}
	}
	@VisitAfter
	public void visitAfterEntityNavigations(ClassifierUserInteraction ui) {
		UserInteractionKind userInteractionKind = ui.getUserInteractionKind();
		switch (userInteractionKind) {
		case CREATE:
			super.visitAfterEntityNavigations(ui);
			break;
		}
	}

	private void addCreateNavigation(ClassifierUserInteraction ui) {
		Navigation createNavigation = new Navigation();
		
		StringBuilder sb = new StringBuilder();
		sb.append("#{crudController.flush()}");
		createNavigation.setOutcome(Expressions.instance().createValueExpression(sb.toString()));
		
		Rule rule = new Rule();
		rule.setCondition(Expressions.instance().createValueExpression("#{true}"));
		List<Param> params = new ArrayList<Param>();
		
		rule.addNavigationHandler(
				new NakedRedirectNavigationHandler(
						stringValueExpressionFor(
								resolveFlattenedViewId(ui.getOriginatingPropertyNavigation().getResultingUserInteraction(),".xhtml")), 
								stringValueExpressionFor(""), 
								params, "", 
								FacesMessage.SEVERITY_INFO, ""));
		createNavigation.getRules().add(rule);
		
		String seamName = "";
		if (ui.getUserInteractionKind()!=UserInteractionKind.CREATE || ui.getOriginatingPropertyNavigation()==null) {
			seamName = NameConverter.decapitalize(ui.getClassifier().getName());
		} 
		seamName = NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName() + "_" + ui.getClassifier().getName());
		
		
		sb = new StringBuilder();
		sb.append("#{");
		sb.append(seamName);
		sb.append(".addToOwningObject()}");
		page.getNavigations().put(sb.toString(), createNavigation);
		
		Navigation backNavigation = new Navigation();
		rule = new Rule();
		rule.setCondition(Expressions.instance().createValueExpression("#{true}"));
		params = new ArrayList<Param>();
		
		rule.addNavigationHandler(
				new NakedRedirectNavigationHandler(
						stringValueExpressionFor(
								resolveFlattenedViewId(ui.getOriginatingPropertyNavigation().getResultingUserInteraction(),".xhtml")), 
								stringValueExpressionFor(""), 
								params, "", 
								FacesMessage.SEVERITY_INFO, ""));
		backNavigation.getRules().add(rule);
		page.getNavigations().put("back", backNavigation);
		
		addMenuNavigations(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction());
		
	}
	
}

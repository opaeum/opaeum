package net.sf.nakeduml.seamgeneration.page;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.seam.SeamSupport;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

import org.jboss.seam.core.Expressions;
import org.jboss.seam.navigation.Navigation;
import org.jboss.seam.navigation.Param;
import org.jboss.seam.navigation.Rule;

@StepDependency(phase = SeamTransformationPhase.class, requires = { SeamSupport.class, SeamLoginPageBuilder.class, SeamListPageBuilder.class })
public class SeamEditPageBuilder extends AbstractSeamPageBuilder {

	@VisitBefore
	public void visitBeforeEntityNavigations(ClassifierUserInteraction ui) {
		UserInteractionKind userInteractionKind = ui.getUserInteractionKind();
		switch (userInteractionKind) {
		case EDIT:
			super.visitBeforeEntityNavigations(ui);
			addEditNavigations(ui);
			break;
		}
	}

	@VisitAfter
	public void visitAfterEntityNavigations(ClassifierUserInteraction ui) {
		UserInteractionKind userInteractionKind = ui.getUserInteractionKind();
		switch (userInteractionKind) {
		case EDIT:
			super.visitAfterEntityNavigations(ui);
			break;
		}
	}

	private void addEditNavigations(ClassifierUserInteraction ui) {
		if (isFromList(ui)) {
			addBackNavigation(ui);
		}
		addUpdateNavigation(ui);
		addDeleteNavigation(ui.getClassifier());
		addMenuNavigations(ui);
	}

	private void addUpdateNavigation(ClassifierUserInteraction ui) {
		Navigation updateNavigation = new Navigation();
		Rule rule = new Rule();
		ExpressionBuilder eb = ExpressionBuilder.instance();
		eb.append("not ");
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append(".equals(role.owningObject) and ");
		if (ui.getOriginatingPropertyNavigation() != null) {
			eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
			eb.append(".");
			eb.append(createUpdateRenderedExpression(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier(), "getOwningObject()"));
		} else {
			eb.append("true");
		}

		rule.setCondition(Expressions.instance().createValueExpression(eb.toString()));
		if (ui.getOriginatingPropertyNavigation() != null) {
			eb = ExpressionBuilder.instance();
			eb.append("(not ");
			eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
			eb.append(".equals(role.owningObject) and ");

			// Need security based rules
			eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
			eb.append(".");
			eb.append(createUpdateRenderedExpression(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier(), "getOwningObject()"));
			eb.append(")");

			eb.append("? crudController.outjectCompositionOwner(");

			eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
			if (ui != ui.getOriginatingPropertyNavigation().getResultingUserInteraction()) {
				eb.append(".");
				eb.append(NameConverter.decapitalize("getOwningObject()"));
			}
			eb.append(") : crudController.outjectCompositionOwner(");
			eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
			eb.append(")");
			updateNavigation.setOutcome(Expressions.instance().createValueExpression(eb.toString()));
			rule.addNavigationHandler(new NakedRedirectNavigationHandler(stringValueExpressionFor(resolveFlattenedViewId(ui.getOriginatingPropertyNavigation()
					.getResultingUserInteraction(), ".xhtml")), stringValueExpressionFor(""), new ArrayList<Param>(), "", FacesMessage.SEVERITY_INFO, ""));
		} else {
			// TODO root and abstract objects appear here it seems
			System.out.println(ui);
		}
		updateNavigation.getRules().add(rule);
		page.getNavigations().put("#{crudController.flush()}", updateNavigation);
	}

	private void addDeleteNavigation(DomainClassifier dc) {
		Navigation delete = flushAndNavigateToPreviousView();
		StringBuilder sb = new StringBuilder();
		sb.append("#{");
		sb.append(NameConverter.decapitalize(dc.getName()));
		sb.append(".markDeleted}");
		page.getNavigations().put(sb.toString(), delete);
	}

	private void addBackNavigation(ClassifierUserInteraction ui) {
		Navigation backNavigation = new Navigation();
		backNavigation.setOutcome(Expressions.instance().createValueExpression("#{crudController.clearPopAndOutject()}"));
		Rule rule = new Rule();
		rule.setCondition(Expressions.instance().createValueExpression("#{true}"));
		rule.addNavigationHandler(new NakedRedirectNavigationHandler(stringValueExpressionFor("#{historyStack.poppedViewId}"), stringValueExpressionFor(""),
				new ArrayList<Param>(), "", FacesMessage.SEVERITY_INFO, ""));
		backNavigation.getRules().add(rule);
		page.getNavigations().put("back", backNavigation);
	}

	private Navigation flushAndNavigateToPreviousView() {
		Navigation delete = new Navigation();
		delete.setOutcome(Expressions.instance().createValueExpression("#{crudController.flushPopAndOutject()}"));
		Rule rule = new Rule();
		rule.setCondition(Expressions.instance().createValueExpression("#{true}"));
		rule.addNavigationHandler(new NakedRedirectNavigationHandler(stringValueExpressionFor("#{historyStack.poppedViewId}"), stringValueExpressionFor(""),
				new ArrayList<Param>(), "", FacesMessage.SEVERITY_INFO, ""));
		delete.getRules().add(rule);
		return delete;
	}

	protected String createUpdateRenderedExpression(DomainClassifier dc, String name) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() && !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() && !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() && dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() && !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() && !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership() && dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() && dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() && !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() && !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership() && dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() && dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() && dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership() && dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() && dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() && dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership() && dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() && !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() && !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership() && dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(name);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		}
		return eb.getString();
	}

}

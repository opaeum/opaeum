package net.sf.nakeduml.seamgeneration.page;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.seam.SeamSupport;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

import org.jboss.seam.core.Expressions;
import org.jboss.seam.navigation.Navigation;
import org.jboss.seam.navigation.Param;
import org.jboss.seam.navigation.Rule;

@StepDependency(phase = SeamTransformationPhase.class, after = SeamEditPageBuilder.class, requires = { SeamSupport.class, SeamLoginPageBuilder.class,
		SeamPageBuilder.class, SeamCreatePageBuilder.class })
public class SeamListPageBuilder extends AbstractSeamPageBuilder {

	@VisitBefore
	public void visitBeforeEntityNavigations(ClassifierUserInteraction ui) {
		super.visitBeforeEntityNavigations(ui);
		UserInteractionKind userInteractionKind = ui.getUserInteractionKind();
		switch (userInteractionKind) {
		case LIST:
			addListNavigations(ui);
			break;
		}
	}

	@VisitAfter
	public void visitAfterEntityNavigations(ClassifierUserInteraction ui) {
		UserInteractionKind userInteractionKind = ui.getUserInteractionKind();
		switch (userInteractionKind) {
		case LIST:
			super.visitAfterEntityNavigations(ui);
			break;
		}
	}
	
	@Override
	protected void addPropertyNavigationNavigations(ClassifierUserInteraction ui) {
		StringBuilder sb;
		List<PropertyNavigation> pns = ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getPropertyNavigation();
		for (PropertyNavigation pn : pns) {
			if (pn.getResultingUserInteraction().isTooMany()) {
				sb = new StringBuilder();
				sb.append("#{crudController.outjectCompositionOwnerOfTooMany(");
				if (ui.getUserInteractionKind() == UserInteractionKind.LIST) {
					sb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
				} else {
					sb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
				}
				sb.append(", '");
				sb.append(NameConverter.decapitalize(pn.getResultingUserInteraction().getClassifier().getQualifiedImplementationType()));
				sb.append("'");
				sb.append(")}");
				
				String ruleIf = Expressions.instance().createValueExpression("#{param.navigateTo eq '" + pn.getName() + "'}").getExpressionString()+pn.getClassifierUserInteraction().getName();
				
				page.getNavigations().put(sb.toString(), listMenuTooManyNavigation.get(ruleIf));
			}
		}
	}
	

	private void addListNavigations(ClassifierUserInteraction ui) {
		Navigation listNavigationEditView = createViewNavigation(ui);
		addEditNavigation(ui, listNavigationEditView);
		if (ui.getOriginatingPropertyNavigation().getProperty().isComposite()) {
			addCreateNavigation(ui);
			addDeleteNavigation();
			addUpdateNavigation(ui);
		} else {
			addUpdateNavigation(ui);
		}
		addMenuNavigations(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction());
		addLinkNavigations(ui);
	}

	@Override
	protected void addMenuNavigation(PropertyNavigation pn) {
		if (pn.getResultingUserInteraction().getUserInteractionKind() == UserInteractionKind.CREATE) {
			throw new IllegalStateException("Not handled");
			// TODO this never fires?
		} else if (pn.getResultingUserInteraction().getUserInteractionKind() == UserInteractionKind.EDIT) {

			Navigation editNavigation = new Navigation();
			Rule rule = new Rule();
			rule.setCondition(Expressions.instance().createValueExpression("#{param.navigateTo eq '" + pn.getName() + "'}"));

			rule.addNavigationHandler(new NakedRedirectNavigationHandler(stringValueExpressionFor(resolveFlattenedViewId(
					/* findClassifierUserInteractionOfKind */pn.getResultingUserInteraction(), ".xhtml")), stringValueExpressionFor(""),
					new ArrayList<Param>(), "", FacesMessage.SEVERITY_INFO, ""));
			editNavigation.getRules().add(rule);

			// TODO this is exactly the same as on the menu
			StringBuilder methodExpression = new StringBuilder();
			methodExpression.append("#{crudController.outjectCompositionOwner(");
			methodExpression.append(NameConverter.decapitalize(pn.getClassifierUserInteraction().getClassifier().getName()));
			methodExpression.append(".");
			methodExpression.append(NameConverter.decapitalize(pn.getName()));
			methodExpression.append(")}");
			page.getNavigations().put(methodExpression.toString(), editNavigation);

		} else if (pn.getResultingUserInteraction().getUserInteractionKind() == UserInteractionKind.LIST) {
			Rule rule = new Rule();
			rule.setCondition(Expressions.instance().createValueExpression("#{param.navigateTo eq '" + pn.getName() + "'}"));
			List<Param> params = new ArrayList<Param>();
			rule.addNavigationHandler(new NakedRedirectNavigationHandler(stringValueExpressionFor(resolveFlattenedViewId(pn.getResultingUserInteraction(),
					".xhtml")), stringValueExpressionFor(""), params, "", FacesMessage.SEVERITY_INFO, ""));
			if (pn.getResultingUserInteraction().isTooMany()) {
				
				Navigation listNav = listMenuTooManyNavigation.get(rule.getCondition().getExpressionString()+pn.getClassifierUserInteraction().getName());
				if (listNav==null) {
					listNav = new Navigation();
					listNav.getRules().add(rule);
					listMenuTooManyNavigation.put(rule.getCondition().getExpressionString()+pn.getClassifierUserInteraction().getName(), listNav);
				}
				
			} else {
				menuNavigation.getRules().add(rule);
			}
		} else {
			throw new IllegalStateException("Not handled");
		}
	}
	
	
	private void addUpdateNavigation(ClassifierUserInteraction ui) {
		Navigation updateNavigation = new Navigation();
		Rule rule = new Rule();
		rule.setCondition(Expressions.instance().createValueExpression("#{true}"));
		updateNavigation.getRules().add(rule);
		page.getNavigations().put("#{crudController.flush()}", updateNavigation);
	}

	private void addLinkNavigations(ClassifierUserInteraction ui) {
		
		Rule rule;
		List<PropertyNavigation> navigations = ui.getLinkNavigation();
		for (PropertyNavigation propertyNavigation : navigations) {
			Navigation linkNavigation = new Navigation();
			rule = new Rule();
			
			//TODO bug here, think resolveFlattenedViewId is incorrect
			rule.addNavigationHandler(new NakedRedirectNavigationHandler(stringValueExpressionFor(resolveFlattenedViewId(propertyNavigation
					.getResultingUserInteraction(), ".xhtml")), stringValueExpressionFor(""), new ArrayList<Param>(), "", FacesMessage.SEVERITY_INFO, ""));
			
			rule.setCondition(Expressions.instance().createValueExpression("#{true}"));
			linkNavigation.getRules().add(rule);
			if (propertyNavigation.getResultingUserInteraction().getUserInteractionKind()==UserInteractionKind.LIST) {
	 			page.getNavigations()
				.put(propertyNavigation.getResultingUserInteraction().getName(), linkNavigation);
	 			
	 			//setOutcome produces evaluate=#{}
	 			linkNavigation.setOutcome(Expressions.instance().createValueExpression("#{crudController.outjectCompositionOwner(objectVar)}"));
	 			
			} else {
	 			page.getNavigations()
				.put("#{crudController.outjectCompositionOwner(objectVar." + propertyNavigation.getProperty().getName() + ")}", linkNavigation);
			}
		}
	}

	private void addDeleteNavigation() {
		Rule rule;
		Navigation delete = new Navigation();
		delete.setOutcome(Expressions.instance().createValueExpression("#{crudController.flush()}"));
		rule = new Rule();
		rule.setCondition(Expressions.instance().createValueExpression("#{true}"));
		delete.getRules().add(rule);
		page.getNavigations().put("#{objectVar.markDeleted}", delete);
	}

	private void addCreateNavigation(ClassifierUserInteraction ui) {
		Rule rule;
		List<ClassifierUserInteraction> classifierUserInteractionCreateList = findClassifierUserInteractionOfKind(workspace, ui, UserInteractionKind.CREATE);
		
		for (ClassifierUserInteraction classifierUserInteraction : classifierUserInteractionCreateList) {
			Navigation create = new Navigation();
			rule = new Rule();
			rule.setCondition(Expressions.instance().createValueExpression("#{true}"));
			if (!classifierUserInteractionCreateList.isEmpty()) {
				rule.addNavigationHandler(new NakedRedirectNavigationHandler(stringValueExpressionFor(resolveFlattenedViewId(classifierUserInteraction,
				".xhtml")), stringValueExpressionFor(""), new ArrayList<Param>(), "", FacesMessage.SEVERITY_INFO, ""));
				create.getRules().add(rule);
			}
			page.getNavigations().put("create_" + classifierUserInteraction.getOriginatingPropertyNavigation().getClassifierUserInteraction().getName(), create);
		}
		
	}

	private void addEditNavigation(ClassifierUserInteraction ui, Navigation listNavigationEditView) {
		Rule rule;
		rule = new Rule();
		rule.setCondition(Expressions.instance().createValueExpression("#{param.navigateTo eq 'edit'}"));
		List<ClassifierUserInteraction> classifierUserInteractionEditList = findClassifierUserInteractionOfKind(workspace, ui, UserInteractionKind.EDIT);
		rule.addNavigationHandler(new NakedRedirectNavigationHandler(stringValueExpressionFor(resolveFlattenedViewId(classifierUserInteractionEditList.get(0), ".xhtml")),
				stringValueExpressionFor(""), new ArrayList<Param>(), "", FacesMessage.SEVERITY_INFO, ""));
		listNavigationEditView.getRules().add(rule);
		page.getNavigations().put("#{crudController.outjectCompositionOwner(objectVar)}", listNavigationEditView);
	}

	private Navigation createViewNavigation(ClassifierUserInteraction ui) {
		Navigation listNavigationEditView = new Navigation();
		Rule rule = new Rule();
		rule.setCondition(Expressions.instance().createValueExpression("#{param.navigateTo eq 'view'}"));
		List<ClassifierUserInteraction> classifierUserInteractionViewList = findClassifierUserInteractionOfKind(workspace, ui, UserInteractionKind.VIEW);
		rule.addNavigationHandler(new NakedRedirectNavigationHandler(stringValueExpressionFor(resolveFlattenedViewId(classifierUserInteractionViewList.get(0), ".xhtml")),
				stringValueExpressionFor(""), new ArrayList<Param>(), "", FacesMessage.SEVERITY_INFO, ""));
		listNavigationEditView.getRules().add(rule);
		return listNavigationEditView;
	}

}

package net.sf.nakeduml.seamgeneration.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;

import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.AbstractBuilder;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.UserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

import org.jboss.seam.annotations.FlushModeType;
import org.jboss.seam.core.Expressions;
import org.jboss.seam.core.Expressions.ValueExpression;
import org.jboss.seam.navigation.Navigation;
import org.jboss.seam.navigation.Page;
import org.jboss.seam.navigation.Param;
import org.jboss.seam.navigation.Rule;

public class AbstractSeamPageBuilder extends AbstractBuilder {

	public static final String VIEW_DIR = "gen-view";
	protected Page page;
	protected Navigation menuNavigation;
	protected Navigation menuTooManyNavigation;
	protected Map<String, Navigation> listMenuTooManyNavigation = new HashMap<String, Navigation>();
	
	public void visitBeforeEntityNavigations(UserInteraction ui) {
		page = new Page(null);
		page.setDescription(ui.getName());
		menuNavigation = new Navigation();
		menuTooManyNavigation = new Navigation();
		page.getConversationControl().setBeginConversation(true);
		page.getConversationControl().setFlushMode(FlushModeType.MANUAL);
		page.getConversationControl().setJoin(true);
	}

	public void visitAfterEntityNavigations(ClassifierUserInteraction ui) {
		StringBuilder sb = new StringBuilder();
		sb.append("#{crudController.outjectCompositionOwner(");
		if (ui.getUserInteractionKind() == UserInteractionKind.LIST) {
			sb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
		} else {
			sb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		}
		sb.append(")}");
		page.getNavigations().put(sb.toString(), menuNavigation);

		addPropertyNavigationNavigations(ui);

		TextOutputRoot outputRoot = textWorkspace.findOrCreateTextOutputRoot(VIEW_DIR);
		List<String> path = new ArrayList<String>();
		for (AbstractUserInteractionFolder folder : ui.getFolder().getPathFromRoot()) {
			path.add(NameConverter.decapitalize(folder.getName()));
		}
		path.add(NameConverter.decapitalize(ui.getFolder().getName()));
		path.set(path.size() - 1, NameConverter.decapitalize(ui.getName()) + ".page.xml");
		outputRoot.findOrCreateTextFile(path, new SeamPagesSource(page));

		// doGenericNavigations(ui);
	}

	protected void addPropertyNavigationNavigations(ClassifierUserInteraction ui) {
		StringBuilder sb;
		List<PropertyNavigation> pns = ui.getPropertyNavigation();
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
				page.getNavigations().put(sb.toString(), menuTooManyNavigation);
			}
		}
	}

	// This is incorrect
	/*
	 * protected void doGenericNavigations(ClassifierUserInteraction ui) { Rule
	 * rule = new Rule();
	 * rule.setOutcomeValue(NameConverter.decapitalize(ui.getClassifier
	 * ().getName())); rule.addNavigationHandler( new
	 * NakedRedirectNavigationHandler( stringValueExpressionFor(
	 * resolveFlattenedViewId(ui,".xhtml")), stringValueExpressionFor(""), new
	 * ArrayList<Param>(), "", FacesMessage.SEVERITY_INFO, ""));
	 * 
	 * Navigation navigation = new Navigation(); Navigation
	 * containmentNavigation = new Navigation(); Navigation
	 * upContainmentNavigation = new Navigation(); Navigation backHistory = new
	 * Navigation();
	 * 
	 * navigation.getRules().add(rule);
	 * containmentNavigation.getRules().add(rule);
	 * upContainmentNavigation.setOutcome
	 * (Expressions.instance().createValueExpression
	 * ("#{crudController.upContainment()}"));
	 * upContainmentNavigation.getRules().add(rule);
	 * 
	 * page.getNavigations().put(
	 * "#{crudController.outjectCompositionOwnerFromRoleSelection}",
	 * navigation);page.getNavigations().put(
	 * "#{crudController.outjectCompositionOwnerFromContainment(entity)}",
	 * containmentNavigation); page.getNavigations().put("up",
	 * upContainmentNavigation);
	 * backHistory.setOutcome(Expressions.instance().createValueExpression
	 * ("#{crudController.clearPopAndOutject()}")); Rule backHistoryRule = new
	 * Rule();
	 * backHistoryRule.setCondition(Expressions.instance().createValueExpression
	 * ("#{true}")); backHistoryRule.addNavigationHandler(new
	 * NakedRedirectNavigationHandler
	 * (stringValueExpressionFor("#{historyStack.poppedViewId}"),
	 * stringValueExpressionFor(""), new ArrayList<Param>(), "",
	 * FacesMessage.SEVERITY_INFO, ""));
	 * backHistory.getRules().add(backHistoryRule);
	 * page.getNavigations().put("backHistory", backHistory); }
	 */
	@SuppressWarnings("unchecked")
	protected static ValueExpression<String> stringValueExpressionFor(String expr) {
		return (ValueExpression<String>) ((expr == null) ? expr : Expressions.instance().createValueExpression(expr, String.class));
	}

	protected void addMenuNavigations(ClassifierUserInteraction ui) {
		List<PropertyNavigation> pns = ui.getPropertyNavigation();
		for (PropertyNavigation pn : pns) {
			addMenuNavigation(pn);
		}
	}

	protected void addMenuNavigation(PropertyNavigation pn) {
		if (pn.getResultingUserInteraction().getUserInteractionKind() == UserInteractionKind.CREATE) {
			throw new IllegalStateException("Not handled");
			// TODO this never fires?
			// Rule rule = new Rule();
			// rule.setCondition(Expressions.instance().createValueExpression("#{param.navigateTo eq '"
			// + pn.getName() + "'}"));
			// rule.addNavigationHandler(new
			// NakedRedirectNavigationHandler(stringValueExpressionFor(resolveFlattenedViewId(findClassifierUserInteractionOfKind(
			// workspace, pn.getResultingUserInteraction(),
			// UserInteractionKind.CREATE), ".xhtml")),
			// stringValueExpressionFor(""), new ArrayList<Param>(), "",
			// FacesMessage.SEVERITY_INFO, ""));
			// menuNavigation.getRules().add(rule);
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
				menuTooManyNavigation.getRules().add(rule);
			} else {
				menuNavigation.getRules().add(rule);
			}
		} else {
			throw new IllegalStateException("Not handled");
		}
	}

}

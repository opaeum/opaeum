package net.sf.nakeduml.seamgeneration.page;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.seamgeneration.UserInteractionElementVisitor;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

import org.jboss.seam.core.Expressions;
import org.jboss.seam.core.Expressions.ValueExpression;
import org.jboss.seam.navigation.Navigation;
import org.jboss.seam.navigation.Page;
import org.jboss.seam.navigation.Param;
import org.jboss.seam.navigation.Rule;

@StepDependency(phase = SeamTransformationPhase.class, after = SeamCreatePageBuilder.class)
public class SeamLoginPageBuilder extends UserInteractionElementVisitor {

	public static final String VIEW_DIR = "gen-view";
	private Page loginPage;
	private Navigation loginNavigation;
	
	@Override
	public void initialize(UserInteractionWorkspace workspace,NakedUmlConfig config,TextWorkspace textWorkspace){
		super.initialize(workspace, config, textWorkspace);
		loginPage = new Page("/login.xhtml");
		loginNavigation = new Navigation();
	}
	
	@VisitAfter
	public void visitBeforeEntityNavigations(ClassifierUserInteraction ui) {
		if (ui.getUserInteractionKind()==UserInteractionKind.EDIT) {
			Rule rule = new Rule();
			rule.setCondition(Expressions.instance().createValueExpression("#{identity.loggedIn}"));
			rule.setOutcomeValue(NameConverter.decapitalize(ui.getClassifier().getName()));
			List<Param> params = new ArrayList<Param>();
			
			rule.addNavigationHandler(
					new NakedRedirectNavigationHandler(
							stringValueExpressionFor(
									resolveFlattenedViewId(ui,".xhtml")), 
									stringValueExpressionFor(""), 
									params, "", 
									FacesMessage.SEVERITY_INFO, ""));
			
			loginNavigation.getRules().add(rule);
		}
	}
	
	private static ValueExpression<String> stringValueExpressionFor(String expr) {
		return (ValueExpression<String>) ((expr == null) ? expr : Expressions.instance().createValueExpression(expr, String.class));
	}
	
	@VisitAfter
	public void visitBeforeEntityNavigations(UserInteractionWorkspace e) {
		loginNavigation.setOutcome(Expressions.instance().createValueExpression("#{crudController.outjectCompositionOwnerAfterLogin()}"));
		loginPage.getNavigations().put("#{identity.login}", loginNavigation);
		
		TextOutputRoot outputRoot = textWorkspace.findOrCreateTextOutputRoot(VIEW_DIR);
		List<String> path = new ArrayList<String>();
		path.add("login.page.xml");
		outputRoot.findOrCreateTextFile(path, new SeamLoginPageSource(loginPage));
	}
	@VisitAfter
	public void visitAfterEntityNavigations(ClassifierUserInteraction e) {
	}
}

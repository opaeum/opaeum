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
import net.sf.nakeduml.textmetamodel.TextFile;
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

@StepDependency(phase = SeamTransformationPhase.class, after = SeamLoginPageBuilder.class, requires=SeamApplicationPageMerger.class)
public class SeamPageBuilder extends UserInteractionElementVisitor {

//	public static final String VIEW_DIR = "gen-resources";
	public static final String VIEW_DIR = "gen-view";
	private Page page;
	private Navigation navigation;
	private Navigation containmentNavigation;
	private Navigation upContainmentNavigation;
	private Navigation backHistory;
	
	private List<PagesException> exceptions;
	
	@Override
	public void initialize(UserInteractionWorkspace workspace,NakedUmlConfig config,TextWorkspace textWorkspace){
		super.initialize(workspace, config, textWorkspace);
		page = new Page("*");
		navigation = new Navigation();
		containmentNavigation = new Navigation();
		upContainmentNavigation = new Navigation();
		backHistory = new Navigation();
		exceptions = new ArrayList<PagesException>();
		exceptions.add(new PagesException("org.jboss.seam.framework.EntityNotFoundException","/error.xhtml",FacesMessage.SEVERITY_WARN,"Record not found"));
		exceptions.add(new PagesException("javax.persistence.EntityNotFoundException","/error.xhtml",FacesMessage.SEVERITY_WARN,"Record not found"));
		exceptions.add(new PagesException("javax.persistence.EntityExistsException","/error.xhtml",FacesMessage.SEVERITY_WARN,"Duplicate record"));
		exceptions.add(new PagesException("javax.persistence.OptimisticLockException","/error.xhtml",FacesMessage.SEVERITY_WARN,"Another user changed the same data, please try again"));
		exceptions.add(new PagesException("org.jboss.seam.security.AuthorizationException","/error.xhtml",FacesMessage.SEVERITY_WARN,"You don't have permission to access this resource"));
		exceptions.add(new PagesException("org.jboss.seam.security.NotLoggedInException","/login.xhtml",FacesMessage.SEVERITY_WARN,"#{messages['org.jboss.seam.NotLoggedIn']}"));
		exceptions.add(new PagesException("javax.faces.application.ViewExpiredException","/error.xhtml",FacesMessage.SEVERITY_WARN,"Your session has timed out, please try again"));
	}
	
	@VisitAfter
	public void visitAfterEntityNavigations(ClassifierUserInteraction e) {
		if (e.getUserInteractionKind()==UserInteractionKind.EDIT) {
			Rule rule = new Rule();
			rule.setOutcomeValue(NameConverter.decapitalize(e.getClassifier().getName()));
			rule.addNavigationHandler(
					new NakedRedirectNavigationHandler(
							stringValueExpressionFor(
									resolveFlattenedViewId(e,".xhtml")), 
									stringValueExpressionFor(""), 
									new ArrayList<Param>(), "", 
									FacesMessage.SEVERITY_INFO, ""));
			
			navigation.getRules().add(rule);
			containmentNavigation.getRules().add(rule);
			upContainmentNavigation.setOutcome(Expressions.instance().createValueExpression("#{crudController.upContainment()}"));
			upContainmentNavigation.getRules().add(rule);
			
		}
	}
	
	@SuppressWarnings("unchecked")
	private static ValueExpression<String> stringValueExpressionFor(String expr) {
		return (ValueExpression<String>) ((expr == null) ? expr : Expressions.instance().createValueExpression(expr, String.class));
	}
	
	@VisitAfter
	public void visitAfterWorkspace(UserInteractionWorkspace e) {
		page.getNavigations().put("#{crudController.outjectCompositionOwnerFromRoleSelection}", navigation);
		page.getNavigations().put("#{crudController.outjectCompositionOwnerFromContainment}", containmentNavigation);
		page.getNavigations().put("up", upContainmentNavigation);
		backHistory.setOutcome(Expressions.instance().createValueExpression("#{crudController.clearPopAndOutject()}"));
		Rule backHistoryRule = new Rule();
		backHistoryRule.setCondition(Expressions.instance().createValueExpression("#{true}"));
		backHistoryRule.addNavigationHandler(new NakedRedirectNavigationHandler(stringValueExpressionFor("#{historyStack.poppedViewId}"), stringValueExpressionFor(""),
				new ArrayList<Param>(), "", FacesMessage.SEVERITY_INFO, ""));
		backHistory.getRules().add(backHistoryRule);
		page.getNavigations().put("backHistory", backHistory);
		
		TextOutputRoot outputRoot = textWorkspace.findOrCreateTextOutputRoot(VIEW_DIR);
		List<String> path = new ArrayList<String>();
		path.add("WEB-INF");
		path.add("pages.xml");
		TextFile t = outputRoot.findOrCreateTextFile(path, new SeamPageSource(page,exceptions));
	}
}

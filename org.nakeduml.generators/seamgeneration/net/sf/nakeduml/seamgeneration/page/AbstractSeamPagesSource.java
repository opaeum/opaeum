package net.sf.nakeduml.seamgeneration.page;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.FlushModeType;
import org.jboss.seam.navigation.Action;
import org.jboss.seam.navigation.Navigation;
import org.jboss.seam.navigation.NavigationHandler;
import org.jboss.seam.navigation.Page;
import org.jboss.seam.navigation.Rule;

public abstract class AbstractSeamPagesSource {
	protected StringBuilder stringBuilder;
	protected Page page;
	public AbstractSeamPagesSource(Page page) {
		super();
		this.stringBuilder = new StringBuilder();
		this.page = page;
	}
	protected void outEvents() {
		for (String event : page.getEventTypes()) {
			stringBuilder.append("    <raise-event type=\"");
			stringBuilder.append(event);
			stringBuilder.append("\" />\n");
		}
	}

	protected void outActions() {
		List<Action> actions = page.getActions();
		for (Action action : actions) {
			stringBuilder.append("    <action execute=\"");
			stringBuilder.append(action.getMethodExpression().getExpressionString());
			stringBuilder.append("\" />\n");
		}
	}

	protected void outNavigations() {
		Map<String, Navigation> nav = page.getNavigations();
		for (String fromAction : nav.keySet()) {
			if (!fromAction.equals("")) {
				stringBuilder.append("    <navigation from-action=\"");
				stringBuilder.append(fromAction);
				stringBuilder.append("\"");
			} else {
				stringBuilder.append("    <navigation ");
			}
			Navigation navigation = nav.get(fromAction);
			if (navigation.getOutcome()!=null) {
				stringBuilder.append(" evaluate=\"");
				stringBuilder.append(navigation.getOutcome().getExpressionString());
				stringBuilder.append("\"");
			}
			stringBuilder.append(">\n");
			List<Rule> rules = navigation.getRules();
			for (Rule rule : rules) {
				stringBuilder.append("        <rule");
				if (rule.getCondition()!=null) {
					stringBuilder.append(" if=\"");
					stringBuilder.append(rule.getCondition().getExpressionString());
					stringBuilder.append("\"");
				}
				if (rule.getOutcomeValue()!=null) {
					stringBuilder.append(" if-outcome=\"");
					stringBuilder.append(rule.getOutcomeValue());
					stringBuilder.append("\"");
				}
				stringBuilder.append(" >\n");
				
				List<NavigationHandler> navHandlers =  rule.getNavigationHandlers();
				for (NavigationHandler navigationHandler : navHandlers) {
					NakedRedirectNavigationHandler redirectNavigationHandler = (NakedRedirectNavigationHandler)navigationHandler;
					stringBuilder.append("            <redirect view-id=\"");
					stringBuilder.append(redirectNavigationHandler.getViewId().getExpressionString());
					stringBuilder.append("\">\n");
					stringBuilder.append("            </redirect>\n");
				}
				if (rule.getNavigationHandlers().isEmpty()) {
					stringBuilder.append("            <redirect />\n");
				}
				stringBuilder.append("        </rule>\n");
			}
			stringBuilder.append("    </navigation>\n");
		}
	}

	protected void outBeginConversation() {
		if (page.getConversationControl().isBeginConversation()) {
			stringBuilder.append("    <begin-conversation");
			if (page.getConversationControl().isJoin()) {
				stringBuilder.append(" join=\"true\"");
			}
			if (page.getConversationControl().getFlushMode()!=null) {
				stringBuilder.append(" flush-mode=\"");
				stringBuilder.append(FlushModeType.MANUAL);
				stringBuilder.append("\" ");
			}
			if (page.getConversationControl().getConversationName()!=null) {
				stringBuilder.append(" conversation=\"RoleConversation\" ");
			}
			stringBuilder.append(" />\n\n");
		}
	}

	protected void outEndConversation() {
		if (page.getConversationControl().isEndConversation()) {
			stringBuilder.append("    <end-conversation />\n\n");
		}
	}

}

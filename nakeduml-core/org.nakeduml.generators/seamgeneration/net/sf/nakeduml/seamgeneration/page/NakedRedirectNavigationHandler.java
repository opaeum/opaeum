package net.sf.nakeduml.seamgeneration.page;

import java.util.List;

import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.jboss.seam.core.Expressions.ValueExpression;
import org.jboss.seam.navigation.NavigationHandler;
import org.jboss.seam.navigation.Param;
import org.jboss.seam.navigation.RedirectNavigationHandler;

public class NakedRedirectNavigationHandler extends NavigationHandler {

	private RedirectNavigationHandler wrapper;
	private final ValueExpression<String> viewId;
	private final ValueExpression<String> url;
	private final List<Param> params;
	private final String message;
	private final Severity severity;
	private final String control;

	public NakedRedirectNavigationHandler(ValueExpression<String> viewId, ValueExpression<String> url, List<Param> params, String message, Severity severity,
			String control) {
		this.viewId = viewId;
		this.url = url;
		this.params = params;
		this.message = message;
		this.severity = severity;
		this.control = control;
		wrapper = new RedirectNavigationHandler(viewId,  url, params, message, severity, control,false);
	}

	@Override
	public boolean navigate(FacesContext arg0) {
		return wrapper.navigate(arg0);
	}

	public RedirectNavigationHandler getWrapper() {
		return wrapper;
	}

	public void setWrapper(RedirectNavigationHandler wrapper) {
		this.wrapper = wrapper;
	}

	public ValueExpression<String> getViewId() {
		return viewId;
	}

	public ValueExpression<String> getUrl() {
		return url;
	}

	public List<Param> getParams() {
		return params;
	}

	public String getMessage() {
		return message;
	}

	public Severity getSeverity() {
		return severity;
	}

	public String getControl() {
		return control;
	}
	
}

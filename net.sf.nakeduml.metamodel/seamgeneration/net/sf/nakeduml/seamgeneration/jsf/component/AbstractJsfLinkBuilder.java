package net.sf.nakeduml.seamgeneration.jsf.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIParameter;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;

import org.ajax4jsf.component.AjaxActionComponent;
import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlDecorate;
import org.jboss.seam.ui.component.html.HtmlFragment;

import com.sun.faces.el.ELContextImpl;

public abstract class AbstractJsfLinkBuilder  implements IJsfLinkBuilder  {

	//TODO duplicated
	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	private DomainClassifier dc; 
	private PropertyNavigation n;

	public AbstractJsfLinkBuilder(DomainClassifier dc, PropertyNavigation n) {
		super();
		this.dc = dc;
		this.n = n;
	}

	@Override
	public HtmlFragment createUILink() {
		HtmlFragment htmlFragment = new HtmlFragment();
		AjaxActionComponent viewLink = createComponent();
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createNavigationValueExpression(dc, n), void.class);
		viewLink.setValue(ve);
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(new ELContextImpl(null), createNavigationActionExpression(dc, n), void.class,
				new Class[] {});
		viewLink.setActionExpression(me);
		addParameter(viewLink, "navigateTo", n.getName());
		viewLink.setAjaxSingle(true);
		setSettedAttributes(viewLink, "value", "action", "ajaxSingle");
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createNavigationRenderedExpression(dc, n), void.class);
		HtmlDecorate htmlDecorate = addDecoration(viewLink, getDisplayTemplate(), ve, n);
		htmlFragment.getChildren().add(htmlDecorate);
		return htmlFragment;
	}

	//TODO refactor, duplicated
	@SuppressWarnings("unchecked")
	protected void setSettedAttributes(UIComponent list, String... s) {
		list.getAttributes().put(ATTRIBUTES_THAT_ARE_SET_KEY, new ArrayList<String>());
		List<String> attributes = (List<String>) list.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.addAll(Arrays.asList(s));
	}

	//TODO check duplication
	protected void addParameter(UICommand button, String name, String value) {
		UIParameter parameter = new UIParameter();
		parameter.setName(name);
		if (value.startsWith("#{")) {
			ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), value, Object.class);
			parameter.setValue(ve);
		} else {
			parameter.setValue(value);
		}
		setSettedAttributes(parameter, "name", "value");
		button.getChildren().add(parameter);
	}
	
	protected abstract String createNavigationValueExpression(DomainClassifier dc, PropertyNavigation n);
	protected abstract String createNavigationActionExpression(DomainClassifier dc, PropertyNavigation n);
	protected abstract String createNavigationRenderedExpression(DomainClassifier dc, PropertyNavigation n);
	protected abstract String getDisplayTemplate();
	protected abstract HtmlDecorate addDecoration(UICommand uiCommand, String template, ValueExpression ve, PropertyNavigation n);
	
}

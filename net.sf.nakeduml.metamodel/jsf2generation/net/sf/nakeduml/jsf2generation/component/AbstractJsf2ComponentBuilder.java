package net.sf.nakeduml.jsf2generation.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIOutput;
import javax.faces.component.ValueHolder;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.jsf2generation.component.dummy.AjaxSupport;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionElement;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlDecorate;

import com.sun.faces.el.ELContextImpl;

public abstract class AbstractJsf2ComponentBuilder implements IJsf2ComponentBuilder {
	// TODO duplicated
	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	protected DomainClassifier dc;
	protected PropertyField pf;

	public AbstractJsf2ComponentBuilder(DomainClassifier dc, PropertyField pf) {
		super();
		this.dc = dc;
		this.pf = pf;
	}
	
	@SuppressWarnings("unchecked")
	protected void addAjaxSupport(UIOutput uiOutput) {
		AjaxSupport ajaxSupport = new AjaxSupport();
		ajaxSupport.setEvent(getEvent());
		ajaxSupport.setRender(retrieveDecorationId(uiOutput));
		setSettedAttributes(ajaxSupport, "event", "render");
		uiOutput.getFacets().put("a4jsupport", ajaxSupport);
		List<String> attributes = (List<String>)uiOutput.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.remove(getEvent());
	}
	
	protected String getEvent() {
		return "onblur";
	}
	
	protected String retrieveDecorationId(ValueHolder valueHolder) {
		ValueExpression ve = (ValueExpression) valueHolder.getValue();
		return ve.getExpressionString().replace("#{", "").replace("}", "").replace(".", "_").concat("_decorate").concat(valueHolder.getClass().getSimpleName());
	}
	
	protected HtmlOutputText setUpLabel(UserInteractionElement ui) {
		HtmlOutputText label = new HtmlOutputText();
		ValueExpression ve = SeamExpressionFactory.INSTANCE
				.createValueExpression(new ELContextImpl(null), retrieveComponentLabel(ui.getRepresentedElement().getName()), Object.class);
		label.setValue(ve);
		setSettedAttributes(label, "value");
		return label;
	}
	
	protected String retrieveComponentLabel(String name) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("#{messages['");
		stringBuilder.append(name);
		stringBuilder.append("']}");
		return stringBuilder.toString();
	}

	// TODO refactor, duplicated
	@SuppressWarnings("unchecked")
	protected void setSettedAttributes(UIComponent list, String... s) {
		list.getAttributes().put(ATTRIBUTES_THAT_ARE_SET_KEY, new ArrayList<String>());
		List<String> attributes = (List<String>) list.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.addAll(Arrays.asList(s));
	}

	protected HtmlDecorate addDecoration(UIOutput uiOutput, String template, ValueExpression ve, PropertyField pf) {
		HtmlDecorate htmlDecorate = new HtmlDecorate();
		htmlDecorate.setId(retrieveDecorationId(uiOutput));
		htmlDecorate.setTemplate(template);
		setSettedAttributes(htmlDecorate, "id", "template");
		htmlDecorate.setValueExpression("rendered", ve);
		HtmlOutputText label = setUpLabel(pf);
		htmlDecorate.getChildren().add(label);
		htmlDecorate.getChildren().add(uiOutput);
		addAjaxSupport(uiOutput);
		return htmlDecorate;
	}
	
	protected String createPropertyValueExpression(DomainClassifier dc, PropertyField pf) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		eb.append(getEditRenderedRoot(dc));
		eb.append(".");
		eb.append(pf.getName());
		return eb.toString();
	}
	
	protected String getEditRenderedRoot(DomainClassifier dc) {
		return NameConverter.decapitalize(dc.getName());
	}
	protected String getEditTemplate() {
		return "/layout/edit.xhtml";
	}
	protected abstract UIComponent createComponent();
}

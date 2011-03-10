package net.sf.nakeduml.seamgeneration.jsf.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectManyMenu;

import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.seamgeneration.jsf.JsfListBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.ajax4jsf.component.html.HtmlAjaxSupport;
import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.jboss.seam.ui.component.html.HtmlSelectItems;
import org.jboss.seam.ui.converter.EnumConverter;
import org.richfaces.component.html.HtmlColumn;

import com.sun.faces.el.ELContextImpl;

public class JsfListEnumColumnBuilder implements IJsfPropertyFieldColumnBuilder {

	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	private PropertyField pf;
	private ClassifierUserInteraction ui;

	public JsfListEnumColumnBuilder(ClassifierUserInteraction ui, PropertyField pf) {
		super();
		this.ui = ui;
		this.pf = pf;
	}

	@Override
	public HtmlColumn createColumn() {
		HtmlColumn column = new HtmlColumn();
		
		column.setLabel(generateComponentLabel(NameConverter.decapitalize(pf.getProperty().getName())));
		column.setWidth("180px");
		
		HtmlFragment headerFragment = new HtmlFragment();
		HtmlOutputText columnHeading = new HtmlOutputText();
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), generateComponentLabel(NameConverter.decapitalize(pf
				.getProperty().getName())), Object.class);
		columnHeading.setValue(ve);
		setSettedAttributes(columnHeading, "value");
		headerFragment.getChildren().add(columnHeading);
		
		HtmlFragment filterFragment = new HtmlFragment();		
		
		HtmlSelectManyMenu htmlSelectBooleaFilter = new HtmlSelectManyMenu();
		ExpressionBuilder eb = ExpressionBuilder.instance();
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append("Filter.");
		eb.append(pf.getName());
		eb.append("Filter");
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), eb.toString(), Object.class);
		htmlSelectBooleaFilter.setValueExpression("value", ve);
		htmlSelectBooleaFilter.setConverter(new EnumConverter());
		
		HtmlSelectItems htmlSelectItems = new HtmlSelectItems();
		eb = ExpressionBuilder.instance();
		eb.append(pf.getName());
		eb.append("Factory");
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), eb.toString(), Object.class);
		htmlSelectItems.setValueExpression("value", ve);
		htmlSelectItems.setVar("enumVar");
		htmlSelectItems.setLabel("#{enumVar}");
		htmlSelectItems.setNoSelectionLabel("#{messages['please.select']}");
		setSettedAttributes(htmlSelectItems, "value", "var", "label", "noSelectionLabel");
		htmlSelectBooleaFilter.getChildren().add(htmlSelectItems);
		
		HtmlAjaxSupport ajaxSupport = new HtmlAjaxSupport();
		ajaxSupport.setEvent("onchange");
		ajaxSupport.setAjaxSingle(true);
		ajaxSupport.setReRender(JsfListBuilder.LISTPANEL);
		
		eb = ExpressionBuilder.instance();
		eb.append("setCaretToEnd(event);");
		ajaxSupport.setOncomplete(eb.getString());
		ajaxSupport.setIgnoreDupResponses(true);
		ajaxSupport.setRequestDelay(700);
		
		setSettedAttributes(ajaxSupport, "ajaxSingle", "event", "reRender", "oncomplete", "ignoreDupResponses", "requestDelay");
		htmlSelectBooleaFilter.getFacets().put("a4jsupport", ajaxSupport);
		List<String> attributes = (List<String>)htmlSelectBooleaFilter.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.remove("onchange");
		
		column.getFacets().put("header", headerFragment);

		filterFragment.getChildren().add(htmlSelectBooleaFilter);		
		column.getFacets().put("filter", filterFragment);		
		
		eb = ExpressionBuilder.instance();
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append("Filter.");
		eb.append(pf.getName());
		eb.append("Filter eq null or ");
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append("Filter.");
		eb.append(pf.getName());
		eb.append("Filter eq objectVar.");
		eb.append(pf.getName());
		
		
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), eb.toString(), void.class);
		column.setValueExpression("filterExpression", ve);
		setSettedAttributes(column, "filterExpression", "label", "width");
		
		return column;
	}

	@SuppressWarnings("unchecked")
	protected void setSettedAttributes(UIComponent list, String... s) {
		list.getAttributes().put(ATTRIBUTES_THAT_ARE_SET_KEY, new ArrayList<String>());
		List<String> attributes = (List<String>) list.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.addAll(Arrays.asList(s));
	}

	protected String generateComponentLabel(String name) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("#{messages['");
		stringBuilder.append(name);
		stringBuilder.append("']}");
		return stringBuilder.toString();
	}

	//TODO duplicated
	protected String generateJsfId(ClassifierUserInteraction e, String componentClassName) {
		return e.getName() + componentClassName + "JsfId";
	}	
	
}

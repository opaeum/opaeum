package net.sf.nakeduml.seamgeneration.jsf.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.seamgeneration.jsf.JsfListBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.ajax4jsf.component.html.HtmlAjaxSupport;
import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.richfaces.component.html.HtmlColumn;

import com.sun.faces.el.ELContextImpl;

public class JsfListStringFilterColumnBuilder implements IJsfPropertyFieldColumnBuilder {

	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	private PropertyField pf;
	private ClassifierUserInteraction ui;
	private boolean filter = true;

	public JsfListStringFilterColumnBuilder(ClassifierUserInteraction ui, PropertyField pf, boolean filter) {
		super();
		this.pf = pf;
		this.ui = ui;
		this.filter = filter;
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
		column.getFacets().put("header", headerFragment);

		if (filter) {
			HtmlFragment filterFragment = new HtmlFragment();
			
			HtmlInputText htmlInputText = new HtmlInputText();
			htmlInputText.setId(generateJsfId(ui, HtmlInputText.class.getSimpleName()+pf.getName()));
			ExpressionBuilder eb = ExpressionBuilder.instance();
			eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
			eb.append("Filter.");
			eb.append(pf.getName());
			eb.append("Filter");
			ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), eb.toString(), Object.class);
			htmlInputText.setValueExpression("value", ve);
			setSettedAttributes(htmlInputText, "id");

			HtmlAjaxSupport ajaxSupport = new HtmlAjaxSupport();
			ajaxSupport.setEvent("onkeyup");
			ajaxSupport.setAjaxSingle(true);
			ajaxSupport.setReRender(JsfListBuilder.LISTPANEL);
			eb = ExpressionBuilder.instance();
			
			eb.append("setCaretToEnd(event);");
			ajaxSupport.setOncomplete(eb.getString());
			ajaxSupport.setIgnoreDupResponses(true);
			ajaxSupport.setRequestDelay(700);
			
			setSettedAttributes(ajaxSupport, "ajaxSingle", "event", "reRender", "oncomplete", "ignoreDupResponses", "requestDelay");
			htmlInputText.getFacets().put("a4jsupport", ajaxSupport);
			List<String> attributes = (List<String>)htmlInputText.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
			attributes.remove("onkeyup");
			
			filterFragment.getChildren().add(htmlInputText);		
			column.getFacets().put("filter", filterFragment);

			eb = ExpressionBuilder.instance();
			eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
			eb.append("Filter.");
			eb.append(pf.getName());
			eb.append("Filter eq null or ");
			eb.append("objectVar.");
			eb.append(pf.getName());
			eb.append(".startsWith(");
			eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
			eb.append("Filter.");
			eb.append(pf.getName());
			eb.append("Filter");
			eb.append(")");
			
			ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), eb.toString(), Object.class);
			column.setValueExpression("filterExpression", ve);		
			
			column.setFilterEvent("onkeyup");
		} 
		
//		eb = ExpressionBuilder.instance();
//		eb.append("fn:containsIgnoreCase(");
//		eb.append("objectVar.");
//		eb.append(pf.getName());
//		eb.append(", ");
//		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
//		eb.append("Filter.");
//		eb.append(pf.getName());		
//		eb.append(")");
		

		if (filter) {
			setSettedAttributes(column, "filterExpression", "filterEvent", "label", "width");			
		} else {
			setSettedAttributes(column, "label", "width");			
		}
		
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

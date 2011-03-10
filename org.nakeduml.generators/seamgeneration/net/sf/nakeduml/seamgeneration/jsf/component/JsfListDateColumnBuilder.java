package net.sf.nakeduml.seamgeneration.jsf.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.seamgeneration.jsf.JsfListBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.ajax4jsf.component.html.HtmlAjaxSupport;
import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlDiv;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.richfaces.component.html.HtmlCalendar;
import org.richfaces.component.html.HtmlColumn;
import org.richfaces.component.html.HtmlDataTable;

import com.sun.faces.el.ELContextImpl;

public class JsfListDateColumnBuilder implements IJsfPropertyFieldColumnBuilder {

	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	private PropertyField pf;
	private ClassifierUserInteraction ui;

	public JsfListDateColumnBuilder(ClassifierUserInteraction dc, PropertyField pf) {
		super();
		this.ui = dc;
		this.pf = pf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HtmlColumn createColumn() {
		HtmlColumn column = new HtmlColumn();
		column.setWidth("180px");
		
		column.setLabel(generateComponentLabel(NameConverter.decapitalize(pf.getProperty().getName())));
		
		HtmlFragment headerFragment = new HtmlFragment();
		HtmlOutputText columnHeading = new HtmlOutputText();
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), generateComponentLabel(NameConverter.decapitalize(pf
				.getProperty().getName())), Object.class);
		columnHeading.setValue(ve);
		setSettedAttributes(columnHeading, "value");
		headerFragment.getChildren().add(columnHeading);
		
		HtmlDiv div = new HtmlDiv();
		
		HtmlCalendar htmlCalendar = new HtmlCalendar();
		ExpressionBuilder eb = ExpressionBuilder.instance();
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append("Filter.");
		eb.append(pf.getName());
		eb.append("Filter");
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), eb.toString(), Object.class);
		htmlCalendar.setValueExpression("value", ve);
		setSettedAttributes(htmlCalendar, "value");
		
		HtmlAjaxSupport ajaxSupport = new HtmlAjaxSupport();
		ajaxSupport.setEvent("onchanged");
		ajaxSupport.setAjaxSingle(true);
		ajaxSupport.setReRender(JsfListBuilder.LISTPANEL);
		setSettedAttributes(ajaxSupport, "ajaxSingle", "event", "reRender");
		htmlCalendar.getFacets().put("a4jsupport", ajaxSupport);
		List<String> attributes = (List<String>)htmlCalendar.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.remove("onchanged");
		
		div.getChildren().add(htmlCalendar);
		headerFragment.getChildren().add(div);
		
		column.getFacets().put("header", headerFragment);
		
		eb = ExpressionBuilder.instance();
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append("Filter.");
		eb.append(pf.getName());
		eb.append("Filter eq null or ");
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append("Filter.");
		eb.append(pf.getName());
		eb.append("Filter");
		eb.append(".before(");
		eb.append("objectVar.");
		eb.append(pf.getName());
		eb.append(")");
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

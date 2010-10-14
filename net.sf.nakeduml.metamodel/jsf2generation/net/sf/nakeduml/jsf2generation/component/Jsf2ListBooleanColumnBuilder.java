package net.sf.nakeduml.jsf2generation.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectManyMenu;

import net.sf.nakeduml.jsf2generation.component.dummy.AjaxSupport;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.seamgeneration.jsf.JsfListBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.jboss.seam.ui.component.html.HtmlSelectItems;
import org.jboss.seam.ui.converter.EnumConverter;

import com.sun.faces.el.ELContextImpl;

public class Jsf2ListBooleanColumnBuilder implements IJsf2PropertyFieldColumnBuilder {

	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	private PropertyField pf;
	private ClassifierUserInteraction ui;

	public Jsf2ListBooleanColumnBuilder(ClassifierUserInteraction ui, PropertyField pf) {
		super();
		this.ui = ui;
		this.pf = pf;
	}

	@Override
	public HtmlColumn createColumn() {
		HtmlColumn column = new HtmlColumn();
		HtmlFragment headerFragment = new HtmlFragment();
		HtmlOutputText columnHeading = new HtmlOutputText();
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), generateComponentLabel(NameConverter.decapitalize(pf
				.getProperty().getName())), Object.class);
		columnHeading.setValue(ve);
		setSettedAttributes(columnHeading, "value");
		headerFragment.getChildren().add(columnHeading);
		
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
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), "#{booleanFilterFactory}", Object.class);
		htmlSelectItems.setValueExpression("value", ve);
		htmlSelectItems.setVar("boolVar");
		htmlSelectItems.setLabel("#{boolVar}");
		setSettedAttributes(htmlSelectItems, "value", "var", "label");
		htmlSelectBooleaFilter.getChildren().add(htmlSelectItems);
		
		
		AjaxSupport ajaxSupport = new AjaxSupport();
		ajaxSupport.setEvent("onchange");
		ajaxSupport.setRender(JsfListBuilder.LISTPANEL);
		setSettedAttributes(ajaxSupport, "ajaxSingle", "event", "render");
		htmlSelectBooleaFilter.getFacets().put("a4jsupport", ajaxSupport);
		List<String> attributes = (List<String>)htmlSelectBooleaFilter.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.remove("onchange");
		
		headerFragment.getChildren().add(htmlSelectBooleaFilter);
		
		
		column.getFacets().put("header", headerFragment);
		
		eb = ExpressionBuilder.instance();
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append("Filter.");
		eb.append(pf.getName());
		eb.append("Filter eq 'NONE' or ");
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append("Filter.");
		eb.append(pf.getName());
		eb.append("Filter.bool eq objectVar.");
		eb.append(pf.getName());
		//#{accountantFilter.juniorFilter eq 'NONE' or accountantFilter.juniorFilter.bool eq objectVar.junior}
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), eb.toString(), void.class);
		column.setValueExpression("filterExpression", ve);
		setSettedAttributes(column, "filterExpression");
		
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

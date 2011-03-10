package net.sf.nakeduml.seamgeneration.jsf.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.seamgeneration.jsf.JsfListBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.ajax4jsf.component.html.HtmlAjaxSupport;
import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.richfaces.component.html.HtmlColumn;
import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.convert.seamtext.tags.HtmlTag;

import com.sun.faces.el.ELContextImpl;

public class JsfListBigDecimalColumnBuilder implements IJsfPropertyFieldColumnBuilder {

	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	private PropertyField pf;
	private ClassifierUserInteraction ui;

	public JsfListBigDecimalColumnBuilder(ClassifierUserInteraction ui, PropertyField pf) {
		super();
		this.pf = pf;
		this.ui = ui;
	}

	@Override
	public HtmlColumn createColumn() {
		HtmlColumn column = new HtmlColumn();
		
		column.setLabel(this.pf.getHumanName());
		
		HtmlFragment headerFragment = new HtmlFragment();
		HtmlOutputText columnHeading = new HtmlOutputText();
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), generateComponentLabel(NameConverter.decapitalize(pf
				.getProperty().getName())), Object.class);
		columnHeading.setValue(ve);
		setSettedAttributes(columnHeading, "value");
		headerFragment.getChildren().add(columnHeading);
		
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createColumnRenderedExpression(), Object.class);		
		column.setValueExpression("rendered", ve);
		
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
		eb.append("rich:element('");
		eb.append(generateJsfId(ui, HtmlInputText.class.getSimpleName()+pf.getName()));
		eb.append("')");
		
		ajaxSupport.setOncomplete(eb.toString()+".focus()");
		setSettedAttributes(ajaxSupport, "ajaxSingle", "event", "reRender", "oncomplete");
		htmlInputText.getFacets().put("a4jsupport", ajaxSupport);
		List<String> attributes = (List<String>)htmlInputText.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.remove("onkeyup");

		
		headerFragment.getChildren().add(htmlInputText);
		column.getFacets().put("header", headerFragment);
		
		eb = ExpressionBuilder.instance();
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append("Filter.");
		eb.append(pf.getName());
		eb.append("Filter eq null or ");
		eb.append("objectVar.");
		eb.append(pf.getName());
		eb.append(".compareTo(");
		eb.append(NameConverter.decapitalize(ui.getClassifier().getName()));
		eb.append("Filter.");
		eb.append(pf.getName());
		eb.append("Filter");
		eb.append(")>0");
		
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), eb.toString(), void.class);
		column.setValueExpression("filterExpression", ve);
		
		setSettedAttributes(column, "rendered", "filterExpression", "label");
		return column;
	}
	
	private String createColumnRenderedExpression() {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		eb.append(createEditPropertyRenderedExpression(ui.getClassifier()));
		eb.append(" or ");
		eb.append(createViewPropertyRenderedExpression(ui.getClassifier()));
		return eb.toString();
	}
	
	private String createEditPropertyRenderedExpression(DomainClassifier dc) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && !pf.getSecurityOnEdit().getRequiresUserOwnership()) {
			if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() && !dc.getSecurityOnView().getRequiresGroupOwnership()) {
				eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
				eb.append(".isGroupOwnershipValidFor");
				eb.append(NameConverter.capitalize(dc.getName()));
				eb.append("(nakedUser)");
			} else {
				eb.append("true");
			}
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()) {
			if (!dc.getSecurityOnEdit().getRequiresUserOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
				eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
				eb.append(".isUserOwnershipValidFor");
				eb.append(NameConverter.capitalize(dc.getName()));
				eb.append("(nakedUser)");
			} else {
				eb.append("true");
			}
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() && pf.getSecurityOnEdit().getRequiresUserOwnership()) {
			if (!dc.getSecurityOnEdit().getRequiresUserOwnership() && !dc.getSecurityOnView().getRequiresUserOwnership()) {
				eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
				eb.append(".isUserOwnershipValidFor");
				eb.append(NameConverter.capitalize(dc.getName()));
				eb.append("(nakedUser)");
			} else {
				eb.append("true");
			}
		}
		return eb.getString();
	}

	private String createViewPropertyRenderedExpression(DomainClassifier dc) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isGroupOwnershipValidFor");
			eb.append(NameConverter.capitalize(dc.getName()));
			eb.append("(nakedUser)");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isUserOwnershipValidFor");
			eb.append(NameConverter.capitalize(dc.getName()));
			eb.append("(nakedUser)");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isUserOwnershipValidFor");
			eb.append(NameConverter.capitalize(dc.getName()));
			eb.append("(nakedUser)");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isUserOwnershipValidFor");
			eb.append(NameConverter.capitalize(dc.getName()));
			eb.append("(nakedUser)");
			eb.append(" and ");
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isGroupOwnershipValidFor");
			eb.append(NameConverter.capitalize(dc.getName()));
			eb.append("(nakedUser)");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isUserOwnershipValidFor");
			eb.append(NameConverter.capitalize(dc.getName()));
			eb.append("(nakedUser)");
			eb.append(" and ");
			eb.append(NameConverter.capitalize(dc.getName()));
			eb.append(".isGroupOwnershipValidFor");
			eb.append(NameConverter.capitalize(dc.getName()));
			eb.append("(nakedUser)");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (!pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& !pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (pf.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !pf.getSecurityOnEdit().getRequiresUserOwnership()
				&& pf.getSecurityOnView().getRequiresGroupOwnership() 
				&& pf.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		}
		return eb.getString();
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

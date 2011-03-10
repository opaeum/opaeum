package net.sf.nakeduml.seamgeneration.jsf.component.primefaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.primefaces.component.column.Column;

import com.sun.faces.el.ELContextImpl;

public class JsfListBigDecimalPrimeColumnBuilder implements IJsfPropertyFieldPrimeColumnBuilder {

	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	private PropertyField pf;
	private ClassifierUserInteraction ui;

	public JsfListBigDecimalPrimeColumnBuilder(ClassifierUserInteraction ui, PropertyField pf) {
		super();
		this.pf = pf;
		this.ui = ui;
	}

	@Override
	public Column createColumn() {
		Column column = new Column();
		column.setResizable(true);
		
		HtmlFragment headerFragment = new HtmlFragment();
		HtmlOutputText columnHeading = new HtmlOutputText();
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), generateComponentLabel(NameConverter.decapitalize(pf
				.getProperty().getName())), Object.class);
		columnHeading.setValue(ve);
		setSettedAttributes(columnHeading, "value");
		headerFragment.getChildren().add(columnHeading);
		
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createColumnRenderedExpression(), Object.class);		
		column.setValueExpression("rendered", ve);
		
		column.getFacets().put("header", headerFragment);
		
		setSettedAttributes(column, "rendered", "resizable");
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

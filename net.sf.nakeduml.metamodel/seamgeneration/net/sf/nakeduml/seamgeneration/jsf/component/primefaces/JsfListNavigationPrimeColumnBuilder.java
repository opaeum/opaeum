package net.sf.nakeduml.seamgeneration.jsf.component.primefaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.component.AbstractJsfListColumnBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.primefaces.component.column.Column;

import com.sun.faces.el.ELContextImpl;

public class JsfListNavigationPrimeColumnBuilder extends AbstractJsfListColumnBuilder implements IJsfPropertyNavigationPrimeColumnBuilder {

	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	public JsfListNavigationPrimeColumnBuilder(ClassifierUserInteraction ui,PropertyNavigation pn) {
		super(ui,pn);
	}

	@Override
	public Column createColumn() {
		Column column = new Column();
		column.setResizable(true);
		
		HtmlFragment headerFragment = new HtmlFragment();
		HtmlOutputText columnHeading = new HtmlOutputText();
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), generateComponentLabel(NameConverter.decapitalize(n
				.getProperty().getName())), Object.class);
		columnHeading.setValue(ve);
		
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createNavigationColumnRenderedExpression(), Object.class);		
		column.setValueExpression("rendered", ve);
		setSettedAttributes(column, "rendered", "rezizable");
		
		setSettedAttributes(columnHeading, "value");
		headerFragment.getChildren().add(columnHeading);
		column.getFacets().put("header", headerFragment);
		
		
		
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

}

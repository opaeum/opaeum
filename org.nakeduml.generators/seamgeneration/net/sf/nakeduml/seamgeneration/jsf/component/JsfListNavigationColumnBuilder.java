package net.sf.nakeduml.seamgeneration.jsf.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlFragment;
import org.richfaces.component.html.HtmlColumn;

import com.sun.faces.el.ELContextImpl;

public class JsfListNavigationColumnBuilder extends AbstractJsfListColumnBuilder implements IJsfPropertyNavigationColumnBuilder {

	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	public JsfListNavigationColumnBuilder(ClassifierUserInteraction ui,PropertyNavigation pn) {
		super(ui,pn);
	}

	@Override
	public HtmlColumn createColumn() {
		HtmlColumn column = new HtmlColumn();
		
		column.setLabel(this.n.getHumanName());
		
		HtmlFragment headerFragment = new HtmlFragment();
		HtmlOutputText columnHeading = new HtmlOutputText();
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), generateComponentLabel(NameConverter.decapitalize(n
				.getProperty().getName())), Object.class);
		columnHeading.setValue(ve);
		
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createNavigationColumnRenderedExpression(), Object.class);		
		column.setValueExpression("rendered", ve);
		setSettedAttributes(column, "rendered", "label");
		
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

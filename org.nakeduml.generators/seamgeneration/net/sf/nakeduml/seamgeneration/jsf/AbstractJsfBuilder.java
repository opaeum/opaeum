package net.sf.nakeduml.seamgeneration.jsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIParameter;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.event.ActionListener;
import javax.faces.event.MethodExpressionActionListener;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.source.JsfBodySource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.UserInteraction;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlDiv;
import org.primefaces.component.menu.Menu;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.submenu.Submenu;

import com.sun.el.ExpressionFactoryImpl;
import com.sun.faces.el.ELContextImpl;

public abstract class AbstractJsfBuilder extends AbstractBuilder {
	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	protected Properties uiComponentProperties;
	protected ELContext dummyELContext;
	protected ExpressionFactory expressionFactory;
	protected UIViewRoot jsfBody;
	protected HtmlDiv bodyDiv;
	
	protected AbstractJsfBuilder() {
		uiComponentProperties = new Properties();
		namespaceProperties = new Properties();
		try {
			uiComponentProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("type.mapper.properties"));
			namespaceProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("namespace.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		dummyELContext = new ELContextImpl(null);
		expressionFactory = new ExpressionFactoryImpl();
	}

	@SuppressWarnings("unchecked")
	protected void setSettedAttributes(UIComponent list, String... s) {
		list.getAttributes().put(ATTRIBUTES_THAT_ARE_SET_KEY, new ArrayList<String>());
		List<String> attributes = (List<String>) list.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.addAll(Arrays.asList(s));
	}

	@SuppressWarnings("unchecked")
	protected void addTotSettedAttributes(UIComponent list, String... s) {
		list.getAttributes().put(ATTRIBUTES_THAT_ARE_SET_KEY, new ArrayList<String>());
		List<String> attributes = (List<String>) list.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		attributes.addAll(Arrays.asList(s));
	}

	protected void addParameter(UICommand button, String name, String value) {
		UIParameter parameter = new UIParameter();
		parameter.setName(name);
		if (value.startsWith("#{")) {
			ValueExpression ve = expressionFactory.createValueExpression(dummyELContext, value, Object.class);
			parameter.setValue(ve);
		} else {
			parameter.setValue(value);
		}
		setSettedAttributes(parameter, "name", "value");
		button.getChildren().add(parameter);
	}

	protected String createCompsiteOwnerValueExpression(PropertyNavigation pn) {
		ExpressionBuilder sb = ExpressionBuilder.instance();
		sb.append(NameConverter.decapitalize(pn.getClassifierUserInteraction().getClassifier().getName()));
		if (pn.getSecurityOnView().getRequiresUserOwnership()) {
			sb.append(".get");
			sb.append(NameConverter.capitalize(pn.getProperty().getName()));
			sb.append("ForUserSecurity(nakedUser)");
			sb.append(".getDataModel()");
		} else if (!pn.getSecurityOnView().getRequiresGroupOwnership()) {
			sb.append(".");
			sb.append(pn.getProperty().getName());
			sb.append(".getDataModel()");
		} else {
			sb.append(".get");
			sb.append(NameConverter.capitalize(pn.getProperty().getName()));
			sb.append("ForGroupSecurity(nakedUser)");
			sb.append(".getDataModel()");
		}
		return sb.toString();
	}

	protected void toSource(UserInteraction ui) {
		TextOutputRoot outputRoot = textWorkspace.findOrCreateTextOutputRoot(VIEW_DIR);
		List<String> path = resolveViewId(ui, ".body.xhtml");
		outputRoot.findOrCreateTextFile(path, new JsfBodySource(jsfBody, path.size() - 1, namespaceProperties));
	}

	protected HtmlDiv createDiv(String style) {
		HtmlDiv div = new HtmlDiv();
		div.setStyleClass(style);
		setSettedAttributes(div, "styleClass");
		return div;
	}

	protected void createBodyHeader(ClassifierUserInteraction ui) {
		Panel panel = new Panel();
		panel.setToggleable(true);
		HtmlOutputText headerX = new HtmlOutputText();
		headerX.setValue(NameConverter.capitalize(NameConverter.toLowerCase(ui.getUserInteractionKind().toString())) + " " + ui.getClassifier().getName());
		setSettedAttributes(headerX, "value");
		panel.getFacets().put("header", headerX);
		
		Menu menu = new Menu();
		Submenu submenu = new Submenu();
		submenu.setLabel("#{messages['settings']}");
		setSettedAttributes(submenu, "label");
		menu.getChildren().add(submenu);
		MenuItem menuItem = new MenuItem();
		submenu.getChildren().add(menuItem);
		
		menuItem.setValue("#{messages['bookmark']}");
		MethodExpression unifiedMethodExpression = 
			SeamExpressionFactory.INSTANCE.createMethodExpression(dummyELContext, "#{bookmarkController.captureBookMark(" + NameConverter.decapitalize(ui.getClassifier().getName()) + ".id)}", null, new Class[]{});
		ActionListener listener = new MethodExpressionActionListener(unifiedMethodExpression);
		menuItem.addActionListener(listener);
		menuItem.setUpdate("growl");
		setSettedAttributes(menuItem, "value", "update");
		panel.getFacets().put("options", menu);		
		
//		HtmlDiv div = createDiv("bodyHeader");
//		jsfBody.getChildren().add(div);
//		HtmlOutputText header = new HtmlOutputText();
//		header.setValue(NameConverter.capitalize(NameConverter.toLowerCase(ui.getUserInteractionKind().toString())) + " " + ui.getClassifier().getName());
//		setSettedAttributes(header, "value");
//		div.getChildren().add(header);
		bodyDiv = createDiv("bodyContainer");
		
		String edit = createUpdateRenderedExpression(ui.getClassifier(), NameConverter.decapitalize(ui.getClassifier().getName()));
		String view = createViewRenderedExpression(ui.getClassifier());
		ExpressionBuilder eb = ExpressionBuilder.instance();
		eb.append(edit.substring(2, edit.length()-1));
		eb.append(" || ");
		eb.append(view.substring(2, view.length()-1));
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(dummyELContext, eb.toString(), void.class);
		bodyDiv.setValueExpression("rendered", ve);
		
//		CommandLink commandLink = new CommandLink();
//		commandLink.setValue("#{messages['bookmark']}");
//		MethodExpression unifiedMethodExpression = 
//			SeamExpressionFactory.INSTANCE.createMethodExpression(dummyELContext, "#{bookmarkController.captureBookMark(" + NameConverter.decapitalize(ui.getClassifier().getName()) + ".id)}", null, new Class[]{});
//		ActionListener listener = new MethodExpressionActionListener(unifiedMethodExpression);
//		commandLink.addActionListener(listener);
//		setSettedAttributes(commandLink, "value");
		
//		bodyDiv.getChildren().add(commandLink);
		panel.getChildren().add(bodyDiv);
		jsfBody.getChildren().add(panel);

	}

	protected void addUpdateButton(HtmlDiv div, DomainClassifier dc) {
		HtmlCommandButton editButton = new HtmlCommandButton();
		editButton.setValue("Update");
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(dummyELContext, "#{crudController.flush()}", void.class, new Class[] {});
		editButton.setActionExpression(me);
		setSettedAttributes(editButton, "value", "action");
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(dummyELContext, createUpdateRenderedExpression(dc, NameConverter.decapitalize(dc.getName())), void.class);
		editButton.setValueExpression("rendered", ve);
		div.getChildren().add(editButton);
	}

	protected void addDeleteButton(DomainClassifier dc, HtmlDiv div) {
		MethodExpression me;
		HtmlCommandButton deleteButton = new HtmlCommandButton();
		deleteButton.setValue("Delete");
		StringBuilder sb = new StringBuilder();
		sb.append("#{");
		sb.append(NameConverter.decapitalize(dc.getName()));
		sb.append(".markDeleted}");
		me = SeamExpressionFactory.INSTANCE.createMethodExpression(dummyELContext, sb.toString(), void.class, new Class[] {});
		deleteButton.setActionExpression(me);
		setSettedAttributes(deleteButton, "value", "action");
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(dummyELContext, createDeleteRenderedExpression(dc, NameConverter.decapitalize(dc.getName())), void.class);
		deleteButton.setValueExpression("rendered", ve);
		div.getChildren().add(deleteButton);
	}

	protected void addResetButton(HtmlDiv div, ClassifierUserInteraction ui, DomainClassifier dc) {
		HtmlCommandButton resetButton = new HtmlCommandButton();
		resetButton.setValue("Reset");
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(dummyELContext, "#{crudController.reset()}", void.class, new Class[] {});
		resetButton.setActionExpression(me);
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(dummyELContext, createUpdateRenderedExpression(dc, NameConverter.decapitalize(dc.getName())), void.class);
		resetButton.setValueExpression("rendered", ve);
		setSettedAttributes(resetButton, "value", "action");
		div.getChildren().add(resetButton);
		resetButton.setOnclick("dummyRequired();");
	}

	protected void addBackButton(HtmlDiv div) {
		HtmlCommandButton backButton = new HtmlCommandButton();
		backButton.setValue("Back");
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(dummyELContext, "back", void.class, new Class[] {});
		backButton.setActionExpression(me);
		setSettedAttributes(backButton, "value", "action");
		div.getChildren().add(backButton);
	}

	protected String generateJsfId(ClassifierUserInteraction e, String componentClassName) {
		return e.getName() + componentClassName + "JsfId";
	}

	protected abstract String getEditRenderedRoot(DomainClassifier dc);
	
	protected String createDeleteRenderedExpression(DomainClassifier dc, String securedObject) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (dc.getSecurityOnDelete().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnDelete().getRequiresGroupOwnership()) {
			eb.append("true");
		} else {
			eb.append(securedObject);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		}
		return eb.toString();
	}
	protected String createUpdateRenderedExpression(DomainClassifier dc, String securedObject) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
					&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
					&& !dc.getSecurityOnView().getRequiresGroupOwnership()
					&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("true");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(securedObject);
			eb.append(".isGroupOwnershipValid(nakedUser)");
		}		
		return eb.toString();
	}

	protected String createViewRenderedExpression(DomainClassifier dc) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
					&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
					&& !dc.getSecurityOnView().getRequiresGroupOwnership()
					&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isUserOwnershipValid(nakedUser)");
			eb.append(" and ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("not ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isUserOwnershipValid(nakedUser)");
			eb.append(" and ");
			eb.append(getEditRenderedRoot(dc));
			eb.append(".isGroupOwnershipValid(nakedUser)");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (!dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& !dc.getSecurityOnView().getRequiresGroupOwnership()
				&& !dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		} else if (dc.getSecurityOnEdit().getRequiresGroupOwnership() 
				&& !dc.getSecurityOnEdit().getRequiresUserOwnership()
				&& dc.getSecurityOnView().getRequiresGroupOwnership()
				&& dc.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append("false");
		}
		return eb.toString();
	}
}
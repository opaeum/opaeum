package net.sf.nakeduml.seamgeneration.jsf;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIData;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.event.ActionListener;
import javax.faces.event.MethodExpressionActionListener;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.linkage.SourcePopulationResolver;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.seamgeneration.jsf.component.JsfFactoryColumn;
import net.sf.nakeduml.seamgeneration.jsf.component.JsfFactoryListInputOutput;
import net.sf.nakeduml.seamgeneration.jsf.component.JsfListNavigationBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.JsfOperationListNavigationBuilder;
import net.sf.nakeduml.seamgeneration.localization.JsfLocalizationPropertiesBuilder;
import net.sf.nakeduml.seamgeneration.page.SeamEditPageBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationNavigation;
import net.sf.nakeduml.userinteractionmetamodel.OperationParticipationKind;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

import org.ajax4jsf.component.html.HtmlAjaxOutputPanel;
import org.ajax4jsf.component.html.HtmlLoadScript;
import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlDiv;
import org.primefaces.component.column.Column;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.panel.Panel;
import org.richfaces.component.UIDataTable;
import org.richfaces.component.html.HtmlColumn;
import org.richfaces.component.html.HtmlDatascroller;
import org.richfaces.component.html.HtmlExtendedDataTable;

import com.sun.faces.el.ELContextImpl;

@StepDependency(phase = SeamTransformationPhase.class, after = JsfEditBuilder.class, requires = { JsfLocalizationPropertiesBuilder.class,
		JsfCreateBuilder.class, JsfMenuBuilder.class, JsfBuilder.class, SeamEditPageBuilder.class, SourcePopulationResolver.class })
public class JsfListBuilder extends AbstractJsfBuilder {

	public static String LISTPANEL = "thisisaveryspecialid";

	@VisitBefore
	public void beforeClassifierUserInteraction(ClassifierUserInteraction ui) {
		this.jsfBody = new UIViewRoot();

		switch (ui.getUserInteractionKind()) {
		case LIST:

			if (ui.isTooMany()) {
				// Build search screen

				createSearchFields(ui);

				createDataTable(ui);
				// iterate here to ensure correct order
				for (PropertyField f : ui.getPropertyField()) {
					addRowField(ui, f, DataTable.class);
				}
				// iterate here to ensure correct order
				for (PropertyNavigation n : ui.getLinkNavigation()) {
					addRowLinksPrimeFaces(ui, n, DataTable.class);
				}

				// for(OperationNavigation n:ui.getOperationNavigation()){
				// //This is all wrong, jippo for now
				// if
				// (n.getParticipationKind()==OperationParticipationKind.VISIBLE)
				// {
				// addRowLinks(ui, n, DataTable.class);
				// }
				// }
			} else {

				addFilterScript();

				createExtendedDataTable(ui);
				// iterate here to ensure correct order
				for (PropertyField f : ui.getPropertyField()) {
					addRowField(ui, f, HtmlExtendedDataTable.class, true);
				}
				// iterate here to ensure correct order
				for (PropertyNavigation n : ui.getLinkNavigation()) {
					addRowLinks(ui, n, HtmlExtendedDataTable.class);
				}

				for (OperationNavigation n : ui.getOperationNavigation()) {
					// This is all wrong, jippo for now
					if (n.getParticipationKind() == OperationParticipationKind.VISIBLE) {
						addRowLinks(ui, n, HtmlExtendedDataTable.class);
					}
				}
			}

			break;
		}
	}

	private void createSearchFields(ClassifierUserInteraction ui) {
		Panel panel = new Panel();
		jsfBody.getChildren().add(panel);
		panel.setStyleClass("searchPanel");
		panel.setToggleable(true);
		setSettedAttributes(panel, "styleClass", "toggleable");
		
		for (PropertyField f : ui.getPropertyField()) {
			//TODO this needs to be unhardcoded, probably the uimetamodel must support search fields
			
			if (f.getName().equals("uid") || f.getName().equals("name")) {
				panel.getChildren().add(
						JsfFactoryListInputOutput.instance().getJsfListSearchPrimeInputOutputBuilder(
								ui, ui.getClassifier(), f).createUIComponent());
			}
			
		}
		CommandButton searchButton = new CommandButton();
		
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(new ELContextImpl(null), "#{crudQueryController.search}", void.class,
				new Class[] {});
		ActionListener listener = new MethodExpressionActionListener(me);
		searchButton.addActionListener(listener);
		searchButton.setUpdate(generateJsfId(ui, DataTable.class.getSimpleName()));
		searchButton.setImmediate(true);
		setSettedAttributes(searchButton, "update", "immediate");
		panel.getChildren().add(searchButton);
	}

	private void createDataTable(ClassifierUserInteraction ui) {
		DataTable dataTable = new DataTable();
		dataTable.setDynamic(true);
		dataTable.setLazy(true);
		dataTable.setPaginator(true);
		dataTable.setStyleClass("cmQueryDataTable");
		dataTable.setScrollable(true);
		dataTable.setWidth("100%");
//		dataTable.setHeight("500px");

		ExpressionBuilder sb = ExpressionBuilder.instance();
		sb.append("nakedQueryTooMany");

		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(dummyELContext, sb.toString(), void.class);
		dataTable.setValueExpression("value", ve);
		dataTable.setId(generateJsfId(ui, dataTable.getClass().getSimpleName()));

		dataTable.setVar("objectVar");
		dataTable.setRows(Integer.valueOf(this.config.getTooManyRataTableRows()));
		addTotSettedAttributes(dataTable, "id", "value", "var", "rows", "styleClass", "dynamic", "lazy", "paginator", "scrollable", "width", "height");

		jsfBody.getChildren().add(dataTable);
	}

	@VisitAfter
	public void afterClassifierUserInteraction(ClassifierUserInteraction ui) {
		switch (ui.getUserInteractionKind()) {
		case LIST:
			if (ui.isTooMany()) {
				doAfterListClassifierUserInteraction(ui, DataTable.class);
			} else {
				doAfterListClassifierUserInteraction(ui, HtmlExtendedDataTable.class);
			}
			toSource(ui);
			break;
		}
	}

	private void addFilterScript() {
		HtmlLoadScript htmlLoadScript = new HtmlLoadScript();
		htmlLoadScript.setSrc("resource:///javascript/filterScript.js");
		addTotSettedAttributes(htmlLoadScript, "src");
		jsfBody.getChildren().add(htmlLoadScript);
	}

	private void createExtendedDataTable(ClassifierUserInteraction ui) {
		HtmlAjaxOutputPanel ajaxOutputPanel = new HtmlAjaxOutputPanel();
		ajaxOutputPanel.setId(LISTPANEL);
		addTotSettedAttributes(ajaxOutputPanel, "id");

		HtmlExtendedDataTable table = new HtmlExtendedDataTable();

		HtmlOutputText tableHeading = new HtmlOutputText();
		tableHeading.setValue(ui.getHumanName());
		setSettedAttributes(tableHeading, "value");
		table.getFacets().put("header", tableHeading);

		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(dummyELContext, createCompsiteOwnerValueExpression(ui
				.getOriginatingPropertyNavigation()), void.class);
		table.setValueExpression("value", ve);
		table.setId(generateJsfId(ui, table.getClass().getSimpleName()));
		table.setVar("objectVar");
		table.setHeaderClass("listHeader");
		table.setRows(5);
		addTotSettedAttributes(table, "id", "value", "var", "headerClass", "rows");
		ajaxOutputPanel.getChildren().add(table);

		HtmlDatascroller datascroller = new HtmlDatascroller();
		datascroller.setFor(generateJsfId(ui, table.getClass().getSimpleName()));
		addTotSettedAttributes(datascroller, "for");

		ajaxOutputPanel.getChildren().add(datascroller);

		jsfBody.getChildren().add(ajaxOutputPanel);

	}

	private void addRowField(ClassifierUserInteraction ui, PropertyField pf, Class tableClass, boolean filter) {
		TypedElementParticipationKind participationKind = pf.getParticipationKind();
		if (participationKind == TypedElementParticipationKind.READONLY || participationKind == TypedElementParticipationKind.READWRITE
				|| participationKind == TypedElementParticipationKind.REQUIRED) {
			UIDataTable table = (UIDataTable) jsfBody.findComponent(generateJsfId(ui, tableClass.getSimpleName()));
			HtmlColumn column = JsfFactoryColumn.instance().getJsfColumnBuilder(ui, pf, filter).createColumn();
			column.getChildren().add(JsfFactoryListInputOutput.instance().getJsfListInputOutputBuilder(ui.getClassifier(), pf).createUIComponent());
			table.getChildren().add(column);
		}
	}

	private void addRowField(ClassifierUserInteraction ui, PropertyField pf, Class tableClass) {
		TypedElementParticipationKind participationKind = pf.getParticipationKind();
		if (participationKind == TypedElementParticipationKind.READONLY || participationKind == TypedElementParticipationKind.READWRITE
				|| participationKind == TypedElementParticipationKind.REQUIRED) {
			DataTable table = (DataTable) jsfBody.findComponent(generateJsfId(ui, tableClass.getSimpleName()));
			Column column = JsfFactoryColumn.instance().getJsfColumnBuilder(ui, pf).createColumn();
			column.getChildren().add(JsfFactoryListInputOutput.instance().getJsfListPrimeInputOutputBuilder(ui.getClassifier(), pf).createUIComponent());
			table.getChildren().add(column);
		}
	}

	private void addRowLinks(ClassifierUserInteraction ui, PropertyNavigation n, Class tableClass) {
		UIDataTable table = (UIDataTable) jsfBody.findComponent(generateJsfId(ui, tableClass.getSimpleName()));
		HtmlColumn column = JsfFactoryColumn.instance().getJsfColumnBuilder(ui, n).createColumn();
		column.getChildren().add(new JsfListNavigationBuilder(ui.getClassifier(), n).createUILink());
		table.getChildren().add(column);
	}

	private void addRowLinksPrimeFaces(ClassifierUserInteraction ui, PropertyNavigation n, Class tableClass) {
		DataTable table = (DataTable) jsfBody.findComponent(generateJsfId(ui, tableClass.getSimpleName()));
		Column column = JsfFactoryColumn.instance().getJsfPrimeColumnBuilder(ui, n).createColumn();
		column.getChildren().add(new JsfListNavigationBuilder(ui.getClassifier(), n).createUILink());
		table.getChildren().add(column);
	}

	private void addRowLinks(ClassifierUserInteraction ui, OperationNavigation n, Class tableClass) {
		UIDataTable table = (UIDataTable) jsfBody.findComponent(generateJsfId(ui, tableClass.getSimpleName()));
		HtmlColumn column = JsfFactoryColumn.instance().getJsfColumnBuilder(ui, n).createColumn();
		column.getChildren().add(new JsfOperationListNavigationBuilder(ui.getClassifier(), n).createUILink());
		table.getChildren().add(column);
	}

	private void doAfterListClassifierUserInteraction(ClassifierUserInteraction ui, Class tableClass) {
		UIData table = (UIData) jsfBody.findComponent(generateJsfId(ui, tableClass.getSimpleName()));
		HtmlDiv div = createDiv("buttonBox");
		addEditColumn(ui.getClassifier(), table);
		jsfBody.getChildren().add(div);
		if (ui.getOriginatingPropertyNavigation().getProperty().isComposite()) {
			addDeleteColumn(ui.getOriginatingPropertyNavigation(), ui.getClassifier(), table);
			addCreateButton(div, ui);
			addUpdateButton(div, ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier());
			addResetButton(div, ui, ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier());
		} else {
			addUpdateButton(div, ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier());

		}
	}

	private void addEditColumn(DomainClassifier dc, UIData table) {
		HtmlOutputText columnHeading = new HtmlOutputText();
		columnHeading.setValue("#{messages['edit']}");
		setSettedAttributes(columnHeading, "value");
		HtmlColumn column = new HtmlColumn();
		column.setLabel("#{messages['edit']}");
		column.getFacets().put("header", columnHeading);
		setSettedAttributes(column, "label");

		HtmlCommandLink viewLink = createLinkTo();
		addParameter(viewLink, "navigateTo", "edit");
		column.getChildren().add(viewLink);
		table.getChildren().add(column);
	}

	private HtmlCommandLink createLinkTo() {
		HtmlCommandLink viewLink = new HtmlCommandLink();
		viewLink.setValue("#{'edit'}");
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(dummyELContext, "#{crudController.outjectCompositionOwner(objectVar)}",
				void.class, new Class[] {});
		viewLink.setActionExpression(me);
		setSettedAttributes(viewLink, "value", "action");
		return viewLink;
	}

	private void addDeleteColumn(PropertyNavigation originating, DomainClassifier dc, UIData table) {
		HtmlOutputText columnHeading = new HtmlOutputText();
		columnHeading.setValue("#{messages['delete']}");
		setSettedAttributes(columnHeading, "value");

		HtmlColumn column = new HtmlColumn();
		column.setLabel("#{messages['delete']}");
		column.getFacets().put("header", columnHeading);

		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null),
				createDeleteColumnRenderedExpression(originating, dc), Object.class);
		column.setValueExpression("rendered", ve);
		setSettedAttributes(column, "rendered", "label");

		HtmlCommandLink deleteLink = createDeleteLink();
		column.getChildren().add(deleteLink);
		ve = SeamExpressionFactory.INSTANCE.createValueExpression(dummyELContext, createDeleteRenderedExpression(dc, "objectVar"), void.class);
		deleteLink.setValueExpression("rendered", ve);
		table.getChildren().add(column);
	}

	private String createDeleteColumnRenderedExpression(PropertyNavigation n, DomainClassifier dc) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (dc.getSecurityOnDelete().getRequiresUserOwnership()) {
			eb.append(NameConverter.decapitalize(n.getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isUserOwnershipValidFor");
			eb.append(NameConverter.capitalize(n.getProperty().getName()));
			eb.append("(nakedUser)");
		} else if (!dc.getSecurityOnDelete().getRequiresGroupOwnership()) {
			eb.append("true");
		} else {
			eb.append(NameConverter.decapitalize(n.getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isGroupOwnershipValidFor");
			eb.append(NameConverter.capitalize(n.getProperty().getName()));
			eb.append("(nakedUser)");
		}
		return eb.toString();
	}

	private HtmlCommandLink createDeleteLink() {
		HtmlCommandLink deleteLink = new HtmlCommandLink();
		deleteLink.setValue("#{messages['delete']}");
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(dummyELContext, "#{objectVar.markDeleted}", void.class, new Class[] {});
		deleteLink.setActionExpression(me);
		setSettedAttributes(deleteLink, "value", "action");
		return deleteLink;
	}

	private void addCreateButton(HtmlDiv div, ClassifierUserInteraction ui) {
		HtmlCommandButton button = new HtmlCommandButton();
		button.setValue(NameConverter.capitalize(UserInteractionKind.CREATE.name().toLowerCase()));
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(dummyELContext, "create_"
				+ ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getName(), void.class, new Class[] {});
		button.setActionExpression(me);

		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), createCreateColumnRenderedExpression(ui
				.getOriginatingPropertyNavigation()), Object.class);
		button.setValueExpression("rendered", ve);

		addTotSettedAttributes(button, "value", "action");
		div.getChildren().add(button);
	}

	private String createCreateColumnRenderedExpression(PropertyNavigation originatingPropertyNavigation) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (originatingPropertyNavigation.getProperty().getSecurityOnAdd().getRequiresUserOwnership()) {
			eb.append(NameConverter.decapitalize(originatingPropertyNavigation.getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!originatingPropertyNavigation.getProperty().getSecurityOnAdd().getRequiresGroupOwnership()) {
			eb.append("true");
		} else {
			eb.append(NameConverter.decapitalize(originatingPropertyNavigation.getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isGroupOwnershipValid(nakedUser)");
		}
		return eb.toString();
	}

	@Override
	protected String getEditRenderedRoot(DomainClassifier dc) {
		return "objectVar";
	}

}

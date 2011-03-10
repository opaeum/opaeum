package net.sf.nakeduml.jsf2generation.component;


public class Jsf2LinkBuilder /*extends AbstractJsf2LinkBuilder*/ {

//	public Jsf2LinkBuilder(DomainClassifier dc, PropertyNavigation n) {
//		super(dc, n);
//	}
//
//	@Override
//	public UICommand createComponent() {
//		return new HtmlCommandLink();
//	}
//
//	@Override
//	protected HtmlDecorate addDecoration(UICommand uiCommand, String template, ValueExpression ve, PropertyNavigation n) {
//		HtmlDecorate htmlDecorate = new HtmlDecorate();
//		htmlDecorate.setTemplate(template);
//		setSettedAttributes(htmlDecorate, "template");
//		htmlDecorate.setValueExpression("rendered", ve);
//		HtmlOutputText label = setUpLabel(n);
//		htmlDecorate.getChildren().add(label);
//		htmlDecorate.getChildren().add(uiCommand);
//		return htmlDecorate;
//	}
//
//	@Override
//	protected String createNavigationActionExpression(DomainClassifier dc, PropertyNavigation n) {
//		ExpressionBuilder eb = ExpressionBuilder.instance();
//		eb.append("crudController.outjectCompositionOwner(");
//		eb.append(NameConverter.decapitalize(dc.getName()));
//		eb.append(".");
//		eb.append(n.getProperty().getName());
//		eb.append(")");
//		return eb.toString();
//	}
//
//	@Override
//	protected String createNavigationRenderedExpression(DomainClassifier dc, PropertyNavigation n) {
//		ExpressionBuilder eb = ExpressionBuilder.instance();
//		if (n.getSecurityOnView().getRequiresUserOwnership()) {
//			eb.append(NameConverter.decapitalize(dc.getName()));
//			eb.append(".isUserOwnershipValid(nakedUser)");
//		} else if (n.getSecurityOnView().getRequiresGroupOwnership()) {
//			eb.append(NameConverter.decapitalize(dc.getName()));
//			eb.append(".isGroupOwnershipValid(nakedUser)");
//		} else if (!n.getSecurityOnView().getRequiresGroupOwnership()) {
//			eb.append("true");
//		} else {
//			eb.append(NameConverter.decapitalize(dc.getName()));
//			eb.append(".isGroupOwnershipValid(nakedUser)");
//		}
//		return eb.toString();
//	}
//
//	@Override
//	protected String createNavigationValueExpression(DomainClassifier dc, PropertyNavigation n) {
//		ExpressionBuilder eb = ExpressionBuilder.instance(); 
//		if (n.getResultingUserInteraction().getUserInteractionKind()==UserInteractionKind.LIST) {
//			eb.append("'");
//			eb.append(n.getName());
//			eb.append("'");
//		} else {
//			eb.append(NameConverter.decapitalize(dc.getName()));
//			eb.append(".");
//			eb.append(n.getTypedElement().getName());
//			eb.append(".name");
//		}
//		return eb.toString();
//	}
//
//	@Override
//	protected String getDisplayTemplate() {
//		return "/layout/display.xhtml";
//	}
//
//	//TODO check for duplicates
//	private HtmlOutputText setUpLabel(UserInteractionElement ui) {
//		HtmlOutputText label = new HtmlOutputText();
//		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(new ELContextImpl(null), retrieveComponentLabel(ui.getRepresentedElement().getName()), Object.class);
//		label.setValue(ve);
//		setSettedAttributes(label, "value");
//		return label;
//	}
//
//	//TODO check for duplicates
//	private String retrieveComponentLabel(String name) {
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append("#{messages['");
//		stringBuilder.append(name);
//		stringBuilder.append("']}");
//		return stringBuilder.toString();
//	}
	
}

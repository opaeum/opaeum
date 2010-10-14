package net.sf.nakeduml.seamgeneration.jsf;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlOutputText;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.linkage.SourcePopulationResolver;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.seamgeneration.jsf.component.JsfFactoryInputOutput;
import net.sf.nakeduml.seamgeneration.page.SeamEditPageBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyField;
import net.sf.nakeduml.userinteractionmetamodel.TypedElementParticipationKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

import org.jboss.seam.el.SeamExpressionFactory;
import org.jboss.seam.ui.component.html.HtmlDiv;
import org.jboss.seam.ui.component.html.HtmlFragment;

@StepDependency(phase = SeamTransformationPhase.class,after=JsfListBuilder.class,requires = {JsfContainmentBuilder.class, JsfMenuBuilder.class,JsfBuilder.class,
	SeamEditPageBuilder.class,SourcePopulationResolver.class})
public class JsfCreateBuilder extends AbstractJsfBuilder {
	@VisitBefore
	public void beforeClassifierUserInteraction(ClassifierUserInteraction ui){
		jsfBody = new UIViewRoot();
		switch(ui.getUserInteractionKind()){
		case CREATE:
			createBodyHeader(ui);
			// iterate here to ensure correct order
			for(PropertyField f:ui.getPropertyField()){
				if (f.getParticipationKind()==TypedElementParticipationKind.HIDDEN || f.getParticipationKind()==TypedElementParticipationKind.NAVIGATION) {
					continue;
				}
				HtmlFragment htmlFragment = JsfFactoryInputOutput.instance().getJsfInputOutputBuilder(ui, ui.getClassifier(), f).createUIComponent();
				bodyDiv.getChildren().add(htmlFragment);
			}
			break;
		}
	}
	
	protected void createBodyHeader(ClassifierUserInteraction ui) {
		HtmlDiv div = createDiv("bodyHeader");
		jsfBody.getChildren().add(div);
		HtmlOutputText header = new HtmlOutputText();
		header.setValue(NameConverter.capitalize(NameConverter.toLowerCase(ui.getUserInteractionKind().toString())) + " " + ui.getClassifier().getName());
		setSettedAttributes(header, "value");
		div.getChildren().add(header);
		bodyDiv = createDiv("bodyContainer");
		
		String create = createCreateRenderedExpression(ui);
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(dummyELContext, create, void.class);
		bodyDiv.setValueExpression("rendered", ve);
		
		jsfBody.getChildren().add(bodyDiv);

	}	
	protected String createCreateRenderedExpression(ClassifierUserInteraction ui) {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (ui.getClassifier().getSecurityOnAdd().getRequiresUserOwnership()) {
			eb.append(NameConverter.toLowerCase(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isUserOwnershipValid(nakedUser)");
		} else if (!ui.getClassifier().getSecurityOnAdd().getRequiresGroupOwnership()) {
			eb.append("true");
		} else {
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isGroupOwnershipValid(nakedUser)");
		}
		return eb.toString();
	}
	@VisitAfter
	public void afterClassifierUserInteraction(ClassifierUserInteraction ui){
		switch(ui.getUserInteractionKind()){
		case CREATE:
			doAfterCreateClassifierUserInteraction(ui);
			toSource(ui);
			break;
		}
	}
	private void doAfterCreateClassifierUserInteraction(ClassifierUserInteraction ui){
		HtmlDiv div = createDiv("buttonBox");
		jsfBody.getChildren().add(div);
		addBackButton(div);
		addCreateButton(ui, div);
	}
	private HtmlCommandButton addCreateButton(ClassifierUserInteraction ui, HtmlDiv div){
		HtmlCommandButton createButton = new HtmlCommandButton();
		createButton.setValue(NameConverter.capitalize(UserInteractionKind.CREATE.name().toLowerCase()));

		String securedObject = "";
		if (ui.getUserInteractionKind()!=UserInteractionKind.CREATE || ui.getOriginatingPropertyNavigation()==null) {
			securedObject = NameConverter.decapitalize(ui.getClassifier().getName());
		} 
		securedObject = NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName() + "_" + ui.getClassifier().getName());
		
		StringBuilder sb = new StringBuilder();
		sb.append("#{");
		sb.append(securedObject);
		sb.append(".addToOwningObject()}");
		MethodExpression me = SeamExpressionFactory.INSTANCE.createMethodExpression(dummyELContext, sb.toString(), void.class, new Class[]{});
		createButton.setActionExpression(me);
		addTotSettedAttributes(createButton, "id", "action", "value");
		
		ValueExpression ve = SeamExpressionFactory.INSTANCE.createValueExpression(dummyELContext, createUpdateRenderedExpression(ui.getClassifier(), securedObject), void.class);
		createButton.setValueExpression("rendered", ve);
		div.getChildren().add(createButton);
		return createButton;
	}
	@Override
	protected String getEditRenderedRoot(DomainClassifier dc) {
		return NameConverter.decapitalize(dc.getName());
	}
	
}
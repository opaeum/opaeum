package net.sf.nakeduml.seamgeneration.jsf.component;

import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;

public abstract class AbstractJsfListColumnBuilder {

	protected PropertyNavigation n;
	protected ClassifierUserInteraction ui; 
	
	public AbstractJsfListColumnBuilder(ClassifierUserInteraction ui,PropertyNavigation pn) {
		super();
		this.n = pn;
		this.ui=ui;
	}
	
	protected String createNavigationColumnRenderedExpression() {
		ExpressionBuilder eb = ExpressionBuilder.instance();
		if (n.getSecurityOnView().getRequiresUserOwnership()) {
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isUserOwnershipValidFor");
			eb.append(ui.getClassifier().getName());
			eb.append("(nakedUser)");
		} else if (n.getSecurityOnView().getRequiresGroupOwnership()) {
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isGroupOwnershipValidFor");
			
			eb.append(NameConverter.capitalize(ui.getOriginatingPropertyNavigation().getProperty().getName()));		
			eb.append("(nakedUser)");
		} else if (!n.getSecurityOnView().getRequiresGroupOwnership()) {
			eb.append("true");
		} else {
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isGroupOwnershipValidFor");
			eb.append(NameConverter.capitalize(ui.getOriginatingPropertyNavigation().getProperty().getName()));		
			eb.append("(nakedUser)");
		}
		return eb.toString();
	}

}

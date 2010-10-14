package net.sf.nakeduml.jsf2generation.component;

import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.jsf.ExpressionBuilder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;

public abstract class AbstractJsf2ListColumnBuilder implements IJsf2PropertyNavigationColumnBuilder {

	protected PropertyNavigation n;
	protected ClassifierUserInteraction ui; 
	
	public AbstractJsf2ListColumnBuilder(ClassifierUserInteraction ui,PropertyNavigation pn) {
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
			eb.append(NameConverter.capitalize(n.getProperty().getName()));
			eb.append("(nakedUser)");
		} else if (n.getSecurityOnView().getRequiresGroupOwnership()) {
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isGroupOwnershipValidFor");
			eb.append(ui.getClassifier().getName());
			eb.append(NameConverter.capitalize(n.getProperty().getName()));
			eb.append("(nakedUser)");
		} else if (!n.getSecurityOnView().getRequiresGroupOwnership()) {
			eb.append("true");
		} else {
			eb.append(NameConverter.decapitalize(ui.getOriginatingPropertyNavigation().getClassifierUserInteraction().getClassifier().getName()));
			eb.append(".isGroupOwnershipValidFor");
			eb.append(ui.getClassifier().getName());
			eb.append(NameConverter.capitalize(n.getProperty().getName()));
			eb.append("(nakedUser)");
		}
		return eb.toString();
	}

}

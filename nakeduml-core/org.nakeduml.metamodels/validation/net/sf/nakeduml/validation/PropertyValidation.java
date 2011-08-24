package net.sf.nakeduml.validation;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.validation.namegeneration.UmlNameRegenerator;

@StepDependency(phase = ValidationPhase.class)
public class PropertyValidation extends AbstractValidator{
	// TODO parameters
	@VisitBefore(matchSubclasses = true)
	public void visitProperty(INakedProperty p){
		if(p.getSubsettedProperties().size()>0){
			for(INakedProperty sp:p.getSubsettedProperties()){
				if(!sp.isDerivedUnion()){
					getErrorMap().putError(sp, CoreValidationRule.SUBSETTED_PROPERTY_NO_UNION, p.getOwner().getName(),p.getName());
				}
			}
		}
	}
}

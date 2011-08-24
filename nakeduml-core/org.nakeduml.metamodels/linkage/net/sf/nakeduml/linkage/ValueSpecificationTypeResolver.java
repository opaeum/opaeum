package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.INakedValuePin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;

@StepDependency(phase = LinkagePhase.class, requires = { TypeResolver.class }, after = { TypeResolver.class })
public class ValueSpecificationTypeResolver extends AbstractModelElementLinker {
	@VisitBefore(matchSubclasses = true)
	public void visitProperty(INakedProperty p) {
		processValue(p.getInitialValue(), p);
	}
	@VisitBefore(matchSubclasses=true)
	public void visitOperation(INakedOperation o){
		if(o.getBodyCondition()!=null && o.getReturnParameter()!=null){
			processValue(o.getBodyCondition().getSpecification(), o.getReturnParameter());
		}
	}

	private void processValue(INakedValueSpecification value, INakedTypedElement te) {
		if (value != null) {
			value.setType(te.getType());
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitValuePin(INakedValuePin p) {
		processValue(p.getValue(), p);
	}

	@VisitBefore(matchSubclasses = true)
	public void visitSlot(INakedSlot p) {
		for (INakedValueSpecification s : p.getValues()) {
			if (p.getValues().size() == 1) {
				s.setType(p.getDefiningFeature().getType());
			} else {
				s.setType(p.getDefiningFeature().getNakedBaseType());
			}
		}
	}
}

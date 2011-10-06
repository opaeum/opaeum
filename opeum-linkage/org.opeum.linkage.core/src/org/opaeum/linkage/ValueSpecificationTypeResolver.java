package org.opeum.linkage;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.activities.INakedValuePin;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSlot;
import org.opeum.metamodel.core.INakedTypedElement;
import org.opeum.metamodel.core.INakedValueSpecification;

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

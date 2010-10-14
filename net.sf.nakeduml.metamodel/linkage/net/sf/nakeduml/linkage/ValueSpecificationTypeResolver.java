package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.INakedValuePin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

@StepDependency(phase = LinkagePhase.class, requires = { TypeResolver.class }, after = { TypeResolver.class })
public class ValueSpecificationTypeResolver extends AbstractModelElementLinker {
	@VisitAfter(matchSubclasses = true)
	public void visitProperty(INakedProperty p) {
		processValue(p.getInitialValue(), p.getOwner(), p, p.getType());
	}

	private void processValue(INakedValueSpecification value, INakedClassifier owner, IModelElement element, IClassifier type) {
		if (value != null) {
			value.setType(type);
			if (value.getValue() instanceof ParsedOclString) {
				ParsedOclString pos = (ParsedOclString) value.getValue();
				pos.setContext(owner, element);
			}
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitValuePin(INakedValuePin p) {
		processValue(p.getValue(), p.getActivity(), p, p.getType());
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

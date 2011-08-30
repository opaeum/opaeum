package net.sf.nakeduml.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedStructuralFeatureAction;

@StepDependency(phase = ValidationPhase.class)
public class ReservedWordValidator extends AbstractValidator{
}

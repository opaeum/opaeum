package org.nakeduml.tinker.linkage;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.linkage.MappedTypeLinker;
import org.opaeum.linkage.NakedParsedOclStringResolver;
import org.opaeum.linkage.PinLinker;
import org.opaeum.linkage.QualifierLogicCalculator;
import org.opaeum.linkage.ReferenceResolver;
import org.opaeum.linkage.TypeResolver;
import org.opaeum.metamodel.core.INakedProperty;

@StepDependency(phase = LinkagePhase.class, after = { MappedTypeLinker.class, PinLinker.class, ReferenceResolver.class, TypeResolver.class }, requires = { MappedTypeLinker.class,
		PinLinker.class, ReferenceResolver.class, TypeResolver.class }, before = NakedParsedOclStringResolver.class, replaces = QualifierLogicCalculator.class)
public class TinkerQualifierLogicCalculator extends QualifierLogicCalculator {

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedProperty p){
	}
	
	
}

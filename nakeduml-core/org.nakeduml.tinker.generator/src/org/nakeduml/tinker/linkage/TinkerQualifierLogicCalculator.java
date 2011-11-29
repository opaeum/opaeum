package org.nakeduml.tinker.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.linkage.MappedTypeLinker;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.linkage.PinLinker;
import net.sf.nakeduml.linkage.QualifierLogicCalculator;
import net.sf.nakeduml.linkage.ReferenceResolver;
import net.sf.nakeduml.linkage.TypeResolver;
import net.sf.nakeduml.metamodel.core.INakedProperty;

@StepDependency(phase = LinkagePhase.class, after = { MappedTypeLinker.class, PinLinker.class, ReferenceResolver.class, TypeResolver.class }, requires = { MappedTypeLinker.class,
		PinLinker.class, ReferenceResolver.class, TypeResolver.class }, before = NakedParsedOclStringResolver.class, replaces = QualifierLogicCalculator.class)
public class TinkerQualifierLogicCalculator extends QualifierLogicCalculator {

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedProperty p){
	}
	
	
}

package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.name.NameConverter;

@StepDependency(phase = LinkagePhase.class,after = {MappedTypeLinker.class,PinLinker.class,
		ReferenceResolver.class,TypeResolver.class,ValueSpecificationTypeResolver.class},requires = {MappedTypeLinker.class,
		PinLinker.class,ReferenceResolver.class,TypeResolver.class,ValueSpecificationTypeResolver.class},before = NakedParsedOclStringResolver.class)
public class QualifierLogicCalculator extends AbstractModelElementLinker{
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedProperty p){
		if(p.getOtherEnd() != null && p.getOtherEnd().getQualifierNames().length > 0){
			StringBuilder ocl = new StringBuilder("self.").append(p.getName()).append(".").append(p.getOtherEnd().getName()).append("->forAll(p|(");
			boolean first = true;
			for(INakedProperty q:p.getOtherEnd().getQualifiers()){
				if(!first){
					ocl.append(" and ");
				}
				first = false;
				ocl.append("p.");
				ocl.append(q.getName());
				ocl.append("=self.");
				ocl.append(q.getName());
			}
			ocl.append(") implies p=self)");
//			System.out.println("qualifierLogic:" + ocl);
			ConstraintUtil.buildArtificialConstraint(p, ocl.toString(), "uniqueIn" + NameConverter.capitalize(p.getName()));
		}
	}
}

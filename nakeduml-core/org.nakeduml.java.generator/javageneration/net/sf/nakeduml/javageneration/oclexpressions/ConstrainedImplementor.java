package net.sf.nakeduml.javageneration.oclexpressions;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.OperationAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedMultiplicityElement;
import net.sf.nakeduml.metamodel.models.INakedModel;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.runtime.domain.IConstrained;
import org.nakeduml.runtime.domain.IInvariantError;

/**
 * This class implements the Constrained interface on classes that have invariants.
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {
	OperationAnnotator.class,NakedParsedOclStringResolver.class
},after = {
	OperationAnnotator.class
},before = CodeCleanup.class)
public class ConstrainedImplementor extends AbstractJavaProducingVisitor{
	private static final OJPathName CONSTRAINED = new OJPathName(IConstrained.class.getName());
	@VisitBefore()
	public void visitModel(INakedModel p){
		if(JavaTransformationPhase.IS_RUNTIME_AVAILABLE){
			super.javaModel.findClass(new OJPathName(UtilityCreator.getUtilPathName() + ".InvariantError")).addToImplementedInterfaces(
					new OJPathName(IInvariantError.class.getName()));
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		OJPathName path = OJUtil.classifierPathname(c);
		OJClassifier ojClassifier = this.javaModel.findIntfOrCls(path);
		if(ojClassifier instanceof OJClass && c instanceof INakedClassifier){
			INakedClassifier nc = c;
			if(hasInvariants(nc)){
				OJClass ojClass = (OJClass) ojClassifier;
				ojClass.addToImplementedInterfaces(CONSTRAINED);
			}
		}
	}
	private boolean hasInvariants(INakedClassifier nc){
		for(INakedConstraint c:nc.getOwnedRules()){
			if(!(c.getConstrainedElement() instanceof INakedMultiplicityElement)){
				return true;
			}
		}
		return false;
	}
}

package org.opeum.javageneration.oclexpressions;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJClassifier;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.OperationAnnotator;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.NakedParsedOclStringResolver;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedConstraint;
import org.opeum.metamodel.core.INakedMultiplicityElement;
import org.opeum.metamodel.models.INakedModel;
import org.opeum.runtime.domain.IConstrained;
import org.opeum.runtime.domain.IInvariantError;

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

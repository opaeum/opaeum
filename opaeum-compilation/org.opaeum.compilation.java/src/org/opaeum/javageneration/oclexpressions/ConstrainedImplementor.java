package org.opaeum.javageneration.oclexpressions;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.NakedParsedOclStringResolver;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedConstraint;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.runtime.domain.IConstrained;
import org.opaeum.runtime.domain.IInvariantError;

/**
 * This class implements the Constrained interface on classes that have invariants.
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class,NakedParsedOclStringResolver.class},after = {OperationAnnotator.class},before = CodeCleanup.class)
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
		OJClassifier ojClassifier = this.javaModel.findClass(path);
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
			if(c.getSpecification().isValidOclValue()){
				IClassifier b = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
				if(c.getSpecification().getOclValue().getExpression().getExpressionType().conformsTo(b)){
					return true;
				}
			}
		}
		return false;
	}
}

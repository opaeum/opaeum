package org.opaeum.javageneration.oclexpressions;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.eclipse.EmfClassifierUtil;
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
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.runtime.domain.IConstrained;
import org.opaeum.runtime.domain.IInvariantError;

/**
 * This class implements the Constrained interface on classes that have invariants.
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class},after = {OperationAnnotator.class},before = CodeCleanup.class)
public class ConstrainedImplementor extends AbstractJavaProducingVisitor{
	private static final OJPathName CONSTRAINED = new OJPathName(IConstrained.class.getName());
	@VisitBefore()
	public void visitModel(Model p){
		if(JavaTransformationPhase.IS_RUNTIME_AVAILABLE){
			super.javaModel.findClass(new OJPathName(UtilityCreator.getUtilPathName() + ".InvariantError")).addToImplementedInterfaces(
					new OJPathName(IInvariantError.class.getName()));
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClass(Classifier c){
		OJPathName path = OJUtil.classifierPathname(c);
		OJClassifier ojClassifier = this.javaModel.findClass(path);
		if(ojClassifier instanceof OJClass && c instanceof Classifier){
			Classifier nc = c;
			if(hasInvariants(nc)){
				OJClass ojClass = (OJClass) ojClassifier;
				ojClass.addToImplementedInterfaces(CONSTRAINED);
			}
		}
	}
	private boolean hasInvariants(Classifier nc){
		for(Constraint c:nc.getOwnedRules()){
			if(c.getSpecification() instanceof OpaqueExpression){
				AbstractOclContext oclExpression = getLibrary().getOclExpressionContext((OpaqueExpression) c.getSpecification());
				if(!oclExpression.hasErrors() && EmfClassifierUtil.comformsToLibraryType(oclExpression.getExpression().getType(), "Boolean")){
					return true;
				}
			}
		}
		return false;
	}
}

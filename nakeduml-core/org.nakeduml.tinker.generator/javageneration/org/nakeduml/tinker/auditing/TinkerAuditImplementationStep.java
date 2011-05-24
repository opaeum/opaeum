package org.nakeduml.tinker.auditing;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.auditing.TinkerAuditGenerationPhase;
import net.sf.nakeduml.javageneration.auditing.TinkerSoftDeleteImplementationStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.nakeduml.tinker.auditing.tinker.TinkerAuditAttributeImplementor;
import org.nakeduml.tinker.auditing.tinker.TinkerAuditAuditClassTransformation;
import org.nakeduml.tinker.auditing.tinker.TinkerAuditCreator;
import org.nakeduml.tinker.auditing.tinker.TinkerAuditOrignalClassTransformation;
import org.nakeduml.tinker.auditing.tinker.TinkerAuditSuperTypeGenerator;
import org.nakeduml.tinker.basicjava.DerivedUnionImplementor;

@StepDependency(phase = TinkerAuditGenerationPhase.class, requires={TinkerSoftDeleteImplementationStep.class}, after={TinkerSoftDeleteImplementationStep.class})
public class TinkerAuditImplementationStep extends AbstractJavaTransformationStep {

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		TinkerAuditCreator tinkerAuditMaker = new TinkerAuditCreator();
		tinkerAuditMaker.initialize(javaModel, config, textWorkspace, context);
		tinkerAuditMaker.startVisiting(workspace);
		TinkerAuditSuperTypeGenerator superTypeAdder = new TinkerAuditSuperTypeGenerator();
		superTypeAdder.initialize(javaModel, config, textWorkspace, context);
		superTypeAdder.startVisiting(workspace);		
		TinkerAuditAttributeImplementor tinkerAuditAttributeImplementor = new TinkerAuditAttributeImplementor(); 
		tinkerAuditAttributeImplementor.initialize(javaModel, config, textWorkspace, context);
		tinkerAuditAttributeImplementor.startVisiting(workspace);
		DerivedUnionImplementor tinkerAuditDerivedUnionImplementor = new DerivedUnionImplementor(); 
		tinkerAuditDerivedUnionImplementor.initialize(javaModel, config, textWorkspace, context, true);
		tinkerAuditDerivedUnionImplementor.startVisiting(workspace);
		TinkerAuditAuditClassTransformation tinkerTransformation = new TinkerAuditAuditClassTransformation();
		tinkerTransformation.initialize(javaModel, config, textWorkspace, context);
		tinkerTransformation.startVisiting(workspace);
		TinkerAuditOrignalClassTransformation tinkerAuditOrignalClassTransformation = new TinkerAuditOrignalClassTransformation();
		tinkerAuditOrignalClassTransformation.initialize(javaModel, config, textWorkspace, context);
		tinkerAuditOrignalClassTransformation.startVisiting(workspace);
	}

}

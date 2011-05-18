package net.sf.nakeduml.javageneration.auditing;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.basicjava.DerivedUnionImplementor;
import net.sf.nakeduml.javageneration.basicjava.TinkerAuditAttributeImplementor;
import net.sf.nakeduml.javageneration.basicjava.TinkerAuditCreator;
import net.sf.nakeduml.javageneration.basicjava.TinkerAuditOrignalClassTransformation;
import net.sf.nakeduml.javageneration.basicjava.TinkerAuditSuperTypeGenerator;
import net.sf.nakeduml.javageneration.basicjava.TinkerAuditAuditClassTransformation;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

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

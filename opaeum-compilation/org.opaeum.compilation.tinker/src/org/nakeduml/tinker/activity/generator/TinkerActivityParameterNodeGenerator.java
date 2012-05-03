package org.nakeduml.tinker.activity.generator;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedParameterNode;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerObjectNodeGenerator.class } , after = { TinkerObjectNodeGenerator.class })
public class TinkerActivityParameterNodeGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedParameterNode.class })
	public void visitActivityParameterNode(INakedParameterNode oa) {
		OJAnnotatedClass activityParameterNodeClass = findJavaClassForActivityNode(oa); 
		if ((oa.getParameter().getDirection() == ParameterDirectionKind.IN) || (oa.getParameter().getDirection() == ParameterDirectionKind.INOUT) && oa.getIncoming().isEmpty()) {
			if (oa.getNakedMultiplicity().isOne()) {
				activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerOneInActivityParameterNodePathName.getCopy());
			} else {
				activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerManyInActivityParameterNodePathName.getCopy());
			}
		} else if ((oa.getParameter().getDirection() == ParameterDirectionKind.OUT) || (oa.getParameter().getDirection() == ParameterDirectionKind.INOUT)
				&& oa.getOutgoing().isEmpty()) {
			if (oa.getNakedMultiplicity().isOne()) {
				activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerOneOutActivityParameterNodePathName.getCopy());
			} else {
				activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerManyOutActivityParameterNodePathName.getCopy());
			}
		}
		OJPathName superClass = activityParameterNodeClass.getSuperclass();
		superClass.addToGenerics(OJUtil.classifierPathname(oa.getParameter().getNakedBaseType()));
		activityParameterNodeClass.addToImports(OJUtil.classifierPathname(oa.getParameter().getNakedBaseType()));
		activityParameterNodeClass.setSuperclass(superClass);
	}
}

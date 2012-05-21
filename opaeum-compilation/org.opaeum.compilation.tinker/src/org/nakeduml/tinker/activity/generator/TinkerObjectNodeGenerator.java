package org.nakeduml.tinker.activity.generator;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.name.NameConverter;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActivityNodeGenerator.class } , after = { TinkerActivityNodeGenerator.class })
public class TinkerObjectNodeGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitAfter(matchSubclasses = true, match = { INakedObjectNode.class })
	public void visitObjectNode(INakedObjectNode oa) {
		OJAnnotatedClass objectNodeClass = findJavaClassForActivityNode(oa);
		implementUpperBound(objectNodeClass, oa);
	}
	
	private void implementUpperBound(OJAnnotatedClass activityParameterNodeClass, INakedObjectNode oa) {
		OJAnnotatedOperation getUpperBound = new OJAnnotatedOperation("getUpperBound", new OJPathName("int"));
		TinkerGenerationUtil.addOverrideAnnotation(getUpperBound);
		if (oa.getUpperBound() != null) {
			getUpperBound.getBody().addToStatements("return " + oa.getUpperBound().intValue());
		} else {
			getUpperBound.getBody().addToStatements("return " + Integer.MAX_VALUE);
		}
		activityParameterNodeClass.addToOperations(getUpperBound);
	}

	@Override
	protected OJAnnotatedClass findJavaClassForActivityNode(INakedActivityNode node){
//		if (node instanceof INakedPin) {
//			OJPathName path = OJUtil.packagePathname(node.getNameSpace());
//			OJPathName copy = path.getCopy();
//			copy.addToNames(NameConverter.decapitalize(((INakedPin)node).getAction().getName()));
//			copy.addToNames(TinkerBehaviorUtil.activityNodePathName(node).getLast());
//			OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findClass(copy);
//			if(owner == null){
//				owner = (OJAnnotatedClass) this.javaModel.findClass(copy);
//			}
//			return owner;
//		} else {
			OJPathName path = OJUtil.packagePathname(node.getNameSpace());
			OJPathName copy = path.getCopy();
			copy.addToNames(TinkerBehaviorUtil.activityNodePathName(node).getLast());
			OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findClass(copy);
			if(owner == null){
				owner = (OJAnnotatedClass) this.javaModel.findClass(copy);
			}
			return owner;
//		}
	}

}

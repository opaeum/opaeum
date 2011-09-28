package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.List;

import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedReplyAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ReplyActionBuilder extends Jbpm5ActionBuilder<INakedReplyAction>{
	public ReplyActionBuilder(NakedUmlLibrary oclEngine,INakedReplyAction node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		List<INakedInputPin> replyValues = node.getReplyValues();
		if(BehaviorUtil.hasMessageStructure(node.getCause())){
			OJAnnotatedField invocation = new OJAnnotatedField("invocation", OJUtil.classifierPathname(node.getCause().getOperation().getMessageStructure()));
			operation.getBody().addToLocals(invocation);
			NakedStructuralFeatureMap actionMap = OJUtil.buildStructuralFeatureMap(node.getCause(), getLibrary());
			invocation.setInitExp(actionMap.getter() + "()");
			for(INakedInputPin pin:replyValues){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin.getLinkedTypedElement());
				String pinExpression = expressor.expressInputPinOrOutParamOrExpansionNode(operation.getBody(), pin);
				operation.getBody().addToStatements("invocation." + map.setter() + "(" + pinExpression + ")");
			}
			operation.getBody().addToStatements("invocation.completed()");
		}
	}
}

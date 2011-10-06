package org.opeum.javageneration.jbpm5.actions;

import java.util.List;

import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.actions.INakedReplyAction;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class ReplyActionBuilder extends Jbpm5ActionBuilder<INakedReplyAction>{
	public ReplyActionBuilder(OpeumLibrary oclEngine,INakedReplyAction node){
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

package org.opaeum.javageneration.jbpm5.actions;

import java.util.List;

import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedReplyAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ReplyActionBuilder extends Jbpm5ActionBuilder<INakedReplyAction>{
	public ReplyActionBuilder(OpaeumLibrary oclEngine,INakedReplyAction node){
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

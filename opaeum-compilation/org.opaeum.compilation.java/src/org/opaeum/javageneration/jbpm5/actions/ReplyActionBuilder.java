package org.opaeum.javageneration.jbpm5.actions;

import java.util.List;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.ReplyAction;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ReplyActionBuilder extends Jbpm5ActionBuilder<ReplyAction>{
	public ReplyActionBuilder(OpaeumLibrary oclEngine,ReplyAction node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		List<InputPin> replyValues = node.getReplyValues();
		AcceptCallAction cause = EmfActionUtil.getCause(node);
		if(cause != null){
			Operation oper = EmfActionUtil.getOperation(cause);
			if(EmfBehaviorUtil.hasExecutionInstance(oper)){
				OJAnnotatedField invocation = new OJAnnotatedField("invocation", OJUtil.classifierPathname(oper));
				operation.getBody().addToLocals(invocation);
				NakedStructuralFeatureMap actionMap = OJUtil.buildStructuralFeatureMap(cause, getLibrary());
				invocation.setInitExp(actionMap.getter() + "()");
				for(InputPin pin:replyValues){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(getContainingActivity(),
							EmfActionUtil.getLinkedTypedElement(pin));
					String pinExpression = expressor.expressInputPinOrOutParamOrExpansionNode(operation.getBody(), pin);
					operation.getBody().addToStatements("invocation." + map.setter() + "(" + pinExpression + ")");
				}
				operation.getBody().addToStatements("invocation.completed()");
			}
		}
	}
}

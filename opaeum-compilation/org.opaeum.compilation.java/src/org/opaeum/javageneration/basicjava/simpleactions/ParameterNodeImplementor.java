package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ParameterNodeImplementor extends SimpleNodeBuilder<INakedParameterNode> {
	public ParameterNodeImplementor(OpaeumLibrary oclEngine, INakedParameterNode action, AbstractObjectNodeExpressor objectNodeExpressor) {
		super(oclEngine, action, objectNodeExpressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		if (!node.getParameter().getDirection().equals(ParameterDirectionKind.IN) && node.getIncoming().size() > 0) {
			if(node.getName().equals("returnParam")){
				System.out.println();
			}
			// consume input token where necessary
			String call = super.expressor.expressInputPinOrOutParamOrExpansionNode(block, node);
			if (node.getParameter().isResult()) {
				NakedStructuralFeatureMap resultMap;
				if(node.getParameter().getLinkedParameter()==null){
					resultMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getParameter());
				}else{
					resultMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getParameter().getLinkedParameter());
				}
				if (node.getParameter().isException()) {
					// TODO JBPM exception handling
					// oper.getBody().addToStatements("processInstance.getRootToken().end()");
					OJPathName pathName = OJUtil.classifierPathname(node.getNakedBaseType());
					operation.getOwner().addToImports(pathName);
					operation.getOwner().addToImports(Jbpm5Util.getExceptionHolder());
					if (node.getActivity().getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD) {
						block.addToStatements(resultMap.setter() + "(" + call + ")");
					}
					operation.getBody().addToStatements(
							"throw new ExceptionHolder(this,\"" + node.getParameter().getName() + "\"," + call + ")");
				} else {
					if (node.getActivity().getActivityKind() == ActivityKind.SIMPLE_SYNCHRONOUS_METHOD) {
						block.addToStatements("result= " + call);
					} else {
						block.addToStatements(resultMap.setter() + "(" + call + ")");
					}
				}
			}
		}
	}

}

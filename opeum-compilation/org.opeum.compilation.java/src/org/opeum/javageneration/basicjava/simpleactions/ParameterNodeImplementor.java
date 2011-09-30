package org.opeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.jbpm5.Jbpm5Util;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.activities.ActivityKind;
import org.opeum.metamodel.activities.INakedParameterNode;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class ParameterNodeImplementor extends SimpleNodeBuilder<INakedParameterNode> {
	public ParameterNodeImplementor(OpeumLibrary oclEngine, INakedParameterNode action, AbstractObjectNodeExpressor objectNodeExpressor) {
		super(oclEngine, action, objectNodeExpressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		if (!node.getParameter().getDirection().equals(ParameterDirectionKind.IN) && node.getIncoming().size() > 0) {
			NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node);
			// consume input token where necessary
			String call = super.expressor.expressInputPinOrOutParamOrExpansionNode(block, node);
			if (node.getParameter().isResult()) {
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
						block.addToStatements("return " + call);
					} else {
						block.addToStatements(resultMap.setter() + "(" + call + ")");
					}
				}
			}
		}
	}

}

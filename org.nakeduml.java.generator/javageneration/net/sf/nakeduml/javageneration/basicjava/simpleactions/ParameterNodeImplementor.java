package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import nl.klasse.octopus.model.ParameterDirectionKind;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ParameterNodeImplementor extends SimpleNodeBuilder<INakedParameterNode> {
	public ParameterNodeImplementor(IOclEngine oclEngine, INakedParameterNode action, AbstractObjectNodeExpressor objectNodeExpressor) {
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

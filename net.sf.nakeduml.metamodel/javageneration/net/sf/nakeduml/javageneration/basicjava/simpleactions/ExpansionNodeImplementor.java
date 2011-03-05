package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import nl.klasse.octopus.oclengine.IOclEngine;

public class ExpansionNodeImplementor extends SimpleNodeBuilder<INakedExpansionNode> {
	public ExpansionNodeImplementor(IOclEngine oclEngine, INakedExpansionNode action, AbstractObjectNodeExpressor objectNodeExpressor) {
		super(oclEngine, action, objectNodeExpressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		if (node.isOutputElement()) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getActivity(), node);
			// expressor.maybeBuildResultVariable(operation, block, map);
			if (node.getFeedingNode() instanceof INakedObjectNode) {
				if (((INakedObjectNode) node.getFeedingNode()).getNakedMultiplicity().isMany()) {
					block.addToStatements(expressor.getterForStructuredResults(map) + ".addAll("
							+ expressor.expressInputPinOrOutParamOrExpansionNode(block, node) + ")");
				} else {
					block.addToStatements(expressor.getterForStructuredResults(map) + ".add("
							+ expressor.expressInputPinOrOutParamOrExpansionNode(block, node) + ")");
				}
			}
		}
	}
}

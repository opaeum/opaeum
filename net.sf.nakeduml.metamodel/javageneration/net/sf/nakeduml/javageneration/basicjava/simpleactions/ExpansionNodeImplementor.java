package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import nl.klasse.octopus.oclengine.IOclEngine;

public class ExpansionNodeImplementor extends SimpleActionBuilder<INakedExpansionNode> {
	public ExpansionNodeImplementor(IOclEngine oclEngine, INakedExpansionNode action, AbstractObjectNodeExpressor objectNodeExpressor) {
		super(oclEngine, action, objectNodeExpressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		if (node.isOutputElement()) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getActivity(), node);
			expressor.maybeBuildResultVariable(operation, block, map);
			block.addToStatements(expressor.getterForStructuredResults(map)+ ".add("+ expressor.expressInputPinOrOutParamOrExpansionNode(block, node) +")");
		}
	}
}

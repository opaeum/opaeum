package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.jbpm5.EventUtil;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.activities.INakedExpansionNode;
import org.opeum.metamodel.activities.INakedObjectNode;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class ExpansionNodeImplementor extends SimpleNodeBuilder<INakedExpansionNode>{
	public ExpansionNodeImplementor(OpeumLibrary oclEngine,INakedExpansionNode action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(oclEngine, action, objectNodeExpressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(node.isOutputElement()){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getActivity(), node);
			// expressor.maybeBuildResultVariable(operation, block, map);
			if(node.getFeedingNode() instanceof INakedObjectNode){
				if(((INakedObjectNode) node.getFeedingNode()).getNakedMultiplicity().isMany()){
					block.addToStatements(expressor.getterForStructuredResults(map) + ".addAll(" + expressor.expressInputPinOrOutParamOrExpansionNode(block, node) + ")");
				}else{
					block.addToStatements(expressor.getterForStructuredResults(map) + ".add(" + expressor.expressInputPinOrOutParamOrExpansionNode(block, node) + ")");
				}
			}
			EventUtil.cancelEvents(block, this.node.getExpansionRegion().getActivityNodes());
		}else{
			EventUtil.requestEvents(operation, this.node.getExpansionRegion().getActivityNodes(),getLibrary().getBusinessRole()!=null);
		}
	}
}

package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ExpansionNodeImplementor extends SimpleNodeBuilder<INakedExpansionNode>{
	public ExpansionNodeImplementor(NakedUmlLibrary oclEngine,INakedExpansionNode action,AbstractObjectNodeExpressor objectNodeExpressor){
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

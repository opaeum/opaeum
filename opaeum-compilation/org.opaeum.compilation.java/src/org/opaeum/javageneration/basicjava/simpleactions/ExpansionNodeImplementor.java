package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ObjectNode;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.bpm.EventUtil;

public class ExpansionNodeImplementor extends SimpleNodeBuilder<ExpansionNode>{
	protected EventUtil eventUtil;
	public ExpansionNodeImplementor(ExpansionNode action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(action, objectNodeExpressor);
		eventUtil=new EventUtil(objectNodeExpressor.getOjUtil());
	}
	
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(node.getRegionAsOutput()!=null){
			StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(node);
			// expressor.maybeBuildResultVariable(operation, block, map);
			ObjectNode feedingNode = EmfActivityUtil.getFeedingNode( node);
			if(feedingNode !=null){
				String call = expressor.expressInputPinOrOutParamOrExpansionNode(block, node);
				String pref="";
				if(EmfActivityUtil.isSimpleSynchronousMethod(getContainingActivity())){
					pref="getNodeContainer().";
				}
				block.addToStatements(pref+expressor.storeResults(map, call, EmfActivityUtil.isMultivalued( feedingNode)));
			}
			EventUtil.cancelEvents(block, this.node.getRegionAsOutput().getContainedNodes());
		}else if(node.getRegionAsInput()!=null){
			eventUtil.requestEvents(operation, this.node.getRegionAsInput().getContainedNodes(), getLibrary().getBusinessRole() != null);
		}
	}
}

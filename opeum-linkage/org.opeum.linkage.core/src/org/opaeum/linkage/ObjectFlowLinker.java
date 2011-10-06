package org.opeum.linkage;

import java.util.Collection;
import java.util.Iterator;

import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.activities.INakedActivityEdge;
import org.opeum.metamodel.activities.INakedControlNode;
import org.opeum.metamodel.activities.INakedExpansionNode;
import org.opeum.metamodel.activities.INakedObjectFlow;
import org.opeum.metamodel.activities.INakedObjectNode;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.activities.INakedParameterNode;
import org.opeum.metamodel.core.INakedClassifier;

@StepDependency(phase = LinkagePhase.class,after = {
	NakedParsedOclStringResolver.class
},requires = {
	NakedParsedOclStringResolver.class
})
public class ObjectFlowLinker extends AbstractModelElementLinker{
	@VisitBefore
	public void visitExpansionNode(INakedExpansionNode node){
		INakedClassifier baseType = calculateIncomingBaseType(node.getIncoming());
		if(baseType != null){
			node.setBaseType(baseType);
			IOclLibrary lib = getOclLibrary();
			TypeResolver.resolveCollection(node, node.getNakedBaseType(), lib);
		}
	}
	private IOclLibrary getOclLibrary(){
		return workspace.getOclEngine().getOclLibrary();
	}
	private INakedClassifier calculateIncomingBaseType(Collection<INakedActivityEdge> source){
		Iterator<INakedActivityEdge> iter = source.iterator();
		if(iter.hasNext()){
			INakedActivityEdge next = iter.next();
			if(next instanceof INakedObjectFlow){
				INakedObjectFlow flow = (INakedObjectFlow) next;
				if(flow.getTransformation() != null){
					return flow.getTransformation().getReturnParameter().getNakedBaseType();
				}
				if(flow.getSource() instanceof INakedObjectNode){
					if(flow.getSource() instanceof INakedParameterNode || flow.getSource() instanceof INakedOutputPin){
						return ((INakedObjectNode) flow.getSource()).getNakedBaseType();
					}else{
						return calculateIncomingBaseType(flow.getSource().getIncoming());
					}
				}else if(flow.getSource() instanceof INakedControlNode){
					return calculateIncomingBaseType(flow.getSource().getIncoming());
				}
			}
		}
		return null;
	}
}

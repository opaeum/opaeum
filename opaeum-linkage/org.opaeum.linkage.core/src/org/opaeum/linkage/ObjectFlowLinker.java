package org.opaeum.linkage;

import java.util.Collection;
import java.util.Iterator;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedPrimitiveType;

@StepDependency(phase = LinkagePhase.class,after = {
	NakedParsedOclStringResolver.class
},before = {},requires = {
		NakedParsedOclStringResolver.class,PinLinker.class
})
public class ObjectFlowLinker extends AbstractModelElementLinker{
	@VisitBefore(match = {
			INakedExpansionNode.class,INakedInputPin.class
	})
	public void visitExpansionNode(INakedObjectNode node){
		if(node.getNakedBaseType() == TypeResolver.DEFAULT_TYPE){
			INakedClassifier baseType = calculateIncomingBaseType(node.getIncoming());
			if(baseType != null){
				node.setBaseType(baseType);
				IClassifier type=baseType;
				if(baseType instanceof INakedPrimitiveType){
					type=((INakedPrimitiveType) baseType).getOclType();
				}
				if(node.getNakedMultiplicity().isMany()){
					TypeResolver.resolveCollection(node, type, getBuiltInTypes().getOclLibrary());
				}else{
					node.setType(type);
				}
			}
		}
	}
	private INakedClassifier calculateIncomingBaseType(Collection<INakedActivityEdge> source){
		Iterator<INakedActivityEdge> iter = source.iterator();
		while(iter.hasNext()){
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

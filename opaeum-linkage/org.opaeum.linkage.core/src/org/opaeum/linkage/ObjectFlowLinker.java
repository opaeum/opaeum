package org.opaeum.linkage;

import java.util.Collection;
import java.util.Iterator;

import nl.klasse.octopus.model.IClassifier;

import org.eclipse.uml2.uml.INakedActivityEdge;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedControlNode;
import org.eclipse.uml2.uml.INakedExpansionNode;
import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedObjectFlow;
import org.eclipse.uml2.uml.INakedObjectNode;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedParameterNode;
import org.eclipse.uml2.uml.INakedPrimitiveType;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.TypeResolver.DefaultPrimitiveType;

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
		if(node.getNakedBaseType() instanceof DefaultPrimitiveType){
			INakedClassifier baseType = calculateIncomingBaseType(node.getIncoming());
			if(baseType != null){
				node.setBaseType(baseType);
				IClassifier type=baseType;
				if(baseType instanceof INakedPrimitiveType){
					type=((INakedPrimitiveType) baseType).getOclType();
				}
				if(node.getNakedMultiplicity().isMany()){
					TypeResolver.resolveCollection(node, type, getLibrary().getOclLibrary());
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

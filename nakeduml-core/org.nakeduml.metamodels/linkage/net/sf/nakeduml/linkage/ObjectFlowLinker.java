package net.sf.nakeduml.linkage;

import java.util.Collection;
import java.util.Iterator;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

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

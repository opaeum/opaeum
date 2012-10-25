package org.opaeum.modeler.compositestructurediagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorKind;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.compositestructuresdiagram.policies.ConnectorEdgeCreationEditPolicy;
import org.topcased.modeler.utils.Utils;

public class AssemblyEdgeCreationPolicy extends ConnectorEdgeCreationEditPolicy{
	@Override
	protected boolean checkSource(GraphElement source){
		return Utils.getElement(source) instanceof Property && ((Property) Utils.getElement(source)).getType() instanceof BehavioredClassifier;
	}
	@Override
	protected boolean checkTargetForSource(GraphElement source,GraphElement target){
		Property sourceProp = (Property) Utils.getElement(source);
		Property targetProp = (Property) Utils.getElement(target);
		if(sourceProp instanceof Port && !(targetProp instanceof Port)){
			return false;
		}
		if(targetProp instanceof Port && !(sourceProp instanceof Port)){
			return false;
		}
		if(!(targetProp.getType() instanceof BehavioredClassifier)){
			return false;
		}
		BehavioredClassifier sourceClassifier = (BehavioredClassifier) sourceProp.getType();
		BehavioredClassifier targetClassifier = (BehavioredClassifier) targetProp.getType();
		EList<Interface> sourceRequiredInterfacecs = sourceClassifier.getAllUsedInterfaces();
		EList<Interface> targetImplementedInterfaces = targetClassifier.getAllImplementedInterfaces();
		for(Interface interface1:sourceRequiredInterfacecs){
			if(!targetImplementedInterfaces.contains(interface1)){
				return false;
			}
		}
		return super.checkTargetForSource(source, target);
	}
	@Override
	protected boolean checkEdge(GraphEdge edge){
		EObject element = Utils.getElement(edge);
		return element instanceof Connector && ((Connector) element).getKind() == ConnectorKind.ASSEMBLY_LITERAL;
	}
}
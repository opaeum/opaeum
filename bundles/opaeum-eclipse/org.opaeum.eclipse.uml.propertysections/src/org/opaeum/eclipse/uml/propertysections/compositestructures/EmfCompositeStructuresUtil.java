package org.opaeum.eclipse.uml.propertysections.compositestructures;

import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.ConnectorKind;
import org.eclipse.uml2.uml.Port;
import org.opaeum.eclipse.EmfActivityUtil.TypeAndMultiplicity;

public class EmfCompositeStructuresUtil{
	public static TypeAndMultiplicity findSourceType(Connector objectFlow){
		if(objectFlow.getKind()==ConnectorKind.DELEGATION_LITERAL){
			for(ConnectorEnd connectorEnd:objectFlow.getEnds()){
				if((connectorEnd.getRole() instanceof Port)){
					return new TypeAndMultiplicity(connectorEnd.getRole(), connectorEnd);
				}
			}
			
		}
		return null;
	}
	public static TypeAndMultiplicity findTargetType(Connector objectFlow){
		if(objectFlow.getKind()==ConnectorKind.DELEGATION_LITERAL){
			for(ConnectorEnd connectorEnd:objectFlow.getEnds()){
				if(!(connectorEnd.getRole() instanceof Port)){
					return new TypeAndMultiplicity(connectorEnd.getRole(), connectorEnd);
				}
			}
			
		}
		return null;
	}
}

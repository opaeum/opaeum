package net.sf.nakeduml.metamodel.components.internal;

import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityElement;

public class NakedConnectorEndImpl extends NakedMultiplicityElement implements INakedConnectorEnd{
	private INakedProperty role;
	private INakedProperty partWitPort;
	@Override
	public INakedProperty getPartWithPort(){
		return this.partWitPort;
	}
	public INakedProperty getPartWitPort(){
		return partWitPort;
	}
	public void setPartWitPort(INakedProperty partWitPort){
		this.partWitPort = partWitPort;
	}
	@Override
	public INakedProperty getRole(){
		return this.role;
	}
	@Override
	public String getMetaClass(){
		return "connectorEnd";
	}
	public void setRole(INakedProperty role){
		this.role = role;
	}
}

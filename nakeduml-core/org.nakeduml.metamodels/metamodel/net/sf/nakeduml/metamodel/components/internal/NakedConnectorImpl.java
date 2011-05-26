package net.sf.nakeduml.metamodel.components.internal;

import net.sf.nakeduml.metamodel.components.INakedConnector;
import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import net.sf.nakeduml.metamodel.components.NakedConnectorKind;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;

public class NakedConnectorImpl extends NakedElementImpl implements INakedConnector{
	private INakedConnectorEnd end2;
	private INakedConnectorEnd end1;
	private NakedConnectorKind kind;
	@Override
	public INakedConnectorEnd getEnd1(){
		return this.end1;
	}
	@Override
	public INakedConnectorEnd getEnd2(){
		return this.end2;
	}
	@Override
	public NakedConnectorKind getKind(){
		return this.kind;
	}
	@Override
	public String getMetaClass(){
		return "connector";
	}
	public void setEnd2(INakedConnectorEnd end2){
		this.end2 = end2;
	}
	public void setEnd1(INakedConnectorEnd end1){
		this.end1 = end1;
	}
	public void setKind(NakedConnectorKind kind){
		this.kind = kind;
	}
}

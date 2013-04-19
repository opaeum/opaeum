package org.opaeum.metamodel.components.internal;

import org.eclipse.uml2.uml.INakedConnector;
import org.eclipse.uml2.uml.INakedConnectorEnd;
import org.eclipse.uml2.uml.NakedConnectorKind;
import org.opaeum.metamodel.core.internal.NakedElementImpl;

public class NakedConnectorImpl extends NakedElementImpl implements INakedConnector{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3752036447101227421L;
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

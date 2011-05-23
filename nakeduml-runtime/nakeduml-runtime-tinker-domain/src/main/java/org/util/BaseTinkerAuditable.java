package org.util;

import java.io.Serializable;
import java.util.Date;

import org.nakeduml.runtime.domain.TinkerAuditableNode;

public abstract class BaseTinkerAuditable extends BaseTinkerSoftDelete implements TinkerAuditableNode, Serializable{

	private static final long serialVersionUID = 3751023772087546585L;
	
	public BaseTinkerAuditable() {
		super();
	}
	
	public void setDeletedOn(Date deletedOn) {
		super.setDeletedOn(deletedOn);
		if ( TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid()) ) {
			createAuditVertex(false);
		}
		getAuditVertex().setProperty("deletedOn", TinkerFormatter.format(deletedOn));

	}

}

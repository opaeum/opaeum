package org.opaeum.runtime.domain;

import org.opeum.runtime.domain.IPersistentObject;


public interface IBusinessServiceInvocation extends IPersistentObject{
	boolean started();
	boolean completed();
}

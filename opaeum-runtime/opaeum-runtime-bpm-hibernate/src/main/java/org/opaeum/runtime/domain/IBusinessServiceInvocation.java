package org.opaeum.runtime.domain;

import org.opaeum.runtime.domain.IPersistentObject;


public interface IBusinessServiceInvocation extends IPersistentObject{
	boolean started();
	boolean completed();
}

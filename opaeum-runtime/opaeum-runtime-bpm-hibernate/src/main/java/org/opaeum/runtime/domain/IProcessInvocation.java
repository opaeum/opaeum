package org.opaeum.runtime.domain;

import org.opeum.runtime.domain.IProcessObject;


public interface IProcessInvocation extends IBusinessServiceInvocation{
	IProcessObject getProcessObject();
}

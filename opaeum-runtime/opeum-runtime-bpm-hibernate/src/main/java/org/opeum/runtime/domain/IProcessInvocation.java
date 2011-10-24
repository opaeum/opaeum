package org.opaeum.runtime.domain;

import org.opaeum.runtime.domain.IProcessObject;


public interface IProcessInvocation extends IBusinessServiceInvocation{
	IProcessObject getProcessObject();
}

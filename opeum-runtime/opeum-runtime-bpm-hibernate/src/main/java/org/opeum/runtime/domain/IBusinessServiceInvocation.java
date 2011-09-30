package org.opeum.runtime.domain;


public interface IBusinessServiceInvocation extends IPersistentObject{
	boolean started();
	boolean completed();
}

package org.opaeum.runtime.domain;



public interface IBusinessServiceInvocation extends IPersistentObject{
	boolean started();
	boolean completed();
}

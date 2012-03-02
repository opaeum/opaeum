package org.opaeum.demo.demo1;

import java.util.Collection;

import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;

import structuredbusiness.Structuredbusiness;

public class OpaeumApplication implements IOpaeumApplication{
	public OpaeumApplication(){
	}
	@Override
	public Environment getEnvironment(){
		Demo1JpaEnvironment i = Demo1JpaEnvironment.getInstance();
		return i;
	}
	@Override
	public IBusinessNetwork getBusinessNetwork(){
		Collection<BusinessNetwork> readAll = getEnvironment().getPersistence().readAll(BusinessNetwork.class);
		if(readAll.isEmpty()){
			BusinessNetwork bn = new BusinessNetwork();
			getEnvironment().getPersistence().persist(bn);
			getEnvironment().getPersistence().flush();
			return bn;
		}else{
			return readAll.iterator().next();
		}
	}
	@Override
	public IBusinessCollaborationBase createRootBusinessCollaboration(){
		return new Structuredbusiness();
	}
}

package org.opaeum.demo1.util;

import java.util.List;

import org.opaeum.demo1.structuredbusiness.ApplianceCollaboration;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.rwt.AbstractOpaeumApplication;
import org.osgi.framework.Bundle;

public class Demo1Application extends AbstractOpaeumApplication {
	static public Demo1Application INSTANCE;

	/** Constructor for Demo1Application
	 * 
	 * @param bundle 
	 */
	public Demo1Application(Bundle bundle) {
	super(bundle);
	}

	public Class<Demo1EntryPoint> getEntryPointType() {
		Class result = Demo1EntryPoint.class;
		
		return result;
	}
	
	public Demo1Environment getEnvironment() {
		Demo1Environment result = Demo1Environment.INSTANCE;
		
		return result;
	}
	
	public IBusinessCollaborationBase getRootBusinessCollaboration() {
		IBusinessCollaborationBase result = null;
		List<ApplianceCollaboration> found = this.applicationPersistence.readAll(ApplianceCollaboration.class);
		if ( found.size()>0 ) {
			result=found.get(0);
		}
		return result;
	}
	
	public IBusinessCollaborationBase newBusinessCollaboration(IBusinessNetwork bn) {
		IBusinessCollaborationBase result = new ApplianceCollaboration((BusinessNetwork)bn);
		
		return result;
	}
	
	public IBusinessNetwork newBusinessNetwork() {
		IBusinessNetwork result = new BusinessNetwork();
		
		return result;
	}
	
	public IPersonNode newPersonNode(IBusinessNetwork bn, String email) {
		IPersonNode result = new PersonNode((BusinessNetwork)bn,email);
		
		return result;
	}

}
package org.opeum.demo1.util;

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

	public IBusinessCollaborationBase createRootBusinessCollaboration() {
		IBusinessCollaborationBase result = null;
		
		return result;
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
		
		return result;
	}
	
	public IBusinessCollaborationBase newBusinessCollaboration(IBusinessNetwork bn) {
		IBusinessCollaborationBase result = null;
		
		return result;
	}
	
	public IBusinessNetwork newBusinessNetwork() {
		IBusinessNetwork result = null;
		
		return result;
	}
	
	public IPersonNode newPersonNode(IBusinessNetwork bn, String email) {
		IPersonNode result = null;
		
		return result;
	}

}
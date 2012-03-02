package org.opaeum.rap.runtime;

import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;

public interface IOpaeumApplication{
	Environment getEnvironment();
	IBusinessNetwork getBusinessNetwork();
	IBusinessCollaborationBase createRootBusinessCollaboration();
	//TODO
	//UimWorkspace getUimWorkspace();
}

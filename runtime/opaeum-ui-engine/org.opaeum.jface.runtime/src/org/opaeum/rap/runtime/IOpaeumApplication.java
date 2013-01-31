package org.opaeum.rap.runtime;

import java.net.URL;

import javax.validation.Validator;

import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IPersonNode;

public interface IOpaeumApplication{
	Environment getEnvironment();
	IBusinessCollaborationBase getRootBusinessCollaboration();
	IBusinessCollaborationBase createRootBusinessCollaboration();
	Resource getUimResource(String id);
	String getIdentifier();
	IPersonNode findOrCreatePersonByEMailAddress(String id);
	Validator getValidator();
	URL getCubeUrl();
}

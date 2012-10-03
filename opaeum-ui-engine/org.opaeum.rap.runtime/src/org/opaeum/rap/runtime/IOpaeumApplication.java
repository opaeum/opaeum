package org.opaeum.rap.runtime;

import java.net.URL;

import javax.validation.Validator;

import org.eclipse.emf.ecore.resource.Resource;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.emf.workspace.EmfWorkspace;

public interface IOpaeumApplication{
	Environment getEnvironment();
	IBusinessCollaborationBase getRootBusinessCollaboration();
	IBusinessCollaborationBase createRootBusinessCollaboration();
	EmfWorkspace getEmfWorkspace();
	Resource getUimResource(String id);
	String getIdentifier();
	IPersonNode findOrCreatePersonByEMailAddress(String id);
	Validator getValidator();
	URL getCubeUrl();
}

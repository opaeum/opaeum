package org.opaeum.runtime.rwt;

import java.net.URL;

import javax.validation.Validator;

import org.eclipse.rap.rwt.application.EntryPoint;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.uim.model.AbstractUserInteractionModel;

public interface IOpaeumApplication {
	Environment getEnvironment();
	IBusinessCollaborationBase getRootBusinessCollaboration();
	IBusinessCollaborationBase createRootBusinessCollaboration();
	AbstractUserInteractionModel getUserInteractionModel(String id);
	String getIdentifier();
	IPersonNode findOrCreatePersonByEMailAddress(String id);
	Validator getValidator();
	URL getCubeUrl();
	Class<? extends EntryPoint> getEntryPointType();
}

package org.nakeduml.jsf;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.WindowContext;
import org.nakeduml.async.DispatchSignal;
import org.nakeduml.environment.TransactionConversationGroup;
import org.nakeduml.runtime.domain.AbstractEntity;

@Named("contextController")
@ConversationScoped
public class ContextController implements Serializable {

	@Inject
	DispatchSignal dispatchSignal;
	AbstractEntity contextObject;
	AbstractEntity selectedContextObject;
	@Inject
	WindowContext windowContext;
	@Inject
	Logger logger;
	
	public AbstractEntity getSelectedContextObject() {
		return selectedContextObject;
	}

	public void setSelectedContextObject(AbstractEntity selectedContextObject) {
		this.selectedContextObject = selectedContextObject;
	}

	public AbstractEntity getContextObject() {
		return contextObject;
	}

	public void setContextObject(AbstractEntity contextObject) {
		this.contextObject = contextObject;
	} 
	
	public void update() {
		logger.info("update");
		windowContext.closeConversationGroup(TransactionConversationGroup.class);
		windowContext.closeConversationGroup(TreeController.class);
		throw new RuntimeException();
	}
	
}

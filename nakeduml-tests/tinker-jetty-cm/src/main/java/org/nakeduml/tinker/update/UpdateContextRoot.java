package org.nakeduml.tinker.update;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.apache.myfaces.extensions.cdi.jsf.api.Jsf;
import org.apache.myfaces.extensions.cdi.message.api.MessageContext;
import org.apache.myfaces.extensions.cdi.message.impl.DefaultMessage;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.NakedGraph;

@ConversationScoped
@Named("updateContextRoot")
public class UpdateContextRoot implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Logger logger;
	
	@ApplicationScopedDb
	@Inject
	NakedGraph db;
	@Inject
	@Jsf
	private MessageContext messageContext;

	public void update() {
		logger.info("updated context root");
		messageContext.addMessage(new DefaultMessage("Updated context root succesfully"));
	}

}

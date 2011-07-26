package org.nakeduml.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.nakeduml.runtime.domain.AbstractEntity;

@Named("contextController")
@ConversationScoped
public class ContextController implements Serializable {

	AbstractEntity contextObject;
	AbstractEntity selectedContextObject;

	@PostConstruct
	public void init() {
	}

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
		System.out.println("");
	}
	
}

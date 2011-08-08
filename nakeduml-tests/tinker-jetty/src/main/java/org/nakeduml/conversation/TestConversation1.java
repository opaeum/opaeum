package org.nakeduml.conversation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.event.StartConversationEvent;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.NakedGraph;

@Named("testConversation1")
@ConversationScoped
public class TestConversation1 implements Serializable {
	
	private String name;
	@ApplicationScopedDb
	@Inject
	NakedGraph db;
	
//	public void onConversationStart(@Observes StartConversationEvent event) {
//		System.out.println("");
//	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getNames() {
		return new ArrayList<String>(Arrays.asList("String1", "String2", "String3"));
	}
	
	public AbstractEntity getRoot() {
		return db.getCompositeRoots().get(0);
	}
	
}

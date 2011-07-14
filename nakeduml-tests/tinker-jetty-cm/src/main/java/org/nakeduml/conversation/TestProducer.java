package org.nakeduml.conversation;

import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.nakeduml.environment.ContextRoot;
import org.nakeduml.environment.ContextRootRequestScoped;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.NakedGraph;

import com.rorotika.cm.core.CmApplication;

public class TestProducer {
	
	@ApplicationScopedDb
	@Inject
	NakedGraph db;
	
	@Produces
    @ConversationScoped
	public ArrayList<String> getConversationProducedStrings() {
		return new ArrayList<String>(Arrays.asList("String1", "String2", "String3"));
	}

	@Named("contextRoot")
	@Produces
    @ConversationScoped
    @ContextRoot
	public CmApplication getContextRoot() {
		return (CmApplication) db.getCompositeRoots().get(0);
	}
	
	@Named("contextRootRequestScoped")
	@Produces
    @RequestScoped
    @ContextRootRequestScoped
	public CmApplication getContextRootRequestScoped() {
		return (CmApplication) db.getCompositeRoots().get(0);
	}	
}

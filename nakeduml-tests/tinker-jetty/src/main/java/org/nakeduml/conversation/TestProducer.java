package org.nakeduml.conversation;

import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.nakeduml.environment.ContextRoot;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.NakedGraph;
import org.tinker.God;

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
	public God getContextRoot() {
//		Set<Universe> universes = ((God) db.getCompositeRoots().get(0)).getUniverse();
//		for (Universe universe : universes) {
//			if (universe.getName().equals("universe1")) {
//				return universe;
//			}
//		}
//		return null;
		return (God) db.getCompositeRoots().get(0);
	}
}

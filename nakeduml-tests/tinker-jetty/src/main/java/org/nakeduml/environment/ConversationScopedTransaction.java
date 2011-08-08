package org.nakeduml.environment;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.transaction.Transaction;

import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationGroup;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.NakedGraph;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;

@ConversationScoped
@ConversationGroup(TransactionConversationGroup.class)
public class ConversationScopedTransaction implements Serializable {

	NakedGraph db; // handle serialization (if needed/supported)
	private Transaction transaction; // handle serialization (if needed/supported)
	@Inject
	Logger logger;
	
    private final ThreadLocal<Boolean> conversationStartedInThisThread = new ThreadLocal<Boolean>() {
        protected Boolean initialValue() {
            return false;
        }
    };

	// required by proxy libs
	protected ConversationScopedTransaction() {
	}

	@Inject
	public ConversationScopedTransaction(@ApplicationScopedDb NakedGraph db) {
		this.db = db;
	}

	@PostConstruct
	protected void createTransaction() {
		logger.info("createTransaction");
		db.setTransactionMode(Mode.MANUAL);
		db.startTransaction();
		this.transaction = db.getTransaction();
		conversationStartedInThisThread.set(true);
	}

	@PreDestroy
	protected void closeTransaction() {
		logger.info("closeTransaction");
		db.stopTransaction(Conclusion.SUCCESS);
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public boolean isConversationStartedInThisThread() {
		return this.conversationStartedInThisThread.get();
	}
	
}

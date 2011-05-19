package org.nakeduml.environment.adaptor;


import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.seam.transaction.TransactionScoped;

@TransactionScoped
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class JbpmKnowledgeSession extends AbstractJbpmKnowledgeSession {
	public void clear(){
		//For mocking purposes only
		this.knowledgeSession=null;
	}
}

package org.nakeduml.hibernate.domain;

import org.hibernate.Session;
import org.nakeduml.environment.CmtPersistence;

public class HibernateCmtPersistence extends AbstractHibernatePersistence implements CmtPersistence{

	public HibernateCmtPersistence(Session session){
		super(session);
	}
}

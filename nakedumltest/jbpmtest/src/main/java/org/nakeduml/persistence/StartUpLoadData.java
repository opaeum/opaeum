package org.nakeduml.persistence;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import jbpm.jbpm.TheBoss;
import jbpm.jbpm.TheBossDataGenerator;
import net.sf.nakeduml.util.DataGeneratorProperty;

import org.hibernate.Session;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.seam.servlet.WebApplication;
import org.jboss.seam.servlet.event.Started;

public class StartUpLoadData {

	@Inject
	Session session;
	@Inject
	@DefaultTransaction
	SeamTransaction transaction;
	@Inject
	private DataGeneratorProperty dataGeneratorProperty;
	@Inject
	private TheBossDataGenerator theBossDataGenerator;
	 
	public void onMessage(@Observes @Started WebApplication webapp) {
		try {
			transaction.begin();
			TheBoss thegod = (TheBoss) session.createQuery("from TheBoss a where a.name = :name")
					.setText("name", dataGeneratorProperty.getProperty("theBoss.name_0")).uniqueResult();
			if (thegod == null) {
				List<TheBoss> bosses = theBossDataGenerator.createTheBoss();
				for (TheBoss theBoss : bosses) {
					session.persist(theBoss);
				}
				theBossDataGenerator.populateTheBoss(bosses);
				session.flush();
				transaction.commit();
			}
		} catch (Exception e) {
			try {
				transaction.rollback();
			} catch (Exception e1) {
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		}
	}
	
}

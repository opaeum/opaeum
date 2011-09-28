package processmodel.integrationtest;

import javax.persistence.EntityManager;

import org.jboss.seam.Component;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.mock.SeamTest;
import org.jboss.seam.transaction.UserTransaction;
import org.testng.annotations.Test;

import processmodel.statemachines.SimpleStateMachine;

public class StateMachinePersistenceTest extends SeamTest {
	 @Test
	public void testController() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				UserTransaction ut=(UserTransaction) Component.getInstance("org.jboss.seam.transaction.transaction");
				ut.begin();
				EntityManager em = (EntityManager) Component.getInstance("entityManager");
				SimpleStateMachine sm = new SimpleStateMachine();
				em.persist(sm);
				sm.execute();
				System.out.println(sm.getInnermostNonParallelStep());
				em.flush();
				ut.commit();
				em.close();
				Contexts.removeFromAllContexts("entityManager");
				Contexts.removeFromAllContexts("jbpmKnowledgeSession");
				Contexts.removeFromAllContexts("jbpmKnowledgeBase");
				ut.begin();
				em = (EntityManager) Component.getInstance("entityManager");
				sm=em.find(SimpleStateMachine.class, sm.getId());
				ut=(UserTransaction) Component.getInstance("org.jboss.seam.transaction.transaction");
				System.out.println(sm.getInnermostNonParallelStep());
				assert sm.getProcessInstance().getVariable("processObject")==sm;
				ut.commit();
			}
		}.run();
	}
}

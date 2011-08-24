package org.tinker.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.InvalidTransactionException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.neo4j.graphdb.NotInTransactionException;
import org.tinker.God;
import org.tinker.SpaceTime;
import org.tinker.Universe;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestTransactionSuspendResume extends BaseLocalDbTest {

	@Test
	public void testTransactionSuspendResume() throws SystemException, InvalidTransactionException, IllegalStateException {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Transaction t = db.suspend();
		db.resume(t);
		SpaceTime spaceTime = new SpaceTime(universe1);
		spaceTime.setName("spaceTime1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(14, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime1", universe1.getSpaceTime().getName());
		assertNotNull(spaceTime.getUniverse());
		assertEquals("THEGOD", spaceTime.getUniverse().getGod().getName());
		assertNotNull(spaceTime.getSpace());
		assertNotNull(spaceTime.getTime());
		assertNotNull(spaceTime.getSpace().getSpaceTime());
		assertNotNull(spaceTime.getTime().getSpaceTime());
		assertEquals("THEGOD", spaceTime.getSpace().getSpaceTime().getUniverse().getGod().getName());
		assertEquals("THEGOD", spaceTime.getTime().getSpaceTime().getUniverse().getGod().getName());		
	}
	
	@Test(expected=NotInTransactionException.class)
	public void testTransactionSuspendFailure() throws SystemException, InvalidTransactionException, IllegalStateException {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Transaction t = db.suspend();
		SpaceTime spaceTime = new SpaceTime(universe1);
		spaceTime.setName("spaceTime1");
		db.stopTransaction(Conclusion.SUCCESS);
	}	
	
	
}
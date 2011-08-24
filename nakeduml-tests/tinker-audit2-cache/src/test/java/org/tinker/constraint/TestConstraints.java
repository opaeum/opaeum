package org.tinker.constraint;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.neo4j.graphdb.TransactionFailureException;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestConstraints extends BaseLocalDbTest {

	@Test(expected=TransactionFailureException.class)
	public void testNotNullNull() {
		db.startTransaction();
		God g = new God(true);
		g.setName("g");
		new TestConstraint(g);
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testNotNullNotNull() {
		db.startTransaction();
		God g = new God(true);
		g.setName("g");
		TestConstraint testConstraint = new TestConstraint(g);
		testConstraint.setName("n");
		db.stopTransaction(Conclusion.SUCCESS);
	}	
	
	@Test(expected=TransactionFailureException.class)
	public void testToYoung() {
		db.startTransaction();
		God g = new God(true);
		g.setName("g");
		TestConstraint testConstraint = new TestConstraint(g);
		testConstraint.setName("n");
		testConstraint.setAge(1);
		db.stopTransaction(Conclusion.SUCCESS);
	}	
	
	@Test(expected=TransactionFailureException.class)
	public void testToOld() {
		db.startTransaction();
		God g = new God(true);
		g.setName("g");
		TestConstraint testConstraint = new TestConstraint(g);
		testConstraint.setName("n");
		testConstraint.setAge(110);
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testRightAge() {
		db.startTransaction();
		God g = new God(true);
		g.setName("g");
		TestConstraint testConstraint = new TestConstraint(g);
		testConstraint.setName("n");
		testConstraint.setAge(50);
		db.stopTransaction(Conclusion.SUCCESS);
	}		
	
}
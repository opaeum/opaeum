package org.tinker;

import org.opeum.test.tinker.BaseLocalDbTest;

public class TestTransaction extends BaseLocalDbTest {

//	@Test
//	public void testTransactionPass() {
//		db.setTransactionMode(Mode.MANUAL);
//		db.startTransaction();
//		God god = new God();
//		ORecordId id = (ORecordId) god.getVertex().getId();
//		db.stopTransaction(Conclusion.SUCCESS);
//		db.startTransaction();
//		Vertex v = db.getVertex(id);
//		assertNotNull(v);
//		God retrievedGod = new God(v);
//		assertNotNull(retrievedGod);
//		db.stopTransaction(Conclusion.SUCCESS);
//		assertEquals(1, countVertices());
//	}
//	
//	@Test
//	public void testTransactionFail() {
//		db.setTransactionMode(Mode.MANUAL);
//		db.startTransaction();
//		God god = new God();
//		ORecordId id = (ORecordId) god.getVertex().getId();
//		db.stopTransaction(Conclusion.FAILURE);
//		db.startTransaction();
//		Vertex v = db.getVertex(id);
//		assertNull(v);
//		db.stopTransaction(Conclusion.SUCCESS);
//		assertEquals(0, countVertices());
//	}
	
}

package org.activitytest.signaltest;

import junit.framework.Assert;

import org.junit.Test;
import org.opaeum.signaltest.Application;
import org.opaeum.signaltest.WaterDispensor;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class ObjectNodeDispenseIntegerTest extends BaseLocalDbTest  {

	@Test
	public void test() {
		db.startTransaction();
		Application application = new Application(true);
		application.setName("app1");
		application.getWaterDispensor().setName("waterDispenser1");
		application.getMoistureController().setName("moistureController1");
		db.stopTransaction(Conclusion.SUCCESS);

		Assert.assertEquals(3, countVertices());
		Assert.assertEquals(3, countEdges());
		
		db.startTransaction();
		WaterDispensor waterDispensor = application.getWaterDispensor();
		waterDispensor.dispense(1);
		db.stopTransaction(Conclusion.SUCCESS);

		Assert.assertEquals(9, countVertices());
		Assert.assertEquals(10, countEdges());
	}
	
}

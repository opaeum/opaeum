package org.tinker;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestDtoController extends BaseLocalDbTest{

	@Test
	public void testOneToMany() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Universe universe3 = new Universe(god);
		universe3.setName("universe3");
		db.stopTransaction(Conclusion.SUCCESS);
		
		UniverseController universeController = new UniverseController();
		universeController.setDb(db);
		List<UniverseDto> universeDto = universeController.getUniverseForGod(god.getId());
		Assert.assertEquals(3, universeDto.size());
	}
	
	@Test
	public void testOneToOne() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime1 = new SpaceTime(universe1);
		spaceTime1.setName("spaceTime1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		SpaceTime spaceTime2 = new SpaceTime(universe2);
		spaceTime2.setName("spaceTime2");
		Universe universe3 = new Universe(god);
		universe3.setName("universe3");
		SpaceTime spaceTime3 = new SpaceTime(universe3);
		spaceTime3.setName("spaceTime3");
		db.stopTransaction(Conclusion.SUCCESS);
		
		UniverseController universeController = new UniverseController();
		universeController.setDb(db);
		UniverseDto universeDto = universeController.getUniverseForSpaceTime(spaceTime1.getId());
		Assert.assertEquals("universe1", universeDto.getName());
		SpaceTimeController spaceTimeController = new SpaceTimeController();
		spaceTimeController.setDb(db);
		SpaceTimeDto spaceTime1Dto = spaceTimeController.getSpaceTimeForUniverse(universe1.getId());
		Assert.assertEquals("spaceTime1", spaceTime1Dto.getName());
		
	}	
}

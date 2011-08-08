package org.nakeduml;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.nakeduml.jetty.StartJetty;
import org.nakeduml.jetty.StopJetty;

public class TestPassByValueOneToManyWs {

	@BeforeClass
	public static void beforeClass() {
		try {
			StartJetty.main(new String[]{});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@AfterClass
	public static void afterClass() {
		try {
			StopJetty.main(new String[]{"false"});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//	@Test
//	public void testFindGod() {
//		JaxWsProxyFactoryBean godFactory = new JaxWsProxyFactoryBean();
//        godFactory.setAddress("http://localhost:8080/tinker-jetty/soap/GodControllerWs");
//        GodWs client = godFactory.create(GodWs.class);
//        GodDto g = client.find(2L);
//        Assert.assertNotNull(g);
//	}
//	
//	@Test
//	public void testOneToMany() {
//		JaxWsProxyFactoryBean godFactory = new JaxWsProxyFactoryBean();
//        godFactory.setAddress("http://localhost:8080/tinker-jetty/soap/GodControllerWs");
//        GodWs client = godFactory.create(GodWs.class);
//        GodDto g = client.find(2L);
//        Assert.assertNotNull(g);
//		JaxWsProxyFactoryBean universeFactory = new JaxWsProxyFactoryBean();
//		universeFactory.setAddress("http://localhost:8080/tinker-jetty/soap/UniverseControllerWs");
//        UniverseWs universeWs = universeFactory.create(UniverseWs.class);
//        List<UniverseDto> universes = universeWs.getUniverseForGod(g.getId());
//        Assert.assertEquals(50, universes.size());
//	}
//	
//	@Test
//	public void testOneToOne() {
//		JaxWsProxyFactoryBean godFactory = new JaxWsProxyFactoryBean();
//        godFactory.setAddress("http://localhost:8080/tinker-jetty/soap/GodControllerWs");
//        GodWs client = godFactory.create(GodWs.class);
//        GodDto g = client.find(2L);
//        Assert.assertNotNull(g);
//		JaxWsProxyFactoryBean universeFactory = new JaxWsProxyFactoryBean();
//		universeFactory.setAddress("http://localhost:8080/tinker-jetty/soap/UniverseControllerWs");
//        UniverseWs universeWs = universeFactory.create(UniverseWs.class);
//        List<UniverseDto> universes = universeWs.getUniverseForGod(g.getId());
//        Assert.assertEquals(50, universes.size());
//		JaxWsProxyFactoryBean spaceTimeFactory = new JaxWsProxyFactoryBean();
//		spaceTimeFactory.setAddress("http://localhost:8080/tinker-jetty/soap/SpaceTimeControllerWs");
//        SpaceTimeWs spaceTimeWs = spaceTimeFactory.create(SpaceTimeWs.class);
//        SpaceTimeDto spaceTimeDto = spaceTimeWs.getSpaceTimeForUniverse(universes.get(0).getId());
//        Assert.assertNotNull(spaceTimeDto);
//	}	
//	
//	@Test
//	public void testPolymorphicOneToMany() {
//		JaxWsProxyFactoryBean godFactory = new JaxWsProxyFactoryBean();
//        godFactory.setAddress("http://localhost:8080/tinker-jetty/soap/GodControllerWs");
//        GodWs client = godFactory.create(GodWs.class);
//        GodDto g = client.find(2L);
//        Assert.assertNotNull(g);
//		
//		JaxWsProxyFactoryBean beingFactory = new JaxWsProxyFactoryBean();
//		beingFactory.setAddress("http://localhost:8080/tinker-jetty/soap/BeingControllerWs");
//        BeingWs beingWs = beingFactory.create(BeingWs.class);
//        List<BeingDto> beings = beingWs.getBeingForGod(g.getId());
//        Assert.assertEquals(3, beings.size());
//        boolean foundCreature = false;
//        boolean foundHuman = false;
//        boolean foundAlien = false;
//        for (BeingDto beingDto : beings) {
//			if (beingDto instanceof HumanDto) {
//				foundHuman = true;
//			}
//			if (beingDto instanceof AlienDto) {
//				foundAlien = true;
//			}
//			if (!(beingDto instanceof AlienDto) && !(beingDto instanceof HumanDto) && (beingDto instanceof CreatureDto)) {
//				foundCreature = true;
//			}
//		}
//        Assert.assertTrue(foundAlien && foundHuman && foundCreature);
//	}
//	
//	@Test
//	public void testPolymorphicOneToOne() {
//		JaxWsProxyFactoryBean godFactory = new JaxWsProxyFactoryBean();
//        godFactory.setAddress("http://localhost:8080/tinker-jetty/soap/GodControllerWs");
//        GodWs client = godFactory.create(GodWs.class);
//        GodDto g = client.find(2L);
//        Assert.assertNotNull(g);
//		
//		JaxWsProxyFactoryBean beingFactory = new JaxWsProxyFactoryBean();
//		beingFactory.setAddress("http://localhost:8080/tinker-jetty/soap/BeingControllerWs");
//        BeingWs beingWs = beingFactory.create(BeingWs.class);
//        List<BeingDto> beings = beingWs.getBeingForGod(g.getId());
//        Assert.assertEquals(3, beings.size());
//        
//        HumanDto humanDto = null;
//        for (BeingDto beingDto : beings) {
//			if (beingDto instanceof HumanDto) {
//				humanDto = (HumanDto) beingDto; 
//			}
//		}
//        Assert.assertNotNull(humanDto);
//        
//		JaxWsProxyFactoryBean spiritFactory = new JaxWsProxyFactoryBean();
//		spiritFactory.setAddress("http://localhost:8080/tinker-jetty/soap/SpiritControllerWs");
//		SpiritWs spiritWs = spiritFactory.create(SpiritWs.class);
//		SpiritDto spiritDto = spiritWs.getSpiritForBeing(humanDto.getId());
//		Assert.assertTrue(spiritDto instanceof SpookDto);
//		Assert.assertTrue(spiritDto.getName().equals("spook"));
//	}
	
}

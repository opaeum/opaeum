package org.nakeduml;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nakeduml.jetty.StartJetty;
import org.nakeduml.jetty.StopJetty;
import org.tinker.AngelDto;
import org.tinker.AngelWs;
import org.tinker.GodDto;
import org.tinker.GodWs;
import org.tinker.UniverseDto;
import org.tinker.UniverseWs;

public class TestPassByValueCrudWs {

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

	@Test
	public void updatePrimitiveField() {
		JaxWsProxyFactoryBean godFactory = new JaxWsProxyFactoryBean();
        godFactory.setAddress("http://localhost:8080/tinker-jetty/soap/GodControllerWs");
        GodWs client = godFactory.create(GodWs.class);
        GodDto g = client.find(2L);
        Assert.assertNotNull(g);
		JaxWsProxyFactoryBean universeFactory = new JaxWsProxyFactoryBean();
		universeFactory.setAddress("http://localhost:8080/tinker-jetty/soap/UniverseControllerWs");
        UniverseWs universeWs = universeFactory.create(UniverseWs.class);
        List<UniverseDto> universes = universeWs.getUniverseForGod(g.getId());
        Assert.assertEquals(50, universes.size());
        
        for (UniverseDto universeDto : universes) {
        	universeDto.setName(universeDto.getName() + "testthis");
			universeWs.update(universeDto);
		}
        
        List<UniverseDto> universesTest = universeWs.getUniverseForGod(g.getId());
        Assert.assertEquals(50, universesTest.size());
        for (UniverseDto universeDto : universesTest) {
			Assert.assertTrue(universeDto.getName().endsWith("testthis"));
		}
	}

	@Test
	public void updateOne() {
		JaxWsProxyFactoryBean godFactory = new JaxWsProxyFactoryBean();
        godFactory.setAddress("http://localhost:8080/tinker-jetty/soap/GodControllerWs");
        GodWs client = godFactory.create(GodWs.class);
        GodDto g = client.find(2L);
        Assert.assertNotNull(g);
		JaxWsProxyFactoryBean universeFactory = new JaxWsProxyFactoryBean();
		universeFactory.setAddress("http://localhost:8080/tinker-jetty/soap/UniverseControllerWs");
        UniverseWs universeWs = universeFactory.create(UniverseWs.class);
        List<UniverseDto> universes = universeWs.getUniverseForGod(g.getId());
        Assert.assertEquals(50, universes.size());

		JaxWsProxyFactoryBean angelFactory = new JaxWsProxyFactoryBean();
		angelFactory.setAddress("http://localhost:8080/tinker-jetty/soap/AngelControllerWs");
		AngelWs angelWs = angelFactory.create(AngelWs.class);
        List<AngelDto> angels = angelWs.getAngelsForGod(g.getId());
        Assert.assertEquals(50, angels.size());
        
        
        UniverseDto universeDto0 = universes.get(0);
        UniverseDto universeDto1 = universes.get(1);
        AngelDto angelDto0 = angelWs.find(universeDto0.getAngelId());
        AngelDto angelDto1 = angelWs.find(universeDto1.getAngelId());
        
        universeDto0.setAngelId(angelDto1.getId());
        universeWs.update(universeDto0);
        universeDto1.setAngelId(angelDto0.getId());
        universeWs.update(universeDto1);
        
        
        UniverseDto universeDto0Again = universeWs.find(universeDto0.getId());
        UniverseDto universeDto1Again = universeWs.find(universeDto1.getId());
        
        Assert.assertEquals(universeDto0Again.getAngelId(), angelDto1.getId());
        Assert.assertEquals(universeDto1Again.getAngelId(), angelDto0.getId());

        AngelDto angelDtoAgain0 = angelWs.find(universeDto0.getAngelId());
        AngelDto angelDtoAgain1 = angelWs.find(universeDto1.getAngelId());
        Assert.assertEquals(angelDtoAgain0.getName(), angelDto1.getName());
        Assert.assertEquals(angelDtoAgain1.getName(), angelDto0.getName());

        
	}
	
	
}

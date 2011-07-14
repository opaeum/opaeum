package org.nakeduml.jetty.ws;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.tinker.GodDto;
import org.tinker.GodWs;
import org.tinker.UniverseDto;
import org.tinker.UniverseWs;

public class GodWsClient {

	private GodWsClient() {
    }

    public static void main(String args[]) throws Exception {
    	JaxWsProxyFactoryBean godFactory = new JaxWsProxyFactoryBean();
        godFactory.setAddress("http://localhost:8080/tinker-jetty/soap/GodControllerWs");
        GodWs client = godFactory.create(GodWs.class);
        GodDto g = client.find(2L);
        System.out.println(g.getName());
        
    	JaxWsProxyFactoryBean universeFactory = new JaxWsProxyFactoryBean();
        universeFactory.setAddress("http://localhost:8080/tinker-jetty/soap/UniverseControllerWs");
        UniverseWs universeClient = universeFactory.create(UniverseWs.class);
        List<UniverseDto> universeDtos = universeClient.getUniverseForGod(g.getId());
        System.out.println(universeDtos.size());
        
        UniverseDto newUniverseDto = new UniverseDto();
        newUniverseDto.setGodId(g.getId());
        newUniverseDto.setName("testthisonelikedude");
        universeClient.create(newUniverseDto);
        
        universeDtos = universeClient.getUniverseForGod(g.getId());
        System.out.println(universeDtos.size());
        
        System.exit(0);
    }

}

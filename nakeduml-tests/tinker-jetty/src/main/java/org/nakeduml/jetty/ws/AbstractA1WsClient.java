package org.nakeduml.jetty.ws;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.tinker.polymorphism.AbstractA1Dto;
import org.tinker.polymorphism.AbstractA1Ws;

public class AbstractA1WsClient {

	private AbstractA1WsClient() {
    }

    public static void main(String args[]) throws Exception {
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        if (args != null && args.length > 0 && !"".equals(args[0])) {
            factory.setAddress(args[0]);
        } else {
            factory.setAddress("http://10.217.104.121:8080/tinker-jetty/soap/AbstractA1ControllerWs");
        }
        AbstractA1Ws client = factory.create(AbstractA1Ws.class);
        List<AbstractA1Dto> abstractA1Dtos = client.getAbstractA1ForGod(2L);
        for (AbstractA1Dto abstractA1Dto : abstractA1Dtos) {
			System.out.println(abstractA1Dto.getName());
		}
        System.exit(0);
    }

}

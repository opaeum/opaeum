package org.nakeduml.jetty.ws;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class CmApplicationWsClient {

	private CmApplicationWsClient() {
    }

    public static void main(String args[]) throws Exception {
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        if (args != null && args.length > 0 && !"".equals(args[0])) {
            factory.setAddress(args[0]);
        } else {
            factory.setAddress("http://localhost:8080/tinker-jetty/soap/CmApplicationWs");
        }
        CmApplicationWs client = factory.create(CmApplicationWs.class);
        System.out.println("Invoke sayHi()....");
        System.out.println(client.sayHi("jesus"));
//        CmApplicationDto cmApplicationDto = client.getCmApp();
//        System.out.println(cmApplicationDto.getName());
//        System.out.println(cmApplicationDto.toXmlString());
        System.exit(0);
    }

}

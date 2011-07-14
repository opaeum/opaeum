package org.nakeduml.jetty.ws;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class Client {

	private Client() {
    }

    public static void main(String args[]) throws Exception {
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        if (args != null && args.length > 0 && !"".equals(args[0])) {
            factory.setAddress(args[0]);
        } else {
            factory.setAddress("http://localhost:8080/tinker-jetty/soap/HelloWorld");
        }
        HelloWorld client = factory.create(HelloWorld.class);
        System.out.println("Invoke sayHi()....");
        System.out.println(client.sayHi("jesus"));
        System.exit(0);
    }

}

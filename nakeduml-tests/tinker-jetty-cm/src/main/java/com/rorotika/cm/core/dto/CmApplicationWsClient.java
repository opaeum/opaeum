package com.rorotika.cm.core.dto;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class CmApplicationWsClient {

	private CmApplicationWsClient() {
    }

    public static void main(String args[]) throws Exception {
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        if (args != null && args.length > 0 && !"".equals(args[0])) {
            factory.setAddress(args[0]);
        } else {
            factory.setAddress("http://10.1.1.102:8080/tinker-jetty/soap/CmApplicationDto");
        }
        CmApplicationWs client = factory.create(CmApplicationWs.class);
//        System.out.println("Invoke sayHi()....");
//        System.out.println(client.sayHi("jesus"));
//        CmApplicationDto cmApplicationDto = client.getCmApplication();
//        System.out.println(cmApplicationDto.getName());
        
        CmApplicationDto applicationDto = new CmApplicationDto();
        applicationDto.setName("test");
        client.updateCmApplication(applicationDto);
        
        System.exit(0);
    }

}

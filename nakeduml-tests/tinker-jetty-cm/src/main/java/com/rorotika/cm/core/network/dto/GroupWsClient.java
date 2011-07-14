package com.rorotika.cm.core.network.dto;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.rorotika.cm.core.HierarchyDto;

public class GroupWsClient {

	private GroupWsClient() {
    }

    public static void main(String args[]) throws Exception {
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        if (args != null && args.length > 0 && !"".equals(args[0])) {
            factory.setAddress(args[0]);
        } else {
            factory.setAddress("http://192.168.1.100:8080/tinker-jetty/soap/GroupDto");
        }
        GroupWs client = factory.create(GroupWs.class);
        GroupDto groupDto1 = new GroupDto();
        groupDto1.setName("test1");
        GroupDto groupDto2 = new GroupDto();
        groupDto2.setName("test2");
//        client.updateGroups(Arrays.asList(new GroupDto[]{groupDto1, groupDto2}));
        
        List<GroupDto> groups = client.getGroups(2L);
        for (GroupDto groupDto : groups) {
			System.out.println(groupDto.getName());
			List<HierarchyDto> children = client.getChildren(groupDto.getId());
			System.out.println(children);
		}
        
        
        System.exit(0);
    }

}

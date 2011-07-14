package com.rorotika.cm.core.network.dto;

import java.util.List;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.rorotika.cm.core.HierarchyDto;
import com.rorotika.cm.core.network.NetworkDto;

@WebService
@XmlSeeAlso({NetworkDto.class, GroupDto.class})
public interface GroupWs {
	String sayHi(String text);
	List<GroupDto> getGroups(Long cmApplicationId);
	void updateGroups(List<GroupDto> groups);
	List<HierarchyDto> getChildren(Long groupId);
}

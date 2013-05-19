package org.opaeum.test.redefinition;

import java.lang.reflect.Field;

import org.opaeum.runtime.bpm.organization.OrganizationNode;

import bpmmodel.custom.CustomBusinessNetwork;
import bpmmodel.custom.CustomOrganizationNode;
import junit.framework.TestCase;

public class MapRedefinition extends TestCase{
	public void testIt(){
		CustomBusinessNetwork bn = new CustomBusinessNetwork();
		CustomOrganizationNode on = new CustomOrganizationNode();
		bn.addToCustomOrganizationNode("asdf", on);
		assertEquals("asdf", getNameValue(on, OrganizationNode.class));
		assertEquals("asdf", getNameValue(on, CustomOrganizationNode.class));
		assertNotNull(on.getZ_keyOfCustomOrganizationNodeOnCustomBusinessNetwork());
		assertNotNull(on.getZ_keyOfOrganizationOnBusinessNetwork());
	}
	public String getNameValue(OrganizationNode on,Class<? extends OrganizationNode> c){
		try{
			Field f = c.getDeclaredField("name");
			f.setAccessible(true);
			return (String) f.get(on);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}

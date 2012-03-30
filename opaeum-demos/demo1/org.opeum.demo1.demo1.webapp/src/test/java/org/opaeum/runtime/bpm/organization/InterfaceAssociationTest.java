package org.opaeum.runtime.bpm.organization;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.opaeum.runtime.jpa.StandaloneJpaEnvironment;
import org.opaeum.runtime.persistence.ConversationalPersistence;

import structuredbusiness.Structuredbusiness;

public class InterfaceAssociationTest{
	@Test
	public void testInterfaceMany(){
		ConversationalPersistence p = StandaloneJpaEnvironment.getInstance().createConversationalPersistence();
		BusinessNetwork bn = new BusinessNetwork();
		Structuredbusiness sb = new Structuredbusiness();
		bn.addToBusinessCollaboration(sb);
		p.persist(bn);
		p.flush();
		System.out.println(sb.getId());
		p.close();
		StandaloneJpaEnvironment.getInstance().reset();
		p = StandaloneJpaEnvironment.getInstance().createConversationalPersistence();
		bn=p.getReference(BusinessNetwork.class, bn.getId());
		Set<IBusinessCollaboration> businessCollaboration = bn.getBusinessCollaboration();
		Assert.assertEquals(1, businessCollaboration.size());
		sb=p.getReference(Structuredbusiness.class, sb.getId());
		Assert.assertTrue(businessCollaboration.contains(sb));
	}
}

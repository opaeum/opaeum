package org.opaeum.runtime.bpm.organization;

import junit.framework.Assert;

import org.junit.Test;
import org.opaeum.demo1.structuredbusiness.branch.Manager;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opeum.demo1.util.Demo1Environment;

public class InterfaceAssociationTest{
	public void testInterfaceMany(){
		ConversationalPersistence p = Demo1Environment.INSTANCE.createConversationalPersistence();
		BusinessNetwork bn = new BusinessNetwork();
//		Structuredbusiness sb = new Structuredbusiness();
//		bn.addToBusinessCollaboration(sb);
//		p.persist(bn);
//		p.flush();
//		System.out.println(sb.getId());
//		p.close();
//		Assert.assertEquals(1,bn.getBusinessNetworkFacilatatesCollaboration_businessCollaboration().size());
//		Demo1Environment.INSTANCE.reset();
//		p = Demo1Environment.INSTANCE.createConversationalPersistence();
//		bn=p.getReference(BusinessNetwork.class, bn.getId());
//		Assert.assertEquals(1,bn.getBusinessNetworkFacilatatesCollaboration_businessCollaboration().size());
//		Set<IBusinessCollaboration> businessCollaboration = bn.getBusinessCollaboration();
//		Assert.assertEquals(1, businessCollaboration.size());
//		sb=p.getReference(Structuredbusiness.class, sb.getId());
//		Assert.assertTrue(businessCollaboration.contains(sb));
	}
	@Test
	public void testInterfaceOne(){
		ConversationalPersistence p = Demo1Environment.INSTANCE.createConversationalPersistence();
		PersonNode pn = new PersonNode();
		p.persist(pn);
		Manager ac = new Manager();
		p.persist(ac);
		ac.setRepresentedPerson(pn);
		ac.setRepresentedPerson(pn);
		p.flush();
		p.close();
		Assert.assertEquals(1, pn.getBusinessRole().size());
		Demo1Environment.INSTANCE.reset();
		p = Demo1Environment.INSTANCE.createConversationalPersistence();
		pn=p.getReference(PersonNode.class, pn.getId());
//		Assert.assertEquals(1, pn.getPerson_iBusinessRole_1_businessRole().size());
		ac=p.getReference(Manager.class, ac.getId());
		Assert.assertTrue(pn.getBusinessRole().contains(ac));
		
	}
}

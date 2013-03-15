package org.opaeum.runtime.hibernate.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.opaeum.opaeum_hibernate_tests.util.Opaeum_hibernate_testsEnvironment;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.test.Aunt;
import org.opaeum.test.Brother;
import org.opaeum.test.Family;
import org.opaeum.test.FamilyMemberHasRelation;

public class SomeTest  {
	@Test
	public void testManyToManySideOne() {
		Aunt aunt = new Aunt();
		Family family = new Family();
		Brother brother = new Brother(family, "John");
		aunt.setFirstName("Clotilda");
		aunt.setSurname("McGilliguddy");
		aunt.setDateOfBirth(new Date());
		aunt.addToFamilyMember(family,"John", brother);
		ConversationalPersistence p = Opaeum_hibernate_testsEnvironment.INSTANCE.createConversationalPersistence();
		p.persist(family);
		p.persist(aunt);
		p.flush();
		p = Opaeum_hibernate_testsEnvironment.INSTANCE.createConversationalPersistence();
		assertManyToManyConditions(p.getReference(Aunt.class, aunt.getId()) ,p.getReference(Brother.class, brother.getId()));
	}
	protected void assertManyToManyConditions(Aunt aunt, Brother brother) {
		Assert.assertEquals(1, aunt.getFamilyMember().size());
		Assert.assertEquals(1, aunt.getFamilyMemberHasRelation_familyMember()
				.size());
		FamilyMemberHasRelation link1 = aunt
				.getFamilyMemberHasRelation_familyMemberFor(brother);
		Assert.assertNotNull(link1);
		Assert.assertEquals(brother, link1.getFamilyMember());
		FamilyMemberHasRelation link2 = brother
				.getFamilyMemberHasRelation_relationFor(aunt);
		Assert.assertSame(link1, link2);
		Assert.assertNotNull(link2);
		Assert.assertEquals(aunt, link2.getRelation());
		Assert.assertTrue(aunt.getFamilyMember().contains(brother));
		Assert.assertTrue(aunt.getFamilyMemberHasRelation_familyMember()
				.contains(link2));
		Assert.assertTrue(brother.getFamilyMemberHasRelation_relation()
				.contains(link2));
	}

}

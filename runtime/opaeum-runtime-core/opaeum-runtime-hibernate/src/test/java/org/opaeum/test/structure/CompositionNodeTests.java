package org.opaeum.test.structure;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.Aunt;
import org.opaeum.test.Brother;
import org.opaeum.test.Family;
import org.opaeum.test.Father;
import org.opaeum.test.Mother;
import org.opaeum.test.Sister;
import org.opaeum.test.StepBrother;
public class CompositionNodeTests {
	@Test
	public void testNonAssociationClassOneToManyOwnership() {
		Family family = new Family();
		Sister sister = new Sister(family, "Savanna");
		Assert.assertEquals(family, sister.getOwningObject());
		Brother brother = new Brother();
		brother.init(family);
		Assert.assertEquals(family, brother.getOwningObject());
		Assert.assertTrue(family.getChild().contains(sister));
		Assert.assertFalse(family.getChild().contains(brother));
		try {
			brother.addToOwningObject();
			Assert.fail("IllegalStateException was not thrown");
		} catch (IllegalStateException e) {
			Assert.assertTrue("IllegalStateException was thrown", true);
		}
		brother.setName("John");
		brother.addToOwningObject();
		assertSisterAndBrotherInFamily(family, sister, brother);
	}

	public  void assertSisterAndBrotherInFamily(Family family, Sister sister,
			Brother brother) {

		Assert.assertTrue(family.getChild().contains(brother));
		brother.removeFromOwningObject();
		Assert.assertFalse(family.getChild().contains(brother));
		Assert.assertEquals(family, brother.getOwningObject());
		sister.markDeleted();
		Assert.assertFalse(family.getChild().contains(sister));
		Assert.assertEquals(family, sister.getOwningObject());
	}

	@Test
	public void testMarkDeletedWithNonAssociationClassManyChild() {
		Family family = new Family();
		Sister sister = new Sister(family, "Savanna");
		sister.setDateOfBirth(new Date());
		// ManyToone with associationClass
		Father father = new Father(family);
		sister.setSurnameProvider(father);
		// ManyToone without associationClass
		sister.setFather(father);
		// ManyToMany without associationClass
		Brother brother = new Brother(family, "John");
		sister.addToBrother("John", brother);
		// ManyToMany with associationClass
		Aunt aunt = new Aunt();
		sister.addToGodParent("blue", "purple", new Date(), aunt);
		// TODO OneToOne with associationClass
		// TODO OneToOne without associationClass
		// TODO OneToMany with associationClass
		sister.addToYounberSiblings(brother);
		assertSisterAddedToSurnameProvider(sister, father);
		assertBrotherAddedToYoungerSiblings(sister, brother);
		assertSisterAddedToFathersChild(sister, father);
		assertSisterAddedToBrother(sister, brother);
		assertSisterAddedToGodParent(sister, aunt);
		// ### mark deleted#####//
		
		sister.markDeleted();
		assertSisterRemovedFromReferences(family, sister, father, brother, aunt);
	}

	public void assertSisterAddedToBrother(Sister sister, Brother brother) {
		Assert.assertTrue(sister.getBrother().contains(brother));
		Assert.assertTrue(brother.getSister().contains(sister));
	}

	public void assertSisterAddedToGodParent(Sister sister, Aunt aunt) {
		Assert.assertTrue(sister.getGodParent().contains(aunt));
		Assert.assertTrue(aunt.getChild().contains(sister));
	}

	public void assertSisterAddedToFathersChild(Sister sister, Father father) {
		Assert.assertEquals(father, sister.getFather());
		Assert.assertTrue(father.getChild().contains(sister));
	}

	public void assertBrotherAddedToYoungerSiblings(Sister sister,
			Brother brother) {
		Assert.assertEquals(sister, brother.getFirstBornSibling());
		Assert.assertTrue(sister.getYounberSiblings().contains(brother));
	}

	public void assertSisterAddedToSurnameProvider(Sister sister, Father father) {
		Assert.assertEquals(father, sister.getSurnameProvider());
		Assert.assertTrue(father.getSurnameCarryingDaughter().contains(sister));
	}

	public  void assertSisterRemovedFromReferences(Family family,
			Sister sister, Father father, Brother brother, Aunt aunt) {
		Assert.assertEquals(sister, sister
				.getSurnameProviderHasDaughter_surnameProvider()
				.getSurnameCarryingDaughter());
		Assert.assertEquals(father, sister.getFather());
		Assert.assertNull(brother.getFirstBornSibling());
		Assert.assertFalse(father.getSurnameCarryingDaughter().contains(sister));
		Assert.assertFalse(father.getChild().contains(sister));
		// Containment oneToMany case
		Assert.assertFalse(family.getChild().contains(sister));
		Assert.assertFalse(brother.getSister().contains(sister));
		Assert.assertFalse(aunt.getChild().contains(sister));
		Assert.assertFalse(sister.getYounberSiblings().contains(brother));
		assertSisterRemovedFromReferencesButItDoesNotWorkinHibernate(sister,
				father, brother, aunt);
	}

	public void assertSisterRemovedFromReferencesButItDoesNotWorkinHibernate(
			Sister sister, Father father, Brother brother, Aunt aunt) {
		//Note these tests fail during persistence tests because the removal from the map results in hibernate updating the objects on the many side
		Assert.assertEquals(father, sister.getSurnameProvider());
		// because of the associationClass we can retain all the the one-to-many
		// info
		Assert.assertTrue(sister.getGodParent().contains(aunt));
		// because of not having an associationClass we loose all the the
		// one-to-many info
		Assert.assertFalse(sister.getBrother().contains(brother));
	}

	@Test
	public void testMarkDeletedWithAssociationClassManyChild() {
		Family family = new Family();
		StepBrother stepBrother = new StepBrother(family, "Savanna");
		assertStepBrotherAdded(family, stepBrother);
		// TODO ManyToOne without associationClass
		// TODO ManyToMany without associationClass
		// ManyToMany with associationClass
		Brother brother = new Brother(family, "John");
		stepBrother.addToStepSibling(brother);
		assertBrotherAddedToStepSibling(stepBrother, brother);
		// ManyToone with associationClass
		Mother mother = new Mother(family);
		stepBrother.setStepMother(mother);
		assertStepBrotherAddedToMother(stepBrother, mother);
		//
		stepBrother.markDeleted();
		assertStepBrotherRemovedFromReferences(family, stepBrother, brother,
				mother);

	}

	protected void assertStepBrotherRemovedFromReferences(Family family,
			StepBrother stepBrother, Brother brother, Mother mother) {
		Assert.assertEquals(mother, stepBrother.getStepMother());
		Assert.assertFalse(mother.getStepChild().contains(stepBrother));
		Assert.assertEquals(family, stepBrother.getFamily());
		Assert.assertFalse(family.getStepChild().contains(stepBrother));
		Assert.assertFalse(brother.getStepSibling().contains(stepBrother));
		// Retained info because of AssociationClass
		Assert.assertEquals(isPojo(), stepBrother.getStepSibling().contains(brother));
	}

	public boolean isPojo() {
		return true;
	}

	protected void assertStepBrotherAddedToMother(StepBrother stepBrother,
			Mother mother) {
		Assert.assertTrue(mother.getStepChild().contains(stepBrother));
		Assert.assertEquals(mother, stepBrother.getStepMother());
	}

	protected void assertBrotherAddedToStepSibling(StepBrother stepBrother,
			Brother brother) {
		Assert.assertTrue(brother.getStepSibling().contains(stepBrother));
		Assert.assertTrue(stepBrother.getStepSibling().contains(brother));
	}

	public  void assertStepBrotherAdded(Family family, StepBrother stepBrother) {
		Assert.assertTrue(family.getStepChild().contains(stepBrother));
		Assert.assertEquals(family, stepBrother.getFamily());
	}
}

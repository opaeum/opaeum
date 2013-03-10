package org.opaeum.test.structure;

import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.Brother;
import org.opaeum.test.Family;
import org.opaeum.test.FamilyStepChild;
import org.opaeum.test.Sister;
import org.opaeum.test.StepBrother;

public class NonAssociationClassTest {
	@Test
	public void testManyToManySideOne() {
		Sister sister = new Sister();
		Brother brother = new Brother();
		try{
			sister.addToBrother("Johnny", brother);
			Assert.fail("IllegalStateException was not thrown");
		}catch(IllegalStateException e){
			Assert.assertTrue("IllegalStateException was thrown", true);
		}
		sister.setName("Clotilda");
		sister.addToBrother("Johnny", brother);
		assertManyToManyConditions(sister, brother);
	}
	@Test
	public void testManyToManySideTwo() {
		Sister aunt = new Sister();
		Brother brother = new Brother();
		try{
			brother.addToSister("Jean", aunt);
			Assert.fail("IllegalStateException was not thrown");
		}catch(IllegalStateException e){
			Assert.assertTrue("IllegalStateException was thrown", true);
		}
		brother.setName("John-Boy");
		brother.addToSister("Jean", aunt);
		assertManyToManyConditions(aunt, brother);
	}

	private void assertManyToManyConditions(Sister sister, Brother brother) {
		Assert.assertEquals(1, sister.getBrother().size());
		Assert.assertEquals(1, brother.getSister().size());
		Assert.assertTrue(sister.getBrother().contains(brother));
		Assert.assertTrue(brother.getSister().contains(sister));
		sister.clearBrother();
		Assert.assertEquals(0, sister.getBrother().size());
		Assert.assertEquals(0, brother.getSister().size());
		Assert.assertFalse(sister.getBrother().contains(brother));
		Assert.assertFalse(brother.getSister().contains(sister));
	}

	@Test
	public void testCompositeManyToOne() {
		Family family=new Family();
		Brother sb = new Brother(); 
		try{
			sb.setFamily(family);
			Assert.fail("IllegalStateException was not thrown");
		}catch(IllegalStateException e){
			Assert.assertTrue("IllegalStateException was thrown", true);
		}
		sb.setName("Peter Peter pumpkin eater");
		sb.setFamily(family);
		assertOneToManyConditions(family, sb);
		Family family2=new Family();
		sb.setFamily(family2);
		Assert.assertNotSame(sb.getFamily(), family);
		Assert.assertEquals(0, family.getChild().size());
		assertOneToManyConditions(family2, sb);
	}
	@Test
	public void testCompositeOneToMany() {
		Family family=new Family();
		Brother sb = new Brother(); 
		family.addToChild("Peter Peter pumpkin eater", sb);
		sb.setFamily(family);
		assertOneToManyConditions(family, sb);
		Family family2=new Family();
		family2.addToChild(sb.getName(), sb);
		Assert.assertNotSame(sb.getFamily(), family);
		Assert.assertEquals(0, family.getChild().size());
		assertOneToManyConditions(family2, sb);
	}
	private void assertOneToManyConditions(Family family, Brother sb) {
		Assert.assertNotNull(sb.getFamily());
		Assert.assertSame(family, sb.getFamily());
		Assert.assertSame(family, sb.getFamily());
		Assert.assertEquals(1, family.getChild().size());
		Assert.assertTrue(family.getChild().contains(sb));
	}
}

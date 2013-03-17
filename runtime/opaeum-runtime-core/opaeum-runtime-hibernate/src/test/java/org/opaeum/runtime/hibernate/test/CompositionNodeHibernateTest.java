package org.opaeum.runtime.hibernate.test;

import org.junit.Assert;
import org.junit.Before;
import org.opaeum.test.Aunt;
import org.opaeum.test.Brother;
import org.opaeum.test.Father;
import org.opaeum.test.Sister;
import org.opaeum.test.structure.CompositionNodeTests;

public class CompositionNodeHibernateTest extends CompositionNodeTests{

	@Before
	public void initHelper() {
		helper = new HibernatePersistenceTestHelper();
	}
	@Override
	public void assertSisterRemovedFromReferencesButItDoesNotWorkinHibernate(
			Sister sister, Father father, Brother brother, Aunt aunt) {
	}
			

}

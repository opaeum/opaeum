package org.opaeum.runtime.hibernate.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.opaeum.test.Aunt;
import org.opaeum.test.Brother;
import org.opaeum.test.Father;
import org.opaeum.test.Sister;
import org.opaeum.test.structure.CompositionNodeTests;
@RunWith(PersistenceInterceptor.class)

public class CompositionNodeHibernateTest extends CompositionNodeTests{

	@Override
	public void assertSisterRemovedFromReferencesButItDoesNotWorkinHibernate(
			Sister sister, Father father, Brother brother, Aunt aunt) {
		System.out.println();
	}
	public boolean isPojo() {
		return false;
	}

			

}

package org.opaeum.runtime.hibernate.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opaeum.test.Aunt;
import org.opaeum.test.Brother;
import org.opaeum.test.Family;
import org.opaeum.test.Father;
import org.opaeum.test.Mother;
import org.opaeum.test.StepBrother;
import org.opaeum.test.structure.AssociationClassTest;

public class AssociationClassHibernateTest extends AssociationClassTest {
	@Before 
	public void initHelper(){
		helper=new HibernatePersistenceTestHelper();
	}
}

package org.nakeduml.jbpm.test;

import jbpm.jbpm.TheBoss;
import jbpm.jbpm.TheBossDataGenerator;
import jbpm.util.FailedConstraintsException;
import jbpm.util.InvariantError;
import jbpm.util.InvariantException;
import jbpm.util.Stdlib;

import org.nakeduml.persistence.StartUpLoadData;


public abstract class BaseTest {

	public static Class<?>[] getTestClasses() {
		return new Class[] { StartUpLoadData.class, JbpmTest.class, BaseTest.class, Stdlib.class, FailedConstraintsException.class, InvariantException.class,
				InvariantError.class, TheBoss.class, TheBossDataGenerator.class};
	}

}

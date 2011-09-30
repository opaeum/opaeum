package org.opeum.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.opeum.test.NakedUtilTestClasses;
import audittest.org.opeum.audit.God;
import audittest.util.InvariantError;

public abstract class BaseTest {

	public static Class<?>[] getTestClasses() throws ClassNotFoundException, IOException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		classes.addAll(NakedUtilTestClasses.getClasses(God.class.getPackage().getName()));
		classes.addAll(NakedUtilTestClasses.getClasses(AuditTest.class.getPackage().getName()));
		classes.addAll(NakedUtilTestClasses.getClasses(InvariantError.class.getPackage().getName()));
		Class<?>[] result = new Class[classes.size()];
		classes.toArray(result);
		return result;
	}

}

package org.opaeum.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.opaeum.test.NakedUtilTestClasses;
import datagenerationtest.org.opaeum.God;
import datagenerationtest.util.InvariantError;

public abstract class BaseTest {

	public static Class<?>[] getTestClasses() throws ClassNotFoundException, IOException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		classes.addAll(NakedUtilTestClasses.getClasses(God.class.getPackage().getName()));
		classes.addAll(NakedUtilTestClasses.getClasses(UserUserGroupManyToManyTest.class.getPackage().getName()));
		classes.addAll(NakedUtilTestClasses.getClasses(InvariantError.class.getPackage().getName()));
		classes.addAll(NakedUtilTestClasses.getClasses(StartUpLoadData.class.getPackage().getName()));
		Class<?>[] result = new Class[classes.size()];
		classes.toArray(result);
		return result;
	}

}

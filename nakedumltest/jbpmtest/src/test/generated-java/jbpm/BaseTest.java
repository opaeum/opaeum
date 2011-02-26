package jbpm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jbpm.jbpm.TheBoss;
import jbpm.util.Stdlib;

public class BaseTest {



	static public Package[] getTestPackages() throws IOException, ClassNotFoundException {
		List<Package> packages = new ArrayList<Package>();
		packages.add(TheBoss.class.getPackage());
		packages.add(Stdlib.class.getPackage());
		packages.add(BaseTest.class.getPackage());
		Package[] result = new Package[packages.size()];
		packages.toArray(result);
		return result;
	}

}
package jbpm.jbpm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jbpm.jbpm.OrderX;
import jbpm.jbpm.rip.NetworkSoftwareVersion;
import jbpm.util.Stdlib;

public class BaseTest {



	static public Package[] getTestPackages() throws IOException, ClassNotFoundException {
		List<Package> packages = new ArrayList<Package>();
		packages.add(NetworkSoftwareVersion.class.getPackage());
		packages.add(OrderX.class.getPackage());
		packages.add(Stdlib.class.getPackage());
		packages.add(BaseTest.class.getPackage());
		Package[] result = new Package[packages.size()];
		packages.toArray(result);
		return result;
	}

}
package net.sf.nakeduml.jbpm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jbpm.jbpm.EmailCustomerSignal;
import jbpm.jbpm.TheBoss;
import jbpm.jbpm.application.SimpleSync1;
import jbpm.jbpm.dispatch.SimpleAsyncShipping;
import jbpm.util.Stdlib;

import org.nakeduml.jbpm5.JbpmKnowledgeBase;

public class BaseTest {

	static public Package[] getTestPackages() throws IOException, ClassNotFoundException {
		List<Package> packages = new ArrayList<Package>();
		packages.add(TheBoss.class.getPackage());
		packages.add(Stdlib.class.getPackage());
		packages.add(BaseTest.class.getPackage());
		packages.add(JbpmKnowledgeBase.class.getPackage());
		packages.add(SimpleSync1.class.getPackage());
		packages.add(SimpleAsyncShipping.class.getPackage());
		packages.add(EmailCustomerSignal.class.getPackage());
		Package[] result = new Package[packages.size()];
		packages.toArray(result);
		return result;
	}

}
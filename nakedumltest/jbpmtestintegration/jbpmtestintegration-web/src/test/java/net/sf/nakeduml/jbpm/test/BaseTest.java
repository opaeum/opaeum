package net.sf.nakeduml.jbpm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jbpm.jbpm.EmailCustomerSignal;
import jbpm.jbpm.TheBoss;
import jbpm.jbpm.application.SimpleSync1;
import jbpm.jbpm.dispatch.SimpleAsyncShipping;
import jbpm.jbpm.rip.Vendor;
import jbpm.jbpm.rip.network.RipProcess;
import jbpm.jbpm.rip.network.ripprocess.RipActivity;
import jbpm.util.Stdlib;
import net.sf.nakeduml.ripper.RipperWebTest;

import org.jboss.arquillian.testng.Arquillian;
import org.nakeduml.util.jbpm.adaptor.JbpmKnowledgeBase;

public class BaseTest extends Arquillian {

	static public Package[] getTestPackages() throws IOException, ClassNotFoundException {
		List<Package> packages = new ArrayList<Package>();
		packages.add(TheBoss.class.getPackage());
		packages.add(Vendor.class.getPackage());
		packages.add(Stdlib.class.getPackage());
		packages.add(BaseTest.class.getPackage());
		packages.add(RipperWebTest.class.getPackage());
		packages.add(JbpmKnowledgeBase.class.getPackage());
		packages.add(SimpleSync1.class.getPackage());
		packages.add(SimpleAsyncShipping.class.getPackage());
		packages.add(RipProcess.class.getPackage());
		packages.add(RipActivity.class.getPackage());
		packages.add(EmailCustomerSignal.class.getPackage());
		Package[] result = new Package[packages.size()];
		packages.toArray(result);
		return result;
	}

}
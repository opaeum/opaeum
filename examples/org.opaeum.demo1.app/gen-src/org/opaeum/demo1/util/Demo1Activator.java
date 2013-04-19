package org.opaeum.demo1.util;

import org.opaeum.runtime.rwt.AbstractOpaeumActivator;
import org.opaeum.runtime.rwt.IOpaeumApplication;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;

public class Demo1Activator extends AbstractOpaeumActivator implements BundleActivator {



	public IOpaeumApplication createApplication(Bundle bundle) {
		IOpaeumApplication result = null;
		if ( Demo1Application.INSTANCE==null ) {
			Demo1Application.INSTANCE=new Demo1Application(bundle);
		}
		result=Demo1Application.INSTANCE;
		return result;
	}
	
	public void destroyApplication() {
		Demo1Application.INSTANCE=null;
	}

}
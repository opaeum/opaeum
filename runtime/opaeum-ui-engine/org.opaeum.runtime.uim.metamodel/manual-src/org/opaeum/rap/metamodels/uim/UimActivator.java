package org.opaeum.rap.metamodels.uim;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class UimActivator implements BundleActivator{
	public static UimActivator INSTANCE;
	private Bundle bundle;
	@Override
	public void start(BundleContext context) throws Exception{
		INSTANCE=this;
		bundle=context.getBundle();
		// TODO Auto-generated method stub
	}
	@Override
	public void stop(BundleContext context) throws Exception{
		// TODO Auto-generated method stub
	}
	public Bundle getBundle(){
		return bundle;
	}
}

package org.opaeum.runtime.rwt;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public abstract class AbstractOpaeumActivator<V> implements BundleActivator{
	private IOpaeumApplication application;
	private ServiceRegistration<IOpaeumApplication> registration;
	protected abstract IOpaeumApplication createApplication(Bundle b);
	@Override
	public void start(BundleContext context) throws Exception{
		this.application=createApplication(context.getBundle());
		Dictionary<String,?> asdf=new Hashtable<String,Object>();
		registration = context.registerService(IOpaeumApplication.class, application, asdf);
	}
	@Override
	public void stop(BundleContext context) throws Exception{
		registration.unregister();
		application=null;
	}
}

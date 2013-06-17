package org.opaeum.velocity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class MiscCompilationActivator implements BundleActivator{
	private static MiscCompilationActivator instance;
	private BundleContext context;
	@Override
	public void start(BundleContext context) throws Exception{
		this.context = context;
		instance = this;
	}
	public InputStream getResource(String plugin,String resource){
		Bundle[] bundles = context.getBundles();
		//TODO cache
		for(Bundle bundle:bundles){
			if(bundle.getSymbolicName().equals(plugin)){
				URL r = bundle.getResource(resource);
				if(r != null){
					try{
						return r.openStream();
					}catch(IOException e){
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return null;
	}
	@Override
	public void stop(BundleContext context) throws Exception{
		this.context = null;
		instance = null;
	}
	public static MiscCompilationActivator getInstance(){
		return instance;
	}
}

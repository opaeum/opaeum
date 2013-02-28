package org.opaeum.runtime.rwt;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.osgi.framework.Bundle;

public class BundleUimResourceWrapper extends AbstractUimResourceWrapper{
	private URL entry;
	public BundleUimResourceWrapper(Bundle bundle,String id){
		super();
		this.entry=bundle.getEntry("/" + id + ".uim");
	}
	@Override
	protected boolean shouldReload(){
		return false;
	}
	@Override
	protected InputStream createInputStream(){
		try{
			return entry.openStream();
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}

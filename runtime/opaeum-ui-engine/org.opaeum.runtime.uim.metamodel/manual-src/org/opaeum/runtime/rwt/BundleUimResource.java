package org.opaeum.runtime.rwt;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.osgi.framework.Bundle;

public class BundleUimResource extends AbstractUimResource{
	private URL entry;
	public BundleUimResource(Bundle bundle,String fileName){
		super(fileName);
		this.entry=bundle.getEntry("/" + fileName);
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

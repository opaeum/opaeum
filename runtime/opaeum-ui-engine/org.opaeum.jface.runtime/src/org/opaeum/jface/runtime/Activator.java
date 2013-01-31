package org.opaeum.jface.runtime;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator{

	// The plug-in ID
	public static final String PLUGIN_ID = "org.opaeum.jface.runtime"; //$NON-NLS-1$

	public static final String ID = PLUGIN_ID;

	public static final String IMG_PROJECT = "project.gif";
  Map<String, Image> images=new HashMap<String,Image>();
	
	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public Image getImage(String string){
		// TODO Auto-generated method stub
		return images.get(string);
	}

}

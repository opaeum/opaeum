package org.opaeum.uim.uml2uim;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Element;

public class AbstractUimSynchronizer2 implements UserInterfaceResourceFactory{
	protected ResourceSet uimRst;
	protected boolean regenerate;
	protected URI directoryUri;
	private Map<Element,Resource> newResources=new HashMap<Element,Resource>();
	public AbstractUimSynchronizer2(){
	}
	public AbstractUimSynchronizer2(URI directoryUri, ResourceSet resourceSet,boolean regenerate){
		this.regenerate = regenerate;
		this.uimRst = resourceSet;
		this.directoryUri=directoryUri;
	}
	@Override
	public final Resource getResource(Element e){
		Resource r = newResources.get(e);
		if(r==null){
			r=UimResourceUtil.getUiResource(e, uimRst, directoryUri);
			newResources.put(e,r);
		}
		return r;
	}
	public void release(){
		this.uimRst = null;
	}
	public Map<Element,Resource> getNewResources(){
		return newResources;
	}
}

package org.opaeum.uim.uml2uim;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class AbstractUimSynchronizer2 implements UserInterfaceResourceFactory{
	protected ResourceSet uimRst;
	protected boolean regenerate;
	protected URI directoryUri;
	public AbstractUimSynchronizer2(){
	}
	public AbstractUimSynchronizer2(URI directoryUri, ResourceSet resourceSet,boolean regenerate){
		this.regenerate = regenerate;
		this.uimRst = resourceSet;
		this.directoryUri=directoryUri;
	}
	@Override
	public final Resource getResource(String id,String deleteThisParam){
		URI formUri = directoryUri.appendSegment("ui");
		formUri = formUri.appendSegment(id);
		formUri = formUri.appendFileExtension("uim");
		Resource resource = null;
		resource = uimRst.getResource(formUri, false);
		if(resource == null){
			resource = uimRst.createResource(formUri);
		}
		return resource;
	}
	public void release(){
		this.uimRst = null;
	}
}

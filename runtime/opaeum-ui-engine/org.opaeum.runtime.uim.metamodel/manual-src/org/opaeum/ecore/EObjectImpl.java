package org.opaeum.ecore;

import org.opaeum.runtime.rwt.AbstractUimResource;
import org.w3c.dom.Element;

public abstract class EObjectImpl implements EObject{
	private String uid;
	private EObject eContainer;
	private AbstractUimResource resource;
	public void init(EObject e,AbstractUimResource resource,Element el){
		eContainer = e;
		this.resource = resource;
		this.uid = el.getAttribute("xmi:id");
		resource.putElement(el,this);
	}
	public AbstractUimResource eResource(){
		return resource;
	}
	@Override
	public String getUid(){
		return uid;
	}
	@Override
	public void setUid(String uid){
		this.uid = uid;
	}
	@Override
	public EObject eContainer(){
		return eContainer;
	}
}

package org.opaeum.ecore;

public abstract class EObjectImpl implements EObject{
	private String uid;
	private EObject eContainer;
	@Override
	public void eContainer(EObject e){
		this.eContainer=e;
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

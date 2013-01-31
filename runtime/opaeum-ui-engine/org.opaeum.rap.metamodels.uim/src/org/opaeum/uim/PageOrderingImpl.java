package org.opaeum.uim;

import org.opaeum.ecore.EObject;

public class PageOrderingImpl implements PageOrdering {
	private Labels labelOverride;
	private Page page;
	private Integer position;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public Page getPage() {
		return this.page;
	}
	
	public Integer getPosition() {
		return this.position;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setPage(Page page) {
		this.page=page;
	}
	
	public void setPosition(Integer position) {
		this.position=position;
	}

}
package org.opaeum.uim;

import org.opaeum.ecore.EObject;

public interface PageOrdering extends EObject {
	public Labels getLabelOverride();
	
	public Page getPage();
	
	public Integer getPosition();
	
	public void setLabelOverride(Labels labelOverride);
	
	public void setPage(Page page);
	
	public void setPosition(Integer position);

}
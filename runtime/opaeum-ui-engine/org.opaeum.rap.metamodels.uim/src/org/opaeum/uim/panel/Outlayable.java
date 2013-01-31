package org.opaeum.uim.panel;

import org.opaeum.ecore.EObject;

public interface Outlayable extends EObject {
	public Boolean getFillHorizontally();
	
	public Boolean getFillVertically();
	
	public Integer getPreferredHeight();
	
	public Integer getPreferredWidth();
	
	public void setFillHorizontally(Boolean fillHorizontally);
	
	public void setFillVertically(Boolean fillVertically);
	
	public void setPreferredHeight(Integer preferredHeight);
	
	public void setPreferredWidth(Integer preferredWidth);

}
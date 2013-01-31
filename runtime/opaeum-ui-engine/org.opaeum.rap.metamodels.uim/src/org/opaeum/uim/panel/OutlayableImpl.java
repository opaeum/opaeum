package org.opaeum.uim.panel;

import org.opaeum.ecore.EObject;

public class OutlayableImpl implements Outlayable {
	private Boolean fillHorizontally;
	private Boolean fillVertically;
	private Integer preferredHeight;
	private Integer preferredWidth;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public Boolean getFillHorizontally() {
		return this.fillHorizontally;
	}
	
	public Boolean getFillVertically() {
		return this.fillVertically;
	}
	
	public Integer getPreferredHeight() {
		return this.preferredHeight;
	}
	
	public Integer getPreferredWidth() {
		return this.preferredWidth;
	}
	
	public void setFillHorizontally(Boolean fillHorizontally) {
		this.fillHorizontally=fillHorizontally;
	}
	
	public void setFillVertically(Boolean fillVertically) {
		this.fillVertically=fillVertically;
	}
	
	public void setPreferredHeight(Integer preferredHeight) {
		this.preferredHeight=preferredHeight;
	}
	
	public void setPreferredWidth(Integer preferredWidth) {
		this.preferredWidth=preferredWidth;
	}

}
package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UserInteractionElement;

public interface ViewAllocation extends EObject, UserInteractionElement {
	public Integer getHeight();
	
	public PositionInPerspective getPosition();
	
	public Integer getWidth();
	
	public void setHeight(Integer height);
	
	public void setPosition(PositionInPerspective position);
	
	public void setWidth(Integer width);

}
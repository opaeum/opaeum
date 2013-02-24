package org.opaeum.uimodeler.common;

import org.eclipse.uml2.uml.Element;
import org.opaeum.uim.perspective.NavigationConstraint;

public interface IUIElementMapMap{
	NavigationConstraint getElementFor(Element e);
}

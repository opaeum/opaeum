package org.opaeum.uimodeler.common;

import org.eclipse.uml2.uml.Element;
import org.opaeum.uim.perspective.ExplorerConstraint;

public interface IExplorerMap{
	ExplorerConstraint getConstraintFor(Element e);
}

package org.opaeum.metamodel.core;

import nl.klasse.octopus.model.IPackageableElement;
import nl.klasse.octopus.model.VisibilityKind;

public interface INakedPackageableElement extends IPackageableElement,INakedElement{
	void setVisibility(VisibilityKind kind);
}

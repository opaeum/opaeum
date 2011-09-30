package org.opeum.metamodel.core.internal;

import org.opeum.metamodel.core.INakedPackageableElement;
import nl.klasse.octopus.model.VisibilityKind;

public abstract class NakedPackageableElementImpl extends NakedElementImpl implements INakedPackageableElement{
	/**
	 * 
	 */
	private static final long serialVersionUID = 197895365975129553L;
	private VisibilityKind visibility;
	public VisibilityKind getVisibility(){
		return visibility;
	}
	public void setVisibility(VisibilityKind visibility){
		this.visibility = visibility;
	}
}

package net.sf.nakeduml.metamodel.core.internal;

import net.sf.nakeduml.metamodel.core.INakedPackageableElement;
import nl.klasse.octopus.model.VisibilityKind;

public abstract class NakedPackageableElementImpl extends NakedModelElementImpl implements INakedPackageableElement{
	private VisibilityKind visibility;
	public VisibilityKind getVisibility(){
		return visibility;
	}
	public void setVisibility(VisibilityKind visibility){
		this.visibility = visibility;
	}
}

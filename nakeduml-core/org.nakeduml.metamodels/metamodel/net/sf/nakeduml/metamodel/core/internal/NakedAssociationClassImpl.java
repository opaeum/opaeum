package net.sf.nakeduml.metamodel.core.internal;

import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public class NakedAssociationClassImpl extends NakedAssociationImpl implements INakedAssociationClass {

	@Override
	public boolean isClass() {
		return true;
	}

	@Override
	public boolean isPersistent() {
		return true;
	}

	@Override
	public boolean hasComposite() {
		return true;
	}

	@Override
	public INakedProperty getEndToComposite() {
		return getPropertyToEnd1();
	}
	@Override
	public List<INakedProperty> getEffectiveAttributes(){
		List<INakedProperty> effectiveAttributes = super.getEffectiveAttributes();
		effectiveAttributes.removeAll(getEnds());
		return effectiveAttributes;
	}

}

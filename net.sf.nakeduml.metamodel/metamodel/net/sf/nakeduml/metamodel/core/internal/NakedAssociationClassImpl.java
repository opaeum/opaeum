package net.sf.nakeduml.metamodel.core.internal;

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
		return getEnd1();
	}

	@Override
	public boolean representsUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRepresentsUser(boolean representsUser) {
		// TODO Auto-generated method stub
		
	}
}

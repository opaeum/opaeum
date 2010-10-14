package net.sf.nakeduml.metamodel.actions.internal;

import net.sf.nakeduml.metamodel.actions.INakedAddStructuralFeatureValueAction;

public class NakedAddStructuralFeatureValueActionImpl extends NakedWriteStructuralFeatureActionImpl implements
		INakedAddStructuralFeatureValueAction {
	private boolean replaceAll;

	public boolean isReplaceAll() {
		return replaceAll;
	}

	public void setReplaceAll(boolean f) {
		this.replaceAll = f;
	}
}

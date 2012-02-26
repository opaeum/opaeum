package org.opaeum.metamodel.core.internal.emulated;

import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedMessageStructure;

public abstract class EmulatedCompositionMessageStructure extends EmulatedClassifier implements INakedMessageStructure{
	private static final long serialVersionUID = -3198245957575601442L;
	protected EmulatedCompositionMessageStructure(INakedClassifier owner,INakedElement element){
		super(owner, element);
	}
}

package org.opaeum.metamodel.core.internal.emulated;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedMessageStructure;

public abstract class EmulatedCompositionMessageStructure extends EmulatedClassifier implements INakedMessageStructure{
	private static final long serialVersionUID = -3198245957575601442L;
	protected EmulatedCompositionMessageStructure(INakedClassifier owner,INakedElement element){
		super(owner, element);
	}
}

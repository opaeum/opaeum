package org.opaeum.metamodel.usecases.internal;

import org.eclipse.uml2.uml.INakedUseCase;
import org.opaeum.metamodel.commonbehaviors.internal.NakedBehavioredClassifierImpl;

public class NakedUseCaseImpl extends NakedBehavioredClassifierImpl implements INakedUseCase{
	private static final long serialVersionUID = 3995799998866016599L;
	@Override
	public String getMetaClass() {
		return "useCase";
	}
}

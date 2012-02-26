package org.opaeum.metamodel.usecases.internal;

import org.opaeum.metamodel.commonbehaviors.internal.NakedBehavioredClassifierImpl;
import org.opaeum.metamodel.usecases.INakedUseCase;

public class NakedUseCaseImpl extends NakedBehavioredClassifierImpl implements INakedUseCase{
	private static final long serialVersionUID = 3995799998866016599L;
	@Override
	public String getMetaClass() {
		return "useCase";
	}
}

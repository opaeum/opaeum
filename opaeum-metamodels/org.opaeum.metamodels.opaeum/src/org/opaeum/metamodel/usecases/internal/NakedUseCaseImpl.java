package org.opaeum.metamodel.usecases.internal;

import org.opaeum.metamodel.core.internal.NakedClassifierImpl;
import org.opaeum.metamodel.usecases.INakedUseCase;

public class NakedUseCaseImpl extends NakedClassifierImpl implements INakedUseCase{
	private static final long serialVersionUID = 3995799998866016599L;
	@Override
	public String getMetaClass() {
		return "useCase";
	}
}

package org.opeum.metamodel.usecases.internal;

import org.opeum.metamodel.core.internal.NakedClassifierImpl;
import org.opeum.metamodel.usecases.INakedUseCase;

public class NakedUseCaseImpl extends NakedClassifierImpl implements INakedUseCase{
	private static final long serialVersionUID = 3995799998866016599L;
	@Override
	public String getMetaClass() {
		return "useCase";
	}
}

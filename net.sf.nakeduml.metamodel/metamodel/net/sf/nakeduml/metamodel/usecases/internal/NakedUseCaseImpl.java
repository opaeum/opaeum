package net.sf.nakeduml.metamodel.usecases.internal;

import net.sf.nakeduml.metamodel.core.internal.NakedClassifierImpl;
import net.sf.nakeduml.metamodel.usecases.INakedUseCase;

public class NakedUseCaseImpl extends NakedClassifierImpl implements INakedUseCase{
	private static final long serialVersionUID = 3995799998866016599L;
	@Override
	public String getMetaClass() {
		return "useCase";
	}
}

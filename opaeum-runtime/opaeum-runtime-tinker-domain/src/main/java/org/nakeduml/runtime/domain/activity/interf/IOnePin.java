package org.nakeduml.runtime.domain.activity.interf;

import java.util.List;

import org.nakeduml.runtime.domain.activity.SingleObjectToken;

public interface IOnePin<O> extends IPin<O, SingleObjectToken<O>, SingleObjectToken<O>> {
	@Override
	List<SingleObjectToken<O>> getInTokens();
	@Override
	List<SingleObjectToken<O>> getOutTokens();
}

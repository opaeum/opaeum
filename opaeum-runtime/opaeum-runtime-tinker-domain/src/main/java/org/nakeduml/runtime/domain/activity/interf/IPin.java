package org.nakeduml.runtime.domain.activity.interf;

import java.util.List;

import org.nakeduml.runtime.domain.activity.ObjectToken;

public interface IPin<O,IN extends ObjectToken<O>,OUT extends ObjectToken<O>> extends IObjectNode<O,IN,OUT>, IMultiplicityElement {
	List<IN> getInTokens();
	List<OUT> getOutTokens();
}

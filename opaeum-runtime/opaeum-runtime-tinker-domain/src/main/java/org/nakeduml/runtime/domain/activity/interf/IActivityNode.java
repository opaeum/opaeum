package org.nakeduml.runtime.domain.activity.interf;

import org.nakeduml.runtime.domain.activity.Token;

public interface IActivityNode<IN extends Token, OUT extends Token> {
	boolean mayContinue();
}

package org.nakeduml.runtime.domain.activity.interf;

import org.nakeduml.runtime.domain.activity.AbstractActivity;
import org.nakeduml.runtime.domain.activity.Token;

public interface IControlNode<IN extends Token, OUT extends Token> extends IActivityNode<IN, OUT> {
	AbstractActivity getActivity();
}

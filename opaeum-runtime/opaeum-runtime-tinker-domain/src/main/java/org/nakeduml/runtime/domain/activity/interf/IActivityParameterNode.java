package org.nakeduml.runtime.domain.activity.interf;

import org.nakeduml.runtime.domain.activity.AbstractActivity;
import org.nakeduml.runtime.domain.activity.ObjectToken;

public interface IActivityParameterNode<O, OUT extends ObjectToken<O>> extends IObjectNode<O, OUT, OUT> {
	AbstractActivity getActivity();
}

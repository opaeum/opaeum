package org.nakeduml.runtime.domain.activity.interf;

import org.nakeduml.runtime.domain.activity.SingleObjectToken;

public interface IOneOutActivityParameterNode<O> extends IOneActivityParameterNode<O>, IInActivityParameterNode<O, SingleObjectToken<O>> {

}

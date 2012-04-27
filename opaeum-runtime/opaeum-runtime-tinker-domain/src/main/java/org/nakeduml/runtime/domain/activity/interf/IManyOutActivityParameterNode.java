package org.nakeduml.runtime.domain.activity.interf;

import org.nakeduml.runtime.domain.activity.CollectionObjectToken;

public interface IManyOutActivityParameterNode<O> extends IManyActivityParameterNode<O>, IInActivityParameterNode<O, CollectionObjectToken<O>> {

}

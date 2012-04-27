package org.nakeduml.runtime.domain.activity.interf;

import java.util.Collection;

import org.nakeduml.runtime.domain.activity.CollectionObjectToken;

public interface IManyValuePin<O> extends IValuePin<O, CollectionObjectToken<O>> {
	Collection<O> getValue();
}

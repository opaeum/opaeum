package org.opaeum.runtime.domain;

public interface IObservation{
void onEntry(IExecutionElement<?> element);
void onExit(IExecutionElement<?> element);
}

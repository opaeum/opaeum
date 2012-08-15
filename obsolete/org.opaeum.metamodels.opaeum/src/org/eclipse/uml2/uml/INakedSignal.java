package org.eclipse.uml2.uml;
public interface INakedSignal extends INakedComplexStructure{
	Integer getListenerPoolSize();
	boolean isNotification();
}
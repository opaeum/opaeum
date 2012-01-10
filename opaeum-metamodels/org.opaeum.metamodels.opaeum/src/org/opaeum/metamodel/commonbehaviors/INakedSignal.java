package org.opaeum.metamodel.commonbehaviors;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedValueSpecification;
public interface INakedSignal extends INakedComplexStructure{
	Integer getListenerPoolSize();
	boolean isNotification();
}
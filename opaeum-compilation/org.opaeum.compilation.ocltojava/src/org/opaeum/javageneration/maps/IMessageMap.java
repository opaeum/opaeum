package org.opaeum.javageneration.maps;

import org.opaeum.java.metamodel.OJPathName;

public interface IMessageMap{

	public abstract OJPathName eventHandlerPath();

	public abstract String eventGeratorMethodName();

	public abstract String eventConsumerMethodName();

}

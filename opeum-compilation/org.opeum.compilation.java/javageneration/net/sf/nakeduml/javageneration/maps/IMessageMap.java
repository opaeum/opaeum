package org.opeum.javageneration.maps;

import org.opeum.java.metamodel.OJPathName;

public interface IMessageMap{

	public abstract OJPathName eventHandlerPath();

	public abstract String eventGeratorMethodName();

	public abstract String eventConsumerMethodName();

}

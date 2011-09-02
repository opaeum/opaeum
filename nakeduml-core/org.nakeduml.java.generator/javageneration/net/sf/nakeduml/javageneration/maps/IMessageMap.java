package net.sf.nakeduml.javageneration.maps;

import org.nakeduml.java.metamodel.OJPathName;

public interface IMessageMap{

	public abstract OJPathName eventHandlerPath();

	public abstract String eventGeratorMethodName();

	public abstract String eventConsumerMethodName();

}

package org.opaeum.javageneration.maps;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.eclipse.uml2.uml.Signal;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.JavaNameGenerator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class SignalMap extends ClassifierMap implements IMessageMap{
	Signal signal;
	private OJPathName handlerTypePath;
	private OJPathName receiverTypePath;
	public Signal getSignal(){
		return signal;
	}
	public SignalMap(OJUtil ojUtil,Signal signal){
		super(ojUtil, signal);
		this.signal = signal;
	}
	public OJPathName handlerTypePath(){
		if(this.handlerTypePath == null){
			this.handlerTypePath = ojUtil.packagePathname(signal.getNamespace()).getCopy().append(signal.getName() + "Handler");
		}
		return handlerTypePath;
	}
	public OJPathName receiverContractTypePath(){
		if(receiverTypePath == null){
			receiverTypePath = ojUtil.packagePathname(signal.getNamespace()).getCopy().append(signal.getName() + "Receiver");
		}
		return receiverTypePath;
	}
	public String receiveMethodName(){
		return JavaNameGenerator.toJavaName("receive" + NameConverter.capitalize(signal.getName()));
	}
	public OJPathName eventHandlerPath(){
		return javaTypePath().getHead().getCopy().append(signal.getName() + "Handler");
	}
	public String eventGeratorMethodName(){
		return JavaNameGenerator.toJavaName("generate" + signal.getName() + "Event");
	}
	public String eventConsumerMethodName(){
		return JavaNameGenerator.toJavaName("consume" + signal.getName() + "Event");
	}
}

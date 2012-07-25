package org.opaeum.javageneration.maps;

import org.eclipse.uml2.uml.Signal;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class SignalMap extends NakedClassifierMap implements IMessageMap{
	Signal signal;
	private OJPathName handlerTypePath;
	private OJPathName receiverTypePath;
	public Signal getSignal(){
		return signal;
	}
	public SignalMap(Signal signal){
		super(signal,null);
		this.signal = signal;
	}
	public OJPathName handlerTypePath(){
		if(this.handlerTypePath==null){
			this.handlerTypePath = OJUtil.packagePathname(signal.getNamespace()).getCopy().append(signal.getName() + "Handler");
		}
		return handlerTypePath;
	}
	public OJPathName receiverContractTypePath(){
		if(receiverTypePath==null){
			receiverTypePath=OJUtil.packagePathname(signal.getNamespace()).getCopy().append(signal.getName() + "Receiver");
		}
		return receiverTypePath;
	}
	public String receiveMethodName(){
		return "receive" +  NameConverter.capitalize(signal.getName());
	}
	public OJPathName eventHandlerPath(){
		return new OJPathName(signal.getName() + "Handler");
	}
	public String eventGeratorMethodName(){
		return "generate" + signal.getName() + "Event";
	}
	public String eventConsumerMethodName(){
		return "consume" + signal.getName() + "Event";
	}
}

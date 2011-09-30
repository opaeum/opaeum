package org.opeum.javageneration.maps;

import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.commonbehaviors.INakedSignal;

import org.opeum.java.metamodel.OJPathName;

public class SignalMap extends NakedClassifierMap implements IMessageMap{
	INakedSignal signal;
	public INakedSignal getSignal(){
		return signal;
	}
	public SignalMap(INakedSignal signal){
		super(signal);
		this.signal = signal;
	}
	public OJPathName handlerTypePath(){
		return OJUtil.packagePathname(signal.getNameSpace()).append(signal.getMappingInfo().getJavaName() + "Handler");
	}
	public OJPathName receiverContractTypePath(){
		return OJUtil.packagePathname(signal.getNameSpace()).append(signal.getMappingInfo().getJavaName() + "Receiver");
	}
	public String receiveMethodName(){
		return "receive" + signal.getMappingInfo().getJavaName().getCapped();
	}
	public OJPathName eventHandlerPath(){
		return new OJPathName(signal.getMappingInfo().getQualifiedJavaName() + "Handler");
	}
	public String eventGeratorMethodName(){
		return "generate"+signal.getName()+"Event";
	}
	public String eventConsumerMethodName(){
		return "consume" + signal.getMappingInfo().getJavaName().getCapped() +"Event";
	}
}

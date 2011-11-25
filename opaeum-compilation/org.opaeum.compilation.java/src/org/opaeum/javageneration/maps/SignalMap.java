package org.opaeum.javageneration.maps;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;

public class SignalMap extends NakedClassifierMap implements IMessageMap{
	INakedSignal signal;
	private OJPathName handlerTypePath;
	private OJPathName receiverTypePath;
	public INakedSignal getSignal(){
		return signal;
	}
	public SignalMap(INakedSignal signal){
		super(signal);
		this.signal = signal;
	}
	public OJPathName handlerTypePath(){
		if(this.handlerTypePath==null){
			this.handlerTypePath = OJUtil.packagePathname(signal.getNameSpace()).getCopy().append(signal.getMappingInfo().getJavaName() + "Handler");
		}
		return handlerTypePath;
	}
	public OJPathName receiverContractTypePath(){
		if(receiverTypePath==null){
			receiverTypePath=OJUtil.packagePathname(signal.getNameSpace()).getCopy().append(signal.getMappingInfo().getJavaName() + "Receiver");
		}
		return receiverTypePath;
	}
	public String receiveMethodName(){
		return "receive" + signal.getMappingInfo().getJavaName().getCapped();
	}
	public OJPathName eventHandlerPath(){
		return new OJPathName(signal.getMappingInfo().getQualifiedJavaName() + "Handler");
	}
	public String eventGeratorMethodName(){
		return "generate" + signal.getName() + "Event";
	}
	public String eventConsumerMethodName(){
		return "consume" + signal.getMappingInfo().getJavaName().getCapped() + "Event";
	}
}

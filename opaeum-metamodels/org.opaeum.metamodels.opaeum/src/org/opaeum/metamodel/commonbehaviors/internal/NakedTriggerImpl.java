package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.expressions.internal.analysis.Conformance;

import org.opaeum.metamodel.bpm.INakedDeadline;
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.internal.NakedElementImpl;

public class NakedTriggerImpl extends NakedElementImpl implements INakedTrigger{
	private static final long serialVersionUID = -8598466207353218533L;
	private INakedEvent event;
	private boolean isHumanTrigger;
	@Override
	public String getMetaClass(){
		return "trigger";
	}
	public void setEvent(INakedEvent event){
		if(!(event instanceof INakedDeadline || this.event == event)){
			// Deadlines could be reused
//			removeOwnedElement(this.event, true);
//			addOwnedElement(event);
//			event.setOwnerElement(this);
//			getRootObject().addDirectlyAccessibleElement(event);
		}
		this.event = event;
	}
	public INakedEvent getEvent(){
		return event;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		if(element instanceof INakedEvent){
			this.event = (INakedEvent) element;
		}
		super.addOwnedElement(element);
	}
	@Override
	public void addStereotype(INakedInstanceSpecification s){
		super.addStereotype(s);
		if(s.hasValueForFeature("isHumanTrigger")){
			isHumanTrigger = s.getFirstValueFor("isHumanTrigger").booleanValue();
		}
	}
	public boolean isHumanTrigger(){
		return isHumanTrigger;
	}
	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	public static List<INakedTypedElement> getParameters(Collection<INakedTrigger> triggers){
		INakedSignal signal = getMostGeneralSignal(triggers);
		if(signal != null){
			return (List) signal.getEffectiveAttributes();
		}
		List<INakedParameter> mostGeneralParameterList = getMostGeneralParameterList(triggers);
		if(mostGeneralParameterList != null){
			return (List) mostGeneralParameterList;
		}
		return Collections.emptyList();
	}
	private static List<INakedParameter> getMostGeneralParameterList(Collection<INakedTrigger> triggers){
		List<INakedParameter> params = null;
		for(INakedTrigger t:triggers){
			if(t.getEvent() instanceof INakedCallEvent){
				INakedOperation event = (INakedOperation) ((INakedCallEvent) t.getEvent()).getOperation();
				List<? extends INakedParameter> argumentParameters = event.getArgumentParameters();
				if(params == null){
					params = new ArrayList<INakedParameter>(argumentParameters);
				}else if(!generalize(params, argumentParameters)){
					params = null;
					break;
				}
			}else{
				params = null;
				break;
			}
		}
		return params;
	}
	private static boolean generalize(List<INakedParameter> params,List<? extends INakedParameter> argumentParameters){
		if(params.size() != argumentParameters.size()){
			return false;
		}
		for(int i = 0;i < params.size();i++){
			INakedParameter from = params.get(i);
			INakedParameter toParm = argumentParameters.get(i);
			if(from.getType() != null && toParm.getType() != null){
				if(Conformance.conformsTo(from.getType(), toParm.getType())){
					params.set(i, toParm);
				}else if(!Conformance.conformsTo(toParm.getType(), from.getType())){
					return false;
				}
			}
		}
		return true;
	}
	private static INakedSignal getMostGeneralSignal(Collection<INakedTrigger> triggers){
		INakedSignal signal = null;
		for(INakedTrigger t:triggers){
			if(t.getEvent() instanceof INakedSignalEvent){
				INakedSignalEvent signalEvent = (INakedSignalEvent) t.getEvent();
				if(signal == null || Conformance.conformsTo(signal, signalEvent.getSignal())){
					signal = signalEvent.getSignal();
				}else if(!Conformance.conformsTo(signalEvent.getSignal(), signal)){
					// Strictly speaking an invalid configuration, just ignore input from the signal
					signal = null;
					break;
				}
			}else{
				signal = null;
				break;
			}
		}
		return signal;
	}
}

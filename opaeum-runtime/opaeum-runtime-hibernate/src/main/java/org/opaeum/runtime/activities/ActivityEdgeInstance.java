package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActivityEdgeInstance<AE extends IActivityNodeContainerExecution,T extends ActivityToken<AE>> implements
		IActivityEdgeInstance<AE,T>{
	public AE group = null;
	public ActivityNodeActivation<AE,T> source = null;
	public ActivityNodeActivation<AE,T> target = null;
	public List<T> offeredTokens;
	private String id;
	Guard guard;
	@SuppressWarnings({"rawtypes","unchecked"})
	public ActivityEdgeInstance(String id,ActivityNodeActivation<AE,T> source,ActivityNodeActivation<AE,T> target){
		super();
		this.id = id;
		this.group = source.getGroup();
		this.source = source;
		this.target = target;
		offeredTokens = (List) group.getTokensOfferedTo(this);
		source.getOutgoingEdges().add(this);
		target.getIncomingEdges().add(this);
	}
	public String getId(){
		return id;
	}
	public Object evaluateGuard(){
		if(guard == null){
			return Boolean.TRUE;
		}else{
			return guard.evaluate();
		}
	}
	public void sendOffer(List<T> tokens){
		// Send an offer from the source to the target.
		// Keep the offered tokens until taken by the target.
		// (Note that any one edge should only be handling either all object
		// tokens or all control tokens.)
		for(int i = 0;i < tokens.size();i++){
			T token = tokens.get(i);
			token.offeredTo(this);
			getOfferedTokens().add(token);
		}
		this.target.receiveOffer();
	} // sendOffer
	public int countOfferedValues(){
		removeWithdrawnTokens();
		// Return the number of values being offered in object tokens.
		int count = 0;
		for(int i = 0;i < getOfferedTokens().size();i++){
			if(getOfferedTokens().get(i).getValue() != null){
				count++;
			}
		}
		return count;
	} // countOfferedValues
	public List<T> takeOfferedTokens(){
		removeWithdrawnTokens();
		// Take all the offered tokens and return them.
		List<T> tokens = new ArrayList<T>();
		for(int i = 0;i < getOfferedTokens().size();i++){
			tokens.add(getOfferedTokens().get(i));
		}
		return tokens;
	} // takeOfferedTokens
	public List<T> takeOfferedTokens(int maxCount){
		// Take all the offered tokens, up to the given maximum count of
		// non-null object tokens, and return them.
		List<T> result = new ArrayList<T>();
		removeWithdrawnTokens();
		Iterator<T> iterator = getOfferedTokens().iterator();
		while(iterator.hasNext() && maxCount > 0){
			T token = (T) iterator.next();
			if(token.getValue() != null){
				result.add(token);
				maxCount--;
			}
			token.unOffer();
			iterator.remove();
		}
		return result;
	}// takeOfferedTokens
	@SuppressWarnings({"rawtypes","unchecked"})
	public List<T> getOfferedTokens(){
		if(this.offeredTokens == null){
			offeredTokens = (List) group.getTokensOfferedTo(this);
		}
		return offeredTokens;
	}
	private void removeWithdrawnTokens(){
		List<T> ts = getOfferedTokens();
		Iterator<T> it = ts.iterator();
		while(it.hasNext()){
			T next = it.next();
			if(next.isWithdrawn()){
				next.unOffer();
				it.remove();
			}
		}
	}// removeWithdrawnTokens
	public boolean hasOffer(){
		removeWithdrawnTokens();
		return getOfferedTokens().size() > 0;
	}
	public boolean isObjectFlow(){
		for(T token:getOfferedTokens()){
			if(token.kind == TokenKind.OBJECT){
				return true;
			}
		}
		return false;
	}
	@Override
	public AE getBehaviorExecution(){
		return group;
	}
}

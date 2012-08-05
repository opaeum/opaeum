package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActivityEdgeInstance{
	public ActivityNodeContainerInstance group = null;
	public ActivityNodeActivation source = null;
	public ActivityNodeActivation target = null;
	public List<Token> offeredTokens;
	private String id;
	Guard guard;
	public ActivityEdgeInstance(String id,ActivityNodeActivation source,ActivityNodeActivation target){
		super();
		this.id = id;
		this.group = source.getGroup();
		this.source = source;
		this.target = target;
		offeredTokens = group.getTokensOfferedTo(this);
		source.getOutgoingEdges().add(this);
		target.getIncomingEdges().add(this);
	}
	public String getId(){
		return id;
	}
	public Object evaluateGuard(){
		if(guard==null){
			return Boolean.TRUE;
		}else{
			return guard.evaluate();
		}
	}
	public void sendOffer(List<Token> tokens){
		// Send an offer from the source to the target.
		// Keep the offered tokens until taken by the target.
		// (Note that any one edge should only be handling either all object
		// tokens or all control tokens.)
		for(int i = 0;i < tokens.size();i++){
			Token token = tokens.get(i);
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
	public List<Token> takeOfferedTokens(){
		removeWithdrawnTokens();
		// Take all the offered tokens and return them.
		List<Token> tokens = new ArrayList<Token>();
		for(int i = 0;i < getOfferedTokens().size();i++){
			tokens.add(getOfferedTokens().get(i));
		}
		return tokens;
	} // takeOfferedTokens
	public List<Token> takeOfferedTokens(int maxCount){
		// Take all the offered tokens, up to the given maximum count of
		// non-null object tokens, and return them.
		List<Token> result = new ArrayList<Token>();
		removeWithdrawnTokens();
		Iterator<Token> iterator = getOfferedTokens().iterator();
		while(iterator.hasNext() && maxCount > 0){
			Token token = (Token) iterator.next();
			if(token.getValue() != null){
				result.add(token);
				maxCount--;
			}
			token.unOffer();
			iterator.remove();
		}
		return result;
	}// takeOfferedTokens
	public List<Token> getOfferedTokens(){
		if(this.offeredTokens == null){
			offeredTokens = group.getTokensOfferedTo(this);
		}
		return offeredTokens;
	}
	private void removeWithdrawnTokens(){
		List<Token> ts = getOfferedTokens();
		Iterator<Token> it = ts.iterator();
		while(it.hasNext()){
			Token next = it.next();
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
		for(Token token:getOfferedTokens()){
			if(token.kind==TokenKind.OBJECT){
				return true;
			}
		}
		return false;
	}
}

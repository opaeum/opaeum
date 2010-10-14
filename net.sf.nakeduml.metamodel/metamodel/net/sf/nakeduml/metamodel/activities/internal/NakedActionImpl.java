package net.sf.nakeduml.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import nl.klasse.octopus.oclengine.IOclContext;

public abstract class NakedActionImpl extends NakedActivityNodeImpl implements INakedAction{
	private static final long serialVersionUID = 2697132216413111920L;
	public static final String META_CLASS = "action";
	private Collection<IOclContext> preConditions = new ArrayList<IOclContext>();
	private Collection<IOclContext> postConditions = new ArrayList<IOclContext>();
	@Override
	public String getMetaClass(){
		return "action";
	}
	@Override
	public boolean isImplicitFork(){
		if(super.isImplicitFork()){
			return true;
		}else{
			int pinOutputCount = 0;
			for(INakedElement e:getOwnedElements()){
				if(e instanceof INakedOutputPin){
					INakedOutputPin outputPin = (INakedOutputPin) e;
					boolean isException = outputPin.getLinkedTypedElement() instanceof INakedParameter
							&& ((INakedParameter) outputPin.getLinkedTypedElement()).isException();
					// exceptions are implicit decisions rather than implicit
					// forks
					if(isException && outputPin.getOutgoing().size() > 0){
						pinOutputCount++;
						if(pinOutputCount > 1){
							return true;
						}
					}
				}
			}
			return false;
		}
	}
	@Override
	public Set<INakedActivityEdge> getAllEffectiveOutgoing(){
		Set<INakedActivityEdge> result = new HashSet<INakedActivityEdge>(super.getOutgoing());
		Iterator children = getOwnedElements().iterator();
		while(children.hasNext()){
			INakedElement element = (INakedElement) children.next();
			if(element instanceof INakedActivityNode){
				result.addAll(((INakedActivityNode) element).getAllEffectiveOutgoing());
			}
		}
		return result;
	}
	@Override
	public Set<INakedActivityEdge> getAllEffectiveIncoming(){
		Set<INakedActivityEdge> result = new HashSet<INakedActivityEdge>(super.getIncoming());
		Iterator children = getOwnedElements().iterator();
		while(children.hasNext()){
			INakedElement element = (INakedElement) children.next();
			if(element instanceof INakedActivityNode){
				result.addAll(((INakedActivityNode) element).getAllEffectiveIncoming());
			}
		}
		return result;
	}
	public Collection<IOclContext> getPostConditions(){
		return this.postConditions;
	}
	public Collection<IOclContext> getPreConditions(){
		return this.preConditions;
	}
	public void addPostCondition(IOclContext postCondition){
		this.postConditions.add(postCondition);
	}
	public void addPreCondition(IOclContext preCondition){
		this.preConditions.add(preCondition);
	}
	public INakedClassifier getContext(){
		if(getActivity().getActivityKind().isSimpleSynchronousMethod()){
			return getActivity().getContext();
		}else{
			return getActivity();
		}
	}
	public void setPostConditions(Collection<IOclContext> postConditions){
		this.postConditions = postConditions;
	}
	public void setPreConditions(Collection<IOclContext> preConditions){
		this.preConditions = preConditions;
	}
}

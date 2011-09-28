package net.sf.nakeduml.metamodel.activities.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import nl.klasse.octopus.oclengine.IOclContext;

public class NakedActivityEdgeImpl extends NakedElementImpl implements INakedActivityEdge{
	private static final long serialVersionUID = 6408889822146373878L;
	private INakedActivityNode source;
	private INakedActivityNode target;
	private INakedValueSpecification guardExpression;
	private INakedValueSpecification weight;
	private Set<INakedActivityEdge> redefinedEdges = new HashSet<INakedActivityEdge>();
	private boolean isElse;
	public INakedValueSpecification getGuard(){
		return this.guardExpression;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature("isElseFlow")){
			isElse = Boolean.TRUE.equals(stereotype.getFirstValueFor("isElseFlow").getValue());
		}
	}
	@Override
	public void removeOwnedElement(INakedElement element,boolean recursively){
		super.removeOwnedElement(element, recursively);
		if(element == guardExpression){
			guardExpression = null;
		}else if(element == weight){
			weight = null;
		}
	};
	public boolean isElse(){
		return isElse || (guardExpression != null && guardExpression.getValue() == INakedActivityEdge.ELSE);
	}
	public void setGuard(INakedValueSpecification guardExpression){
		this.guardExpression = guardExpression;
	}
	public INakedActivityNode getSource(){
		return this.source;
	}
	public void setSource(INakedActivityNode source){
		if(this.source != null){
			this.source.removeOutgoing(this);
		}
		if(source != null){
			source.addOutgoing(this);
		}
		this.source = source;
	}
	public INakedActivityNode getTarget(){
		return this.target;
	}
	public void setTarget(INakedActivityNode target){
		if(this.target != null){
			this.target.removeIncoming(this);
		}
		if(target != null){
			target.addIncoming(this);
		}
		this.target = target;
	}
	@Override
	public String getMetaClass(){
		return "activityEdge";
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		Collection<INakedElement> results = super.getOwnedElements();
		if(this.guardExpression != null){
			results.add(this.guardExpression);
		}
		if(this.weight != null){
			results.add(weight);
		}
		return results;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
	}
	public INakedActivityNode getEffectiveTarget(){
		return getTarget();
	}
	public boolean isFromExceptionPin(){
		if(getSource() instanceof INakedOutputPin){
			INakedOutputPin o = (INakedOutputPin) getSource();
			if(o.getLinkedTypedElement() instanceof INakedParameter){
				INakedParameter p = (INakedParameter) o.getLinkedTypedElement();
				if(p.isException()){
					return true;
				}
			}
		}
		return false;
	}
	public INakedValueSpecification getWeight(){
		return weight;
	}
	public void setWeight(INakedValueSpecification weight){
		this.weight = weight;
	}
	public INakedActivityNode getEffectiveSource(){
		return getSource();
	}
	public INakedClassifier getContext(){
		return getTarget().getActivity().getContext();
	}
	public INakedActivity getOwningBehavior(){
		return getActivity();
	}
	public INakedActivity getActivity(){
		return getTarget().getActivity();
	}
	@Override
	public boolean hasGuard(){
		return !(getGuard() == null || this.getGuard().getValue() instanceof IOclContext
				&& ((IOclContext) this.getGuard().getValue()).getExpressionString().trim().equals("true") || Boolean.TRUE.equals(getGuard().getValue()) || ELSE.equals(getGuard().getValue()));
	}
	@Override
	public Set<INakedActivityEdge> getRedefinedEdges(){
		return redefinedEdges;
	}
	public void setRedefinedEdges(Set<INakedActivityEdge> redefinedEdges){
		this.redefinedEdges = redefinedEdges;
	}
}

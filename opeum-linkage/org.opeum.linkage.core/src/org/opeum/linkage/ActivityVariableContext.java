package org.opeum.linkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.model.IPackage;

import org.opeum.metamodel.activities.INakedActivity;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedReception;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedConstraint;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedTypedElement;
import org.opeum.metamodel.core.internal.emulated.MessageStructureImpl;
import org.opeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;

public final class ActivityVariableContext extends MessageStructureImpl{
	private static final long serialVersionUID = -4731155143691216024L;
	private final INakedBehavior activity;
	public ActivityVariableContext(INakedClassifier owner,INakedElement element,INakedBehavior activity){
		super(owner, element);
		this.activity = activity;
	}
	@Override
	public IPackage getRoot(){
		return activity.getNakedRoot();
	}
	@Override
	public List<? extends INakedConstraint> getOwnedRules(){
		return Collections.emptyList();
	}
	@Override
	public boolean isPersistent(){
		return false;
	}
	@Override
	public List<INakedProperty> getOwnedAttributes(){
		List<INakedProperty> result = new ArrayList<INakedProperty>();
		addVariables(result, element);
		return result;
	}
	private void addVariables(List<INakedProperty> result,INakedElement element){
		Collection<? extends INakedTypedElement> variables = null;
		if(element instanceof INakedActivity){
			INakedActivity activity = (INakedActivity) element;
			HashSet<INakedTypedElement> localVariables = new HashSet<INakedTypedElement>(activity.getVariables());
			localVariables.addAll(activity.getOwnedParameters());
			variables = localVariables;
		}
		if(variables != null){
			for(INakedTypedElement var:variables){
				result.add(new TypedElementPropertyBridge(activity, var));
			}
		}
		if(!(element == null || element instanceof INakedActivity)){
			addVariables(result, (INakedElement) element.getOwnerElement());
		}
	}
	@Override
	public boolean hasComposite(){
		return false;
	}
	@Override
	public INakedProperty getEndToComposite(){
		return null;
	}
	@Override
	public void setEndToComposite(INakedProperty artificialProperty){
	}
	@Override
	public void removeObsoleteArtificialProperties(){
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getImplementationCode(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<INakedProperty> getDirectlyImplementedAttributes(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<INakedOperation> getDirectlyImplementedOperations(){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<? extends INakedReception> getDirectlyImplementedReceptions(){
		// TODO Auto-generated method stub
		return null;
	}
}
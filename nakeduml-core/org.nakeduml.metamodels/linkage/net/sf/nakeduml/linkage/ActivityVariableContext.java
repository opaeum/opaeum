package net.sf.nakeduml.linkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.oclengine.IOclContext;

public final class ActivityVariableContext extends MessageStructureImpl{
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
}
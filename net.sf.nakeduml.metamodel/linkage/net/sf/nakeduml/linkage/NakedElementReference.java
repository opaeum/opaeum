package net.sf.nakeduml.linkage;

import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.oclengine.IModelElementReference;
import nl.klasse.octopus.oclengine.internal.ModelElementReferenceImpl;

public class NakedElementReference implements IModelElementReference{
	private ModelElementReferenceImpl delegate;
	public NakedElementReference(ModelElementReferenceImpl context){
		this.delegate = context;
		if(context.getModelElement() instanceof GuardedFlow){
			GuardedFlow t=(GuardedFlow)context.getModelElement();
			this.delegate=new ModelElementReferenceImpl(context.getSurroundingClassifier(),t.getOwningBehavior());
		}else if(context.getModelElement() instanceof INakedOpaqueAction){
			INakedOpaqueAction t=(INakedOpaqueAction)context.getModelElement();
			this.delegate=new ModelElementReferenceImpl(context.getSurroundingClassifier(),t.getMessageStructure());
		}
	}
	public IModelElement getModelElement(){
		return delegate.getModelElement();
	}
	public IClassifier getSurroundingClassifier(){
		return delegate.getSurroundingClassifier();
	}
	public boolean isInheritedFeature(){
		return delegate.isInheritedFeature();
	}
}

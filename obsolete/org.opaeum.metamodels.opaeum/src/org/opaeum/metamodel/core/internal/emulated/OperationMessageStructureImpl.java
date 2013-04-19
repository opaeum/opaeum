package org.opaeum.metamodel.core.internal.emulated;

import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClass;
import nl.klasse.octopus.oclengine.IOclContext;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedConstraint;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.INakedParameter;
import org.eclipse.uml2.uml.INakedProperty;
import org.eclipse.uml2.uml.INakedTypedElement;
import org.opaeum.metamodel.core.internal.NonInverseArtificialProperty;

public class OperationMessageStructureImpl extends EmulatedCompositionMessageStructure implements IClass{
	private static final long serialVersionUID = 5866143223294264619L;
	private INakedOperation oper;
	private INakedProperty endToSelf;
	public OperationMessageStructureImpl(INakedClassifier owner,INakedOperation oper){
		super(owner, oper);
		this.oper = oper;
		reinitialize();
	}
	public OperationMessageStructureImpl(INakedOperation nop){
		this(nop.getOwner(), nop);
	}
	@Override
	public IAttribute findAttribute(String attName){
		for(INakedProperty a:getEffectiveAttributes()){
			if(a.getName().equals(attName)){
				return a;
			}
		}
		return null;
	}
	@Override
	public void reinitialize(){
		super.reinitialize();
		for(INakedElement p:oper.getOwnedElements()){
			if(p instanceof INakedParameter){
				addOwnedElement(new TypedElementPropertyBridge(this, (INakedTypedElement) p));
			}
		}
		removeOwnedElement(endToSelf, false);
		endToSelf=new NonInverseArtificialProperty("self", oper.getOwner(),true);
		addOwnedElement(endToSelf);		
		super.owner=oper.getOwner();
	}
	
	public List<IOclContext> getDefinitions(){
		return Collections.emptyList();
	}
	public List<INakedConstraint> getOwnedRules(){
		return Collections.emptyList();
	}
	public boolean isPersistent(){
		return oper.isLongRunning();
	}
	public INakedOperation getOperation(){
		return oper;
	}
	public INakedProperty getEndToSelf(){
		return endToSelf;
	}
}

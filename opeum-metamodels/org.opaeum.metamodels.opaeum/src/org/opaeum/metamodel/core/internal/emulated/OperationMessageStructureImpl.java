package org.opeum.metamodel.core.internal.emulated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.model.IClass;
import nl.klasse.octopus.oclengine.IOclContext;

import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedConstraint;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedParameter;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedTypedElement;

public class OperationMessageStructureImpl extends EmulatedCompositionMessageStructure implements IClass{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2997263407528904470L;
	private INakedOperation oper;
	public OperationMessageStructureImpl(INakedClassifier owner,INakedOperation oper){
		super(owner, oper);
		this.oper = oper;
	}
	public OperationMessageStructureImpl(INakedOperation nop){
		this(nop.getOwner(), nop);
	}
	@Override
	public List<INakedProperty> getOwnedAttributes(){
		if(attributes == null){
			attributes = new ArrayList<INakedProperty>();
			for(INakedElement p:oper.getOwnedElements()){
				if(p instanceof INakedParameter){
					attributes.add(new TypedElementPropertyBridge(this, (INakedTypedElement) p));
				}
			}
			attributes.add(getEndToComposite());
		}
		return attributes;
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
}

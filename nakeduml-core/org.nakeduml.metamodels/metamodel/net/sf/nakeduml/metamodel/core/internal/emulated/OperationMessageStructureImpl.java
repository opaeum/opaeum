package net.sf.nakeduml.metamodel.core.internal.emulated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.model.IClass;
import nl.klasse.octopus.oclengine.IOclContext;

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

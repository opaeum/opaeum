package net.sf.nakeduml.metamodel.core.internal.emulated;

import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public abstract class EmulatedCompositionMessageStructure extends MessageStructureImpl implements ICompositionParticipant{
	private INakedProperty endToComposite;
	public EmulatedCompositionMessageStructure(INakedClassifier owner,INakedElement element){
		super(owner, element);
	}
	@Override
	public boolean hasComposite(){
		return true;
	}
	@Override
	public INakedProperty getEndToComposite(){
		return this.endToComposite;
	}
	@Override
	public void setEndToComposite(INakedProperty artificialProperty){
		this.endToComposite = artificialProperty;
	}
}
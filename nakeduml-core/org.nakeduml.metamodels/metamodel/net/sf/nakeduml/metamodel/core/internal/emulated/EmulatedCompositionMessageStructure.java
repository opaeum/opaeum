package net.sf.nakeduml.metamodel.core.internal.emulated;

import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.ArtificialProperty;
import nl.klasse.octopus.stdlib.IOclLibrary;

public abstract class EmulatedCompositionMessageStructure extends MessageStructureImpl implements ICompositionParticipant{
	private INakedProperty endToComposite;
	private IOclLibrary lib;
	public EmulatedCompositionMessageStructure(INakedClassifier owner,INakedElement element, IOclLibrary lib){
		super(owner, element);
		this.lib=lib;
	}
	@Override
	public boolean hasComposite(){
		return true;
	}
	@Override
	public INakedProperty getEndToComposite(){
		if(endToComposite==null){
			endToComposite=new ArtificialProperty(this, lib).getOtherEnd();
		}
		return this.endToComposite;
	}
	@Override
	public void setEndToComposite(INakedProperty artificialProperty){
		this.endToComposite = artificialProperty;
	}
}
package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActionImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;

public class NakedCreateObjectActionimpl extends NakedActionImpl implements INakedCreateObjectAction {

	private static final long serialVersionUID = -7769342738149726492L;
	INakedClassifier classifier;
	INakedOutputPin result;
	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> results =super.getOwnedElements();
		return results;
	}
	public Collection<INakedInputPin> getInput(){
		return new HashSet<INakedInputPin>();
	}
	public Collection<INakedOutputPin> getOutput() {
		Collection<INakedOutputPin> results =new HashSet<INakedOutputPin>();
		if(this.result!=null){
			results.add(this.result);
		}
		return results;
	}

	public INakedClassifier getClassifier() {
		return this.classifier;
	}
	public void setClassifier(INakedClassifier classifier) {
		this.classifier = classifier;
	}
	public INakedOutputPin getResult() {
		return this.result;
	}
	public void setResult(INakedOutputPin result) {
		removeOwnedElement(result);
		this.result = result;
	}
}

package org.opaeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedCreateObjectAction;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.opaeum.metamodel.activities.internal.NakedActionImpl;

public class NakedCreateObjectActionimpl extends NakedActionImpl implements INakedCreateObjectAction{
	private static final long serialVersionUID = -7769342738149726492L;
	INakedClassifier classifier;
	INakedOutputPin result;
	@Override
	public Collection<INakedElement> getOwnedElements(){
		Collection<INakedElement> results = super.getOwnedElements();
		return results;
	}
	public Collection<INakedInputPin> getInput(){
		return new HashSet<INakedInputPin>();
	}
	public Collection<INakedOutputPin> getOutput(){
		Collection<INakedOutputPin> results = new HashSet<INakedOutputPin>();
		if(this.result != null){
			results.add(this.result);
		}
		return results;
	}
	public INakedClassifier getClassifier(){
		return this.classifier;
	}
	public void setClassifier(INakedClassifier classifier){
		this.classifier = classifier;
	}
	public INakedOutputPin getResult(){
		return this.result;
	}
	public void setResult(INakedOutputPin result){
		if(this.result != result){
			replacePin(this.result, result);
			this.result = result;
		}
	}
}

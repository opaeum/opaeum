package net.sf.nakeduml.metamodel.activities.internal;

import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.internal.NakedTypedElementImpl;
import nl.klasse.octopus.model.VisibilityKind;

public class NakedActivityVariable extends NakedTypedElementImpl implements INakedActivityVariable{
	public INakedClassifier getOwner(){
		return getActivity();
	}
	public INakedActivity getActivity(){
		return (INakedActivity) getOwnerElement();
	}
	public String getSignature(){
		return "";
	}
	public boolean hasClassScope(){
		return false;
	}
	public boolean isAggregate(){
		return false;
	}
	public boolean isComposite(){
		return false;
	}
	public boolean isDerived(){
		return false;
	}
	public VisibilityKind getVisibility(){
		return VisibilityKind.PUBLIC;
	}
	public boolean isInverse(){
		return false;
	}
	public boolean isReadOnly(){
		return false;
	}
	public boolean isRequired(){
		return false;
	}
	public void setVisibility(VisibilityKind kind){
	}
	public boolean isOclDef(){
		return false;
	}
}

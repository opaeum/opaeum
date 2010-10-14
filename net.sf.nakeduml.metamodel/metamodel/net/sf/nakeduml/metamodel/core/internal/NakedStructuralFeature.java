package net.sf.nakeduml.metamodel.core.internal;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import nl.klasse.octopus.model.IStructuralFeature;
import nl.klasse.octopus.model.VisibilityKind;

public class NakedStructuralFeature extends NakedTypedElementImpl implements IStructuralFeature{
	private static final long serialVersionUID = -7706816871787624121L;
	private VisibilityKind visibility;
	private boolean inverse;
	private boolean derived;
	private boolean readOnly;
	private boolean hasClassScope;
	private boolean composite;
	public VisibilityKind getVisibility(){
		return visibility;
	}
	public void setVisibility(VisibilityKind visibility){
		this.visibility = visibility;
	}
	@Override
	public String getMetaClass(){
		return "structuralFeature";
	}
	public boolean isInverse(){
		return inverse;
	}
	public void setInverse(boolean inverse){
		this.inverse = inverse;
	}
	public boolean isDerived(){
		return derived;
	}
	public void setDerived(boolean derived){
		this.derived = derived;
	}
	public boolean isReadOnly(){
		return readOnly;
	}
	public boolean isRequired(){
		return getNakedMultiplicity().getLower() > 1;
	}
	public INakedClassifier getOwner(){
		INakedElementOwner owner = getOwnerElement();
		if(owner instanceof INakedClassifier){
			return (INakedClassifier) owner;
		}else{
			return null;
		}
	}
	public boolean isOclDef(){
		return false;
	}
	public boolean hasClassScope(){
		return hasClassScope;
	}
	public boolean isAggregate(){
		return false;
	}
	public boolean isComposite(){
		return composite;
	}
	public void setComposite(boolean composite){
		this.composite = composite;
	}
	public void setHasClassScope(boolean hasClassScope){
		this.hasClassScope = hasClassScope;
	}
	public void setReadOnly(boolean readOnly){
		this.readOnly = readOnly;
	}
	public String getSignature(){
		String classScope = " ";
		if(this.hasClassScope()){
			classScope = " $ ";
		}
		return getVisibility() + classScope + getName() + " : " + (getType() == null ? "<null>" : getType().getName());
	}
}

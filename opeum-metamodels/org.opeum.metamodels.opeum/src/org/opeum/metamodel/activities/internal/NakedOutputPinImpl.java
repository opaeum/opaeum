package org.opeum.metamodel.activities.internal;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.core.INakedMultiplicity;
import org.opeum.metamodel.core.INakedParameter;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.VisibilityKind;
public class NakedOutputPinImpl extends NakedPinImpl implements INakedOutputPin {
	private static final long serialVersionUID = 2680974388304633167L;
	public boolean treatAsAttribute() {
		return true;
	}
	@Override
	public INakedMultiplicity getNakedMultiplicity() {
		if (super.multiplicity == null && getLinkedTypedElement() != null) {
			return getLinkedTypedElement().getNakedMultiplicity();
		} else {
			return super.multiplicity;
		}
	}
	@Override
	public IClassifier getType() {
		if (super.type==null && getLinkedTypedElement()!=null){
			return this.getLinkedTypedElement().getType();
		} else {
			return super.type;
		}
	}
	public boolean isException() {
		if(getLinkedTypedElement() instanceof INakedParameter ){
			return ((INakedParameter)getLinkedTypedElement()).isException();
		}
		return false;
	}
	public boolean isInverse(){
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isManyToMany(){
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isManyToOne(){
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isOneToMany(){
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isOneToOne(){
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isReadOnly(){
		// TODO Auto-generated method stub
		return false;
	}
	public void setVisibility(VisibilityKind kind){
		// TODO Auto-generated method stub
		
	}
	public VisibilityKind getVisibility(){
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isOclDef(){
		// TODO Auto-generated method stub
		return false;
	}
	public boolean hasClassScope(){
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isAggregate(){
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isComposite(){
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isDerived(){
		// TODO Auto-generated method stub
		return false;
	}
}

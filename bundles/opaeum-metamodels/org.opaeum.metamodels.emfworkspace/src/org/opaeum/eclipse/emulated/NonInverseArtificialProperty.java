package org.opaeum.eclipse.emulated;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.emf.workspace.EmfWorkspace;

public class NonInverseArtificialProperty extends AbstractEmulatedProperty {
	private InverseArtificialProperty otherEnd;
	private String name;
	@SuppressWarnings("restriction")
	public NonInverseArtificialProperty(InverseArtificialProperty otherEnd,String name){
		super((Classifier) otherEnd.getType(), otherEnd.getOriginalElement());
		this.otherEnd = otherEnd;
		this.name=name;
		setType(otherEnd.getOwner());
	}
	@Override
	public int getUpper(){
		return 1;
	}
	public Classifier getOwner(){
		return (Classifier) super.getOwner();
	}
	@Override
	public String getName(){
		return name;
	}
	@Override
	public String getId(){
		return EmfWorkspace.getId(getOriginalElement()) + "@" + getName();
	}
	@Override
	public boolean equals(Object other){
		if(other == this){
			return true;
		}else if(other instanceof NonInverseArtificialProperty){
			NonInverseArtificialProperty ap = (NonInverseArtificialProperty) other;
			return ap.originalElement.equals(originalElement) && ap.owner.equals(owner) && ap.getName().equals(getName());
		}else{
			return super.equals(other);
		}
	}
	@Override
	public Property getOtherEnd(){
		return otherEnd;
	}
	@Override
	public boolean shouldEmulate(){
		return true;
	}
}

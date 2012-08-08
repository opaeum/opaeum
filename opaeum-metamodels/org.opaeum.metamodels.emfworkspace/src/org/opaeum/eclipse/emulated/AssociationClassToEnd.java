package org.opaeum.eclipse.emulated;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.emf.workspace.EmfWorkspace;

public class AssociationClassToEnd extends AbstractEmulatedProperty{
	private Property property;
	private AssociationClassToEnd otherEnd;
	public AssociationClassToEnd(Property property){
		super((Classifier) property.getAssociation(), property);
		this.property = property;
	}
	@Override
	public boolean isComposite(){
		return property.isComposite();
	}
	@Override
	public int getUpper(){
		return 1;
	}
	@Override
	public int getLower(){
		return 1;
	}
	@Override
	public String getName(){
		return property.getName();
	}
	@Override
	public boolean isOrdered(){
		return false;
	}
	@Override
	public boolean isUnique(){
		return true;
	}
	@Override
	public Type getType(){
		return property.getType();
	}
	@Override
	public String getId(){
		return EmfWorkspace.getId(property.getAssociation()) + "@" + EmfWorkspace.getId(property);
	}
	@Override
	public boolean shouldEmulate(){
		return EmfAssociationUtil.isClass(property.getAssociation());
	}
	public void setOtherEnd(AssociationClassToEnd otherEnd){
		this.otherEnd=otherEnd;
	}
	@Override
	public Property getOtherEnd(){
		return otherEnd;
	}

}

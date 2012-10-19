package org.opaeum.eclipse.commands;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;

public class LazySetTagValueCommand extends ApplyStereotypeCommand{
	private Stereotype stereotype;
	private Object oldValue;
	private String tagName;
	private Object newValue;
	public LazySetTagValueCommand(Element element,String profileName,String stereotypeName, String tagName, Object value){
		super(element, false, getStereotype(element, profileName, stereotypeName));
		if(super.stereotypes.size() == 1){
			this.stereotype = super.stereotypes.get(0);
			if(element.isStereotypeApplied(stereotype)){
				oldValue=element.getValue(stereotype, tagName);
			}
		}
		this.tagName=tagName;
		this.newValue=value;
		this.element=element;
	}
	private static Stereotype[] getStereotype(Element element2,String profileName,String stereotypeName){
		Profile p = ProfileApplier.getProfile(element2, profileName);
		if(p != null){
			return new Stereotype[]{p.getOwnedStereotype(stereotypeName)};
		}
		return new Stereotype[0];
	}
	private Element element;
	@Override
	public boolean canExecute(){
		return stereotype!=null;
	}
	public void execute(){
		if(stereotype!=null && !element.isStereotypeApplied(stereotype)){
			super.execute();
		}
		element.setValue(stereotype, tagName, newValue);
	}
	@Override
	public boolean canUndo(){
		return true;
	}
	@Override
	public void undo(){
		super.undo();
		if(oldValue!=null){
			element.setValue(stereotype, tagName, oldValue);
		}
	}
	public void redo(){
		execute();
	}
}

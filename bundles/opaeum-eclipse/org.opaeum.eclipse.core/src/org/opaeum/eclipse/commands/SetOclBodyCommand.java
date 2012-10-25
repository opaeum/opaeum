package org.opaeum.eclipse.commands;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.NamedElement;

public class SetOclBodyCommand extends CompoundCommand{
	protected static final String DEFAULT_TEXT = "Type expression here";
	public static SetOclBodyCommand create(EditingDomain ed, NamedElement owner, EStructuralFeature bodiesFeature,EStructuralFeature languagesFeature, String expression){
		return new SetOclBodyCommand(ed,owner,bodiesFeature,languagesFeature,expression);
	}
	@SuppressWarnings("unchecked")
	public SetOclBodyCommand(EditingDomain ed, NamedElement owner,EStructuralFeature bodiesFeature,EStructuralFeature languagesFeature, String expression){
		super("Set Ocl Expression");
		EList<String> bodies = (EList<String>) owner.eGet(bodiesFeature);
		EList<String> languages = (EList<String>) owner.eGet(languagesFeature);
		if(languages.isEmpty() || !(languages.contains("OCL") || languages.contains("ocl"))){
			super.append( AddCommand.create(ed, owner, languagesFeature, "OCL"));
		}
		int bodyIndex = 0;
		for(int i = 0;i < languages.size();i++){
			if(languages.get(i).equalsIgnoreCase("ocl")){
				bodyIndex=i;
				break;
			}else if(i == languages.size()-1){
				//Add command has been added above
				bodyIndex=languages.size();
			}
		}
		boolean bodyCreated=false;
		for(int i=bodies.size();i<=bodyIndex;i++){
			if(i==bodyIndex){
				//body needs to be added
				super.append( AddCommand.create(ed, owner, bodiesFeature, expression));
				bodyCreated=true;
			}else{
				super.append( AddCommand.create(ed, owner, bodiesFeature, languages.get(i) +" bla bla"));
			}
		}
		if(!bodyCreated){
			super.append( SetCommand.create(ed, owner, bodiesFeature, expression,bodyIndex));
		}
	}
}

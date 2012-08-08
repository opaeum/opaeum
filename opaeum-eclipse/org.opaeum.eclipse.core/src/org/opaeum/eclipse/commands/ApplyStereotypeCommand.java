package org.opaeum.eclipse.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.LibraryImporter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class ApplyStereotypeCommand extends AbstractCommand{
	private Element element;
	private Collection<Stereotype> stereotypes;
	Collection<Stereotype> applied = new HashSet<Stereotype>();
	Collection<Profile> appliedProfile = new HashSet<Profile>();
	private boolean stereotypeIsKeyword;
	public ApplyStereotypeCommand(Element element,boolean stereotypeIsKeyword,Stereotype...stereotype){
		this.element = element;
		this.stereotypes = Arrays.asList(stereotype);
		this.stereotypeIsKeyword = stereotypeIsKeyword;
	}
	public ApplyStereotypeCommand(Element element,Stereotype...stereotype){
		this(element, true, stereotype);
	}
	@Override
	public boolean canExecute(){
		return true;
	}
	public void execute(){
		for(Stereotype stereotype:stereotypes){
			if(!element.getAppliedStereotypes().contains(stereotype)){
				Element owner = getOwner();
				if(owner instanceof org.eclipse.uml2.uml.Package){
					org.eclipse.uml2.uml.Package pkg = (org.eclipse.uml2.uml.Package) owner;
					if(!pkg.getAllAppliedProfiles().contains(stereotype.getProfile())){
						pkg.applyProfile(stereotype.getProfile());
					}
					applied.add(stereotype);
					element.applyStereotype(stereotype);
					if(!(element instanceof Pin) && element instanceof NamedElement && owner instanceof Namespace){
						NamedElement ne = (NamedElement) element;
						if(stereotypeIsKeyword && ne.getName().startsWith(ne.eClass().getName())
								&& Character.isDigit(ne.getName().charAt(ne.getName().length() - 1))){
							String keyWord = stereotype.getName();
							setUniqueName(keyWord, ne);
						}
					}
					implementInterfacesIfNecessary(element);
				}
			}
		}
	}
	private Element getOwner(){
		Element owner = element;
		while(owner.getOwner() != null){
			owner = owner.getOwner();
		}
		return owner;
	}
	@Override
	public boolean canUndo(){
		return true;
	}
	@Override
	public void undo(){
		// TODO unapply profiles
		for(Stereotype stereotype:applied){
			element.unapplyStereotype(stereotype);
		}
		for(Profile profile:appliedProfile){
			Element owner = getOwner();
			if(owner instanceof org.eclipse.uml2.uml.Package){
				((org.eclipse.uml2.uml.Package) owner).unapplyProfile(profile);
			}
		}
	}
	private void implementInterfacesIfNecessary(Object newValue){
		if(newValue instanceof Signal){
			implementAppropriateInterface((Element) newValue, StereotypeNames.NOTIFICATION, StereotypeNames.PKG_DOCUMENT);
		}
		if(newValue instanceof Class){
			Class c = (Class) newValue;
			if(c.eClass().equals(UMLPackage.eINSTANCE.getClass_())){
				implementAppropriateInterface((Element) newValue, StereotypeNames.BUSINESS_ROLE, StereotypeNames.PKG_ORGANIZATION);
				implementAppropriateInterface((Element) newValue, StereotypeNames.BUSINESS_DOCUMENT, StereotypeNames.PKG_DOCUMENT);
			}
		}
		if(newValue instanceof Interface){
			implementAppropriateInterface((Element) newValue, StereotypeNames.BUSINESS_SERVICE, StereotypeNames.PKG_ORGANIZATION);
		}
		if(newValue instanceof Component){
			implementAppropriateInterface((Element) newValue, StereotypeNames.BUSINESS_COMPONENT, StereotypeNames.PKG_ORGANIZATION);
			implementAppropriateInterface((Element) newValue, StereotypeNames.BUSINESS, StereotypeNames.PKG_ORGANIZATION);
		}
		if(newValue instanceof Actor){
			implementAppropriateInterface((Element) newValue, StereotypeNames.BUSINESS_ACTOR, StereotypeNames.PKG_ORGANIZATION);
		}
	}
	private static void implementAppropriateInterface(Element ass,String stereotypeName,String name){
		if(ass instanceof Classifier){
			Classifier specific = (Classifier) ass;
			if(StereotypesHelper.hasStereotype(specific, stereotypeName)){
				Model lib = LibraryImporter.importLibraryIfNecessary(specific.getModel(), StereotypeNames.OPAEUM_BPM_LIBRARY);
				Package pkg = lib.getNestedPackage(name);
				Classifier general = (Classifier) pkg.getOwnedType("I" + stereotypeName);
				if(general != null){
					if(general instanceof Interface){
						if(specific instanceof BehavioredClassifier){
							maybeRealizeInterface((BehavioredClassifier) specific, (Interface) general);
						}else if(specific instanceof Interface){
							maybeGeneralize(specific, general);
						}
					}else{
						maybeGeneralize(specific, general);
					}
				}
			}
		}
	}
	private static void maybeGeneralize(Classifier specific,Classifier general){
		boolean found = false;
		for(Generalization g:specific.getGeneralizations()){
			if(g.getGeneral() == general){
				found = true;
			}
		}
		if(found == false){
			specific.createGeneralization(general);
		}
	}
	private static void maybeRealizeInterface(BehavioredClassifier behavioredClassifier,Interface general2){
		boolean found = false;
		for(InterfaceRealization g:behavioredClassifier.getInterfaceRealizations()){
			if(g.getContract() == general2){
				found = true;
			}
		}
		if(found == false){
			behavioredClassifier.createInterfaceRealization("isA" + general2.getName(), general2);
		}
	}
	private void setUniqueName(String stereotypeName,NamedElement ne){
		int lastNumber = 0;
		List<NamedElement> members = new ArrayList<NamedElement>();
		if(ne.getNamespace() == null){
			if(ne.getOwner() != null)
				for(Element element:ne.getOwner().getOwnedElements()){
					if(element instanceof NamedElement){
						members.add((NamedElement) element);
					}
				}
			else{
			}
		}else{
			members.addAll(ne.getNamespace().getMembers());
		}
		for(NamedElement namedElement:members){
			if(namedElement != ne && namedElement.getName() != null && namedElement.getName().contains(stereotypeName)){
				String number = namedElement.getName().substring(stereotypeName.length());
				try{
					int currentNumber = Integer.parseInt(number);
					if(currentNumber > lastNumber){
						lastNumber = currentNumber;
					}
				}catch(Exception e){
				}
			}
		}
		ne.setName(stereotypeName + (lastNumber + 1));
	}
	private String getSignificantKeyWord(NamedElement ne){
		String keyWord = null;
		for(Stereotype s:ne.getAppliedStereotypes()){
			if(!s.getName().equals(ne.eClass().getName()) && !s.getName().equals("Entity")){
				keyWord = s.getName();
				break;
			}
		}
		return keyWord;
	}
	public void redo(){
		execute();
	}
}

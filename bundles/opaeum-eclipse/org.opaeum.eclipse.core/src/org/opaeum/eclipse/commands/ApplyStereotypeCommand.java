package org.opaeum.eclipse.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil.StereotypeApplicationHelper;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.LibraryImporter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class ApplyStereotypeCommand extends AbstractCommand{
	protected Element element;
	protected List<Stereotype> stereotypes;
	Collection<Stereotype> applied = new HashSet<Stereotype>();
	Collection<Profile> appliedProfile = new HashSet<Profile>();
	private boolean stereotypeIsKeyword;
	private String[] stereotypeNames;
	public ApplyStereotypeCommand(Element element,boolean stereotypeIsKeyword,Stereotype...stereotype){
		this.element = element;
		this.stereotypes = stereotype == null ? new ArrayList<Stereotype>() : Arrays.asList(stereotype);
		this.stereotypeIsKeyword = stereotypeIsKeyword;
	}
	public ApplyStereotypeCommand(Element element,Stereotype...stereotype){
		this(element, true, stereotype);
	}
	public ApplyStereotypeCommand(Element owner,String...stereotypeNames){
		this(owner, findStereotypes(owner, stereotypeNames));
		this.stereotypeNames = stereotypeNames;
	}
	public ApplyStereotypeCommand(Element owner,boolean stereotypeIsKeyWord,String...stereotypeNames){
		this(owner, findStereotypes(owner, stereotypeNames));
		this.stereotypeNames = stereotypeNames;
		this.stereotypeIsKeyword = stereotypeIsKeyWord;
	}
	private static Stereotype[] findStereotypes(Element owner,String[] stereotypeNames){
		List<Stereotype> result = new ArrayList<Stereotype>();
		if(owner!=null && owner.eResource() != null){
			for(String string:stereotypeNames){
				inner:for(Resource resource:owner.eResource().getResourceSet().getResources()){
					if(resource.getContents().size() > 0 && resource.getContents().get(0) instanceof Profile){
						Profile p = (Profile) resource.getContents().get(0);
						Stereotype ownedStereotype = p.getOwnedStereotype(string);
						if(ownedStereotype != null){
							result.add(ownedStereotype);
							break inner;
						}
					}
				}
			}
		}
		return result.toArray(new Stereotype[result.size()]);
	}
	@Override
	public boolean canExecute(){
		return true;
	}
	public void execute(){
		if(stereotypes.isEmpty()){
			stereotypes = Arrays.asList(findStereotypes(element, stereotypeNames));
		}
		for(Stereotype stereotype:stereotypes){
			if(!element.getAppliedStereotypes().contains(stereotype)){
				Element owner = getOwner();
				if(owner instanceof org.eclipse.uml2.uml.Package){
					org.eclipse.uml2.uml.Package pkg = (org.eclipse.uml2.uml.Package) owner;
					if(!pkg.getAllAppliedProfiles().contains(stereotype.getProfile())){
						pkg.applyProfile(stereotype.getProfile());
					}
					applied.add(stereotype);
					EObject sa = StereotypeApplicationHelper.INSTANCE.applyStereotype(element, stereotype.getDefinition());
					element.eResource().getContents().add(sa);
					if(!(element instanceof Pin) && element instanceof NamedElement && owner instanceof Namespace){
						NamedElement ne = (NamedElement) element;
						String keyWord = stereotype.getName();
						maybeSetUniqueName(ne, keyWord);
					}
					implementInterfacesIfNecessary(element);
				}
			}
		}
		if(stereotypeIsKeyword && stereotypes.isEmpty()){
			addKeyword();
		}
	}
	private void maybeSetUniqueName(NamedElement ne,String keyWord){
		if(stereotypeIsKeyword
				&& (ne.getName() == null || (ne.getName().toLowerCase().startsWith(ne.eClass().getName().toLowerCase()) && Character.isDigit(ne.getName().charAt(
						ne.getName().length() - 1))))){
			setUniqueName(keyWord, ne);
		}
	}
	private Element getOwner(){
		return EmfElementFinder.getRootObject(element);
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
	public static void implementInterfacesIfNecessary(Object newValue){
		if(newValue instanceof Activity){
			implementAppropriateInterface((Element) newValue, StereotypeNames.BUSINES_PROCESS, StereotypeNames.PKG_REQUEST_OBJECT);
		}
		if(newValue instanceof Collaboration){
			implementAppropriateInterface((Element) newValue, StereotypeNames.BUSINESS_COLLABORATION, StereotypeNames.PKG_ORGANIZATION);
		}
		if(newValue instanceof OpaqueBehavior){
			implementAppropriateInterface((Element) newValue, StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK, StereotypeNames.PKG_REQUEST_OBJECT);
		}
		if(newValue instanceof StateMachine){
			implementAppropriateInterface((Element) newValue, StereotypeNames.STANDALONE_SCREENFLOW_TASK, StereotypeNames.PKG_REQUEST_OBJECT);
			implementAppropriateInterface((Element) newValue, StereotypeNames.BUSINESS_STATE_MACHINE, StereotypeNames.PKG_REQUEST_OBJECT);
		}
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
			if(namedElement != ne && namedElement.getName() != null && namedElement.getName().toLowerCase().contains(stereotypeName.toLowerCase())){
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
	public void redo(){
		execute();
	}
	public List<Stereotype> getStereotypes(){
		return stereotypes;
	}
	private void addKeyword(){
		EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
		ann.setSource(StereotypeNames.NUML_ANNOTATION);
		ann.getDetails().put(stereotypeNames[0], "");
		element.getEAnnotations().add(ann);
		if(element instanceof NamedElement){
			maybeSetUniqueName((NamedElement) element, stereotypeNames[0]);
		}
	}
}

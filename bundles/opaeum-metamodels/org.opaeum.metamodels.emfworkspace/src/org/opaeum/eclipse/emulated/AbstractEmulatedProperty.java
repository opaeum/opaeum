package org.opaeum.eclipse.emulated;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StringExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.internal.impl.PropertyImpl;

@SuppressWarnings("restriction")
public abstract class AbstractEmulatedProperty extends PropertyImpl implements Adapter,IEmulatedElement{
	protected NamedElement owner;
	protected NamedElement originalElement;
	private Notifier target;

	public AbstractEmulatedProperty(Classifier owner,NamedElement originalElement){
		super();
		this.owner = owner;
		this.originalElement = originalElement;
	}
	public abstract int getUpper();
	public boolean isNavigable(){
		return true;
	}
	public EList<EAnnotation> getEAnnotations(){
		return originalElement.getEAnnotations();
	}

	public EList<Element> getOwnedElements(){
		return originalElement.getOwnedElements();
	}

	public EAnnotation getEAnnotation(String source){
		return originalElement.getEAnnotation(source);
	}

	public EList<Comment> getOwnedComments(){
		return originalElement.getOwnedComments();
	}

	public boolean isSetName(){
		return originalElement.isSetName();
	}

	public boolean isSetVisibility(){
		return originalElement.isSetVisibility();
	}

	public EList<EObject> getStereotypeApplications(){
		return originalElement.getStereotypeApplications();
	}

	public String getQualifiedName(){
		return originalElement.getQualifiedName();
	}
	@Override
	public boolean isMultivalued(){
		return getUpper()==LiteralUnlimitedNatural.UNLIMITED || getUpper()>1;
	}
	public ValueSpecification getUpperValue(){
		ValueSpecification result = super.getUpperValue();
		if(!(result instanceof LiteralUnlimitedNatural)){
			setUpperValue(result=createUpperValue("", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural()));
		}
		((LiteralUnlimitedNatural)result).setValue(getUpper());
		return result;
	}
	public EObject getStereotypeApplication(Stereotype stereotype){
		return originalElement.getStereotypeApplication(stereotype);
	}

	public EList<Stereotype> getRequiredStereotypes(){
		return originalElement.getRequiredStereotypes();
	}

	public EList<Dependency> getClientDependencies(){
		return originalElement.getClientDependencies();
	}

	public Stereotype getRequiredStereotype(String qualifiedName){
		return originalElement.getRequiredStereotype(qualifiedName);
	}

	public EList<Stereotype> getAppliedStereotypes(){
		return originalElement.getAppliedStereotypes();
	}

	public Dependency getClientDependency(String name){
		return originalElement.getClientDependency(name);
	}

	public Stereotype getAppliedStereotype(String qualifiedName){
		return originalElement.getAppliedStereotype(qualifiedName);
	}

	public Dependency getClientDependency(String name,boolean ignoreCase,EClass eClass){
		return originalElement.getClientDependency(name, ignoreCase, eClass);
	}

	public EList<Stereotype> getAppliedSubstereotypes(Stereotype stereotype){
		return originalElement.getAppliedSubstereotypes(stereotype);
	}

	public Stereotype getAppliedSubstereotype(Stereotype stereotype,String qualifiedName){
		return originalElement.getAppliedSubstereotype(stereotype, qualifiedName);
	}

	public Namespace getNamespace(){
		return originalElement.getNamespace();
	}

	public boolean hasValue(Stereotype stereotype,String propertyName){
		return originalElement.hasValue(stereotype, propertyName);
	}

	public StringExpression getNameExpression(){
		return originalElement.getNameExpression();
	}

	public Object getValue(Stereotype stereotype,String propertyName){
		return originalElement.getValue(stereotype, propertyName);
	}

	public EList<Relationship> getRelationships(){
		return originalElement.getRelationships();
	}

	public EList<Relationship> getRelationships(EClass eClass){
		return originalElement.getRelationships(eClass);
	}

	public EList<DirectedRelationship> getSourceDirectedRelationships(){
		return originalElement.getSourceDirectedRelationships();
	}

	public EList<DirectedRelationship> getSourceDirectedRelationships(EClass eClass){
		return originalElement.getSourceDirectedRelationships(eClass);
	}

	public EList<DirectedRelationship> getTargetDirectedRelationships(){
		return originalElement.getTargetDirectedRelationships();
	}

	public EList<DirectedRelationship> getTargetDirectedRelationships(EClass eClass){
		return originalElement.getTargetDirectedRelationships(eClass);
	}

	public String getLabel(){
		return originalElement.getLabel();
	}

	public EList<String> getKeywords(){
		return originalElement.getKeywords();
	}

	public String getLabel(boolean localize){
		return originalElement.getLabel(localize);
	}

	public EList<Namespace> allNamespaces(){
		return originalElement.allNamespaces();
	}

	public Package getNearestPackage(){
		return originalElement.getNearestPackage();
	}

	public Model getModel(){
		return originalElement.getModel();
	}

	public boolean isDistinguishableFrom(NamedElement n,Namespace ns){
		return originalElement.isDistinguishableFrom(n, ns);
	}

	public boolean isStereotypeApplicable(Stereotype stereotype){
		return originalElement.isStereotypeApplicable(stereotype);
	}

	public boolean isStereotypeRequired(Stereotype stereotype){
		return originalElement.isStereotypeRequired(stereotype);
	}

	public String separator(){
		return originalElement.separator();
	}

	public EList<Package> allOwningPackages(){
		return originalElement.allOwningPackages();
	}

	public boolean isStereotypeApplied(Stereotype stereotype){
		return originalElement.isStereotypeApplied(stereotype);
	}

	public EList<Stereotype> getApplicableStereotypes(){
		return originalElement.getApplicableStereotypes();
	}

	public Stereotype getApplicableStereotype(String qualifiedName){
		return originalElement.getApplicableStereotype(qualifiedName);
	}

	public boolean hasKeyword(String keyword){
		return originalElement.hasKeyword(keyword);
	}

	public EList<Element> allOwnedElements(){
		return originalElement.allOwnedElements();
	}

	public boolean mustBeOwned(){
		return originalElement.mustBeOwned();
	}

	public NamedElement getOwner(){
		return owner;
	}
	@Override
	public void notifyChanged(Notification notification){
		
	}
	@Override
	public String getName(){
		return originalElement.getName();
	}
	@Override
	public VisibilityKind getVisibility(){
		return originalElement.getVisibility();
	}

	@Override
	public Notifier getTarget(){
		return target;
	}

	@Override
	public void setTarget(Notifier newTarget){
		this.target=newTarget;
	}

	@Override
	public boolean isAdapterForType(Object type){
		return false;
	}

	public NamedElement getOriginalElement(){
		return originalElement;
	}
}

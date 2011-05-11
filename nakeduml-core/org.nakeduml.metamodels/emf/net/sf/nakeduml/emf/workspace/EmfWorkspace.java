package net.sf.nakeduml.emf.workspace;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.metamodel.mapping.internal.WorkspaceMappingInfoImpl;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Stereotype;

/**
 * Represents the concept of multiple emf models as one root workspace. Hacked to implement Element because of visitor constraints
 * 
 */
public class EmfWorkspace implements Element{
	Set<Package> generatingModels = new HashSet<Package>();
	Set<Package> primaryModels = new HashSet<Package>();
	private WorkspaceMappingInfoImpl mappingInfo;
	private ResourceSet resourceSet;
	private String name;
	private String directoryName;
	private URI directoryUri;
	public String getDirectoryName(){
		return directoryName;
	}
	public EmfWorkspace(Package model,WorkspaceMappingInfoImpl mappingInfo,String name){
		this(getContainingDir(model), model.eResource().getResourceSet(), mappingInfo, name);
		addGeneratingModelOrProfile(model);
		this.directoryUri=model.eResource().getURI().trimSegments(2);
	}
	private static File getContainingDir(Package model){
		URI uri = model.eResource().getURI();
		if(uri.isFile()){
			return new File(uri.trimSegments(2).toFileString());
		}else if(uri.isPlatform()){
			return new File(uri.trimSegments(2).toPlatformString(true));
		}else{
			throw new RuntimeException("Unknown URI type:" + uri);
		}
	}
	public EmfWorkspace(File modelDir,ResourceSet rs,WorkspaceMappingInfoImpl mappingInfo,String name){
		this.resourceSet = rs;
		this.mappingInfo = mappingInfo;
		directoryName = modelDir.getName();
		for(Element pkg:getOwnedElements()){
			if(isPrimaryModelOrProfile((Package) pkg, modelDir)){
				primaryModels.add((Package) pkg);
			}
		}
		this.directoryUri=URI.createFileURI(modelDir.getAbsolutePath());
		this.name = name;
	}
	public Collection<Package> getRootObjects(){
		EList<Element> ownedElements = getOwnedElements();
		Collection<Package> result = new HashSet<Package>();
		for(Element element:ownedElements){
			result.add((Package) element);
		}
		return result;
	}
	public Set<Package> getPrimaryModels(){
		return primaryModels;
	}
	public WorkspaceMappingInfoImpl getMappingInfo(){
		return mappingInfo;
	}
	public Set<Package> getGeneratingModelsOrProfiles(){
		return this.generatingModels;
	}
	private boolean isPrimaryModelOrProfile(Package p,File entryModelDir){
		if(p instanceof Model){
			URI uri = p.eResource().getURI();
			if(uri.isFile()){
				File packageFile = new File(uri.toFileString());
				return packageFile.getParentFile().equals(entryModelDir);
			}
			if(uri.isPlatform()){
				File packageFile = new File(uri.toPlatformString(true));
				return packageFile.getParentFile().equals(entryModelDir);
			}
		}
		return false;
	}
	public void guessGeneratingModelsAndProfiles(File dir){
		generatingModels.clear();
		for(Element e:getOwnedElements()){
			if(isPrimaryModelOrProfile((Package) e, dir)){
				generatingModels.add((Package) e);
			}
		}
	}
	public void addGeneratingModelOrProfile(Package p){
		generatingModels.add(p);
		this.resourceSet = p.eResource().getResourceSet();
	}
	public EList<Element> getOwnedElements(){
		final EList<Element> result = new BasicEList<Element>();
		for(Resource r:resourceSet.getResources()){
			Package pkg = getPackageFrom(r);
			String fileString = r.getURI().toString();
			if(!fileString.contains("UML_METAMODELS") && !pkg.getName().equalsIgnoreCase("ecore") && isRootObject(pkg)){
				result.add(pkg);
			}
		}
		return result;
	}
	private boolean isRootObject(Package pkg){
		return((pkg instanceof Profile || pkg instanceof Model) && pkg.getOwner() == null);
	}
	private Package getPackageFrom(Resource r){
		for(EObject o:r.getContents()){
			if(o instanceof Package){
				return (Package) o;
			}
		}
		return null;
	}
	public boolean addKeyword(String arg0){
		return false;
	}
	public EList<Element> allOwnedElements(){
		return getOwnedElements();
	}
	public EObject applyStereotype(Stereotype arg0){
		return null;
	}
	public EAnnotation createEAnnotation(String arg0){
		return null;
	}
	public Comment createOwnedComment(){
		return null;
	}
	public void destroy(){
	}
	public Stereotype getApplicableStereotype(String arg0){
		return null;
	}
	public EList<Stereotype> getApplicableStereotypes(){
		return null;
	}
	public Stereotype getAppliedStereotype(String arg0){
		return null;
	}
	public EList<Stereotype> getAppliedStereotypes(){
		return null;
	}
	public Stereotype getAppliedSubstereotype(Stereotype arg0,String arg1){
		return null;
	}
	public EList<Stereotype> getAppliedSubstereotypes(Stereotype arg0){
		return null;
	}
	public EList<String> getKeywords(){
		return null;
	}
	public Model getModel(){
		return null;
	}
	public Package getNearestPackage(){
		return null;
	}
	public EList<Comment> getOwnedComments(){
		return null;
	}
	public Element getOwner(){
		return null;
	}
	public EList<Relationship> getRelationships(){
		return null;
	}
	public EList<Relationship> getRelationships(EClass arg0){
		return null;
	}
	public Stereotype getRequiredStereotype(String arg0){
		return null;
	}
	public EList<Stereotype> getRequiredStereotypes(){
		return null;
	}
	public EList<DirectedRelationship> getSourceDirectedRelationships(){
		return null;
	}
	public EList<DirectedRelationship> getSourceDirectedRelationships(EClass arg0){
		return null;
	}
	public EObject getStereotypeApplication(Stereotype arg0){
		return null;
	}
	public EList<EObject> getStereotypeApplications(){
		return null;
	}
	public EList<DirectedRelationship> getTargetDirectedRelationships(){
		return null;
	}
	public EList<DirectedRelationship> getTargetDirectedRelationships(EClass arg0){
		return null;
	}
	public Object getValue(Stereotype arg0,String arg1){
		return null;
	}
	public boolean hasKeyword(String arg0){
		return false;
	}
	public boolean hasValue(Stereotype arg0,String arg1){
		return false;
	}
	public boolean isStereotypeApplicable(Stereotype arg0){
		return false;
	}
	public boolean isStereotypeApplied(Stereotype arg0){
		return false;
	}
	public boolean isStereotypeRequired(Stereotype arg0){
		return false;
	}
	public boolean mustBeOwned(){
		return false;
	}
	public boolean removeKeyword(String arg0){
		return false;
	}
	public void setValue(Stereotype arg0,String arg1,Object arg2){
	}
	public EObject unapplyStereotype(Stereotype arg0){
		return null;
	}
	public boolean validateHasOwner(DiagnosticChain arg0,Map<Object,Object> arg1){
		return false;
	}
	public boolean validateNotOwnSelf(DiagnosticChain arg0,Map<Object,Object> arg1){
		return false;
	}
	public EAnnotation getEAnnotation(String arg0){
		return null;
	}
	public EList<EAnnotation> getEAnnotations(){
		return null;
	}
	public TreeIterator<EObject> eAllContents(){
		return null;
	}
	public EClass eClass(){
		return null;
	}
	public EObject eContainer(){
		return null;
	}
	public EStructuralFeature eContainingFeature(){
		return null;
	}
	public EReference eContainmentFeature(){
		return null;
	}
	public EList<EObject> eContents(){
		return null;
	}
	public EList<EObject> eCrossReferences(){
		return null;
	}
	public Object eGet(EStructuralFeature arg0){
		return null;
	}
	public Object eGet(EStructuralFeature arg0,boolean arg1){
		return null;
	}
	public boolean eIsProxy(){
		return false;
	}
	public boolean eIsSet(EStructuralFeature arg0){
		return false;
	}
	public Resource eResource(){
		return null;
	}
	public void eSet(EStructuralFeature arg0,Object arg1){
	}
	public void eUnset(EStructuralFeature arg0){
	}
	public EList<Adapter> eAdapters(){
		return null;
	}
	public boolean eDeliver(){
		return false;
	}
	public void eNotify(Notification arg0){
	}
	public void eSetDeliver(boolean arg0){
	}
	public Object eInvoke(EOperation arg0,EList<?> arg1) throws InvocationTargetException{
		return null;
	}
	public String getName(){
		return this.name;
	}
	public void setDirectoryName(String name2){
		this.directoryName = name2;
	}
	public ResourceSet getResourceSet(){
		return resourceSet;
	}
	public Collection<Profile> getOwnedProfiles(){
		EList<Element> ownedElements = getOwnedElements();
		Collection<Profile> result = new HashSet<Profile>();
		for(Element element:ownedElements){
			if(element instanceof Profile){
				result.add((Profile) element);
			}
		}
		return result;
	}
}

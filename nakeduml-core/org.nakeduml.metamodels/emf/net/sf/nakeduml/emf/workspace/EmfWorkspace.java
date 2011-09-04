package net.sf.nakeduml.emf.workspace;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.WorkspaceMappingInfo;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
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
 * Represents the concept of multiple emf models as one root nakedWorkspace. Hacked to implement Element because of visitor constraints
 * 
 */
public class EmfWorkspace implements Element{
	private Set<Package> generatingModels = new HashSet<Package>();
	private Set<Package> primaryModels = new HashSet<Package>();
	private WorkspaceMappingInfo mappingInfo;
	private ResourceSet resourceSet;
	private URI directoryUri;
	private String identifier;
	private EmfResourceHelper uriResolver;
	private Set<Model> libraries = new HashSet<Model>();
	private Map<String,Element> elementMap = new HashMap<String,Element>();
	private String name;
	// Load single model
	public EmfWorkspace(Package model,WorkspaceMappingInfo mappingInfo,String identifier){
		this(model.eResource().getURI().trimFileExtension().trimSegments(1), model.eResource().getResourceSet(), mappingInfo, identifier);
		addGeneratingModelOrProfile(model);
	}
	// Load entire resourceSet
	public EmfWorkspace(URI uri,ResourceSet rs,WorkspaceMappingInfo mappingInfo,String identifier){
		this.resourceSet = rs;
		this.mappingInfo = mappingInfo;
		for(Element pkg:getOwnedElements()){
			if(isPrimaryModelOrProfile((Package) pkg, uri)){
				primaryModels.add((Package) pkg);
			}
		}
		this.directoryUri = uri;
		this.identifier = identifier;
	}
	public final void putElement(Element e){
		elementMap.put(getId(e), e);
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
	public WorkspaceMappingInfo getMappingInfo(){
		return mappingInfo;
	}
	public Set<Package> getGeneratingModelsOrProfiles(){
		return this.generatingModels;
	}
	private boolean isPrimaryModelOrProfile(Package p,URI entryModelDir){
		if(p instanceof Model){
			URI uri = p.eResource().getURI();
			boolean equals = uri.trimSegments(1).equals(entryModelDir);
			return equals;
		}
		return false;
	}
	public void guessGeneratingModelsAndProfiles(URI dir){
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
			if((pkg != null && (pkg.getName() == null || (!fileString.contains("UML_METAMODELS") && !pkg.getName().equalsIgnoreCase("ecore"))) && isRootObject(pkg))){
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
		return name;
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
	public URI getDirectoryUri(){
		return this.directoryUri;
	}
	public void setIdentifier(String directoryName){
		this.identifier = directoryName;
	}
	public String getIdentifier(){
		return identifier;
	}
	public Collection<Model> getOwnedModels(){
		EList<Element> ownedElements = getOwnedElements();
		Collection<Model> result = new HashSet<Model>();
		for(Element element:ownedElements){
			if(element instanceof Model){
				result.add((Model) element);
			}
		}
		return result;
	}
	public void setResourceHelper(EmfResourceHelper uriResolver){
		this.uriResolver = uriResolver;
	}
	public EmfResourceHelper getResourceHelper(){
		if(uriResolver == null){
			uriResolver = new DefaultUriResolver();
		}
		return uriResolver;
	}
	public void setMappingInfo(WorkspaceMappingInfo mappingInfo2){
		this.mappingInfo = (WorkspaceMappingInfo) mappingInfo2;
	}
	public void markLibraries(String...names){
		for(Element element:this.getOwnedElements()){
			for(String string:names){
				if(element instanceof Model && element.eResource().getURI().toString().contains(string)){
					this.libraries.add((Model) element);
				}
			}
		}
	}
	public boolean isLibrary(Model p){
		return this.libraries.contains(p);
	}
	public String getId(EModelElement model){
		return getResourceHelper().getId(model);
	}
	public void saveAll(){
		for(Resource resource:getResourceSet().getResources()){
			if(!isReadOnly(resource)){
				try{
					resource.save(new HashMap<Object,Object>());
				}catch(IOException e){
					throw new RuntimeException(e);
				}
			}
		}
	}
	private static boolean isReadOnly(Resource resource){
		URI uri = resource.getURI();
		return isSchemeReadOnly(uri.scheme()) || isPluginModel(uri);
	}
	private static boolean isPluginModel(URI uri){
		return uri.toString().startsWith("platform:/plugin");
	}
	private static boolean isSchemeReadOnly(String scheme){
		return Arrays.asList("pathmap").contains(scheme);
	}
	public Map<String,Element> getElementMap(){
		return elementMap;
	}
	public void setName(String workspaceIdentifier){
		this.name = workspaceIdentifier;
	}
}

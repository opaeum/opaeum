package org.opaeum.emf.workspace;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.opaeum.feature.WorkspaceMappingInfo;
import org.opaeum.metamodel.core.internal.StereotypeNames;

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
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
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
	private Map<String,Resource> resources = new HashMap<String,Resource>();
	private Set<Package> generatingModels = new HashSet<Package>();
	private Set<Package> primaryModels = new HashSet<Package>();
	private WorkspaceMappingInfo mappingInfo;
	private ResourceSet resourceSet;
	private URI directoryUri;
	private String identifier;
	private Set<Model> libraries = new HashSet<Model>();
	private UriToFileConverter uriToFileConverter = new DefaultUriToFileConverter();
	private String name;
	private ECrossReferenceAdapter crossReferenceAdaptor;
	// Load single model
	public EmfWorkspace(Package model,WorkspaceMappingInfo mappingInfo,String identifier){
		this(model.eResource().getURI().trimFileExtension().trimSegments(1), model.eResource().getResourceSet(), mappingInfo, identifier);
		addGeneratingModelOrProfile(model);
	}
	public ECrossReferenceAdapter getCrossReferenceAdapter(){
		if(crossReferenceAdaptor==null){
			crossReferenceAdaptor=ECrossReferenceAdapter.getCrossReferenceAdapter(resourceSet);
		}
		return this.crossReferenceAdaptor;
	}
	// Load entire resourceSet
	public EmfWorkspace(URI directoryUri,ResourceSet rs,WorkspaceMappingInfo mappingInfo,String identifier){
		this.resourceSet = rs;
		this.mappingInfo = mappingInfo;
		this.directoryUri = directoryUri;
		this.identifier = identifier;
		calculatePrimaryModels();
	}
	public void calculatePrimaryModels(){
		for(Element pkg:getOwnedElements()){
			if(isPrimaryModelOrProfile((Package) pkg, directoryUri)){
				primaryModels.add((Package) pkg);
			}
		}
		EList<Adapter> eAdapters = getResourceSet().eAdapters();
		for(Adapter adapter:eAdapters){
			if(adapter instanceof ECrossReferenceAdapter){
				this.crossReferenceAdaptor = (ECrossReferenceAdapter) adapter;
			}
		}
	}
	public Collection<Package> getRootObjects(){
		EList<Element> ownedElements = getOwnedElements();
		Collection<Package> result = new HashSet<Package>();
		for(Element element:ownedElements){
			result.add((Package) element);
		}
		return result;
	}
	public Set<Package> getPotentialGeneratingModels(){
		return primaryModels;
	}
	public WorkspaceMappingInfo getMappingInfo(){
		return mappingInfo;
	}
	public Set<Package> getGeneratingModelsOrProfiles(){
		return this.generatingModels;
	}
	private boolean isPrimaryModelOrProfile(Package p,URI entryModelDir){
		if(p instanceof Model || p instanceof Profile){
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
		if(result.size() != resources.size()){
			for(Element element:result){
				resources.put(getResourceId(element.eResource()), element.eResource());
			}
		}
		return result;
	}
	private boolean isRootObject(Package pkg){
		return((pkg instanceof Profile || pkg instanceof Model) && pkg.getOwner() == null);
	}
	private Package getPackageFrom(Resource r){
		for(EObject o:r.getContents()){
			if(o instanceof Profile || o instanceof Model){
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
	public void saveAll(){
		for(Resource resource:getResourceSet().getResources()){
			if(!isReadOnly(resource)){
				try{
					saveStereotypes(resource);
					resource.save(new HashMap<Object,Object>());
				}catch(IOException e){
					throw new RuntimeException(e);
				}
			}
		}
	}
	private static void saveStereotypes(Resource r){
		// see StereotypeApplicationHelper;
	}
	public static boolean isReadOnly(Resource resource){
		URI uri = resource.getURI();
		return isSchemeReadOnly(uri.scheme()) || isPluginModel(uri);
	}
	private static boolean isPluginModel(URI uri){
		return uri.toString().startsWith("platform:/plugin");
	}
	private static boolean isSchemeReadOnly(String scheme){
		return Arrays.asList("pathmap").contains(scheme);
	}
	public Element getElement(String s){
		String[] split = s.split("@");
		Resource r = resources.get(split[0]);
		// NB!!! Note that we only use the first occurrence of a @-seperated pair. When having to correlate back to model elements make sure
		// that the id begins with real Emf element's natural id
		if(r != null){
			return (Element) r.getEObject(split[1]);
		}else{
			return null;
		}
	}
	public void setName(String workspaceIdentifier){
		this.name = workspaceIdentifier;
	}
	public Model findOwnedModel(String string){
		for(Model model:getOwnedModels()){
			if(model.getName().equals(string)){
				return model;
			}
		}
		return null;
		// TODO Auto-generated method stub
	}
	public Profile findOwnedProfile(String string){
		for(Profile model:getOwnedProfiles()){
			if(model.getName().equals(string)){
				return model;
			}
		}
		return null;
		// TODO Auto-generated method stub
	}
	public UriToFileConverter getUriToFileConverter(){
		return uriToFileConverter;
	}
	public void setUriToFileConverter(UriToFileConverter uriToFileConverter){
		this.uriToFileConverter = uriToFileConverter;
	}
	public String getId(EObject object){
		if(object == null){
			return null;
		}else if(object instanceof EmfWorkspace){
			return ((EmfWorkspace) object).getIdentifier();
		}else{
			String uid = null;
			if(object.eResource() != null){
				uid = getResourceId(object.eResource()) + "@" + object.eResource().getURIFragment(object);
			}else if(object instanceof EModelElement && ((EModelElement) object).getEAnnotation(StereotypeNames.NUML_ANNOTATION) != null){
				// Deleted in realtime - See UmlElementCache.notifyChanged
				return ((EModelElement) object).getEAnnotation(StereotypeNames.NUML_ANNOTATION).getDetails().get("opaeumId");
			}else{
				uid = object.hashCode() + "";
			}
			return uid;
		}
	}
	public String getResourceId(Resource eResource){
		Element v = (Element) eResource.getContents().get(0);
		EAnnotation ann = v.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
		if(ann == null){
			return eResource.getURI().lastSegment();
		}else{
			String uuid = ann.getDetails().get("uuid");
			if(uuid == null || uuid.trim().length() == 0){
				return eResource.getURI().lastSegment();
			}else{
				return uuid;
			}
		}
	}
}

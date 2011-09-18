package net.sf.nakeduml.metamodel.workspace.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.MappingInfo;
import net.sf.nakeduml.feature.WorkspaceMappingInfo;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;
import nl.klasse.octopus.oclengine.IOclEngine;
import nl.klasse.octopus.oclengine.internal.OclEngine;

public class NakedModelWorkspaceImpl implements INakedModelWorkspace{
	public static final String META_CLASS = "nakedWorkspace";
	private NakedUmlLibrary builtInTypes;
	private Map<String,INakedElement> allElementsByModelId = new HashMap<String,INakedElement>();
	private INakedInterface businessRole;
	private WorkspaceMappingInfo modelMappingInfo;
	
	private Set<INakedRootObject> children = new HashSet<INakedRootObject>();
	private String name;
	private IOclEngine oclEngine = new OclEngine();
	private ErrorMap validator = new ErrorMap();
	private List<INakedRootObject> generatingRootObjects = new ArrayList<INakedRootObject>();
	private Set<INakedRootObject> primaryRootObjects = new HashSet<INakedRootObject>();
	private String identifier;
	private Map<INakedElement,Set<INakedElement>> dependencies = new HashMap<INakedElement,Set<INakedElement>>();
	public NakedModelWorkspaceImpl(){
	}
	public synchronized void markDependency(INakedElement from,INakedElement to){
		Set<INakedElement> set = getDependentElements(to);
		set.add(from);
	}
	public synchronized  Set<INakedElement> getDependentElements(INakedElement to){
		Set<INakedElement> set = this.dependencies.get(to);
		if(set == null){
			set = new HashSet<INakedElement>();
			this.dependencies.put(to, set);
		}
		return set;
	}
	@Override
	public IOclEngine getOclEngine(){
		return this.oclEngine;
	}
	@Override
	public void setWorkspaceMappingInfo(WorkspaceMappingInfo modelMappingInfo){
		this.modelMappingInfo = modelMappingInfo;
	}
	@Override
	public synchronized void putModelElement(INakedElement mw){
		if(this.allElementsByModelId.containsKey(mw.getId())){
			throw new IllegalStateException("Element " + mw.getName() + " is already in the workspace");
		}
		this.allElementsByModelId.put(mw.getId(), mw);
		MappingInfo vi = this.modelMappingInfo.getMappingInfo(mw.getId(), mw.isStoreMappingInfo());
		mw.setMappingInfo(vi);
		if(mw instanceof INakedRootObject){
			addOwnedElement((INakedRootObject) mw);
		}
	}
	@Override
	public synchronized void removeModelElement(INakedElement mw){
		this.allElementsByModelId.put(mw.getId(), mw);
		for(INakedElement child:mw.getOwnedElements()){
			removeModelElement(child);
			
		}
		this.modelMappingInfo.removeMappingInfo(mw.getId());
	}
	@Override
	public INakedElement getModelElement(Object id){
		if(id == null){
			return null;
		}else{
			return this.allElementsByModelId.get(id);
		}
	}
	public Collection<INakedElement> getAllElements(){
		return this.allElementsByModelId.values();
	}
	public INakedInterface getBusinessRole(){
		return this.businessRole;
	}
	public void setBusinessRole(INakedInterface rootUserEntity){
		this.businessRole = rootUserEntity;
	}
	public WorkspaceMappingInfo getWorkspaceMappingInfo(){
		return this.modelMappingInfo;
	}
	public void setModelMappingInfo(WorkspaceMappingInfo modelMappingInfo){
		this.modelMappingInfo = modelMappingInfo;
	}
	public MappingInfo getMappingInfo(){
		return this.getWorkspaceMappingInfo().getMappingInfo("replace with name identifying the transformation", false);
	}
	public Collection<? extends INakedElement> getOwnedElements(){
		return this.children;
	}
	public void setName(String string){
		this.name = string;
		if(name == null)
			throw new IllegalStateException();
	}
	public String getName(){
		return this.name;
	}
	public synchronized void addOwnedElement(INakedElement element){
		if(this.children.contains(element)){
			this.children.remove(element);
		}
		this.children.add((INakedRootObject) element);
	}
	public NakedUmlLibrary getNakedUmlLibrary(){
		if(this.builtInTypes == null){
			this.builtInTypes = new NakedUmlLibrary(this.getOclEngine().getOclLibrary());
		}
		return this.builtInTypes;
	}
	public void setBuiltInTypes(NakedUmlLibrary builtInTypes){
		this.builtInTypes = builtInTypes;
	}
	public ErrorMap getErrorMap(){
		return validator;
	}
	@Override
	public Collection<INakedRootObject> getRootObjects(){
		return children;
	}
	@Override
	public void removeOwnedElement(INakedElement element, boolean recursively){
		this.children.remove(element);
		this.generatingRootObjects.remove(element);
		this.primaryRootObjects.remove(element);
	}
	@Override
	public List<INakedRootObject> getGeneratingModelsOrProfiles(){
		return generatingRootObjects;
	}
	@Override
	public void addGeneratingRootObject(INakedRootObject p){
		generatingRootObjects.add(p);
	}
	@Override
	public void clearGeneratingModelOrProfiles(){
		generatingRootObjects.clear();
	}
	@Override
	public String getMetaClass(){
		return "nakedWorkspace";
	}
	@Override
	public boolean isPrimaryModel(INakedRootObject rootObject){
		return primaryRootObjects.contains(rootObject);
	}
	@Override
	public void addPrimaryModel(INakedRootObject rootObject){
		primaryRootObjects.add(rootObject);
	}
	public void setIdentifier(String directoryName){
		this.identifier = directoryName;
	}
	public String getIdentifier(){
		return identifier;
	}
	@Override
	public Collection<INakedRootObject> getPrimaryRootObjects(){
		return primaryRootObjects;
	}
}
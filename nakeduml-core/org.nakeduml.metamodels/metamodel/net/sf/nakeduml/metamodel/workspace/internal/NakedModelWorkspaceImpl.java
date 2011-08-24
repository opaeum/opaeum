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
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.ClassifierDependencyComparator;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;
import nl.klasse.octopus.oclengine.IOclEngine;
import nl.klasse.octopus.oclengine.internal.OclEngine;

public class NakedModelWorkspaceImpl implements INakedModelWorkspace{
	private static final long serialVersionUID = -825314743586339864L;
	public static final String META_CLASS = "nakedWorkspace";
	private NakedUmlLibrary builtInTypes;
	private Map<String,INakedElement> allElementsByModelId = new HashMap<String,INakedElement>();
	private Map<Class<? extends INakedElement>,Set<INakedElement>> elementsByMetaClass = new HashMap<Class<? extends INakedElement>,Set<INakedElement>>();
	private INakedInterface businessRole;
	private WorkspaceMappingInfo modelMappingInfo;
	
	private Set<INakedRootObject> children = new HashSet<INakedRootObject>();
	private String name;
	private IOclEngine oclEngine = new OclEngine();
	private ErrorMap validator = new ErrorMap();
	private List<INakedRootObject> generatingRootObjects = new ArrayList<INakedRootObject>();
	private Set<INakedRootObject> primaryRootObjects = new HashSet<INakedRootObject>();
	private String directoryName;
	private Map<INakedElement,Set<INakedElement>> dependencies = new HashMap<INakedElement,Set<INakedElement>>();
	public NakedModelWorkspaceImpl(){
	}
	public void markDependency(INakedElement from,INakedElement to){
		Set<INakedElement> set = getDependentElements(to);
		set.add(from);
	}
	public Set<INakedElement> getDependentElements(INakedElement to){
		Set<INakedElement> set = this.dependencies.get(to);
		if(set == null){
			set = new HashSet<INakedElement>();
			this.dependencies.put(to, set);
		}
		return set;
	}
	public IOclEngine getOclEngine(){
		return this.oclEngine;
	}
	public void setWorkspaceMappingInfo(WorkspaceMappingInfo modelMappingInfo){
		this.modelMappingInfo = modelMappingInfo;
	}
	public void putModelElement(INakedElement mw){
		String metaClass = mw.getMetaClass();
		if(this.allElementsByModelId.containsKey(mw.getId())){
			INakedElement clash = this.allElementsByModelId.get(mw.getId());
			String msg = metaClass + ":" + mw.getName() + " already exists:" + clash;
			System.out.println(msg);
		}
		Class<?> startClass = mw.getClass();
		while(INakedElement.class.isAssignableFrom(startClass)){
			for(Class<?> intf:startClass.getInterfaces()){
				if(INakedElement.class.isAssignableFrom(intf)){
					Set<INakedElement> set = (Set<INakedElement>) getElementsOfType((Class<? extends INakedElement>) intf);
					set.add(mw);
				}
			}
			startClass = startClass.getSuperclass();
		}
		this.allElementsByModelId.put(mw.getId(), mw);
		MappingInfo vi = this.modelMappingInfo.getMappingInfo(mw.getId(), mw.isStoreMappingInfo());
		mw.setMappingInfo(vi);
		if(mw instanceof INakedRootObject){
			addOwnedElement((INakedRootObject) mw);
		}
	}
	public <T extends INakedElement>Set<T> getElementsOfType(Class<T> c){
		Set<INakedElement> set = elementsByMetaClass.get(c);
		if(set == null){
			elementsByMetaClass.put(c, set = new HashSet<INakedElement>());
		}
		return (Set<T>) set;
	}
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
	public Collection getOwnedElements(){
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
	public void addOwnedElement(INakedElement element){
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
	public <E extends INakedComplexStructure>List<E> getClasses(Class<E> c){
		List<E> result = new ArrayList<E>();
		ClassElementCollector<E> cec = new ClassElementCollector<E>(c);
		for(INakedPackage m:getGeneratingModelsOrProfiles()){
			cec.startVisiting(m);
			for(E type:cec.getClassElements()){
				ClassifierDependencyComparator.addTo(c, type, result, 10);
			}
		}
		return result;
	}
	@Override
	public List<INakedComplexStructure> getClassElementsInDependencyOrder(){
		return getClasses(INakedComplexStructure.class);
	}
	@Override
	public Collection<INakedRootObject> getRootObjects(){
		return children;
	}
	@Override
	public void removeElementById(String id){
		allElementsByModelId.remove(id);
	}
	@Override
	public void removeOwnedElement(INakedElement element){
		// TODO remove all recursively from allElementsByModelId
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
		this.directoryName = directoryName;
	}
	public String getIdentifier(){
		return directoryName;
	}
	@Override
	public Collection<INakedRootObject> getPrimaryRootObjects(){
		return primaryRootObjects;
	}
}
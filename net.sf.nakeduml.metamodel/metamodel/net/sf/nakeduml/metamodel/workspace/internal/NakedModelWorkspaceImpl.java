package net.sf.nakeduml.metamodel.workspace.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.ClassifierDependencyComparator;
import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import net.sf.nakeduml.metamodel.mapping.IWorkspaceMappingInfo;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.profiles.INakedProfile;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.MappedTypes;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.oclengine.IOclEngine;
import nl.klasse.octopus.oclengine.internal.OclEngine;

public class NakedModelWorkspaceImpl implements INakedModelWorkspace {
	public static final String META_CLASS = "workspace";
	private MappedTypes builtInTypes;
	private static final long serialVersionUID = -825314743586339864L;
	private Map<String, INakedElement> allElementsByModelId = new HashMap<String, INakedElement>();
	private INakedEntity rootUserEntity;
	private IWorkspaceMappingInfo modelMappingInfo;
	private Collection<INakedPackage> children = new ArrayList<INakedPackage>();
	private String name;
	private IOclEngine oclEngine = new OclEngine();
	private ErrorMap validator = new ErrorMap();
	private List<INakedRootObject> generatingRootObjects = new ArrayList<INakedRootObject>();
	private Set<INakedRootObject> primaryRootObjects = new HashSet<INakedRootObject>();
	private boolean singleModelWorkspace;
	private String directoryName;

	public NakedModelWorkspaceImpl() {
	}

	public IOclEngine getOclEngine() {
		return this.oclEngine;
	}

	public void setWorkspaceMappingInfo(IWorkspaceMappingInfo modelMappingInfo) {
		this.modelMappingInfo = modelMappingInfo;
	}

	public void putModelElement(INakedElement mw) {
		if (this.allElementsByModelId.containsKey(mw.getId())) {
			INakedElement clash = this.allElementsByModelId.get(mw.getId());
			String msg = mw.getMetaClass() + ":" + mw.getName() + " already exists:" + clash;
			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
		this.allElementsByModelId.put(mw.getId(), mw);
		IMappingInfo vi = this.modelMappingInfo.getMappingInfo(mw.getId(), isInGeneratingModel(mw));
		mw.setMappingInfo(vi);
		if (mw instanceof INakedModel || mw instanceof INakedProfile) {
			this.children.add((INakedPackage) mw);
		}
	}

	private boolean isInGeneratingModel(INakedElement mw) {
		while (mw.getOwnerElement() instanceof INakedElement) {
			mw = (INakedElement) mw.getOwnerElement();
		}
		return generatingRootObjects.contains(mw);
	}

	public INakedElement getModelElement(Object id) {
		if (id == null) {
			return null;
		} else {
			return this.allElementsByModelId.get(id);
		}
	}

	public Collection<? extends INakedElement> getElementsOfType(String metaClass) {
		List<INakedElement> results = new ArrayList<INakedElement>();
		for (INakedPackage np : getGeneratingModelsOrProfiles()) {
			if (np.getMetaClass().equalsIgnoreCase(metaClass)) {
				results.add(np);
			}
			addAllElementsTo(metaClass, np, results);
		}
		return results;
	}

	private void addAllElementsTo(String metaClass, INakedElementOwner np, List results) {
		Iterator iter = np.getOwnedElements().iterator();
		while (iter.hasNext()) {
			INakedElement element = (INakedElement) iter.next();
			if (element.getMetaClass().equalsIgnoreCase(metaClass)) {
				results.add(element);
			}
			if (element instanceof INakedElementOwner) {
				addAllElementsTo(metaClass, element, results);
			}
		}
	}

	public Collection<INakedElement> getAllElements() {
		return this.allElementsByModelId.values();
	}

	public INakedEntity getRootUserEntity() {
		return this.rootUserEntity;
	}

	public void setRootUserEntity(INakedEntity rootUserEntity) {
		this.rootUserEntity = rootUserEntity;
	}

	public IWorkspaceMappingInfo getWorkspaceMappingInfo() {
		return this.modelMappingInfo;
	}

	public void setModelMappingInfo(IWorkspaceMappingInfo modelMappingInfo) {
		this.modelMappingInfo = modelMappingInfo;
	}

	public IMappingInfo getMappingInfo() {
		return this.getWorkspaceMappingInfo().getMappingInfo("replace with name identifying the transformation", false);
	}

	public Collection getOwnedElements() {
		return this.children;
	}

	public void setName(String string) {
		this.name = string;
		if(name==null) throw new IllegalStateException();
	}

	public String getName() {
		
		return this.name;
	}

	public void addOwnedElement(INakedElement element) {
		this.children.add((INakedPackage) element);
	}

	public MappedTypes getMappedTypes() {
		if (this.builtInTypes == null) {
			this.builtInTypes = new MappedTypes();
		}
		return this.builtInTypes;
	}

	public void setBuiltInTypes(MappedTypes builtInTypes) {
		this.builtInTypes = builtInTypes;
	}

	public ErrorMap getErrorMap() {
		return validator;
	}

	public <E extends INakedComplexStructure> List<E> getClasses(Class<E> c) {
		List<E> result = new ArrayList<E>();
		ClassElementCollector<E> cec = new ClassElementCollector<E>(c);
		for (INakedPackage m : getGeneratingModelsOrProfiles()) {
			cec.startVisiting(m);
			for (E type : cec.getClassElements()) {
				ClassifierDependencyComparator.addTo(c, type, result, 10);
			}
		}
		return result;
	}

	@Override
	public List<INakedComplexStructure> getClassElementsInDependencyOrder() {
		return getClasses(INakedComplexStructure.class);
	}

	@Override
	public Collection<INakedPackage> getChildren() {
		return children;
	}

	@Override
	public void removeElementById(String id) {
		allElementsByModelId.remove(id);
	}

	@Override
	public void removeOwnedElement(INakedElement element) {
		this.children.remove(element);
	}

	@Override
	public List<INakedRootObject> getGeneratingModelsOrProfiles() {
		return generatingRootObjects;
	}


	@Override
	public void addGeneratingRootObject(INakedRootObject p) {
		generatingRootObjects.add(p);
	}

	@Override
	public void clearGeneratingModelOrProfiles() {
		generatingRootObjects.clear();
	}


	@Override
	public String getMetaClass() {
		return "workspace";
	}

	@Override
	public boolean isPrimaryModel(INakedRootObject rootObject) {
		return primaryRootObjects.contains(rootObject);
	}

	@Override
	public void addPrimaryModel(INakedRootObject rootObject) {
		primaryRootObjects.add(rootObject);
		
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	@Override
	public Collection<INakedRootObject> getPrimaryRootObjects() {
		return primaryRootObjects;
	}
}
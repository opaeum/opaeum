package net.sf.nakeduml.metamodel.core.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.RootObjectStatus;
import nl.klasse.octopus.model.IImportedElement;

public class NakedRootObjectImpl extends NakedPackageImpl implements INakedRootObject {
	
	private static final long serialVersionUID = 7779979219779726122L;
	private String identifier;
	private RootObjectStatus status=RootObjectStatus.CREATED;
	private Map<Class<? extends INakedElement>,Set<? extends INakedElement>> typeMap = new HashMap<Class<? extends INakedElement>,Set<? extends INakedElement>>();
	private String fileName;
	public String getFileName(){
		return fileName;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	public RootObjectStatus getStatus(){
		return status;
	}

	public void setStatus(RootObjectStatus status){
		this.status = status;
	}

	public void setIdentifier(String modelFile) {
		this.identifier = modelFile;
	}

	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature("artifactIdentifier")){
			setIdentifier((String) stereotype.getFirstValueFor("artifactIdentifier").getValue());
		}
	}

	public INakedRootObject getNakedRoot() {
		return this;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	public Collection<INakedRootObject> getAllDependencies() {
		Set<INakedRootObject> result = new HashSet<INakedRootObject>();
		addImports(result, this);
		return result;
	}

	private void addImports(Set<INakedRootObject> result, INakedRootObject ro) {
		if (result.contains(ro)) {
			return;
		} else {
			result.add(ro);
			Collection<IImportedElement> imports = ro.getImports();
			for (IImportedElement imp : imports) {
				if (imp.getElement() instanceof INakedRootObject) {
					INakedRootObject element = (INakedRootObject) imp.getElement();
					addImports(result, element);
				}
			}
		}
	}
	@Override
	public INakedRootObject getRootObject(){
		return this;
	}
	@Override
	public void addDirectlyAccessibleElement(INakedElement c){
		addClassifier(c, c.getClass());
	}
	@SuppressWarnings("unchecked")
	private void addClassifier(INakedElement c,Class<? extends INakedElement> class1){
		for(Class<?> intf:class1.getInterfaces()){
			if(INakedClassifier.class.isAssignableFrom(intf)){
				@SuppressWarnings("rawtypes")
				Set classifiersOfType = getDirectlyAccessibleElementOfType((Class<? extends INakedClassifier>) intf);
				classifiersOfType.add(c);
				addClassifier(c,(Class<? extends INakedClassifier>) intf);
			}
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T extends INakedElement> Set<T> getDirectlyAccessibleElementOfType(Class<T> intf){
		Set<T> set = (Set<T>) typeMap.get(intf);
		if(set == null){
			typeMap.put((Class<T>) intf, set = new HashSet<T>());
		}
		return set;
	}
}

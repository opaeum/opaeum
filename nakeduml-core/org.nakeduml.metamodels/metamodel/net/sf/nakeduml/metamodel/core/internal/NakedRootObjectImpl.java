package net.sf.nakeduml.metamodel.core.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedRootObject;
import nl.klasse.octopus.model.IImportedElement;

public class NakedRootObjectImpl extends NakedPackageImpl implements INakedRootObject {

	private String fileName;


	public void setIdentifier(String modelFile) {
		this.fileName = modelFile;
	}

	public INakedRootObject getNakedRoot() {
		return this;
	}

	@Override
	public String getIdentifier() {
		return fileName;
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
}

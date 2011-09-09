package net.sf.nakeduml.metamodel.core.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.RootObjectStatus;
import nl.klasse.octopus.model.IImportedElement;

public class NakedRootObjectImpl extends NakedPackageImpl implements INakedRootObject {

	private static final long serialVersionUID = 7779979219779726122L;
	private String fileName;
	private RootObjectStatus status=RootObjectStatus.CREATED;

	public RootObjectStatus getStatus(){
		return status;
	}

	public void setStatus(RootObjectStatus status){
		this.status = status;
	}

	public void setIdentifier(String modelFile) {
		this.fileName = modelFile;
	}

	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature("identifier")){
			setIdentifier((String) stereotype.getFirstValueFor("identifier").getValue());
		}
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

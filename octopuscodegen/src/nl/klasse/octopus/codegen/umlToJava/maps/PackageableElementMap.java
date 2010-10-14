package nl.klasse.octopus.codegen.umlToJava.maps;

import net.sf.nakeduml.javametamodel.OJVisibilityKind;
import nl.klasse.octopus.model.IPackageableElement;
import nl.klasse.octopus.model.VisibilityKind;

public class PackageableElementMap {
	protected IPackageableElement e = null;

	public PackageableElementMap(IPackageableElement e) {
		super();
		this.e = e;
	}

	public OJVisibilityKind javaVisibility() {
		if (e.getVisibility() == VisibilityKind.PUBLIC) return OJVisibilityKind.PUBLIC;
		if (e.getVisibility() == VisibilityKind.PRIVATE) return OJVisibilityKind.PRIVATE;
		if (e.getVisibility() == VisibilityKind.PROTECTED) return OJVisibilityKind.PROTECTED;
		if (e.getVisibility() == VisibilityKind.NONE) return OJVisibilityKind.PUBLIC;
		return OJVisibilityKind.PUBLIC;
	}	
}

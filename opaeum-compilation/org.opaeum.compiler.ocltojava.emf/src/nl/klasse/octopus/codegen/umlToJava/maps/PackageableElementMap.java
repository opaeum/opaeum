package nl.klasse.octopus.codegen.umlToJava.maps;

import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.VisibilityKind;
import org.opaeum.java.metamodel.OJVisibilityKind;

public class PackageableElementMap {
	protected NamedElement e = null;

	public PackageableElementMap(NamedElement e) {
		super();
		this.e = e;
	}

	public OJVisibilityKind javaVisibility() {
		if (e.getVisibility() == VisibilityKind.PUBLIC_LITERAL) return OJVisibilityKind.PUBLIC;
		if (e.getVisibility() == VisibilityKind.PRIVATE_LITERAL) return OJVisibilityKind.PRIVATE;
		if (e.getVisibility() == VisibilityKind.PROTECTED_LITERAL) return OJVisibilityKind.PROTECTED;
		return OJVisibilityKind.PUBLIC;
	}	
}

package nl.klasse.octopus.codegen.umlToJava.maps;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.VisibilityKind;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.javageneration.util.OJUtil;

public class PackageableElementMap {
	protected NamedElement e = null;
	protected OJUtil ojUtil;
	public PackageableElementMap(OJUtil ojUtil, NamedElement e) {
		super();
		this.ojUtil=ojUtil;
		this.e = e;
	}

	public OJVisibilityKind javaVisibility() {
		if (e.getVisibility() == VisibilityKind.PUBLIC_LITERAL) return OJVisibilityKind.PUBLIC;
		if (e.getVisibility() == VisibilityKind.PRIVATE_LITERAL) return OJVisibilityKind.PRIVATE;
		if (e.getVisibility() == VisibilityKind.PROTECTED_LITERAL) return OJVisibilityKind.PROTECTED;
		return OJVisibilityKind.PUBLIC;
	}	
	public static CollectionKind getCollectionKind(MultiplicityElement exp) {
		if (exp.getUpper() == -1
				|| exp.getUpper() > 1) {
			if (exp.isOrdered()) {
				if (exp.isUnique()) {
					return CollectionKind.ORDERED_SET_LITERAL;
				} else {
					return CollectionKind.SEQUENCE_LITERAL;
				}
			} else {
				if (exp.isUnique()) {
					return CollectionKind.SET_LITERAL;
				} else {
					return CollectionKind.BAG_LITERAL;
				}

			}
		}
		return null;
	}
}

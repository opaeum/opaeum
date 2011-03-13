package net.sf.nakeduml.metamodel.core.internal;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import nl.klasse.octopus.model.IEnumLiteral;
import nl.klasse.octopus.model.IEnumerationType;
public class NakedEnumerationLiteralImpl extends NakedInstanceSpecificationImpl implements IEnumLiteral, INakedElement,
		INakedEnumerationLiteral {
	private static final long serialVersionUID = 6454193211164627516L;
	public NakedEnumerationLiteralImpl() {
	}
	@Override
	public String getMetaClass() {
		return "EnumerationLiteral";
	}
	public IEnumerationType getEnumeration() {
		return (IEnumerationType) getNameSpace();
	}
}

package net.sf.nakeduml.metamodel.core.internal;
import java.util.Iterator;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedPowerType;
import net.sf.nakeduml.metamodel.core.INakedPowerTypeInstance;

import org.eclipse.uml2.uml.Classifier;
/**
 * Somewhere between a generalization set and a powertype, represented as a kind
 * of enumeration. The generalization set is assumed to be all the
 * generalizations that have the representedSupertype as parent - directly or
 * indirectly. The PowerTypeInstances (enum literals) of a powertype This type
 * of classifier is ideal for use as the type of a discriminator field in
 * hibernate.
 */
public class NakedPowerTypeImpl extends NakedEnumerationImpl implements INakedPowerType {
	private static final long serialVersionUID = -375810033999646853L;
	INakedClassifier representedSupertype;
	public INakedClassifier getRepresentedSupertype() {
		return this.representedSupertype;
	}
	public void setRepresentedSupertype(INakedClassifier representedSupertype) {
		this.representedSupertype = representedSupertype;
		representedSupertype.setPowerType(this);
	}
	public boolean isPowerType() {
		return true;
	}
	public INakedPowerTypeInstance getLiteralForSubtype(Classifier element) {
		Iterator it = getLiterals().iterator();
		while (it.hasNext()) {
			INakedPowerTypeInstance el = (INakedPowerTypeInstance) it.next();
			if (el.getRepresentedGeneralization().getSpecific().equals(element)) {
				return el;
			}
		}
		return null;
	}
}

package org.opaeum.metamodel.core.internal;
import org.eclipse.uml2.uml.INakedGeneralization;
import org.eclipse.uml2.uml.INakedPowerTypeInstance;
/**
 * A type of enumeration literal that, being an instance of a powertype. None of
 * its specific features are used yet
 * 
 * @author tmp15944
 * 
 */
public class NakedPowerTypeInstanceImpl extends NakedEnumerationLiteralImpl implements INakedPowerTypeInstance {
	private static final long serialVersionUID = -1907683041128056634L;
	private INakedGeneralization representedGeneralization;
	public INakedGeneralization getRepresentedGeneralization() {
		return this.representedGeneralization;
	}
	public void setRepresentedGeneralization(INakedGeneralization representedGeneralization) {
		this.representedGeneralization = representedGeneralization;
	}
}

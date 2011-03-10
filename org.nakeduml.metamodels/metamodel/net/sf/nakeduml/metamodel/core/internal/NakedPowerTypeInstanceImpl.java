package net.sf.nakeduml.metamodel.core.internal;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedPowerTypeInstance;
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

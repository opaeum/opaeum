package net.sf.nakeduml.metamodel.profiles.internal;
import net.sf.nakeduml.metamodel.core.internal.NakedClassifierImpl;
import net.sf.nakeduml.metamodel.profiles.INakedStereotype;
public class NakedStereotypeImpl extends NakedClassifierImpl implements INakedStereotype {
	private static final long serialVersionUID = 1578420288235712431L;
	@Override
	public String getMetaClass() {
		return "stereotype";
	}
}

package net.sf.nakeduml.metamodel.core;

import java.util.Collection;
import java.util.Set;

public interface INakedRootObject extends INakedPackage{
	/**
	 * An identifier used as a suffix/prefix in calculating valid file and directory name 
	 * @return
	 */
	
	String getIdentifier();
	Collection<INakedRootObject> getAllDependencies();
	void setStatus(RootObjectStatus s);
	RootObjectStatus getStatus();
	public abstract <T extends INakedElement> Set<T> getDirectlyAccessibleElementOfType(Class<T> intf);
	public abstract void addDirectlyAccessibleElement(INakedElement c);
}

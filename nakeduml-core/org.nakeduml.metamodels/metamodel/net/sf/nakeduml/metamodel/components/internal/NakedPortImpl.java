package net.sf.nakeduml.metamodel.components.internal;

import net.sf.nakeduml.metamodel.bpm.INakedBusinessRole;
import net.sf.nakeduml.metamodel.bpm.INakedBusinessService;
import net.sf.nakeduml.metamodel.components.INakedPort;
import net.sf.nakeduml.metamodel.core.internal.NakedPropertyImpl;

public class NakedPortImpl extends NakedPropertyImpl implements INakedPort{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1593895201835920222L;

	@Override
	public boolean isBusinessService(){
		if(getNakedBaseType() instanceof INakedBusinessService){
			return true;
		}else if(getNakedBaseType() instanceof INakedBusinessRole){
			return true;
		}
		return false;
	}
}

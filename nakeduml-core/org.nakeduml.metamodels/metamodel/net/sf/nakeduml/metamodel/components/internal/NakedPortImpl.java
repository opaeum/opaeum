package net.sf.nakeduml.metamodel.components.internal;

import net.sf.nakeduml.metamodel.components.INakedPort;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.internal.NakedPropertyImpl;

public class NakedPortImpl extends NakedPropertyImpl implements INakedPort{

	@Override
	public boolean isResponsibility(){
		if(getNakedBaseType() instanceof INakedInterface){
			return ((INakedInterface)getNakedBaseType()).isResponsibility();
		}else if(getNakedBaseType() instanceof INakedEntity){
			return ((INakedEntity)getNakedBaseType()).representsUser();
		}
		return false;
	}
}

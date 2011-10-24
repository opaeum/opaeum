package org.opaeum.metamodel.core.internal;

import org.opaeum.metamodel.core.INakedHelper;

public class NakedHelperImpl extends NakedInterfaceImpl implements INakedHelper {
	private static final long serialVersionUID = 4838934344077383043L;

	@Override
	public boolean isPersistent(){
		return false;
	}
}

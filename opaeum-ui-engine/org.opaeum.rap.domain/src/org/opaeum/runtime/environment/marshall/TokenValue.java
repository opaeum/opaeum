package org.opaeum.runtime.environment.marshall;	

import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.environment.Environment;

public class TokenValue extends EntityValue {
	private static final long serialVersionUID = 6501143361256819464L;

	public TokenValue(IToken e) {
		super(e.getClassIdentifier(), e);
	}
	public Class<?> getValueClass(){
		return Environment.getInstance().getMetaInfoMap().getTokenClass(getTypeId());
	}

}

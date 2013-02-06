package org.opaeum.runtime.environment.marshall;	

import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;

public class TokenValue extends EntityValue {
	private static final long serialVersionUID = 6501143361256819464L;

	public TokenValue(@SuppressWarnings("rawtypes") IToken e, Environment env) {
		super(env.getMetaInfoMap().getUuidFor(IntrospectionUtil.getOriginalClass(e)), e);
	}
	public Class<?> getValueClass(Environment env){
		return env.getMetaInfoMap().getTokenClass(getTypeId());
	}

}

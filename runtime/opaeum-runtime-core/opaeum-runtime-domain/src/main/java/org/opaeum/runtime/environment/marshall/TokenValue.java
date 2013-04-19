package org.opaeum.runtime.environment.marshall;	

import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class TokenValue extends EntityValue {
	private static final long serialVersionUID = 6501143361256819464L;

	public TokenValue(@SuppressWarnings("rawtypes") IToken e, JavaMetaInfoMap env) {
		super(env.getUuidFor(IntrospectionUtil.getOriginalClass(e)), e);
	}
	public Class<?> getValueClass(JavaMetaInfoMap env){
		return env.getTokenClass(getTypeId());
	}

}

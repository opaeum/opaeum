package org.opaeum.runtime.domain;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.domain.TaskDelegation;

public class DocumentTypeResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		throw new RuntimeException();
	}
	
	public Class<?> returnedClass() {
		return DocumentType.class;
	}
	
	public long toOpaeumId(IEnum en) {
		throw new RuntimeException();
	}

}
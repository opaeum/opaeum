package org.opaeum.runtime.domain;

import org.opaeum.hibernate.domain.AbstractEnumResolver;

public class DocumentTypeResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==4510870660847370576l ) {
			result = DocumentType.SPREADSHEET;
		} else {
			if ( i==8255221197372907126l ) {
				result = DocumentType.PRESENTATION;
			} else {
				if ( i==3715427553278174788l ) {
					result = DocumentType.READONLYDOCUMENT;
				} else {
					if ( i==3753040871797758258l ) {
						result = DocumentType.DOCUMENT;
					} else {
						if ( i==1766041522512898104l ) {
							result = DocumentType.IMAGE;
						} else {
						
						}
					}
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return DocumentType.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (DocumentType)en ) {
			case IMAGE:
				result = 1766041522512898104l;
			break;
		
			case DOCUMENT:
				result = 3753040871797758258l;
			break;
		
			case READONLYDOCUMENT:
				result = 3715427553278174788l;
			break;
		
			case PRESENTATION:
				result = 8255221197372907126l;
			break;
		
			case SPREADSHEET:
				result = 4510870660847370576l;
			break;
		
		}
		
		return result;
	}

}
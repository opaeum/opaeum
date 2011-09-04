package org.nakeduml.runtime.bpm;

import org.nakeduml.hibernate.domain.AbstractEnumResolver;
import org.nakeduml.runtime.domain.EnumResolver;
import org.nakeduml.runtime.domain.IEnum;

public class TaskRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromNakedUmlId(int i) {
		IEnum result = null;
		switch ( i ) {
			case 217:
				result = TaskRequestState.OBSOLETE;
			break;
		
			case 212:
				result = TaskRequestState.INPROGRESSBUTSUSPENDED;
			break;
		
			case 214:
				result = TaskRequestState.CREATED;
			break;
		
			case 213:
				result = TaskRequestState.READYBUTSUSPENDED;
			break;
		
			case 207:
				result = TaskRequestState.FINALSTATE1;
			break;
		
			case 215:
				result = TaskRequestState.COMPLETED;
			break;
		
			case 209:
				result = TaskRequestState.SUSPENDED;
			break;
		
			case 204:
				result = TaskRequestState.READY;
			break;
		
			case 201:
				result = TaskRequestState.ACTIVE;
			break;
		
			case 205:
				result = TaskRequestState.RESERVED;
			break;
		
			case 203:
				result = TaskRequestState.INPROGRESS;
			break;
		
			case 211:
				result = TaskRequestState.RESERVEDBUTSUSPENDED;
			break;
		
		}
		
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.nakeduml.runtime.bpm.TaskRequestState.class;
	}
	
	public int toNakedUmlId(IEnum en) {
		int result = -1;
		switch ( (TaskRequestState)en ) {
			case OBSOLETE:
				result = 217;
			break;
		
			case INPROGRESSBUTSUSPENDED:
				result = 212;
			break;
		
			case CREATED:
				result = 214;
			break;
		
			case READYBUTSUSPENDED:
				result = 213;
			break;
		
			case FINALSTATE1:
				result = 207;
			break;
		
			case COMPLETED:
				result = 215;
			break;
		
			case SUSPENDED:
				result = 209;
			break;
		
			case READY:
				result = 204;
			break;
		
			case ACTIVE:
				result = 201;
			break;
		
			case RESERVED:
				result = 205;
			break;
		
			case INPROGRESS:
				result = 203;
			break;
		
			case RESERVEDBUTSUSPENDED:
				result = 211;
			break;
		
		}
		
		return result;
	}

}
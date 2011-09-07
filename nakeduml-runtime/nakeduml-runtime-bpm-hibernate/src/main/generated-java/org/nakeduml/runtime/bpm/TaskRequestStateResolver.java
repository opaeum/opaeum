package org.nakeduml.runtime.bpm;

import org.nakeduml.hibernate.domain.AbstractEnumResolver;
import org.nakeduml.runtime.domain.EnumResolver;
import org.nakeduml.runtime.domain.IEnum;

public class TaskRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromNakedUmlId(int i) {
		IEnum result = null;
		switch ( i ) {
			case 821:
				result = TaskRequestState.INPROGRESS;
			break;
		
			case 817:
				result = TaskRequestState.ACTIVE;
			break;
		
			case 816:
				result = TaskRequestState.OBSOLETE;
			break;
		
			case 819:
				result = TaskRequestState.RESERVED;
			break;
		
			case 818:
				result = TaskRequestState.FINALSTATE1;
			break;
		
			case 814:
				result = TaskRequestState.RESERVEDBUTSUSPENDED;
			break;
		
			case 823:
				result = TaskRequestState.COMPLETED;
			break;
		
			case 815:
				result = TaskRequestState.READYBUTSUSPENDED;
			break;
		
			case 822:
				result = TaskRequestState.CREATED;
			break;
		
			case 820:
				result = TaskRequestState.READY;
			break;
		
			case 812:
				result = TaskRequestState.SUSPENDED;
			break;
		
			case 813:
				result = TaskRequestState.INPROGRESSBUTSUSPENDED;
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
			case INPROGRESS:
				result = 821;
			break;
		
			case ACTIVE:
				result = 817;
			break;
		
			case OBSOLETE:
				result = 816;
			break;
		
			case RESERVED:
				result = 819;
			break;
		
			case FINALSTATE1:
				result = 818;
			break;
		
			case RESERVEDBUTSUSPENDED:
				result = 814;
			break;
		
			case COMPLETED:
				result = 823;
			break;
		
			case READYBUTSUSPENDED:
				result = 815;
			break;
		
			case CREATED:
				result = 822;
			break;
		
			case READY:
				result = 820;
			break;
		
			case SUSPENDED:
				result = 812;
			break;
		
			case INPROGRESSBUTSUSPENDED:
				result = 813;
			break;
		
		}
		
		return result;
	}

}
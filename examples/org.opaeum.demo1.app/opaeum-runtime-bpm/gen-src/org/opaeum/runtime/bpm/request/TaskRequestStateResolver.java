package org.opaeum.runtime.bpm.request;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class TaskRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==6900666214990186221l ) {
			result = TaskRequestState.ACTIVE_READY;
		} else {
			if ( i==4930734197145594991l ) {
				result = TaskRequestState.ACTIVE_RESERVED;
			} else {
				if ( i==5036147100148054299l ) {
					result = TaskRequestState.CREATED;
				} else {
					if ( i==3934459648279920547l ) {
						result = TaskRequestState.SUSPENDED_READYBUTSUSPENDED;
					} else {
						if ( i==6170327107602400395l ) {
							result = TaskRequestState.ACTIVE_INPROGRESS;
						} else {
							if ( i==8186560320517253471l ) {
								result = TaskRequestState.ACTIVE;
							} else {
								if ( i==5077142398307196385l ) {
									result = TaskRequestState.ACTIVE_FINALACTIVESTATE;
								} else {
									if ( i==3262741496760643789l ) {
										result = TaskRequestState.COMPLETED;
									} else {
										if ( i==5887023903477310989l ) {
											result = TaskRequestState.SUSPENDED;
										} else {
											if ( i==3060939886108721567l ) {
												result = TaskRequestState.OBSOLETE;
											} else {
												if ( i==2581032990836164737l ) {
													result = TaskRequestState.SUSPENDED_RESERVEDBUTSUSPENDED;
												} else {
													if ( i==9048647638684503117l ) {
														result = TaskRequestState.SUSPENDED_INPROGRESSBUTSUSPENDED;
													} else {
													
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.request.TaskRequestState.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (TaskRequestState)en ) {
			case SUSPENDED_INPROGRESSBUTSUSPENDED:
				result = 9048647638684503117l;
			break;
		
			case SUSPENDED_RESERVEDBUTSUSPENDED:
				result = 2581032990836164737l;
			break;
		
			case OBSOLETE:
				result = 3060939886108721567l;
			break;
		
			case SUSPENDED:
				result = 5887023903477310989l;
			break;
		
			case COMPLETED:
				result = 3262741496760643789l;
			break;
		
			case ACTIVE_FINALACTIVESTATE:
				result = 5077142398307196385l;
			break;
		
			case ACTIVE:
				result = 8186560320517253471l;
			break;
		
			case ACTIVE_INPROGRESS:
				result = 6170327107602400395l;
			break;
		
			case SUSPENDED_READYBUTSUSPENDED:
				result = 3934459648279920547l;
			break;
		
			case CREATED:
				result = 5036147100148054299l;
			break;
		
			case ACTIVE_RESERVED:
				result = 4930734197145594991l;
			break;
		
			case ACTIVE_READY:
				result = 6900666214990186221l;
			break;
		
		}
		
		return result;
	}

}
package org.opaeum.uim.action;


public enum ActionKind {
	UPDATE,
	DELETE,
	EXECUTE,
	DELEGATE_TASK,
	COMPLETE_TASK,
	SUSPEND,
	FORWARD_TASK,
	CLAIM_TASK,
	ADD,
	REFRESH,
	REVERT,
	ABORT,
	SKIP,
	RESUME;


	static public ActionKind getByName(String name) {
		ActionKind result = null;
		if ( "update".equals(name) ) {
			return UPDATE;
		}
		if ( "delete".equals(name) ) {
			return DELETE;
		}
		if ( "execute".equals(name) ) {
			return EXECUTE;
		}
		if ( "delegateTask".equals(name) ) {
			return DELEGATE_TASK;
		}
		if ( "completeTask".equals(name) ) {
			return COMPLETE_TASK;
		}
		if ( "suspend".equals(name) ) {
			return SUSPEND;
		}
		if ( "forwardTask".equals(name) ) {
			return FORWARD_TASK;
		}
		if ( "claimTask".equals(name) ) {
			return CLAIM_TASK;
		}
		if ( "add".equals(name) ) {
			return ADD;
		}
		if ( "refresh".equals(name) ) {
			return REFRESH;
		}
		if ( "revert".equals(name) ) {
			return REVERT;
		}
		if ( "abort".equals(name) ) {
			return ABORT;
		}
		if ( "skip".equals(name) ) {
			return SKIP;
		}
		if ( "resume".equals(name) ) {
			return RESUME;
		}
		return result;
	}

}
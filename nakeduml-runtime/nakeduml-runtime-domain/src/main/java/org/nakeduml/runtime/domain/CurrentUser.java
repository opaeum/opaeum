package org.nakeduml.runtime.domain;


public class CurrentUser {
	private static ThreadLocal<NakedUmlUser> instance = new ThreadLocal<NakedUmlUser>();
	public static void associateUserWithThread(NakedUmlUser user) {
		instance.set(user);
	}
	public static NakedUmlUser getCurrentUser() {
		return instance.get();
	}
}

package org.opaeum.runtime.domain;


public class CurrentUser {
	private static ThreadLocal<OpeumUser> instance = new ThreadLocal<OpeumUser>();
	public static void associateUserWithThread(OpeumUser user) {
		instance.set(user);
	}
	public static OpeumUser getCurrentUser() {
		return instance.get();
	}
}

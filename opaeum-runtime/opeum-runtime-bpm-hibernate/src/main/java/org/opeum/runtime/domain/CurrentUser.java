package org.opaeum.runtime.domain;


public class CurrentUser {
	private static ThreadLocal<OpaeumUser> instance = new ThreadLocal<OpaeumUser>();
	public static void associateUserWithThread(OpaeumUser user) {
		instance.set(user);
	}
	public static OpaeumUser getCurrentUser() {
		return instance.get();
	}
}

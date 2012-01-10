package org.opaeum.runtime.domain;


public class CurrentUser {
	private static ThreadLocal<IOpaeumUser> instance = new ThreadLocal<IOpaeumUser>();
	public static void associateUserWithThread(IOpaeumUser user) {
		instance.set(user);
	}
	public static IOpaeumUser getCurrentUser() {
		return instance.get();
	}
}

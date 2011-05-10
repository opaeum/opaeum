package org.nakeduml.runtime.domain;


public class CurrentUser {
	private static ThreadLocal<AbstractUser> instance = new ThreadLocal<AbstractUser>();
	public static void associateUserWithThread(AbstractUser user) {
		instance.set(user);
	}
	public static AbstractUser getCurrentUser() {
		return instance.get();
	}
}

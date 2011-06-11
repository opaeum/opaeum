package org.nakeduml.generate;

public class TestReflectionForSpeed {

	public static void main(String[] args) throws Exception {
		doRegular();
		doReflection();
	}

	public static void doRegular() throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			ReflectionTestA a = new ReflectionTestA();
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	public static void doReflection() throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			ReflectionTestA a = (ReflectionTestA) Class.forName("org.nakeduml.generate.A").newInstance();
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}

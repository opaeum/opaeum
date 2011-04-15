package org.nakeduml.generate;

public class TestClassForNameSpeed {

	public static void main(String[] args) throws Exception
	{
	        doRegular();
	        doReflection();
	}

	public static void doRegular() throws Exception
	{
	        long start = System.currentTimeMillis();
	        for (int i=0; i<1000000; i++)
	        {
	                A a = new A();
	        }
	        System.out.println(System.currentTimeMillis() - start);
	}

	public static void doReflection() throws Exception
	{
	        long start = System.currentTimeMillis();
	        for (int i=0; i<1000000; i++)
	        {
	                A a = (A) Class.forName("org.nakeduml.generate.A").newInstance();
	        }
	        System.out.println(System.currentTimeMillis() - start);
	}
	}

package org.nakeduml.annotation;


public class Main {
	public static void main(String[] args) {
		Package p=Package.getPackage("net.sf.nakeduml.generator.feature.accesscontrol");
		System.out.println(p.isAnnotationPresent(PackageAnnotation.class));
	}
}

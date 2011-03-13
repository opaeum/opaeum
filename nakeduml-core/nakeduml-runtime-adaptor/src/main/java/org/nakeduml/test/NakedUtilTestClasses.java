package org.nakeduml.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.nakeduml.audit.AuditSyncManager;
import org.nakeduml.environment.Environment;
import org.nakeduml.hibernate.adaptor.PrePersistListener;
import org.nakeduml.jbpm.adaptor.HibernateProcessPersistenceContext;
import org.nakeduml.jbpm.domain.UmlProcessMarshaller;
import org.nakeduml.runtime.adaptor.DataGeneratorProperty;
import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.seam3.persistence.ManagedHibernateSessionFactoryProvider;

public class NakedUtilTestClasses {

	public static Package[] getTestPackages() throws ClassNotFoundException, IOException {
		List<Package> packages = new ArrayList<Package>();
		packages.add(Environment.class.getPackage());
		packages.add(AbstractEntity.class.getPackage());
		packages.add(ManagedHibernateSessionFactoryProvider.class.getPackage());
		packages.add(AuditSyncManager.class.getPackage());
		packages.add(UmlProcessMarshaller.class.getPackage());
		packages.add(PrePersistListener.class.getPackage());
		packages.add(HibernateProcessPersistenceContext.class.getPackage());
		packages.add(IntrospectionUtil.class.getPackage());
		packages.add(DataGeneratorProperty.class.getPackage());
		Package[] result = new Package[packages.size()];
		packages.toArray(result);
		return result;
	}
	
//	public static Class<?>[] getTestClasses() throws ClassNotFoundException, IOException {
//		List<Class<?>> classes = new ArrayList<Class<?>>();
//		classes.addAll(getClasses(Component.class.getPackage().getName()));
//		classes.addAll(getClasses(AbstractEntity.class.getPackage().getName()));
//		classes.addAll(getClasses(InitializeHibernate.class.getPackage().getName()));
//		classes.addAll(getClasses(AuditSyncManager.class.getPackage().getName()));
//		classes.addAll(getClasses(AbstractJbpmKnowledgeBase.class.getPackage().getName()));
//		classes.addAll(getClasses(UmlProcessMarshaller.class.getPackage().getName()));
//		return classes.toArray(new Class[classes.size()]);
//	}
//
//	public static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		assert classLoader != null;
//		String path = packageName.replace('.', '/');
//		Enumeration<URL> resources = classLoader.getResources(path);
//		List<File> dirs = new ArrayList<File>();
//		while (resources.hasMoreElements()) {
//			URL resource = resources.nextElement();
//			dirs.add(new File(resource.getFile()));
//		}
//		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
//		for (File directory : dirs) {
//			classes.addAll(findClasses(directory, packageName));
//		}
//		return classes;
//	}
//	
//	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
//        List<Class<?>> classes = new ArrayList<Class<?>>();
//        if (!directory.exists()) {
//            return classes;
//        }
//        File[] files = directory.listFiles();
//        for (File file : files) {
//            if (file.isDirectory()) {
//                assert !file.getName().contains(".");
//                classes.addAll(findClasses(file, packageName + "." + file.getName()));
//            } else if (file.getName().endsWith(".class")) {
//                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
//            }
//        }
//        return classes;
//    }
	

}
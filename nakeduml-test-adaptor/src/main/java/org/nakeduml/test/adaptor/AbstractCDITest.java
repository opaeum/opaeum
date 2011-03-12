package org.nakeduml.test.adaptor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.jboss.weld.manager.BeanManagerImpl;
import org.jboss.weld.mock.MockBeanDeploymentArchive;
import org.jboss.weld.mock.MockDeployment;
import org.jboss.weld.mock.MockServletLifecycle;
import org.jboss.weld.test.BeanManagerLocator;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractCDITest {

	protected MockServletLifecycle lifecycle;
	protected BeanManagerImpl manager;

	@After
	public void afterClass() throws Exception {
		lifecycle.endRequest();
		lifecycle.endSession();
		lifecycle.endApplication();
		lifecycle = null;
	}

	@Before
	public void beforeClass() throws Throwable {
		MockBeanDeploymentArchive jar = new MockBeanDeploymentArchive();
		MockDeployment deployment = new MockDeployment(jar);
		jar.setBeansXmlFiles(getBeansXmlFiles());
		lifecycle = new MockServletLifecycle(deployment, jar);
		lifecycle.initialize();
		final List<Class<?>> allBeansList = new ArrayList<Class<?>>(getDefaultWebBeans());
		allBeansList.addAll(getAdditionalWebBeans());
		jar.setBeanClasses(allBeansList);
		lifecycle.beginApplication();
		lifecycle.beginSession();
		lifecycle.beginRequest();
		manager = getCurrentManager();
	}

	protected Collection<URL> getBeansXmlFiles() {
		return Collections.EMPTY_LIST;
	}

	protected BeanManagerImpl getCurrentManager() {
		return BeanManagerLocator.INSTANCE.locate();
	}

	protected List<Class<? extends Object>> getDefaultWebBeans() {
		return Collections.EMPTY_LIST;
	}

	/**
	 * Override in your tests to register specific beans with the manager.
	 * 
	 * @return
	 */
	protected List<Class<? extends Object>> getAdditionalWebBeans() {
		return Collections.EMPTY_LIST;
	}

	public static List<Class<?>> getClasses(String packageName) {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			assert classLoader != null;
			String path = packageName.replace('.', '/');
			Enumeration<URL> resources = classLoader.getResources(path);
			List<File> dirs = new ArrayList<File>();
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				dirs.add(new File(resource.getFile()));
			}
			ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
			for (File directory : dirs) {
				classes.addAll(findClasses(directory, packageName));
			}
			return classes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

}

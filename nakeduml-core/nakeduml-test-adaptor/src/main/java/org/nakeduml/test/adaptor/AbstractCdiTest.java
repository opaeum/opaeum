package org.nakeduml.test.adaptor;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;

import org.jboss.weld.mock.MockServletLifecycle;
import org.junit.Before;
import org.nakeduml.environment.cdi.unittest.CdiUnitTestEnvironment;

public abstract class AbstractCdiTest{
	protected MockServletLifecycle lifecycle;
	@Before
	public void beforeClass() throws Throwable{
		try{
			Collection<URL> beansXmlFiles = getBeansXmlFiles();
			List<Class<? extends Object>> defaultWebBeans = getDefaultWebBeans();
			final List<Class<?>> allBeansList = new ArrayList<Class<?>>(defaultWebBeans);
			List<Class<? extends Object>> additionalWebBeans = getAdditionalWebBeans();
			CdiUnitTestEnvironment.getInstance().initializeDeployment(beansXmlFiles, allBeansList, additionalWebBeans);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	protected Collection<URL> getBeansXmlFiles(){
		Collection<String> beansXmlFileNames = getBeansXmlResources();
		Collection<URL> result = new HashSet<URL>();
		for(String string:beansXmlFileNames){
			result.add(Thread.currentThread().getContextClassLoader().getResource(string));
		}
		return result;
	}
	protected Collection<String> getBeansXmlResources(){
		return Collections.EMPTY_LIST;
	}
	protected List<Class<? extends Object>> getDefaultWebBeans(){
		return Collections.EMPTY_LIST;
	}
	/**
	 * Override in your tests to register specific beans with the manager.
	 * 
	 * @return
	 */
	protected List<Class<? extends Object>> getAdditionalWebBeans(){
		return Collections.EMPTY_LIST;
	}
	public static List<Class<?>> getClasses(String packageName){
		try{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			assert classLoader != null;
			String path = packageName.replace('.', '/');
			Enumeration<URL> resources = classLoader.getResources(path);
			List<File> dirs = new ArrayList<File>();
			while(resources.hasMoreElements()){
				URL resource = resources.nextElement();
				dirs.add(new File(resource.getFile()));
			}
			ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
			for(File directory:dirs){
				classes.addAll(findClasses(directory, packageName));
			}
			return classes;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	private static List<Class<?>> findClasses(File directory,String packageName) throws ClassNotFoundException{
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if(!directory.exists()){
			return classes;
		}
		File[] files = directory.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			}else if(file.getName().endsWith(".class")){
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}
}

package org.opaeum.environment.cdi.test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jboss.weld.mock.MockServletLifecycle;
import org.junit.Before;
import org.opaeum.runtime.domain.IntrospectionUtil;
@Deprecated

public abstract class AbstractCdiTest{
	protected MockServletLifecycle lifecycle;
	@Before
	public void beforeClass() throws Throwable{
		try{
			CdiTestEnvironment.getInstance().initializeDeployment(getBeansXmlResources(), getAdditionalWebBeans());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	protected Collection<String> getBeansXmlResources(){
		return Collections.EMPTY_LIST;
	}
	protected List<Class<?>> getClasses(String packageName){
		return IntrospectionUtil.getClasses(packageName);
	}
	/**
	 * Override in your tests to register specific beans with the manager.
	 * 
	 * @return
	 */
	protected List<Class<? extends Object>> getAdditionalWebBeans(){
		return Collections.EMPTY_LIST;
	}
	
}

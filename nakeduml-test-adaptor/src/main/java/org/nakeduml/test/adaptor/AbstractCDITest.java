package org.nakeduml.test.adaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jbpm.jbpm.nodefinition.cron.AnotherTestBean;
import jbpm.jbpm.nodefinition.cron.TestBean;

import org.jboss.seam.scheduling.quartz.QuartzStarter;
import org.jboss.weld.manager.BeanManagerImpl;
import org.jboss.weld.mock.MockBeanDeploymentArchive;
import org.jboss.weld.mock.MockDeployment;
import org.jboss.weld.mock.MockServletLifecycle;
import org.jboss.weld.test.BeanManagerLocator;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractCDITest {

    MockServletLifecycle lifecycle;
    BeanManagerImpl manager;

    @After
    public void afterClass() throws Exception
    {
        lifecycle.endRequest();
        lifecycle.endSession();
        lifecycle.endApplication();
        lifecycle = null;
    }

    @Before
    public void beforeClass() throws Throwable
    {

//        BeanManagerImpl manager = (BeanManagerImpl) new StartMain(new String[] {}).go();

        MockBeanDeploymentArchive jar = new MockBeanDeploymentArchive();
        MockDeployment deployment = new MockDeployment(jar);
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
    
    protected BeanManagerImpl getCurrentManager()
    {
        return BeanManagerLocator.INSTANCE.locate();
    }

    public List<Class<? extends Object>> getDefaultWebBeans()
    {
        return Arrays.asList(TestBean.class, AnotherTestBean.class, QuartzStarter.class);
    }

    /**
     * Override in your tests to register specific beans with the manager.
     * @return
     */
    public List<Class<? extends Object>> getAdditionalWebBeans()
    {
        return Collections.EMPTY_LIST;
    }    
}

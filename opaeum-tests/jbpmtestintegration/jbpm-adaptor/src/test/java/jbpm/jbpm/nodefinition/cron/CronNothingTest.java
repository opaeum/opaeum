package jbpm.jbpm.nodefinition.cron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.seam.scheduling.annotations.Scheduled;
import org.jboss.seam.scheduling.events.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opeum.environment.Environment;
import org.opeum.environment.cdi.test.CdiTestEnvironment;
import org.opeum.environment.cdi.test.CdiTestSeamTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CronNothingTest  {

    private static final int MAX_TIME_TO_WAIT = 20000;
    private static final int SLEEP_TIME = 2000;
    private boolean scheduledEventObserved = false;
    private boolean namedEventObserved = false;
    private boolean typesafeEventObserved = false;
    private Logger log = LoggerFactory.getLogger(CronNothingTest.class);
    
	@Before
	public void beforeClass() throws Throwable {
		CdiTestEnvironment.getInstance().initializeDeployment(Arrays.asList("test-beans.xml"), getAdditionalWebBeans());
	}
	
    protected List<Class<? extends Object>> getAdditionalWebBeans() {
        List<Class<? extends Object>> list = new ArrayList<Class<? extends Object>>(1);
        list.add(CronNothingTest.class);
        list.add(CdiTestSeamTransaction.class);
        return list;    	
    }

	@Test
    public void testScheduleDoesFire()
    {
		CdiTestEnvironment.getInstance().beforeRequest(this);

        log.info("Testing shedule observer receiving events");
        CronNothingTest cronTestBean = Environment.getInstance().getComponent(CronNothingTest.class);
        Assert.assertTrue( cronTestBean.isScheduledEventObserved() == false );
        Assert.assertTrue( cronTestBean.isNamedEventObserved() == false );
        Assert.assertTrue( cronTestBean.isTypesafeEventObserved() == false );
        int totalTimeWaited = 0;
        while (!cronTestBean.isScheduledEventObserved() && !cronTestBean.isNamedEventObserved() &&
                !cronTestBean.isTypesafeEventObserved() && totalTimeWaited < MAX_TIME_TO_WAIT) {
            try {
                log.info("Sleeping for a few seconds, waiting for all events to fire. Waited for " + totalTimeWaited + "ms so far ...");
                totalTimeWaited += SLEEP_TIME;
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                log.error("Thread was woken up while sleeping");
                Assert.fail("Thread was woken up while sleeping. Why?");
                ex.printStackTrace();
            }
        }
        Assert.assertTrue( cronTestBean.isScheduledEventObserved() == true );
        Assert.assertTrue( cronTestBean.isNamedEventObserved() == true );
        Assert.assertTrue( cronTestBean.isTypesafeEventObserved() == false );
		CdiTestEnvironment.getInstance().afterRequest(this);

    }
    
    public void onSchedule(@Observes @Scheduled("*/5 * * ? * *") Event event)
    {
        log.info("Event observed: " + event.toString());
        this.scheduledEventObserved = true;
    }
    
    public void onNamedSchedule(@Observes @Scheduled("test.one") Event event)
    {
        log.info("Event observed: " + event.toString());
        this.namedEventObserved = true;
    }

    /**
     * @return if the unnamed, scheduled event has been observed.
     */
    public boolean isScheduledEventObserved()
    {
        return scheduledEventObserved;
    }

    /**
     * @return if the named event has been observed.
     */
    public boolean isNamedEventObserved()
    {
        return namedEventObserved;
    }

    /**
     * @return if the typesafe event has been observed.
     */
    public boolean isTypesafeEventObserved()
    {
        return typesafeEventObserved;
    }
    
}

package processmodel.unittest;

import javax.jms.JMSException;

import net.sf.opaeum.seam.SignalDispatcher;
import net.sf.opaeum.seam.TimeEventDispatcher;

import org.testng.annotations.Test;

import processmodel.processes.EventActivityOwner;
import processmodel.processes.SomeSignal;
import processmodel.processes.eventactivityowner.EventActivity;
import processmodel.processes.eventactivityowner.EventActivityState;

public class EventActivityTest {
	@Test
	public void testFlow() {
		EventActivityOwner owner = new EventActivityOwner() {
		};
		EventActivity eventActivity = owner.executeEventActivity();
		eventActivity.setId(13l);
		eventActivity.execute();
		assert eventActivity.isStepActive(EventActivityState.ACCEPTEVENTOPERATION);
		eventActivity.eventOperation("123", true);
		assert eventActivity.getPortId()==null;
		assert eventActivity.isStepActive(EventActivityState.ACCEPTEVENTOPERATION);
		for(int i = 0; i <=3; i ++){
			eventActivity.eventOperation("123"+i, true);

		}
		assert eventActivity.isStepActive(EventActivityState.ACCEPTEVENTOPERATION);
		eventActivity.eventOperation("12345678901234567", true);
		assert eventActivity.getPortId().equals("12345678901234567AFAF");
		assert eventActivity.isStepActive(EventActivityState.ACCEPTRELATIVETIMEEVENT);
		assert TimeEventDispatcher.getInstance().getTimer(eventActivity, "on_relative_time_event")!=null;
		int i = eventActivity.getNodeInstancesRecursively().size();
		assert eventActivity.getNodeInstancesRecursively().size()>1;
		assert eventActivity.isStepActive(EventActivityState.THEJOIN);
		eventActivity.on_relative_time_event();
		assert SignalDispatcher.getInstance().getFirstSignalOfType(SomeSignal.class)!=null;
		assert eventActivity.isStepActive(EventActivityState.ACCEPTABOLUTETIMEEVENT);
		assert eventActivity.isStepActive(EventActivityState.THEJOIN);
		eventActivity.on_absolute_time_event();
		assert eventActivity.isStepActive(EventActivityState.THEJOIN);
		assert eventActivity.isStepActive(EventActivityState.ACCEPTSIGNALEVENT);
		SomeSignal signal = new SomeSignal();
		signal.setParam1(123);
		signal.setParam2("123");
		owner.processSignal(signal);
		assert eventActivity.isStepActive(EventActivityState.ACCEPTCALLACTION1);
		assert eventActivity.getNodeInstancesRecursively().size()==1;
		owner.superEventOperation("super");
		assert eventActivity.isStepActive(EventActivityState.EVENTACTIVITYFINAL);
	}
}

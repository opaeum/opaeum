package processmodel.unittest;

import net.sf.opeum.seam.ITimeEventDispatcher;
import net.sf.opeum.seam.TimeEventDispatcher;

import org.testng.annotations.Test;

import processmodel.statemachines.SimpleStateMachine;
import processmodel.statemachines.SimpleStateMachineState;

public class SimpleStateMachineTest {
	private static final String ON_ABSOLUTE_TIME_EVENT = "on_absolute_time_event";

	@Test
	public void testFlow(){
		SimpleStateMachine sm =new SimpleStateMachine();
		sm.setId(123l);
		sm.execute();
		assert sm.getState1();
		//test that onEntryOfState1 was called
		assert sm.getStringAttribute().equals("bla");
		ITimeEventDispatcher instance = TimeEventDispatcher.getInstance();
		assert instance.getTimer(sm,ON_ABSOLUTE_TIME_EVENT)!=null;
		instance.cancelTimer(sm, ON_ABSOLUTE_TIME_EVENT);
		assert instance.getTimer(sm, ON_ABSOLUTE_TIME_EVENT)==null;
		assert sm.getProcessInstance().getVariable("processObject")==sm;
		sm.setIntAttribute(4);
		sm.on_absolute_time_event();
		assert sm.getState1();
		sm.setIntAttribute(13);
		sm.on_absolute_time_event();
		assert sm.getState2();
		//Test if doState2Activity was called
		assert sm.getStringAttribute().equals("asdfasdf");
		sm.forceToStep(SimpleStateMachineState.STATE1);
		assert sm.getState1();
		sm.setIntAttribute(12);
		sm.on_absolute_time_event();
		assert sm.getState3();
		sm.forceToStep(SimpleStateMachineState.STATE1);
		assert sm.getState1();
		sm.setIntAttribute(11);
		sm.on_absolute_time_event();
		assert sm.getFinalNode();
	}
}

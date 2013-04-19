package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class WorkDayActualInstance4_6z3cMX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<WorkDay> {
	static final public WorkDayActualInstance4_6z3cMX_JEeK5usaVqVCtXw INSTANCE = new WorkDayActualInstance4_6z3cMX_JEeK5usaVqVCtXw();
	static final public WorkDay WORKDAYACTUALINSTANCE4 = new WorkDay();


	public WorkDay createNewObject(CompositionNode parent) throws Exception {
		WorkDay result = WORKDAYACTUALINSTANCE4;
		result.init(parent);
		int startTimeCount = 0;
		while ( startTimeCount<1 ) {
			startTimeCount++;
			result.setStartTime((TimeOfDay)SimulationMetaData.getInstance().getStructValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance2::businessCalendar::BusinessCalendarContainedActualInstance::BusinessCalendarActualInstance2::workDay::WorkDayContainedActualInstance::WorkDayActualInstance4","startTime").createNewInstance());
		}
		int endTimeCount = 0;
		while ( endTimeCount<1 ) {
			endTimeCount++;
			result.setEndTime((TimeOfDay)SimulationMetaData.getInstance().getStructValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance2::businessCalendar::BusinessCalendarContainedActualInstance::BusinessCalendarActualInstance2::workDay::WorkDayContainedActualInstance::WorkDayActualInstance4","endTime").createNewInstance());
		}
		result.setKind(WorkDayKind.WEEKDAY);
		result.setKind(WorkDayKind.WEEKDAY);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		WorkDay instance = (WorkDay)in;
	}

}
package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class WorkDayActualInstance9_6z3ctn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<WorkDay> {
	static final public WorkDayActualInstance9_6z3ctn_JEeK5usaVqVCtXw INSTANCE = new WorkDayActualInstance9_6z3ctn_JEeK5usaVqVCtXw();
	static final public WorkDay WORKDAYACTUALINSTANCE9 = new WorkDay();


	public WorkDay createNewObject(CompositionNode parent) throws Exception {
		WorkDay result = WORKDAYACTUALINSTANCE9;
		result.init(parent);
		int startTimeCount = 0;
		while ( startTimeCount<1 ) {
			startTimeCount++;
			result.setStartTime((TimeOfDay)SimulationMetaData.getInstance().getStructValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance3::businessCalendar::BusinessCalendarContainedActualInstance::BusinessCalendarActualInstance3::workDay::WorkDayContainedActualInstance::WorkDayActualInstance9","startTime").createNewInstance());
		}
		int endTimeCount = 0;
		while ( endTimeCount<1 ) {
			endTimeCount++;
			result.setEndTime((TimeOfDay)SimulationMetaData.getInstance().getStructValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance3::businessCalendar::BusinessCalendarContainedActualInstance::BusinessCalendarActualInstance3::workDay::WorkDayContainedActualInstance::WorkDayActualInstance9","endTime").createNewInstance());
		}
		result.setKind(WorkDayKind.WEEKDAY);
		result.setKind(WorkDayKind.SUNDAY);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		WorkDay instance = (WorkDay)in;
	}

}
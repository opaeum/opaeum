package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class WorkDayActualInstance2_6z21E3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<WorkDay> {
	static final public WorkDayActualInstance2_6z21E3_JEeK5usaVqVCtXw INSTANCE = new WorkDayActualInstance2_6z21E3_JEeK5usaVqVCtXw();
	static final public WorkDay WORKDAYACTUALINSTANCE2 = new WorkDay();


	public WorkDay createNewObject(CompositionNode parent) throws Exception {
		WorkDay result = WORKDAYACTUALINSTANCE2;
		result.init(parent);
		int startTimeCount = 0;
		while ( startTimeCount<1 ) {
			startTimeCount++;
			result.setStartTime((TimeOfDay)SimulationMetaData.getInstance().getStructValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance1::businessCalendar::BusinessCalendarContainedActualInstance::BusinessCalendarActualInstance1::workDay::WorkDayContainedActualInstance::WorkDayActualInstance2","startTime").createNewInstance());
		}
		int endTimeCount = 0;
		while ( endTimeCount<1 ) {
			endTimeCount++;
			result.setEndTime((TimeOfDay)SimulationMetaData.getInstance().getStructValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance1::businessCalendar::BusinessCalendarContainedActualInstance::BusinessCalendarActualInstance1::workDay::WorkDayContainedActualInstance::WorkDayActualInstance2","endTime").createNewInstance());
		}
		result.setKind(WorkDayKind.WEEKDAY);
		result.setKind(WorkDayKind.SATURDAY);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		WorkDay instance = (WorkDay)in;
	}

}
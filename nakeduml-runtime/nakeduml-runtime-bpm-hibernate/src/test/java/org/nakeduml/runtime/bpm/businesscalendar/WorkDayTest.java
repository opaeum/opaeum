package org.nakeduml.runtime.bpm.businesscalendar;

import org.junit.Test;

public class WorkDayTest implements WorkDayTestContract {



	@Test
	public void testminutesPerDayInitialValue() {
		org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar parent = new org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar();
		WorkDay object = new WorkDay(parent);
	}

}
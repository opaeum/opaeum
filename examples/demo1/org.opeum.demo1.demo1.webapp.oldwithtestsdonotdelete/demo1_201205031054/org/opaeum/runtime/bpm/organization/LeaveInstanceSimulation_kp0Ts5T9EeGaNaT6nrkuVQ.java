package org.opaeum.runtime.bpm.organization;

import java.text.ParseException;
import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class LeaveInstanceSimulation_kp0Ts5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public Leave createNewObject(CompositionNode parent) throws ParseException {
		Leave result = new Leave();
		result.init(parent);
		int fromDateCount = 0;
		while ( fromDateCount<1 ) {
			fromDateCount++;
			result.setFromDate((Date)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::LeaveInstanceSimulation","fromDate"));
		}
		int toDateCount = 0;
		while ( toDateCount<1 ) {
			toDateCount++;
			result.setToDate((Date)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::LeaveInstanceSimulation","toDate"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Leave instance = (Leave)in;
	}

}
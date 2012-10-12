package org.opaeum.javageneration.bpm;

import org.eclipse.uml2.uml.Element;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;

public class ObservationUtil{
	OJUtil ojUtil;
	ValueSpecificationUtil valueSpecificationUtil;
	public ObservationUtil(OJUtil ojUtil){
		super();
		this.ojUtil = ojUtil;
		valueSpecificationUtil = new ValueSpecificationUtil(ojUtil);
	}
	public void implementObservationsOnEntry(Element vertex,OJAnnotatedOperation oper,String expressionToNearestBehaviorExecution){
		implementObservations(vertex, oper, expressionToNearestBehaviorExecution, true);
	}
	public void implementObservationsOnExit(Element vertex,OJAnnotatedOperation oper,String expressionToNearestBehaviorExecution){
		implementObservations(vertex, oper, expressionToNearestBehaviorExecution, false);
	}
	private void implementObservations(Element vertex,OJAnnotatedOperation oper,String expressionToNearestBehaviorExecution,boolean isOnEntry){
		boolean hasDurationObservations = false;
	}
}

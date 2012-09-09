package org.opaeum.javageneration.bpm;

import java.util.Collection;
import java.util.Date;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeObservation;
import org.opaeum.eclipse.EmfObservationUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.runtime.domain.BusinessTimeUnit;

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

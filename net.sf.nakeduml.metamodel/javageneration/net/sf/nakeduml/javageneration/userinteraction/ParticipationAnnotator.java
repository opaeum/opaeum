package net.sf.nakeduml.javageneration.userinteraction;

import java.util.Collections;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.bpm.BpmUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJElement;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJEnum;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

public class ParticipationAnnotator extends StereotypeAnnotator{
	public static final String STATE_PARTICIPATION = "StateParticipation";
	public static final String OPERATION_PARTICIPATION = "OperationParticipation";
	public static final String MODEL_PARTICIPATION = "ModelParticipation";
	public static final String PROPERTY_PARTICIPATION = "PropertyParticipation";
	@VisitAfter(matchSubclasses = true)
	public void visitAttribute(INakedProperty np){
		if(super.hasOJClass(np.getOwner())){
			OJAnnotatedClass ojClass = findJavaClass(np.getOwner());
			StructuralFeatureMap smap = new NakedStructuralFeatureMap(np);
			OJElement prop = ojClass.findOperation(smap.getter(), Collections.EMPTY_LIST);
			annotate(np, ojClass, PROPERTY_PARTICIPATION, prop);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void operation_Before(INakedOperation no){
		if(super.hasOJClass(no.getOwner())){
			OJAnnotatedClass ojClass = findJavaClass(no.getOwner());
			NakedOperationMap smap = new NakedOperationMap(no);
			OJElement prop = ojClass.findOperation(smap.javaOperName(), smap.javaParamTypePaths());
			annotate(no, ojClass, OPERATION_PARTICIPATION, prop);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void state(INakedState ns){
		OJPackage p = findOrCreatePackage(OJUtil.packagePathname(ns.getOwner().getNameSpace()));
		OJEnum ojClass = (OJEnum) p.findClass(new OJPathName(ns.getOwner().getName() + "State"));
		if(ojClass != null){
			// We do not know if BPM has been generated
			OJElement prop = ojClass.findLiteral(BpmUtil.stepLiteralName(ns));
			annotate(ns, ojClass, STATE_PARTICIPATION, prop);
		}
	}
}
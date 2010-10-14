package net.sf.nakeduml.javageneration.accesscontrol;

import java.util.Collections;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
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
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

public class AccessControlAnnotator extends StereotypeAnnotator{
	@VisitAfter(matchSubclasses = true)
	public void attribute(INakedProperty np){
		if(np.getOwner() instanceof INakedEntity){
			OJAnnotatedClass ojClass = findJavaClass(np.getOwner());
			StructuralFeatureMap smap = new NakedStructuralFeatureMap(np);
			OJElement prop = ojClass.findOperation(smap.getter(), Collections.EMPTY_LIST);
			annotate(np, ojClass, "SecurityOnEdit", prop);
			annotate(np, ojClass, "SecurityOnView", prop);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedEntity nc){
		if(!(nc instanceof INakedStructuredDataType)){
			OJAnnotatedClass ojClass = findJavaClass(nc);
			annotate(nc, ojClass, "SecurityOnEdit", ojClass);
			annotate(nc, ojClass, "SecurityOnView", ojClass);
			annotate(nc, ojClass, "SecurityOnCreate", ojClass);
			annotate(nc, ojClass, "SecurityOnDelete", ojClass);
			annotate(nc, ojClass, "SecurityOnAuditTrail", ojClass);
			annotate(nc, ojClass, "SecurityOnForceStateChange", ojClass);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation no){
		OJAnnotatedClass ojClass = findJavaClass(no.getOwner());
		NakedOperationMap smap = new NakedOperationMap(no);
		OJElement prop = ojClass.findOperation(smap.javaOperName(), smap.javaParamTypePaths());
		annotate(no, ojClass, "SecurityOnView", prop);
	}
	@VisitBefore(matchSubclasses = true)
	public void state(INakedState s){
		OJPackage p = findOrCreatePackage(OJUtil.packagePathname(s.getOwner().getNameSpace()));
		OJEnum ojClass = (OJEnum) p.findClass(new OJPathName(s.getOwner().getName() + "State"));
		if(ojClass != null){
			OJElement prop = ojClass.findLiteral(BpmUtil.stepLiteralName(s));
			annotate(s, ojClass, "SecurityOnView", prop);
			annotate(s, ojClass, "SecurityOnEdit", prop);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void state(INakedAction a){
		OJPackage p = findOrCreatePackage(OJUtil.classifierPathname(a.getActivity()));
		OJEnum ojClass = (OJEnum) p.findClass(new OJPathName(a.getActivity().getName() + "State"));
		if(ojClass != null){
			OJElement prop = ojClass.findLiteral(BpmUtil.stepLiteralName(a));
			annotate(a, ojClass, "SecurityOnView", prop);
			annotate(a, ojClass, "SecurityOnEdit", prop);
		}
	}
}

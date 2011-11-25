package org.opaeum.jaxb;

import java.util.ArrayList;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.simpleactions.EventGeneratorImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = JavaTransformationPhase.class, after={EventGeneratorImplementor.class})
public class JaxbImplementor extends AbstractJavaProducingVisitor{
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c){
		// TODO this should be a opaeum webservice step
		if(OJUtil.hasOJClass(c) && !(c instanceof INakedInterface)){
			OJAnnotatedClass owner = findJavaClass(c);
			addXmlRootElement(owner);
			OJOperation outgoingEvents = owner.getUniqueOperation("getOutgoingEvents");
			if(outgoingEvents!=null){
				JaxbAnnotator.addXmlTransient((OJAnnotatedOperation) outgoingEvents);
			}
			OJOperation cancelledEvents = owner.getUniqueOperation("getCancelledEvents");
			if(cancelledEvents!=null){
				JaxbAnnotator.addXmlTransient((OJAnnotatedOperation) cancelledEvents);
			}

			for(INakedProperty p:c.getEffectiveAttributes()){
				if(p.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)){
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
					OJAnnotatedOperation getter = (OJAnnotatedOperation) owner.findOperation(map.getter(), new ArrayList<IClassifier>());
					if(getter != null){
						JaxbAnnotator.addXmlTransient(getter);
					}
				}else if(p.getNakedBaseType() instanceof INakedInterface){
					addXmlAnyElement(owner, c, p);
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior behavior){
		if(behavior.getContext() != null && BehaviorUtil.hasExecutionInstance(behavior)){
			OJAnnotatedClass ojContext = findJavaClass(behavior.getContext());
			if(behavior.isClassifierBehavior()){
				OJAnnotatedOperation oper = (OJAnnotatedOperation) ojContext.getUniqueOperation("getClassifierBehavior");
				JaxbAnnotator.addXmlTransient(oper);
				// OJAnnotatedOperation getCurrentState= (OJAnnotatedOperation) OJUtil.findOperation(ojContext, "getCurrentState");
				// getCurrentState.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlTransient")));
			}else{
				OJAnnotatedOperation oper = (OJAnnotatedOperation) ojContext.getUniqueOperation("get" + behavior.getMappingInfo().getJavaName().getCapped());
				JaxbAnnotator.addXmlTransient(oper);
			}
		}
	}
	private void addXmlAnyElement(OJAnnotatedClass clazz,INakedEntity c,INakedProperty p){
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
		OJAnnotatedOperation oper = (OJAnnotatedOperation) clazz.findOperation(map.getter(), Collections.EMPTY_LIST);
		oper.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlAnyElement")));
	}
	private void addXmlRootElement(OJAnnotatedClass owner){
		owner.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlRootElement")));
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedProperty np){
		if(np.getNakedBaseType() instanceof INakedEntity && !np.isInverse() && OJUtil.hasOJClass(np.getOwner())){
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
			OJAnnotatedClass owner = findJavaClass(np.getOwner());
			OJAnnotatedOperation o = (OJAnnotatedOperation) owner.findOperation(map.getter(), Collections.EMPTY_LIST);
			JaxbAnnotator.addXmlTransient(o);
		}
	}
}

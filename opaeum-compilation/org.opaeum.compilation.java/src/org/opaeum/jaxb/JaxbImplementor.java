package org.opeum.jaxb;

import java.util.ArrayList;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.annotation.OJAnnotationValue;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.simpleactions.EventGeneratorImplementor;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = JavaTransformationPhase.class, after={EventGeneratorImplementor.class})
public class JaxbImplementor extends AbstractJavaProducingVisitor{
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c){
		// TODO this should be a opeum webservice step
		if(OJUtil.hasOJClass(c) && !(c instanceof INakedInterface)){
			OJAnnotatedClass owner = findJavaClass(c);
			addXmlRootElement(owner);
			OJOperation outgoingEvents = OJUtil.findOperation(owner, "getOutgoingEvents");
			if(outgoingEvents!=null){
				JaxbAnnotator.addXmlTransient((OJAnnotatedOperation) outgoingEvents);
			}
			OJOperation cancelledEvents = OJUtil.findOperation(owner, "getCancelledEvents");
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
				OJAnnotatedOperation oper = (OJAnnotatedOperation) OJUtil.findOperation(ojContext, "getClassifierBehavior");
				JaxbAnnotator.addXmlTransient(oper);
				// OJAnnotatedOperation getCurrentState= (OJAnnotatedOperation) OJUtil.findOperation(ojContext, "getCurrentState");
				// getCurrentState.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlTransient")));
			}else{
				OJAnnotatedOperation oper = (OJAnnotatedOperation) OJUtil.findOperation(ojContext, "get" + behavior.getMappingInfo().getJavaName().getCapped());
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

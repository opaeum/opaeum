package net.sf.nakeduml.jaxb;

import java.util.ArrayList;
import java.util.Collections;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedHelperClass;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import nl.klasse.octopus.model.IClassifier;

public class JaxbImplementor extends AbstractJavaProducingVisitor {
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		// TODO this should be a numl webservice step
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedInterface)) {
			OJAnnotatedClass owner = findJavaClass(c);
			addXmlRootElement(owner);
			for (INakedProperty p : c.getEffectiveAttributes()) {
				if (p.getNakedBaseType() instanceof INakedInterface) {
					addXmlAnyElement(owner, c, p);
				}
				if (p.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)) {
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
					OJAnnotatedOperation getter = (OJAnnotatedOperation) owner.findOperation(map.getter(), new ArrayList<IClassifier>());
					if (getter != null) {
						JaxbAnnotator.addXmlTransient(getter);
					}
				}
			}
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior behavior) {
		if (behavior.getContext() != null && BehaviorUtil.hasExecutionInstance(behavior)) {
			OJAnnotatedClass ojContext = findJavaClass(behavior.getContext());
			if (behavior.isClassifierBehavior()) {
				OJAnnotatedOperation oper = (OJAnnotatedOperation) OJUtil.findOperation(ojContext, "getClassifierBehavior");
				JaxbAnnotator.addXmlTransient(oper);
//				OJAnnotatedOperation getCurrentState= (OJAnnotatedOperation) OJUtil.findOperation(ojContext, "getCurrentState");
//				getCurrentState.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlTransient")));
			} else {
				OJAnnotatedOperation oper = (OJAnnotatedOperation) OJUtil.findOperation(ojContext, "get"
						+ behavior.getMappingInfo().getJavaName().getCapped());
				JaxbAnnotator.addXmlTransient(oper);
			}
		}
	}

	private void addXmlAnyElement(OJAnnotatedClass clazz, INakedEntity c, INakedProperty p) {
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
		OJAnnotatedOperation oper = (OJAnnotatedOperation) clazz.findOperation(map.getter(), Collections.EMPTY_LIST);
		oper.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlAnyElement")));
	}

	private void addXmlRootElement(OJAnnotatedClass owner) {
		owner.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlRootElement")));
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedProperty np) {
		if (!np.isDerived() && np.getNakedBaseType() instanceof INakedEntity && !np.isInverse() && OJUtil.hasOJClass(np.getOwner())) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
			OJAnnotatedClass owner = findJavaClass(np.getOwner());
			OJAnnotatedOperation o = (OJAnnotatedOperation) owner.findOperation(map.getter(), Collections.EMPTY_LIST);
			JaxbAnnotator.addXmlTransient(o);
		}
	}
}

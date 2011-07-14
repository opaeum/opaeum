package org.nakeduml.tinker.auditing.tinker;

import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import nl.klasse.octopus.model.IModelElement;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.generated.OJVisibilityKindGEN;

public class AuditCopyMethodImplementor extends AbstractJavaProducingVisitor {
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		OJPathName path = OJUtil.classifierPathname(c);
		OJClassifier myOwner = this.javaModel.findIntfOrCls(path);
		// nakedModel.lookup(c.getPathName());
		if (myOwner instanceof OJClass && (c instanceof INakedEntity || c instanceof INakedStructuredDataType)) {
			INakedComplexStructure nc = (INakedComplexStructure) c;
			OJClass ojClass = (OJClass) myOwner;
			addShallowCopyStateMethod(nc, ojClass);
		}
	}

	private void addShallowCopyStateMethod(INakedClassifier classifier, OJClass owner) {
		OJOperation oper = new OJAnnotatedOperation();
		oper.setVisibility(OJVisibilityKindGEN.PUBLIC);
		oper.setName("copyAuditShallowState");
		oper.addParam("from", owner.getPathName());
		oper.addParam("to", owner.getPathName());
		addCopyStatements(classifier, owner, oper.getBody(), false, true);
		owner.addToOperations(oper);
	}

	private void addCopyStatements(INakedClassifier classifier, OJClass owner, OJBlock body, boolean deep, boolean shallowCopy) {
		OJField change = new OJField();
		change.setName("change");
		OJPathName changeType = new OJPathName("java.util.List");
		owner.addToImports(changeType);
		owner.addToImports(new OJPathName("java.lang.ArrayList"));
		changeType.addToElementTypes(new OJPathName("java.lang.String"));
		change.setType(changeType);
		change.setInitExp("new ArrayList<String>()");
		body.addToLocals(change);
		List<? extends INakedProperty> properties = classifier.getEffectiveAttributes();
		for (int i = 0; i < properties.size(); i++) {
			IModelElement a = (IModelElement) properties.get(i);
			if (a instanceof INakedProperty) {
				INakedProperty np = (INakedProperty) a;
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
				if (!(np.isDerived() || np.isReadOnly() || (np.getOtherEnd() != null && np.getOtherEnd().isComposite()))) {
					if (np.getNakedBaseType() instanceof INakedSimpleType || np.getNakedBaseType() instanceof INakedEnumeration) {
						OJIfStatement ifNotNull = new OJIfStatement("from." + map.getter() + "() != null");
						ifNotNull.addToThenPart("to." + map.setter() + "(from." + map.getter() + "())");
						ifNotNull.addToThenPart("change.add(\""+map.getProperty().getMappingInfo().getPersistentName()+"\")");
						body.addToStatements(ifNotNull);
					}
				}
			}
		}
		body.addToStatements("to.auditVertex.setProperty(\"change\", change.toArray(new String[]{}))");
	}
}

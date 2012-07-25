package org.nakeduml.tinker.audit;

import java.util.List;

import nl.klasse.octopus.model.IModelElement;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedComplexStructure;
import org.eclipse.uml2.uml.INakedEntity;
import org.eclipse.uml2.uml.INakedEnumeration;
import org.eclipse.uml2.uml.INakedProperty;
import org.eclipse.uml2.uml.INakedSimpleType;
import org.eclipse.uml2.uml.INakedStructuredDataType;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.generated.OJVisibilityKindGEN;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;

@StepDependency(phase = TinkerAuditPhase.class, requires={TinkerAuditSuperTypeGenerator.class, TinkerAuditClassTransformation.class}, after={TinkerAuditSuperTypeGenerator.class, TinkerAuditClassTransformation.class})
public class TinkerAuditCopyMethodImplementor extends AbstractAuditJavaProducingVisitor {

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		OJPathName path = OJUtil.classifierPathname(c);
		OJClassifier myOwner = this.javaModel.findClass(path);
		// nakedModel.lookup(c.getPathName());
		if (myOwner instanceof OJClass && (c instanceof INakedEntity || c instanceof INakedStructuredDataType)) {
			INakedComplexStructure nc = (INakedComplexStructure) c;
			OJClass ojClass = (OJClass) myOwner;
			addShallowCopyStateMethod(nc, ojClass);
		}
	}

	private void addShallowCopyStateMethod(INakedClassifier classifier, OJClass owner) {
		OJOperation oper = new OJAnnotatedOperation("copyAuditShallowState");
		oper.setVisibility(OJVisibilityKindGEN.PUBLIC);
		oper.setName("copyAuditShallowState");
		oper.addParam("from", owner.getPathName());
		oper.addParam("to", owner.getPathName());
		addCopyStatements(classifier, owner, oper.getBody(), false, true);
		addGetOriginalUid(oper);
		owner.addToOperations(oper);
	}

	private void addGetOriginalUid(OJOperation oper) {
		oper.getBody().addToStatements("to.auditVertex.setProperty(\"" + TinkerGenerationUtil.ORIGINAL_UID + "\" , getUid())");
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
				if (!(np.isDerived() || np.isReadOnly() || (np.getOtherEnd() != null && np.getOtherEnd().isComposite()) || (np.getBaseType() instanceof INakedEnumeration) || (map.isMany() && map.elementTypeIsUmlPrimitive()))) {
					if (np.getNakedBaseType() instanceof INakedSimpleType || np.getNakedBaseType() instanceof INakedEnumeration) {
						//TODO check for manies that is empty before add change
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

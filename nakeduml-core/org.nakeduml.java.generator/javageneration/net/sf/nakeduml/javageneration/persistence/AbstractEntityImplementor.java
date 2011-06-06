package net.sf.nakeduml.javageneration.persistence;

import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedPowerType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.models.INakedModel;
import nl.klasse.octopus.model.IModelElement;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.IPersistentObject;

/**
 * This class builds all the operations specified by the AbstractEntity
 * interface. It also provides an implementation for the equals method that uses
 * the id of the instance involved
 */
public class AbstractEntityImplementor extends AbstractJavaProducingVisitor {
	private static final OJPathName ABSTRACT_ENTITY = new OJPathName(IPersistentObject.class.getName());

	@VisitBefore
	public void visitModel(INakedModel p) {
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		OJPathName path = OJUtil.classifierPathname(c);
		OJClassifier ojClassifier = super.findJavaClass(c);
		if (ojClassifier instanceof OJAnnotatedInterface) {
			if (c.getStereotype(StereotypeNames.HELPER) == null) {
				((OJAnnotatedInterface) ojClassifier).addToSuperInterfaces(ABSTRACT_ENTITY);
				ojClassifier.addToImports(ABSTRACT_ENTITY);
			}
		} else if (ojClassifier instanceof OJClass) {
			OJClass ojClass = (OJClass) ojClassifier;
			if (isPersistent(c)) {
				INakedComplexStructure entity = (INakedComplexStructure) c;
				ojClass.addToImports(ABSTRACT_ENTITY);
				if (entity.findAttribute("name") == null) {
					addGetName(entity, ojClass);
				}
				ojClass.addToImplementedInterfaces(ABSTRACT_ENTITY);
				if (entity instanceof INakedEntity) {
					addDiscriminatorInitialization((INakedEntity) entity, ojClass);
				}
			}
		}
	}


	private void addDiscriminatorInitialization(INakedEntity entity, OJClass ojClass) {
		List atr = entity.getAllAttributes();
		OJBlock dcBody = new OJBlock();
		for (int i = 0; i < atr.size(); i++) {
			IModelElement a = (IModelElement) atr.get(i);
			if (a instanceof INakedProperty) {
				INakedProperty attr = (INakedProperty) a;
				if (attr.isDiscriminator()) {
					INakedPowerType powerType = (INakedPowerType) attr.getNakedBaseType();
					if (entity.isPowerTypeInstance()) {
						INakedGeneralization generalization = entity.getNakedGeneralizations().iterator().next();
						String literal = powerType.getMappingInfo().getQualifiedJavaName() + "."
								+ generalization.getPowerTypeLiteral().getMappingInfo().getJavaName().getUpperCase();
						dcBody.addToStatements("set" + attr.getMappingInfo().getJavaName().getCapped() + "(" + literal + ")");
					}
				}
			}
		}
		ojClass.getDefaultConstructor().setBody(dcBody);
	}

	private void addGetName(INakedComplexStructure entity, OJClass ojClass) {
		OJOperation getName = new OJAnnotatedOperation();
		getName.setName("getName");
		getName.setReturnType(new OJPathName("String"));
		getName.setBody(new OJBlock());
		getName.getBody().addToStatements("return \"" + entity.getMappingInfo().getJavaName() + "[\"+getId()+\"]\"");
		ojClass.addToOperations(getName);
	}
}

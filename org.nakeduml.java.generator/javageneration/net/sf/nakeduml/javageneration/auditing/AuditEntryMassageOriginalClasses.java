package net.sf.nakeduml.javageneration.auditing;

import java.util.List;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.runtime.domain.AuditId;
import org.nakeduml.runtime.domain.Auditable;
import org.nakeduml.runtime.domain.Audited;

public class AuditEntryMassageOriginalClasses extends AbstractJavaProducingVisitor {
	@VisitBefore(matchSubclasses = true)
	public void visitClasses(INakedEntity entity) {
		if (isPersistent(entity) && OJUtil.hasOJClass(entity)) {
			OJPathName path = OJUtil.classifierPathname(entity);
			OJAnnotatedClass ojClass = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
			if (ojClass != null) {
				addMakeAuditCopyIdOnly(entity, ojClass);
				addMakeAuditCopyWithoutParentMethod(ojClass);
				addMakeAuditCopyMethod(entity, ojClass);
				// addEntityListenerAnnotation(ojClass);
			}
		}
	}

	private void addMakeAuditCopyIdOnly(INakedEntity entity, OJAnnotatedClass ojClass) {
		ojClass.addToImports(new OJPathName(AuditId.class.getName()));
		OJAnnotatedOperation makeAuditCopyIdOnly = new OJAnnotatedOperation();
		makeAuditCopyIdOnly.setName("makeAuditCopyIdOnly");
		OJPathName pathName = ojClass.getPathName();
		String remove = pathName.getNames().remove(pathName.getNames().size() - 1);
		pathName.getNames().add(remove + "_Audit");
		makeAuditCopyIdOnly.setReturnType(pathName);
		makeAuditCopyIdOnly.setAbstract(ojClass.isAbstract());
		if (!ojClass.isAbstract()) {
			OJBlock body = makeAuditCopyIdOnly.getBody();
			OJAnnotatedField result = new OJAnnotatedField();
			result.setName("result");
			result.setType(pathName);
			result.setInitExp("new " + pathName + "()");
			body.addToLocals(result);
			String rootClassName = entity.getMappingInfo().getJavaName().toString();
			OJSimpleStatement setId = new OJSimpleStatement("result.setId(new  AuditId(getId(), getObjectVersion()))");
			body.addToStatements(setId);
			body.addToStatements("result.setOriginal(new " + rootClassName + "())");
			body.addToStatements("result.getOriginal().setId(getId())");
			body.addToStatements("return result");
		}
		ojClass.addToOperations(makeAuditCopyIdOnly);
	}

	private void addMakeAuditCopyMethod(INakedEntity entity, OJAnnotatedClass ojClass) {
		OJOperation operation = new OJAnnotatedOperation();
		operation.setName("makeAuditCopy");
		OJPathName pathName = ojClass.getPathName();
		String remove = pathName.getNames().remove(pathName.getNames().size() - 1);
		pathName.getNames().add(remove + "_Audit");
		operation.setReturnType(new OJPathName(Audited.class.getName()));
		operation.setOwner(ojClass);
		operation.setAbstract(ojClass.isAbstract());
		if (!ojClass.isAbstract()) {
			OJBlock body = operation.getBody();
			OJAnnotatedField result = new OJAnnotatedField();
			result.setName("result");
			result.setType(pathName);
			result.setInitExp("makeAuditCopyWithoutParent()");
			body.addToLocals(result);
			List<? extends INakedProperty> attributes = entity.getEffectiveAttributes();
			for (INakedProperty property : attributes) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(property);
				if (!property.isDerived() && !property.isComposite() && isPersistent((property).getNakedBaseType())
						&& (map.isManyToOne() || map.isOne())) {
					OJIfStatement ifStatement = new OJIfStatement();
					ifStatement.setCondition(map.getter() + "() != null");
					OJBlock then = new OJBlock();
					OJSimpleStatement thenStatement = new OJSimpleStatement("result." + map.setter() + "(" + map.getter()
							+ "().makeAuditCopyIdOnly())");
					then.addToStatements(thenStatement);
					ifStatement.setThenPart(then);
					body.addToStatements(ifStatement);
				}
			}
			String rootClassName = entity.getMappingInfo().getJavaName().toString();
			body.addToStatements("result.setOriginal(new " + rootClassName + "())");
			body.addToStatements("result.getOriginal().setId(getId())");
			body.addToStatements("return result");
		}
	}

	private void addEntityListenerAnnotation(OJAnnotatedClass c) {
		OJAnnotationValue entityListener = new OJAnnotationValue(new OJPathName("javax.persistence.EntityListeners"));
		entityListener.addClassValue(new OJPathName("util.NakedUmlEJB3AuditListener"));
		c.addAnnotationIfNew(entityListener);
	}

	private void addMakeAuditCopyWithoutParentMethod(OJAnnotatedClass c) {
		OJOperation operation = new OJAnnotatedOperation();
		operation.setName("makeAuditCopyWithoutParent");
		OJPathName pathName = c.getPathName();
		String remove = pathName.getNames().remove(pathName.getNames().size() - 1);
		pathName.getNames().add(remove + "_Audit");
		operation.setReturnType(pathName);
		c.addToImports(pathName);
		operation.setOwner(c);
		operation.setAbstract(c.isAbstract());
		if (!c.isAbstract()) {
			OJBlock body = operation.getBody();
			OJAnnotatedField result = new OJAnnotatedField();
			result.setName("result");
			result.setType(pathName);
			result.setInitExp("new " + pathName + "()");
			body.addToLocals(result);
			// body.addToStatements("result.set"+c.getName()+"(this)");
			// body.addToStatements("result.setOriginal(this)");
			body.addToStatements("result.copyShallowState((" + c.getPathName() + ")this,result)");
			body.addToStatements("return result");
		}
		c.addToImplementedInterfaces(new OJPathName(Auditable.class.getName()));
	}
}

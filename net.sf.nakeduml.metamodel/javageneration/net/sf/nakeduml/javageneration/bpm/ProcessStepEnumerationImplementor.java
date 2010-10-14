package net.sf.nakeduml.javageneration.bpm;

import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJEnum;
import net.sf.nakeduml.javametamodel.annotation.OJEnumLiteral;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.util.AbstractProcessStep;

public abstract class ProcessStepEnumerationImplementor extends StereotypeAnnotator {

	protected abstract INakedElement getEnclosingElement(INakedElement s);

	protected OJEnum buildOJEnum(INakedClassifier c, boolean hasStateComposition) {
		OJEnum e = new OJEnum();
		e.addToImplementedInterfaces(ReflectionUtil.getUtilInterface(AbstractProcessStep.class));
		e.setName(((INakedBehavior) c).getMappingInfo().getJavaName().getAsIs() + "State");
		OJPackage p = findOrCreatePackage(OJUtil.packagePathname(c.getNameSpace()));
		p.addToClasses(e);
		super.createTextPath(e, JavaTextSource.GEN_SRC);
		OJOperation getParentState = new OJAnnotatedOperation();
		getParentState.setName("getParentState");
		getParentState.setReturnType(e.getPathName());
		e.addToOperations(getParentState);
		e.addToConstructors(new OJConstructor());
		OJOperation getQualifiedName = new OJAnnotatedOperation();
		getQualifiedName.setName("getQualifiedName");
		getQualifiedName.setReturnType(new OJPathName("String"));
		e.addToOperations(getQualifiedName);
		OJAnnotatedField persistentName = new OJAnnotatedField();
		persistentName.setName("persistentName");
		persistentName.setType(new OJPathName("String"));
		e.addToFields(persistentName);
		OJConstructor constructor = new OJConstructor();
		constructor.addParam("persistentName", new OJPathName("String"));
		constructor.getBody().addToStatements("this.persistentName=persistentName");
		e.addToConstructors(constructor);
		if (hasStateComposition) {
			OJAnnotatedField parent = new OJAnnotatedField();
			parent.setName("parent");
			parent.setType(e.getPathName());
			e.addToFields(parent);
			OJIfStatement ifParentNull = new OJIfStatement("parent==null", "return persistentName");
			ifParentNull.setElsePart(new OJBlock());
			ifParentNull.getElsePart().addToStatements("return parent.getQualifiedName()+ \"/\" + persistentName");
			getQualifiedName.getBody().addToStatements(ifParentNull);
			getParentState.getBody().addToStatements("return parent");
			OJConstructor constructor2 = constructor.getConstructorCopy();
			constructor2.addParam(parent.getName(), parent.getType());
			constructor2.getBody().addToStatements("this.parent=parent");
			e.addToConstructors(constructor2);
		} else {
			getParentState.getBody().addToStatements("return null");
			getQualifiedName.getBody().addToStatements("return persistentName");
		}
		OJOperation resolve = new OJAnnotatedOperation();
		resolve.setName("resolveByQualifiedName");
		resolve.setStatic(true);
		resolve.setReturnType(e.getPathName());
		resolve.addParam("qualifiedName", "String");
		OJForStatement forAll = new OJForStatement();
		forAll.setBody(new OJBlock());
		forAll.setElemName("s");
		forAll.setElemType(e.getPathName());
		forAll.setCollection("values()");
		resolve.getBody().addToStatements(forAll);
		OJIfStatement ifEquals = new OJIfStatement("s.getQualifiedName().equals(qualifiedName)", "return s");
		forAll.getBody().addToStatements(ifEquals);
		resolve.getBody().addToStatements("return null");
		e.addToOperations(resolve);
		return e;
	}

	protected void buildLiteral(INakedElement step, OJEnum e) {
		OJEnumLiteral l = new OJEnumLiteral();
		l.setName(BpmUtil.stepLiteralName(step));
		e.addToLiterals(l);
		OJAnnotatedField persistentName = new OJAnnotatedField();
		persistentName.setType(e.getPathName());
		persistentName.setName("persistentName");
		persistentName.setInitExp('"' + step.getMappingInfo().getPersistentName().getWithoutId().getAsIs() + '"');
		l.addToAttributeValues(persistentName);
		if (getEnclosingElement(step) != null) {
			OJAnnotatedField parent = new OJAnnotatedField();
			parent.setType(e.getPathName());
			parent.setName("parent");
			parent.setInitExp(BpmUtil.stepLiteralName(getEnclosingElement(step)));
			l.addToAttributeValues(parent);
		}
		applyStereotypesAsAnnotations(step, l);
	}
}

package org.opeum.javageneration.jbpm5;

import java.util.Collection;
import java.util.Iterator;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJConstructor;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.annotation.OJEnum;
import org.opeum.java.metamodel.annotation.OJEnumLiteral;
import org.opeum.javageneration.JavaSourceFolderIdentifier;
import org.opeum.javageneration.StereotypeAnnotator;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.javageneration.util.ReflectionUtil;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedStep;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.runtime.domain.IProcessStep;
import org.opeum.runtime.domain.TriggerMethod;


public abstract class ProcessStepEnumerationImplementor extends StereotypeAnnotator {
	protected abstract INakedStep getEnclosingElement(INakedElement step);
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}

	protected abstract Collection<INakedTrigger> getOperationTriggers(INakedElement step);
	protected OJEnum buildOJEnum(INakedClassifier c, boolean hasStateComposition) {
		OJEnum e = new OJEnum(((INakedBehavior) c).getMappingInfo().getJavaName().getAsIs() + "State");
		OJPathName abstractProcessStep = ReflectionUtil.getUtilInterface(IProcessStep.class);
		e.addToImplementedInterfaces(abstractProcessStep);
		OJPackage p = findOrCreatePackage(OJUtil.packagePathname(c.getNameSpace()));
		p.addToClasses(e);
		super.createTextPath(e, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		OJConstructor constructor = new OJConstructor();
		e.addToConstructors(constructor);
		OJUtil.addField(e, constructor, "parentState", abstractProcessStep);
		OJUtil.addField(e, constructor, "uuid", new OJPathName("String"));
		OJUtil.addField(e, constructor, "id", new OJPathName("long"));
		OJUtil.addField(e, constructor, "humanName", new OJPathName("String"));
		OJUtil.addField(e, constructor, "triggerMethods", new OJPathName(TriggerMethod.class.getName()+"[]"));
		e.addToImports(TriggerMethod.class.getName());
		// OJIfStatement ifParentNull = new OJIfStatement("parent==null", "return persistentName");
		// ifParentNull.setElsePart(new OJBlock());
		// ifParentNull.getElsePart().addToStatements("return parent.getQualifiedName()+ \"/\" + persistentName");
		// getQualifiedName.getBody().addToStatements(ifParentNull);
		OJOperation resolve = new OJAnnotatedOperation("resolveById");
		resolve.setStatic(true);
		resolve.setReturnType(e.getPathName());
		resolve.addParam("id", "long");
		OJForStatement forAll = new OJForStatement();
		forAll.setBody(new OJBlock());
		forAll.setElemName("s");
		forAll.setElemType(e.getPathName());
		forAll.setCollection("values()");
		resolve.getBody().addToStatements(forAll);
		OJIfStatement ifEquals = new OJIfStatement("s.getId()==id", "return s");
		forAll.getBody().addToStatements(ifEquals);
		resolve.getBody().addToStatements("return null");
		e.addToOperations(resolve);
		return e;
	}


	protected void buildLiteral(INakedStep step, OJEnum e) {
		OJEnumLiteral l = new OJEnumLiteral();
		l.setName(Jbpm5Util.stepLiteralName(step));
		e.addToLiterals(l);
		if (getEnclosingElement(step) != null) {
			OJUtil.addParameter(l, "parentState", Jbpm5Util.stepLiteralName(getEnclosingElement(step)));
		}else{
			OJUtil.addParameter(l, "parentState", "null");
		}
		OJUtil.addParameter(l, "uuid", '"' + step.getMappingInfo().getIdInModel()+ '"');
		OJUtil.addParameter(l, "id", step.getMappingInfo().getOpeumId().toString() + 'l');
		OJUtil.addParameter(l, "humanName", '"' + step.getMappingInfo().getJavaName().getCapped().getSeparateWords().getAsIs() + '"');
		OJUtil.addParameter(l, "triggerMethods", buildTriggerMethodParameter(getOperationTriggers(step)));
		applyStereotypesAsAnnotations(step, l);
	}
	
	private String buildTriggerMethodParameter(Collection<INakedTrigger> methodTriggers) {
		StringBuilder sb = new StringBuilder("new TriggerMethod[]{");
		Iterator<INakedTrigger> iter = methodTriggers.iterator();
		while(iter.hasNext()){
			INakedTrigger t = iter.next();
			sb.append("new TriggerMethod(");
			sb.append(t.isHumanTrigger());
			sb.append(",\"");
			sb.append(t.getEvent().getMappingInfo().getJavaName().getCapped().getSeparateWords().getAsIs());
			sb.append("\",\"");
			sb.append(t.getEvent().getName());
			sb.append("\")");
			if(iter.hasNext()){
				sb.append(',');
			}
		}
		sb.append('}');
		return sb.toString();
	}
}

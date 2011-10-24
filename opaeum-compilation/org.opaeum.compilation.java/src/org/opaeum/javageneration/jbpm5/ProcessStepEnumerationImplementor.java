package org.opaeum.javageneration.jbpm5;

import java.util.Collection;
import java.util.Iterator;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.java.metamodel.annotation.OJEnumLiteral;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.javageneration.util.ReflectionUtil;
import org.opaeum.metamodel.commonbehaviors.INakedStep;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opeum.runtime.domain.IProcessStep;
import org.opeum.runtime.domain.TriggerMethod;

public abstract class ProcessStepEnumerationImplementor extends StereotypeAnnotator{
	protected abstract INakedStep getEnclosingElement(INakedElement step);
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
	protected abstract Collection<INakedTrigger> getOperationTriggers(INakedElement step);
	protected OJEnum buildOJEnum(INakedClassifier c, boolean hasStateposition) {
		OJEnum e = new OJEnum(c.getMappingInfo().getJavaName().getAsIs() + "State");
		OJPathName abstractProcessStep = ReflectionUtil.getUtilInterface(IProcessStep.class);
		e.addToImplementedInterfaces(abstractProcessStep);
		OJPackage p = findOrCreatePackage(OJUtil.packagePathname(c.getNameSpace()));
		p.addToClasses(e);
		super.createTextPath(e, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC).setDependsOnVersion(true);
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
	protected void buildLiteral(INakedStep step,OJEnum e,String parentLiteral){
		OJEnumLiteral l = new OJEnumLiteral();
		l.setName(Jbpm5Util.stepLiteralName(step));
		e.addToLiterals(l);
		if(getEnclosingElement(step) != null){
			OJUtil.addParameter(l, "parentState", parentLiteral);
		}else{
			OJUtil.addParameter(l, "parentState", "null");
		}
		OJUtil.addParameter(l, "uuid", '"' + step.getMappingInfo().getIdInModel() + '"');
		OJUtil.addParameter(l, "id", step.getMappingInfo().getOpaeumId().toString() + 'l');
		OJUtil.addParameter(l, "humanName", '"' + step.getMappingInfo().getJavaName().getCapped().getSeparateWords().getAsIs() + '"');
		OJUtil.addParameter(l, "triggerMethods", buildTriggerMethodParameter(getOperationTriggers(step)));
		applyStereotypesAsAnnotations(step, l);
	}
	private String buildTriggerMethodParameter(Collection<INakedTrigger> methodTriggers){
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

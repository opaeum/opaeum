package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.Iterator;

import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJEnum;
import org.nakeduml.java.metamodel.annotation.OJEnumLiteral;
import org.nakeduml.name.NameConverter;
import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.TriggerMethod;


public abstract class ProcessStepEnumerationImplementor extends StereotypeAnnotator {
	protected abstract INakedElement getEnclosingElement(INakedElement step);
	protected abstract Collection<INakedTrigger> getMethodTriggers(INakedElement step);
	interface x{
		
	}
	enum en implements x{
		
	}
	protected OJEnum buildOJEnum(INakedClassifier c, boolean hasStateComposition) {
		OJEnum e = new OJEnum();
		OJPathName abstractProcessStep = ReflectionUtil.getUtilInterface(IProcessStep.class);
		e.addToImplementedInterfaces(abstractProcessStep);
		e.setName(((INakedBehavior) c).getMappingInfo().getJavaName().getAsIs() + "State");
		OJPackage p = findOrCreatePackage(OJUtil.packagePathname(c.getNameSpace()));
		p.addToClasses(e);
		super.createTextPath(e, JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
		OJConstructor constructor = new OJConstructor();
		e.addToConstructors(constructor);
		OJUtil.addField(e, constructor, "parentState", abstractProcessStep);
		OJUtil.addField(e, constructor, "persistentName", new OJPathName("String"));
		OJUtil.addField(e, constructor, "id", new OJPathName("long"));
		OJUtil.addField(e, constructor, "humanName", new OJPathName("String"));
		OJUtil.addField(e, constructor, "triggerMethods", new OJPathName(TriggerMethod.class.getName()+"[]"));
		e.addToImports(TriggerMethod.class.getName());
		OJOperation getQualifiedName = new OJAnnotatedOperation();
		getQualifiedName.setName("getQualifiedName");
		getQualifiedName.setReturnType(new OJPathName("String"));
		e.addToOperations(getQualifiedName);
		// OJIfStatement ifParentNull = new OJIfStatement("parent==null", "return persistentName");
		// ifParentNull.setElsePart(new OJBlock());
		// ifParentNull.getElsePart().addToStatements("return parent.getQualifiedName()+ \"/\" + persistentName");
		// getQualifiedName.getBody().addToStatements(ifParentNull);
		getQualifiedName.getBody().addToStatements("return persistentName");
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
		OJAnnotatedOperation getNakedUmlId = new OJAnnotatedOperation("getNakedUmlId",new OJPathName("int"));
		getNakedUmlId.getBody().addToStatements("return (int)getId()");
		e.addToOperations(getNakedUmlId);
		return e;
	}


	protected void buildLiteral(INakedElement step, OJEnum e) {
		OJEnumLiteral l = new OJEnumLiteral();
		l.setName(Jbpm5Util.stepLiteralName(step));
		e.addToLiterals(l);
		if (getEnclosingElement(step) != null) {
			OJUtil.addParameter(l, "parentState", Jbpm5Util.stepLiteralName(getEnclosingElement(step)));
		}else{
			OJUtil.addParameter(l, "parentState", "null");
		}
		OJUtil.addParameter(l, "persistentName", '"' + step.getMappingInfo().getQualifiedPersistentName() + '"');
		OJUtil.addParameter(l, "id", step.getMappingInfo().getNakedUmlId().toString() + 'l');
		OJUtil.addParameter(l, "humanName", '"' + step.getMappingInfo().getJavaName().getCapped().getSeparateWords().getAsIs() + '"');
		OJUtil.addParameter(l, "triggerMethods", buildTriggerMethodParameter(getMethodTriggers(step)));
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

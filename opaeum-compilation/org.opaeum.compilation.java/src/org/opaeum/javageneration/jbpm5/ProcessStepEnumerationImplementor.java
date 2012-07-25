package org.opaeum.javageneration.jbpm5;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.emf.workspace.DefaultOpaeumComparator;
import org.opaeum.emf.workspace.EmfWorkspace;
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
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

public abstract class ProcessStepEnumerationImplementor extends StereotypeAnnotator{
	protected abstract NamedElement getEnclosingElement(NamedElement step);
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
	protected abstract Collection<Trigger> getOperationTriggers(Element step);
	protected OJEnum buildOJEnum(Classifier c, boolean hasStateposition) {
		OJEnum e = new OJEnum(c.getName() + "State");
		OJPathName abstractProcessStep = ReflectionUtil.getUtilInterface(IProcessStep.class);
		e.addToImplementedInterfaces(abstractProcessStep);
		OJPackage p = findOrCreatePackage(OJUtil.packagePathname(c.getNamespace()));
		p.addToClasses(e);
		super.createTextPath(e, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC).setDependsOnVersion(true);
		OJConstructor constructor = new OJConstructor();
		e.addToConstructors(constructor);
		OJUtil.addField(e, constructor, "parentState", abstractProcessStep);
		OJUtil.addField(e, constructor, "uuid", new OJPathName("String"));
		OJUtil.addField(e, constructor, "id", new OJPathName("long"));
		OJAnnotatedOperation getUid = new OJAnnotatedOperation("getUid", new OJPathName("String"));
		getUid.initializeResultVariable("getUuid()");
		e.addToOperations(getUid);
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
	protected void buildLiteral(NamedElement step,OJEnum e,String parentLiteral){
		OJEnumLiteral l = new OJEnumLiteral();
		l.setName(Jbpm5Util.stepLiteralName(step));
		e.addToLiterals(l);
		if(getEnclosingElement(step) != null){
			OJUtil.addParameter(l, "parentState", parentLiteral);
		}else{
			OJUtil.addParameter(l, "parentState", "null");
		}
		OJUtil.addParameter(l, "uuid", '"' + EmfWorkspace.getId(step)+ '"');
		OJUtil.addParameter(l, "id", EmfWorkspace.getOpaeumId(step) + "l");
		OJUtil.addParameter(l, "humanName", '"' + NameConverter.separateWords(NameConverter.capitalize(step.getName())) + '"');
		OJUtil.addParameter(l, "triggerMethods", buildTriggerMethodParameter(getOperationTriggers(step)));
		applyStereotypesAsAnnotations(step, l);
	}
	private String buildTriggerMethodParameter(Collection<Trigger> methodTriggers){
		SortedSet<Trigger> sortedSet = new TreeSet<Trigger>(new DefaultOpaeumComparator());
		sortedSet.addAll(methodTriggers);
		StringBuilder sb = new StringBuilder("new TriggerMethod[]{");
		Iterator<Trigger> iter = sortedSet.iterator();
		while(iter.hasNext()){
			Trigger t = iter.next();
			sb.append("new TriggerMethod(");
			sb.append(EmfBehaviorUtil.isHumanTrigger(t));
			sb.append(",\"");
			sb.append( NameConverter.separateWords(NameConverter.capitalize(t.getEvent().getName())));
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

package org.opaeum.eclipse.starter;

import java.util.Set;

import org.opaeum.eclipse.javasync.JavaTransformationProcessManager;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.IntegrationCodeGenerator;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class GeneratorGenerator extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	@VisitBefore
	public void visitWorkspace(EmfWorkspace workspace){
		OJPackage pkg = findOrCreatePackage(new OJPathName(config.getMavenGroupId() + ".generator"));
		buildGeneratorClass(pkg);
	}
	// TODO generate example Java step
	OJAnnotatedClass buildGeneratorClass(OJPackage genPackage){
		OJAnnotatedClass c = new OJAnnotatedClass(NameConverter.capitalize(workspace.getIdentifier()) + "Generator");
		genPackage.addToClasses(c);
		OJConstructor constr = new OJConstructor();
		constr.addParam("outputRoot", new OJPathName("String"));
		constr.addParam("modelFile", new OJPathName("String"));
		c.addToConstructors(constr);
		constr.getBody().addToStatements("super(outputRoot,modelFile)");
		super.createTextPath(c, GeneratorSourceFolderIdentifier.GENERATOR_SRC);
		addMainOperation(c);
		OJPathName setOfSteps = new OJPathName("java.util.Set");
		OJPathName clazzPathName = new OJPathName("java.lang.Class");
		OJPathName transStepPathName = new OJPathName(ITransformationStep.class.getName());
		c.addToImports(transStepPathName);
		clazzPathName.addToElementTypes(new OJPathName("? extends ITransformationStep"));
		setOfSteps.addToElementTypes(clazzPathName);
		addGetSteps(c, setOfSteps, JavaTransformationProcessManager.getBasicSteps(), "getSteps");
		addGetSteps(c, setOfSteps, JavaTransformationProcessManager.getBasicIntegrationSteps(), "getIntegrationSteps");
		return c;
	}
	private void addGetSteps(OJAnnotatedClass c,OJPathName setOfSteps,Set<Class<? extends ITransformationStep>> set,String operNAme){
		OJAnnotatedOperation getSteps = new OJAnnotatedOperation(operNAme, setOfSteps);
		c.addToOperations(getSteps);
		StringBuilder sb = new StringBuilder("return toSet(");
		for(Class<? extends ITransformationStep> class1:set){
			sb.append(class1.getName() + ".class,");
		}
		sb.setCharAt(sb.length() - 1, ')');
		getSteps.getBody().addToStatements(sb.toString());
	}
	private void addMainOperation(OJAnnotatedClass c){
		OJOperation oper = OJUtil.buildMain(c);
		OJPathName ioFile = new OJPathName("java.io.File");
		c.addToImports(ioFile);
		OJBlock block1 = new OJBlock();
		oper.getBody().addToStatements(block1);
		OJAnnotatedField workspaceFile = new OJAnnotatedField("workspaceFile", ioFile);
		workspaceFile.setInitExp("null");
		block1.addToLocals(workspaceFile);
		OJIfStatement ifArgs = new OJIfStatement("args.length>0", "workspaceFile=new File(args[0])");
		block1.addToStatements(ifArgs);
		ifArgs.setElsePart(new OJBlock());
		ifArgs.getElsePart().addToStatements("workspaceFile=new File(\".\").getAbsoluteFile().getParentFile().getParentFile().getParentFile()");
		OJBlock block2 = new OJBlock();
		oper.getBody().addToStatements(block2);
		OJAnnotatedField instance = new OJAnnotatedField("instance", c.getPathName());
		StringBuilder str = new StringBuilder("new " + c.getName() + "(");
		str.append("workspaceFile.getAbsolutePath() +\"/");
		str.append(workspace.getIdentifier());
		str.append("\",workspaceFile.getAbsolutePath()+\"");
		str.append(workspace.getDirectoryUri().toPlatformString(true));
		str.append("\")");
		instance.setInitExp(str.toString());
		block2.addToLocals(instance);
		oper.getBody().addToStatements("instance.transformDirectory()");
		oper.getBody().addToStatements("instance.generateIntegrationCode()");
		oper.addToThrows(new OJPathName("Exception"));
		c.addToOperations(oper);
	}
}

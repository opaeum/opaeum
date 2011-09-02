package org.nakeduml.eclipse.starter;

import java.util.Set;

import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.IntegrationCodeGenerator;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.MavenProjectCodeGenerator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.name.NameConverter;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class GeneratorGenerator extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace workspace){
		OJAnnotatedPackage pkg = findOrCreatePackage(new OJPathName(config.getMavenGroupId() + ".generator"));
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
		c.setSuperclass(new OJPathName(MavenProjectCodeGenerator.class.getName()));
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
		StringBuffer sb = new StringBuffer("return toSet(");
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
		StringBuffer str = new StringBuffer("new " + c.getName() + "(");
		str.append("workspaceFile.getAbsolutePath() +\"/");
		str.append(workspace.getIdentifier());
		str.append("\",workspaceFile.getAbsolutePath()+\"");
		str.append(NakedUmlEditor.getCurrentContext().getUmlElementCache().getCurrentEmfWorkspace().getDirectoryUri().toPlatformString(true));
		str.append("\")");
		instance.setInitExp(str.toString());
		block2.addToLocals(instance);
		oper.getBody().addToStatements("instance.transformDirectory()");
		oper.getBody().addToStatements("instance.generateIntegrationCode()");
		oper.addToThrows(new OJPathName("Exception"));
		c.addToOperations(oper);
	}
}

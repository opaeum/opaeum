package net.sf.nakeduml.javageneration.seam;

import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {BasicJavaModelStep.class, ExtendedCompositionSemantics.class},after = {BasicJavaModelStep.class,
		OclExpressionExecution.class, ExtendedCompositionSemantics.class})
public class SeamSupport extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		SeamAnnotator ea = new SeamAnnotator();
		ea.initialize(workspace, javaModel, config, textWorkspace);
		ea.startVisiting(workspace);
		
//		SeamObjectFactoryMaker sef = new SeamObjectFactoryMaker();
//		sef.initialize(workspace, javaModel, config, textWorkspace);
//		sef.startVisiting(workspace);		
		
//		SeamListFilterSupport slfs = new SeamListFilterSupport();
//		slfs.initialize(workspace, javaModel, config, textWorkspace);
//		slfs.startVisiting(workspace);

		SeamNavigationSupport sns = new SeamNavigationSupport();
		sns.initialize(workspace, javaModel, config, textWorkspace);
		sns.startVisiting(workspace);
		
//		SeamListNavigationSupport slns = new SeamListNavigationSupport();
//		slns.initialize(workspace, javaModel, config, textWorkspace);
//		slns.startVisiting(workspace);

		SeamSecuritySupport ss = new SeamSecuritySupport();
		ss.initialize(workspace, javaModel, config, textWorkspace);
		ss.startVisiting(workspace);
		
//		BooleanFilterMaker bfm = new BooleanFilterMaker();
//		bfm.initialize(workspace, javaModel, config, textWorkspace);
//		bfm.startVisiting(workspace);

		
		DataModelWrapper dmw=new DataModelWrapper();
		dmw.initialize(workspace, javaModel, config, textWorkspace);
		dmw.startVisiting(workspace);
		OJPackage util = javaModel.findPackage(UtilityCreator.getUtilPathName());
		addHashSetDataModel(ea, util, "java.util.HashSet", "SetDataModel", Set.class);
		addHashSetDataModel(ea, util, "java.util.ArrayList", "ListDataModel", List.class);

	}
	private void addHashSetDataModel(SeamAnnotator ea,OJPackage util,String setName,String dataModel,Class collectionInterface){
		OJAnnotatedClass setWithModel = new OJAnnotatedClass();
		OJConstructor c = new OJConstructor();
		setWithModel.addGenericTypeParam("T");
		c.getBody().addToStatements("this.source=source");
		setWithModel.addToConstructors(c);
		OJField source = new OJField();
		source.setName("source");
		OJPathName interfacePathNAe = new OJPathName(collectionInterface.getSimpleName());
		interfacePathNAe.addToElementTypes(new OJPathName("T"));
		source.setType(interfacePathNAe);
		setWithModel.addToFields(source);
		OJPathName setPath = new OJPathName(setName);
		c.addParam("source", collectionInterface.getSimpleName());
		setWithModel.addToImports(setPath);
		setWithModel.addToImports(collectionInterface.getName());
		OJPathName hashSet = new OJPathName(setPath.getLast() + "<T>");
		setWithModel.setSuperclass(hashSet);
		setWithModel.setName(setPath.getLast() + "WithModel");
		util.addToClasses(setWithModel);
		ea.createTextPath(setWithModel, JavaTextSource.GEN_SRC);
		OJOperation getDataModel = new OJOperation();
		getDataModel.setName("getDataModel");
		getDataModel.setReturnType(new OJPathName("org.jboss.seam.jsf." + dataModel));
		getDataModel.getBody().addToStatements("return new " + dataModel + "(this)");
		setWithModel.addToOperations(getDataModel);
		ReflectionUtil.addOperationsFromJava(collectionInterface, setWithModel, "source",null);
	}
	
}

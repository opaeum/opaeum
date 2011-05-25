package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.linkage.MappedTypeLinker;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.linkage.ReferenceResolver;
import net.sf.nakeduml.linkage.TypeResolver;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.strategies.DateTimeStrategyFactory;
import net.sf.nakeduml.strategies.IdStrategyFactory;
import net.sf.nakeduml.strategies.TextStrategyFactory;
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		TextFileGenerator.class,MappedTypeLinker.class,ReferenceResolver.class,JavaNameRegenerator.class,TypeResolver.class,RedefinitionAndUnions.class,
		ProcessIdentifier.class
})
public class Java5ModelGenerationStep extends AbstractJavaTransformationStep{
	static{
		// Because of eclipse classloading issues
		MappedTypeLinker.registerStrategyFactory(new DateTimeStrategyFactory());
		MappedTypeLinker.registerStrategyFactory(new TextStrategyFactory());
		MappedTypeLinker.registerStrategyFactory(new IdStrategyFactory());
	}
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		StdlibMap.javaRealType.replaceTail("double");
		StdlibMap.javaRealObjectType.replaceTail("Double");
		// INakedPackage umlModel = workspace.getEntryModel();
		Java5ModelGenerator maker = new Java5ModelGenerator();
		maker.initialize(javaModel, config, textWorkspace, context);
		maker.startVisiting(workspace);
		SuperTypeGenerator superTypeAdder = new SuperTypeGenerator();
		superTypeAdder.initialize(javaModel, config, textWorkspace, context);
		superTypeAdder.startVisiting(workspace);
	}
}

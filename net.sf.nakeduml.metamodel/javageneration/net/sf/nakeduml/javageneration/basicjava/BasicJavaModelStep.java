package net.sf.nakeduml.javageneration.basicjava;

import java.util.Collections;
import java.util.Set;

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
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

@StepDependency(phase = JavaTransformationPhase.class,requires = {TextFileGenerator.class,MappedTypeLinker.class,ReferenceResolver.class,
		JavaNameRegenerator.class,TypeResolver.class,RedefinitionAndUnions.class,ProcessIdentifier.class})
public class BasicJavaModelStep extends AbstractJavaTransformationStep{
	public BasicJavaModelStep(){
		super();
	}
	public Set<String> getOptionalDependencies(){
		return Collections.emptySet();
	}
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		StdlibMap.javaRealType.replaceTail("double");
		StdlibMap.javaRealObjectType.replaceTail("Double");

//		INakedPackage umlModel = workspace.getEntryModel();
		Java5ModelGenerator maker = new Java5ModelGenerator();
		maker.initialize(javaModel, config, textWorkspace, context);
		maker.startVisiting(workspace);
		SuperTypeGenerator superTypeAdder = new SuperTypeGenerator();
		superTypeAdder.initialize(javaModel, config, textWorkspace, context);
		superTypeAdder.startVisiting(workspace);
		AttributeImplementor ai = new AttributeImplementor();
		ai.initialize(javaModel, config, textWorkspace, context);
		ai.startVisiting(workspace);

		OperationAnnotator operationAnnotator = new OperationAnnotator();
		operationAnnotator.initialize(javaModel, config, textWorkspace, context);
		operationAnnotator.startVisiting(workspace);


		ToXmlStringBuilder txsb = new ToXmlStringBuilder();
		txsb.initialize(javaModel, config, textWorkspace, context);
		txsb.startVisiting(workspace);
		
		ToStringBuilder tsb = new ToStringBuilder();
		tsb.initialize(javaModel, config, textWorkspace, context);
		tsb.startVisiting(workspace);

		
		EnumerationLiteralImplementor eli = new EnumerationLiteralImplementor();
		eli.initialize(javaModel, config, textWorkspace, context);
		eli.startVisiting(workspace);

		SimpleActivityMethodImplementor sami = new SimpleActivityMethodImplementor();
		sami.initialize(javaModel, config, textWorkspace, context);
		sami.startVisiting(workspace);
		
		HierarchicalSourcePopulationImplementor hsi = new HierarchicalSourcePopulationImplementor();
		hsi.initialize(javaModel, config, textWorkspace, context);
		hsi.startVisiting(workspace);

		HashcodeBuilder hcb = new HashcodeBuilder();
		hcb.initialize(javaModel, config, textWorkspace, context);
		hcb.startVisiting(workspace);


	}
}

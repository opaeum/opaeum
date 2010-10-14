package net.sf.nakeduml.javageneration.basicjava;

import java.util.Collections;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.linkage.MappedTypeLinker;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.linkage.ReferenceResolver;
import net.sf.nakeduml.linkage.TypeResolver;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

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
		UtilityCreator utilMaker = new UtilityCreator();
		UtilityCreator.setUtilPathName(new OJPathName(workspace.getName() + ".util"));
		StdlibMap.javaRealType.replaceTail("double");
		StdlibMap.javaRealObjectType.replaceTail("Double");

//		INakedPackage umlModel = workspace.getEntryModel();
		Java5ModelGenerator maker = new Java5ModelGenerator();
		maker.initialize(workspace, javaModel, config, textWorkspace);
		maker.startVisiting(workspace);
		utilMaker.makeUtilPack(javaModel);
		SuperTypeGenerator superTypeAdder = new SuperTypeGenerator();
		superTypeAdder.initialize(workspace, javaModel, config, textWorkspace);
		superTypeAdder.startVisiting(workspace);
		AttributeImplementor ai = new AttributeImplementor();
		ai.initialize(workspace, javaModel, config, textWorkspace);
		ai.startVisiting(workspace);

		OperationAnnotator operationAnnotator = new OperationAnnotator();
		operationAnnotator.initialize(workspace, javaModel, config, textWorkspace);
		operationAnnotator.startVisiting(workspace);


//		AnotherInterfaceAttributeImplementor aiai = new AnotherInterfaceAttributeImplementor();
//		aiai.initialize(workspace, javaModel, config, textWorkspace);
//		aiai.startVisiting(workspace);
		
		ToXmlStringBuilder txsb = new ToXmlStringBuilder();
		txsb.initialize(workspace, javaModel, config, textWorkspace);
		txsb.startVisiting(workspace);
		ToStringBuilder tsb = new ToStringBuilder();
		tsb.initialize(workspace, javaModel, config, textWorkspace);
		tsb.startVisiting(workspace);
		// ImplementedInterfacesGenerator implementedInterfacesAdder = new
		// ImplementedInterfacesGenerator(javaModel);
		// umlModel.accept(implementedInterfacesAdder);
		EnumerationLiteralImplementor eli = new EnumerationLiteralImplementor();
		eli.initialize(workspace, javaModel, config, textWorkspace);
		eli.startVisiting(workspace);

		SimpleActivityMethodImplementor sami = new SimpleActivityMethodImplementor();
		sami.initialize(workspace, javaModel, config, textWorkspace);
		sami.startVisiting(workspace);
		
		HierarchicalSourcePopulationImplementor hsi = new HierarchicalSourcePopulationImplementor();
		hsi.initialize(workspace, javaModel, config, textWorkspace);
		hsi.startVisiting(workspace);
		
	}
}

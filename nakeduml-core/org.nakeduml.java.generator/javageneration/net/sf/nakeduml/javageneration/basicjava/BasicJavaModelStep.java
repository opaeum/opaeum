package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.linkage.MappedTypeLinker;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.linkage.ReferenceResolver;
import net.sf.nakeduml.linkage.TypeResolver;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	AttributeImplementationStep.class,
},after = {
	AttributeImplementationStep.class
})
public class BasicJavaModelStep extends AbstractJavaTransformationStep{

	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
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
		
		PersistentNameMapGenerator pnmg=new PersistentNameMapGenerator(false);
		pnmg.initialize(javaModel, config, textWorkspace, context);
		pnmg.startVisiting(workspace);
	}
}

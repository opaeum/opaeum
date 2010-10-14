package net.sf.nakeduml.javageneration.oclexpressions;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

@StepDependency(phase = JavaTransformationPhase.class, requires = { BasicJavaModelStep.class, NakedParsedOclStringResolver.class }, after = { BasicJavaModelStep.class })
public class OclExpressionExecution extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		OclUtilityCreator ouc = new OclUtilityCreator(javaModel);
		ouc.makeOclUtilities(null, workspace.getOclEngine().getOclLibrary());
		AttributeExpressionGenerator attrExpressionAdder = new AttributeExpressionGenerator();
		attrExpressionAdder.initialize(workspace, javaModel, config, textWorkspace);
		attrExpressionAdder.startVisiting(workspace);
		OperationExpressionGenerator operExpressionAdder = new OperationExpressionGenerator();
		operExpressionAdder.initialize(workspace, javaModel, config, textWorkspace);
		operExpressionAdder.startVisiting(workspace);
		InvariantsGenerator invariantsAdder = new InvariantsGenerator();
		invariantsAdder.initialize(workspace, javaModel, config, textWorkspace);
		invariantsAdder.startVisiting(workspace);
		ConstrainedImplementor ci = new ConstrainedImplementor();
		ci.initialize(workspace, javaModel, config, textWorkspace);
		ci.startVisiting(workspace);		
		CodeCleanup cc = new CodeCleanup();
		cc.initialize(workspace, javaModel, config, textWorkspace);
		cc.startVisiting(workspace);
		OJPackage util= javaModel.findPackage(UtilityCreator.getUtilPathName());
		for(OJClassifier c:util.getClasses()){			
			cc.createTextPath(c, JavaTextSource.GEN_SRC);
		}
	}
}

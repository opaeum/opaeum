package net.sf.nakeduml.javageneration.oclexpressions;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

@StepDependency(phase = JavaTransformationPhase.class, requires = { BasicJavaModelStep.class, NakedParsedOclStringResolver.class }, after = { BasicJavaModelStep.class })
public class OclExpressionExecution extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		AttributeExpressionGenerator attrExpressionAdder = new AttributeExpressionGenerator();
		attrExpressionAdder.initialize(javaModel, config, textWorkspace, context);
		attrExpressionAdder.startVisiting(workspace);
		PreAndPostConditionGenerator operExpressionAdder = new PreAndPostConditionGenerator();
		operExpressionAdder.initialize(javaModel, config, textWorkspace, context);
		operExpressionAdder.startVisiting(workspace);
		InvariantsGenerator invariantsAdder = new InvariantsGenerator();
		invariantsAdder.initialize(javaModel, config, textWorkspace, context);
		invariantsAdder.startVisiting(workspace);
		ConstrainedImplementor ci = new ConstrainedImplementor();
		ci.initialize(javaModel, config, textWorkspace, context);
		ci.startVisiting(workspace);
		UtilCreator uc=new UtilCreator();
		uc.initialize(javaModel, config, textWorkspace, context);
		uc.startVisiting(workspace);
		CodeCleanup cc = new CodeCleanup();
		cc.initialize(javaModel, config, textWorkspace, context);
		cc.startVisiting(workspace);
	}

	public class UtilCreator extends AbstractJavaProducingVisitor {
		@VisitBefore
		public void visitModel(INakedModel pkg) {
			OclUtilityCreator ouc = new OclUtilityCreator(javaModel);
			ouc.makeOclUtilities(null, workspace.getOclEngine().getOclLibrary());
			for (OJClassifier c : UtilityCreator.getUtilPack().getClasses()) {
				createTextPath(c, JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
			}
		}
	}
}

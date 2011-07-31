package net.sf.nakeduml.javageneration.auditing;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationStep;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

@PhaseDependency(after = {
	JavaTransformationPhase.class
},before = {
	FileGenerationPhase.class
})
public class AuditGenerationPhase implements TransformationPhase<AbstractJavaTransformationStep,INakedElement>{
	private NakedUmlConfig config;
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	INakedModelWorkspace workspace;
	@InputModel
	OJAnnotatedPackage javaModel;
	@Override
	public void initialize(NakedUmlConfig config){
		this.config = config;
	}
	@Override
	public Object[] execute(List<AbstractJavaTransformationStep> features,TransformationContext context){
		for(JavaTransformationStep a:features){
			if(a instanceof AbstractJavaProducingVisitor){
				a.initialize(javaModel, config, textWorkspace, context);
				((AbstractJavaProducingVisitor) a).startVisiting(workspace);
			}
		}
		return new Object[]{
			javaModel
		};
	}
	@Override
	public Collection<Object> processElements(TransformationContext context,Collection<INakedElement> element){
		// TODO Auto-generated method stub
		return null;
	}
}

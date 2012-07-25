package org.nakeduml.tinker.audit;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.uml2.uml.INakedElement;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.filegeneration.FileGenerationPhase;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.generated.OJElementGEN;
import org.opaeum.javageneration.IntegrationCodeGenerator;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.JavaTransformationStep;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.visitor.TextFileGeneratingVisitor;

@PhaseDependency(after = { JavaTransformationPhase.class }, before={FileGenerationPhase.class})
public class TinkerAuditPhase implements TransformationPhase<JavaTransformationStep,INakedElement> {

	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	OJWorkspace javaModel;
	@InputModel
	private ModelWorkspace modelWorkspace;
	private List<JavaTransformationStep> features;
	private OpaeumConfig config;

	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		return null;
	}
	@Override
	public void execute(TransformationContext context){
		OJUtil.clearCache();
		context.getLog().registerInstanceCountMap(OJElementGEN.counts);
		context.getLog().registerInstanceCountMap(TextOutputNode.counts);
		context.getLog().startTask("Generating Java Model", features.size());
		for(JavaTransformationStep f:features){
			context.getLog().startStep("Executing " + f.getClass().getSimpleName());
			boolean matchesPhase = true;
			if(context.isIntegrationPhase()){
				matchesPhase = f instanceof IntegrationCodeGenerator;
			}
			if(f instanceof TextFileGeneratingVisitor && !context.getLog().isCanceled() && matchesPhase){
				// Remember HibernateConfigGenerator
				TextFileGeneratingVisitor v = (TextFileGeneratingVisitor) f;
				f.setTransformationContext(context);
				v.startVisiting(this.modelWorkspace);
			}
			context.featureApplied(f.getClass());
			context.getLog().endLastStep();
		}
		context.getLog().endLastTask();
		OJUtil.clearCache();
	}
	@Override
	public void initialize(OpaeumConfig config,List<JavaTransformationStep> features){
		this.config = config;
		this.features = features;
	}
	public void initializeSteps(){
		OJUtil.clearCache();
		for(JavaTransformationStep f:this.features){
			f.initialize(javaModel, this.config, textWorkspace, modelWorkspace);
		}
	}
	@Override
	public Collection<JavaTransformationStep> getSteps(){
		return this.features;
	}
	@Override
	public void release(){
		this.textWorkspace=null;
		this.javaModel=null;
		this.modelWorkspace=null;
		for(JavaTransformationStep j:this.features){
			j.release();
		}
		UtilityCreator.setUtilPackage(null);
	}
}

package org.opeum.javageneration;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opeum.feature.InputModel;
import org.opeum.feature.IntegrationPhase;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.PhaseDependency;
import org.opeum.feature.TransformationContext;
import org.opeum.feature.TransformationPhase;
import org.opeum.filegeneration.FileGenerationPhase;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.LinkagePhase;
import org.opeum.metamodel.bpm.INakedEmbeddedTask;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedEvent;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedPackage;
import org.opeum.metamodel.visitor.NakedElementOwnerVisitor;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.textmetamodel.TextOutputNode;
import org.opeum.textmetamodel.TextWorkspace;
import org.opeum.validation.ValidationPhase;
import org.opeum.validation.namegeneration.NameGenerationPhase;

import org.opeum.java.metamodel.OJPackage;

@PhaseDependency(after = {
		LinkagePhase.class,NameGenerationPhase.class,ValidationPhase.class
},before = {
	FileGenerationPhase.class
})
public class JavaTransformationPhase implements TransformationPhase<JavaTransformationStep,INakedElement>,IntegrationPhase{
	private static JavaTransformationPhase INSTANCE = new JavaTransformationPhase();
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	@InputModel
	OJPackage javaModel;
	private OpeumConfig config;
	private List<JavaTransformationStep> features;
	public static final boolean IS_RUNTIME_AVAILABLE = false;
	private TextWorkspace getTextWorkspaceInternal(){
		return textWorkspace;
	}
	public static TextWorkspace getTextWorkspace(){
		return INSTANCE.getTextWorkspaceInternal();
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		Set<INakedElement> realChanges = calculateEffectiveChanges(elements);
		OJUtil.clearCache();
		Collection<TextOutputNode> files = new HashSet<TextOutputNode>();
		for(INakedElement e:elements){
			if(e instanceof INakedClassifier && e.getMappingInfo().requiresJavaRename()){
				realChanges.addAll(modelWorkspace.getDependentElements((INakedClassifier) e));
			}
		}
		for(JavaTransformationStep f:features){
			if(f instanceof NakedElementOwnerVisitor){
				f.setTransformationContext(context);
				NakedElementOwnerVisitor v = (NakedElementOwnerVisitor) f;
				f.getTextFiles().clear();
				f.setTransformationContext(context);
				if(v instanceof IntegrationCodeGenerator){
					v.startVisiting(modelWorkspace);
				}else{
					for(INakedElement element:realChanges){
						v.visitOnly(element);
					}
				}
				files.addAll(f.getTextFiles());
			}
			context.featureApplied(f.getClass());
		}
		return files;
	}
	private Set<INakedElement> calculateEffectiveChanges(Collection<INakedElement> elements){
		Set<INakedElement> result = new HashSet<INakedElement>();
		for(INakedElement object:elements){
			INakedElement ne = (INakedElement) object;
			while(!(ne instanceof INakedClassifier || ne instanceof INakedPackage || ne instanceof INakedEvent || ne == null || ne instanceof INakedOperation || ne instanceof INakedEmbeddedTask)){
				ne = (INakedElement) ne.getOwnerElement();
			}
			if(ne != null){
				result.add(ne);
				if(ne instanceof INakedBehavior && ((INakedBehavior) ne).getContext() != null){
					result.add(((INakedBehavior) ne).getContext());
				}
			}
		}
		return result;
	}
	@Override
	public void execute(TransformationContext context){
		OJUtil.clearCache();
		context.getLog().startTask("Generating Java Model", features.size());
		for(JavaTransformationStep f:features){
			context.getLog().startStep("Executing " + f.getClass().getSimpleName());
			boolean matchesPhase = true;
			if(context.isIntegrationPhase()){
				matchesPhase = f instanceof IntegrationCodeGenerator;
			}
			if(f instanceof NakedElementOwnerVisitor && !context.getLog().isCanceled() && matchesPhase){
				// Remember HibernateConfigGenerator
				NakedElementOwnerVisitor v = (NakedElementOwnerVisitor) f;
				f.setTransformationContext(context);
				v.startVisiting(this.modelWorkspace);
			}
			context.featureApplied(f.getClass());
			context.getLog().endLastStep();
		}
		context.getLog().endLastTask();
	}
	@Override
	public void initialize(OpeumConfig config,List<JavaTransformationStep> features){
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
}

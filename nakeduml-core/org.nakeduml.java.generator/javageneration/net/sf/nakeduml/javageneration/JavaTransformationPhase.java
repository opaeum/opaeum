package net.sf.nakeduml.javageneration;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.IntegrationPhase;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.validation.ValidationPhase;
import net.sf.nakeduml.validation.namegeneration.NameGenerationPhase;

import org.nakeduml.java.metamodel.OJPackage;

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
	private NakedUmlConfig config;
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
	public void initialize(NakedUmlConfig config,List<JavaTransformationStep> features){
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

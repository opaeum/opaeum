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
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.validation.namegeneration.NameGenerationPhase;
import nl.klasse.octopus.expressions.internal.types.RealLiteralExp;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

@PhaseDependency(after = {
		LinkagePhase.class,NameGenerationPhase.class
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
	OJAnnotatedPackage javaModel;
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
		Set<INakedElement> realChanges = new HashSet<INakedElement>(elements);
		OJUtil.clearCache();
		Collection<TextOutputNode> files = new HashSet<TextOutputNode>();
		for(INakedElement e:elements){
			if(e instanceof INakedClassifier && e.getMappingInfo().requiresJavaRename()){
				realChanges.addAll(modelWorkspace.getDependentClassifiers((INakedClassifier)e));
			}
		}

		for(JavaTransformationStep f:features){
			if(f instanceof NakedElementOwnerVisitor){
				f.setTransformationContext(context);
				NakedElementOwnerVisitor v = (NakedElementOwnerVisitor) f;
				f.getTextFiles().clear();
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
	@Override
	public void execute(TransformationContext context){
		OJUtil.clearCache();
		for(JavaTransformationStep f:features){
			if(f instanceof AbstractJavaProducingVisitor && (context.isIntegrationPhase() == (f instanceof IntegrationCodeGenerator))){
				AbstractJavaProducingVisitor v = (AbstractJavaProducingVisitor) f;
				f.setTransformationContext(context);
				v.startVisiting(this.modelWorkspace);
			}
			context.featureApplied(f.getClass());
		}
	}
	@Override
	public void initialize(NakedUmlConfig config,List<JavaTransformationStep> features){
		OJUtil.clearCache();
		this.config = config;
		this.features = features;
		for(JavaTransformationStep f:features){
			if(f instanceof AbstractJavaProducingVisitor){
				AbstractJavaProducingVisitor v = (AbstractJavaProducingVisitor) f;
				v.initialize(javaModel, config, textWorkspace, modelWorkspace);
			}
		}
	}
	@Override
	public Collection<JavaTransformationStep> getSteps(){
		return this.features;
	}
}

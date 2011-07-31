package net.sf.nakeduml.javageneration;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputModel;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.validation.namegeneration.NameGenerationPhase;

import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

@PhaseDependency(after = {
		LinkagePhase.class,NameGenerationPhase.class
},before = {
	FileGenerationPhase.class
})
public class JavaTransformationPhase implements TransformationPhase<JavaTransformationStep,INakedElement>{
	private static JavaTransformationPhase INSTANCE = new JavaTransformationPhase();
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	@OutputModel
	OJAnnotatedPackage javaModel;
	private NakedUmlConfig config;
	private List<JavaTransformationStep> features;
	public static final boolean IS_RUNTIME_AVAILABLE = false;
	public void initialize(NakedUmlConfig config){
		this.config = config;
	}
	public Object[] execute(List<JavaTransformationStep> features,TransformationContext context){
		OJUtil.clearCache();
		this.features=features;
		javaModel = new OJAnnotatedPackage("");
		for(JavaTransformationStep f:features){
			if( f instanceof AbstractJavaProducingVisitor){
				AbstractJavaProducingVisitor v = (AbstractJavaProducingVisitor) f;
				v.initialize(javaModel, config, textWorkspace,context);
				v.startVisiting(this.modelWorkspace);
			}
			context.featureApplied(f.getClass());
		}
		return new Object[]{
			javaModel
		};
	}
	private TextWorkspace getTextWorkspaceInternal(){
		return textWorkspace;
	}
	public static TextWorkspace getTextWorkspace(){
		return INSTANCE.getTextWorkspaceInternal();
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		OJUtil.clearCache();
		textWorkspace=new TextWorkspace();
		for(INakedElement element:elements){
			for(JavaTransformationStep f:features){
				if(f instanceof NakedElementOwnerVisitor){
					f.initialize(javaModel, config, textWorkspace,context);
					((NakedElementOwnerVisitor) f).visitRecursively(element);
				}
				context.featureApplied(f.getClass());
			}
		}
		return Collections.singleton(textWorkspace);
	}
}

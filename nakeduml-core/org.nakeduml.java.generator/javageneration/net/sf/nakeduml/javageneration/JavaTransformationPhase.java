package net.sf.nakeduml.javageneration;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
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
import net.sf.nakeduml.linkage.OclParsingPhase;
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
		LinkagePhase.class,NameGenerationPhase.class,OclParsingPhase.class
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
	public static final boolean IS_RUNTIME_AVAILABLE = false;
	public void initialize(NakedUmlConfig config){
		this.config = config;
	}
	public static void main(String[] args){
	}
	public Object[] execute(List<JavaTransformationStep> features,TransformationContext context){
		OJUtil.clearCache();
		javaModel = new OJAnnotatedPackage();
		for(JavaTransformationStep f:features){
			f.initialize(javaModel, config, textWorkspace);
			f.generate(modelWorkspace, context);
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
	public Object processSingleElement(List<JavaTransformationStep> features,TransformationContext context,INakedElement element){
		OJUtil.clearCache();
		javaModel = new OJAnnotatedPackage();
		for(JavaTransformationStep f:features){
			if(f instanceof NakedElementOwnerVisitor){
				f.initialize(javaModel, config, textWorkspace);
				((NakedElementOwnerVisitor) f).visitRecursively(element);
			}
			context.featureApplied(f.getClass());
		}
		while(!(element instanceof INakedClassifier || element instanceof INakedPackage)){
			element = (INakedElement) element.getOwnerElement();
		}
		if(element instanceof INakedClassifier){
			OJPackage pkg = javaModel.findPackage(OJUtil.packagePathname((INakedNameSpace) element));
			if(pkg != null){
				return pkg;
			}else{
				return javaModel.findClass(OJUtil.classifierPathname((INakedClassifier) element));
			}
		}else if(element instanceof INakedPackage){
			return javaModel.findPackage(OJUtil.packagePathname((INakedNameSpace) element));
		}else{
			return javaModel;
		}
	}
}

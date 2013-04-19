package org.opaeum.javageneration;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.emf.extraction.AbstractEmfPhase;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.IntegrationPhase;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.generated.OJElementGEN;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.javageneration.util.PropertyStrategy;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.validation.LinkagePhase;
import org.opaeum.visitor.TextFileGeneratingVisitor;

@PhaseDependency(after = {LinkagePhase.class},before = {})
public class JavaTransformationPhase extends AbstractEmfPhase implements TransformationPhase<JavaTransformationStep,Element>,IntegrationPhase{
	@InputModel
	protected EmfWorkspace modelWorkspace;

	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	OJWorkspace javaModel;
	private OpaeumConfig config;
	@InputModel
	OJUtil ojUtil;
	private List<JavaTransformationStep> features;
	public static final boolean IS_RUNTIME_AVAILABLE = false;
	private TextWorkspace getTextWorkspaceInternal(){
		return textWorkspace;
	}
	@Override
	protected EmfWorkspace getModelWorkspace(){
		return modelWorkspace;
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<Element> elements){
		Set<Element> realChanges = calculateEffectiveChanges(elements);
		ojUtil.initialise(modelWorkspace,context.getStrategy(PropertyStrategy.class));
		ojUtil.clearCache();
		Collection<TextOutputNode> files = new HashSet<TextOutputNode>();
		for(Element e:elements){
			if(e instanceof Classifier &&  ojUtil.requiresJavaRename((NamedElement) e)){
				realChanges.addAll(modelWorkspace.getDependentElements((Classifier) e));
			}
		}
		for(JavaTransformationStep f:features){
			if(f instanceof TextFileGeneratingVisitor){
				f.setTransformationContext(context);
				TextFileGeneratingVisitor v = (TextFileGeneratingVisitor) f;
				f.getTextFiles().clear();
				f.setTransformationContext(context);
				if(v instanceof IntegrationCodeGenerator){
					v.startVisiting(modelWorkspace);
				}else{
					for(Element element:realChanges){
						v.visitRecursively(element);
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
		ojUtil.clearCache();
		ojUtil.initialise(modelWorkspace, context.getStrategy(PropertyStrategy.class));
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
	}
	@Override
	public void initialize(OpaeumConfig config,List<JavaTransformationStep> features){
		this.config = config;
		this.features = features;
	}
	public void initializeSteps(){
		ojUtil.clearCache();
		ojUtil.initialise(modelWorkspace, null);
		for(JavaTransformationStep f:this.features){
			f.initialize(javaModel, this.config, textWorkspace, modelWorkspace, ojUtil);
		}
	}
	@Override
	public Collection<JavaTransformationStep> getSteps(){
		return this.features;
	}
	@Override
	public void release(){
		this.textWorkspace = null;
		this.javaModel = null;
		this.modelWorkspace = null;
		for(JavaTransformationStep j:this.features){
			j.release();
		}
		UtilityCreator.setUtilPackage(null);
	}
	
}

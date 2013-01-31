package org.opaeum.javageneration;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfElementFinder;
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
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.visitor.TextFileGeneratingVisitor;

@PhaseDependency(after = {},before = {})
public class MetaModelJavaTransformationPhase implements TransformationPhase<MetaModelJavaTransformationStep,EModelElement>,
		IntegrationPhase{
	private static MetaModelJavaTransformationPhase INSTANCE = new MetaModelJavaTransformationPhase();
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private EPackage modelWorkspace;
	@InputModel
	OJWorkspace javaModel;
	private OpaeumConfig config;
	private List<MetaModelJavaTransformationStep> features;
	public static final boolean IS_RUNTIME_AVAILABLE = false;
	private TextWorkspace getTextWorkspaceInternal(){
		return textWorkspace;
	}
	public static TextWorkspace getTextWorkspace(){
		return INSTANCE.getTextWorkspaceInternal();
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<ITransformationStep> elements){
		Set<Element> realChanges = calculateEffectiveChanges(elements);
		Collection<TextOutputNode> files = new HashSet<TextOutputNode>();
		for(MetaModelJavaTransformationStep f:features){
			f.setTransformationContext(context);
			TextFileGeneratingVisitor v = (TextFileGeneratingVisitor) f;
			f.getTextFiles().clear();
			f.setTransformationContext(context);
			for(EModelElement element:realChanges){
				v.visitRecursively(element);
			}
			files.addAll(f.getTextFiles());
			context.featureApplied(f.getClass());
		}
		return files;
	}
	@Override
	public void execute(TransformationContext context){
		ojUtil.clearCache();
		context.getLog().registerInstanceCountMap(OJElementGEN.counts);
		context.getLog().registerInstanceCountMap(TextOutputNode.counts);
		context.getLog().startTask("Generating Java Model", features.size());
		for(MetaModelJavaTransformationStep f:features){
			context.getLog().startStep("Executing " + f.getClass().getSimpleName());
			boolean matchesPhase = true;
			if(context.isIntegrationPhase()){
				matchesPhase = f instanceof IntegrationCodeGenerator;
			}
			if(!context.getLog().isCanceled() && matchesPhase){
				f.startVisiting(this.modelWorkspace);
			}
			context.featureApplied(f.getClass());
			context.getLog().endLastStep();
		}
		context.getLog().endLastTask();
	}
	@Override
	public void initialize(OpaeumConfig config,List<MetaModelJavaTransformationStep> features){
		this.config = config;
		this.features = features;
	}
	public void initializeSteps(){
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

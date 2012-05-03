package org.opaeum.javageneration;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IAttribute;

import org.opaeum.feature.InputModel;
import org.opaeum.feature.IntegrationPhase;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.generated.OJElementGEN;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedInterfaceRealization;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedPackage;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.validation.ValidationPhase;
import org.opaeum.validation.namegeneration.NameGenerationPhase;
import org.opaeum.visitor.TextFileGeneratingVisitor;

@PhaseDependency(after = {LinkagePhase.class,NameGenerationPhase.class,ValidationPhase.class},before = {})
public class JavaTransformationPhase implements TransformationPhase<JavaTransformationStep,INakedElement>,IntegrationPhase{
	private static JavaTransformationPhase INSTANCE = new JavaTransformationPhase();
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	@InputModel
	OJWorkspace javaModel;
	private OpaeumConfig config;
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
			if(f instanceof TextFileGeneratingVisitor){
				f.setTransformationContext(context);
				TextFileGeneratingVisitor v = (TextFileGeneratingVisitor) f;
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
			if(ne instanceof INakedProperty && ((INakedProperty) ne).getAssociation() != null){
				INakedAssociation ass = (INakedAssociation) ((INakedProperty) ne).getAssociation();
				addAssociation(result, ass);
			}else if(ne instanceof INakedAssociation){
				addAssociation(result, (INakedAssociation) ne);
			}else if(ne instanceof INakedGeneralization){
				addProcessibleElementsRecursively(result, ((INakedGeneralization) ne).getSpecific());
			}else if(ne instanceof INakedInterfaceRealization){
				addProcessibleElementsRecursively(result, ((INakedInterfaceRealization) ne).getImplementingClassifier());
			}
			addProcessibleElementsRecursively(result, ne);
		}
		result.remove(null);// Just in case the null element was added
		return result;
	}
	private void addAssociation(Set<INakedElement> result,INakedAssociation ass){
		if(ass.isClass()){
			addProcessibleElementsRecursively(result, ass);
		}
		for(INakedProperty p:ass.getOwnedAttributes()){
			addProcessibleElementsRecursively(result, p);
		}
	}
	private void addProcessibleElementsRecursively(Set<INakedElement> result,INakedElement ne){
		if(ne != null){
			INakedElement processibleElement = getProcessibleElement(ne);
			result.add(processibleElement);
			if(ne.getOwnerElement() instanceof INakedElement){
				addProcessibleElementsRecursively(result, (INakedElement) ne.getOwnerElement());
			}
		}
	}
	private INakedElement getProcessibleElement(INakedElement ne){
		while(!(ne instanceof INakedClassifier || ne instanceof INakedPackage || ne instanceof INakedEvent || ne == null
				|| ne instanceof INakedOperation || ne instanceof INakedEmbeddedTask)){
			ne = (INakedElement) ne.getOwnerElement();
		}
		return ne;
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
		this.textWorkspace = null;
		this.javaModel = null;
		this.modelWorkspace = null;
		for(JavaTransformationStep j:this.features){
			j.release();
		}
		UtilityCreator.setUtilPackage(null);
	}
}

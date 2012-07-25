package org.opaeum.javageneration;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

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
import org.opaeum.validation.ValidationPhase;
import org.opaeum.visitor.TextFileGeneratingVisitor;

@PhaseDependency(after = {ValidationPhase.class},before = {})
public class JavaTransformationPhase implements TransformationPhase<JavaTransformationStep,Element>,IntegrationPhase{
	private static JavaTransformationPhase INSTANCE = new JavaTransformationPhase();
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private EmfWorkspace modelWorkspace;
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
	public Collection<?> processElements(TransformationContext context,Collection<Element> elements){
		Set<Element> realChanges = calculateEffectiveChanges(elements);
		OJUtil.clearCache();
		Collection<TextOutputNode> files = new HashSet<TextOutputNode>();
		for(Element e:elements){
			if(e instanceof Classifier &&  OJUtil.requiresJavaRename((NamedElement) e)){
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
						v.visitOnly(element);
					}
				}
				files.addAll(f.getTextFiles());
			}
			context.featureApplied(f.getClass());
		}
		return files;
	}
	private Set<Element> calculateEffectiveChanges(Collection<Element> elements){
		Set<Element> result = new HashSet<Element>();
		for(Element object:elements){
			Element ne = (Element) object;
			if(ne instanceof Property && ((Property) ne).getAssociation() != null){
				Association ass = (Association) ((Property) ne).getAssociation();
				addAssociation(result, ass);
			}else if(ne instanceof Association){
				addAssociation(result, (Association) ne);
			}else if(ne instanceof Generalization){
				addProcessibleElementsRecursively(result, ((Generalization) ne).getSpecific());
			}else if(ne instanceof InterfaceRealization){
				addProcessibleElementsRecursively(result, ((InterfaceRealization) ne).getImplementingClassifier());
			}
			addProcessibleElementsRecursively(result, ne);
		}
		result.remove(null);// Just in case the null element was added
		return result;
	}
	private void addAssociation(Set<Element> result,Association ass){
		if(EmfAssociationUtil.isClass(ass)){
			addProcessibleElementsRecursively(result, ass);
		}
		for(Property p:ass.getAttributes()){
			addProcessibleElementsRecursively(result, p);
		}
	}
	private void addProcessibleElementsRecursively(Set<Element> result,Element ne){
		if(ne != null){
			Element processibleElement = getProcessibleElement(ne);
			result.add(processibleElement);
			if(EmfElementFinder.getContainer(ne) instanceof Element){
				addProcessibleElementsRecursively(result, (Element) EmfElementFinder.getContainer(ne));
			}
		}
	}
	private Element getProcessibleElement(Element ne){
		while(!(ne instanceof Classifier || ne instanceof Package || ne instanceof Event || ne == null
				|| ne instanceof Operation || (ne instanceof Action && EmfActionUtil.isEmbeddedTask((ActivityNode) ne)))){
			ne = (Element) EmfElementFinder.getContainer(ne);
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

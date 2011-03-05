package net.sf.nakeduml.javageneration.hibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.linkage.InterfaceUtil;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.environment.domain.AbstractDomainEnvironment;
import org.nakeduml.jbpm.domain.AbstractJbpmKnowledgeBase;

//TODO execute for the domain projects as well as adapter projects
public abstract class AbstractMetaDefAnnotator extends AbstractJavaProducingVisitor {
	private boolean isIntegrationPhase;

	public final class InterfaceCollector extends AbstractJavaProducingVisitor {
		Set<INakedInterface> interfaces = new HashSet<INakedInterface>();
		@VisitBefore
		public void visitInterface(INakedInterface i){
			interfaces.add(i);
		}
	}

	public AbstractMetaDefAnnotator(boolean isIntegrationPhase) {
		super();
		this.isIntegrationPhase = isIntegrationPhase;
	}

	public void startVisiting(INakedModelWorkspace root) {
		super.workspace=root;
		if(isIntegrationPhase){
			OJPathName pathName = new OJPathName(config.getMavenGroupId() + ".util");
			addEnvironment(pathName, workspace.getDirectoryName()
					+ "-hibernate.cfg.xml", JavaTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC);

			Collection<? extends INakedElement> ownedElements = workspace.getOwnedElements();
			Set<INakedInterface> interfaces = collectInterfaces((ownedElements));
			for (INakedInterface i : interfaces) {
				doInterface(i, pathName, InterfaceUtil.getImplementationsOf(i,(Collection<INakedRootObject>) ownedElements));
			}
		}else{
			List<INakedRootObject> generatingModelsOrProfiles = workspace.getGeneratingModelsOrProfiles();
			for (INakedRootObject rootObject : generatingModelsOrProfiles) {
				if(rootObject instanceof INakedModel){
					super.currentRootObject=rootObject;
					if(rootObject.getName().contains("huawei_umts")){
						System.out.println();
					}
					addEnvironment(UtilityCreator.getUtilPathName(), rootObject.getFileName()
							+ "-hibernate.cfg.xml", JavaTextSource.OutputRootId.DOMAIN_GEN_TEST_SRC);

					Collection<INakedRootObject> dependencies = ((INakedModel) rootObject).getDependencies();
					Set<INakedInterface> interfaces = collectInterfaces(dependencies);
					for (INakedInterface i : interfaces) {
						doInterface(i, UtilityCreator.getUtilPathName(), InterfaceUtil.getImplementationsOf(i,dependencies));
					}
				}
			}
		}
	}

	private void addEnvironment(OJPathName utilPackagePath, String configName, OutputRootId outputRootId) {
		OJAnnotatedClass domainEnvironment = new OJAnnotatedClass();
		domainEnvironment.setName("DomainEnvironment");
		//TODO parameterise this
		domainEnvironment.setSuperclass(new OJPathName(AbstractDomainEnvironment.class.getName()));
		OJPackage pkg =findOrCreatePackage(utilPackagePath);
		pkg.addToClasses(domainEnvironment);
		OJAnnotatedOperation getHibernateConfigName = new OJAnnotatedOperation("getHibernateConfigName", new OJPathName("java.lang.String"));
		domainEnvironment.addToOperations(getHibernateConfigName);
		getHibernateConfigName.getBody().addToStatements("return \"" +configName+"\"");
		OJAnnotatedOperation createJbpmKnowledgeBase = new OJAnnotatedOperation("createJbpmKnowledgeBase", new OJPathName(AbstractJbpmKnowledgeBase.class.getName()));
		domainEnvironment.addToOperations(createJbpmKnowledgeBase);
		OJPathName jbpmKnowledgeBase = utilPackagePath.getDeepCopy();
		jbpmKnowledgeBase.addToNames("JbpmKnowledgeBase");
		domainEnvironment.addToImports(jbpmKnowledgeBase);
		createJbpmKnowledgeBase.getBody().addToStatements("return new " + jbpmKnowledgeBase.getLast() + "()");
		createTextPath(domainEnvironment, outputRootId);
	}

	private Set<INakedInterface> collectInterfaces(Collection<? extends INakedElement> ownedElements) {
		InterfaceCollector collector = new InterfaceCollector();
		for (INakedElement e : ownedElements) {
			if(e instanceof INakedModel){
				INakedModel model = (INakedModel)e;
				collector.visitRecursively(model);
			}
		}
		Set<INakedInterface> interfaces = collector.interfaces;
		return interfaces;
	}

	private void doInterface(INakedInterface i, OJPathName path, Collection<INakedEntity> impls) {
		OJAnnotationValue metaDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDef"));
		OJAnnotatedPackage p = (OJAnnotatedPackage) this.findOrCreatePackage(path);
		createTextPathIfRequired(p, getOutputRoot());
		OJAnnotationValue anyMetaDefs = getAnyMetaDefs(p);
		anyMetaDefs.addAnnotationValue(metaDef);
		metaDef.putAttribute("name", getMetaDefName(i));
		metaDef.putAttribute("metaType", "string");
		metaDef.putAttribute("idType", getIdType());
		OJAnnotationAttributeValue metaValues = new OJAnnotationAttributeValue("metaValues");
		metaDef.putAttribute(metaValues);
		for (INakedEntity iNakedEntity : impls) {
			OJAnnotationValue metaValue = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.MetaValue"));
			NakedClassifierMap map = new NakedClassifierMap(iNakedEntity);
			OJPathName javaTypePath = map.javaTypePath();
			metaValue.putAttribute("value", javaTypePath.toString());
			metaValue.putAttribute("targetEntity", getTargetEntity(javaTypePath));
			metaValues.addAnnotationValue(metaValue);
		}
	}

	protected abstract OJPathName getTargetEntity(OJPathName javaTypePath);

	protected abstract String getIdType();

	protected abstract String getMetaDefName(INakedInterface i);

	protected final JavaTextSource.OutputRootId getOutputRoot() {
		if (isIntegrationPhase) {
			return JavaTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC;
		} else {
			// One model
			return JavaTextSource.OutputRootId.DOMAIN_GEN_TEST_SRC;
		}
	}

	private OJAnnotationValue getAnyMetaDefs(OJAnnotatedPackage p) {
		OJAnnotationValue anyMetaDefs = p.findAnnotation(new OJPathName("org.hibernate.annotations.AnyMetaDefs"));
		if (anyMetaDefs == null) {
			anyMetaDefs = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDefs"));
			p.putAnnotation(anyMetaDefs);
		}
		return anyMetaDefs;
	}
}
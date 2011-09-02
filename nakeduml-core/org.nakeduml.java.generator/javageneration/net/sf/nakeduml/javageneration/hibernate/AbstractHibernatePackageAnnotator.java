package net.sf.nakeduml.javageneration.hibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.IntegrationCodeGenerator;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.jbpm5.ProcessStepResolverImplementor;
import net.sf.nakeduml.javageneration.maps.NakedClassifierMap;
import net.sf.nakeduml.linkage.GeneralizationUtil;
import net.sf.nakeduml.metamodel.bpm.INakedBusinessComponent;
import net.sf.nakeduml.metamodel.bpm.INakedBusinessRole;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.hibernate.dialect.Dialect;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;

public abstract class AbstractHibernatePackageAnnotator extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	private static final String PARTICIPANT_META_DEF = "ParticipantMetaDef";
	private static final String OPERATION_PROCESS_META_DEF = "OperationProcessMetaDef";
	private static final String TASK_OBJECT_META_DEF = "TaskObjectMetaDef";
	public static final class MetaDefElementCollector extends AbstractJavaProducingVisitor{
		Set<INakedInterface> interfaces = new HashSet<INakedInterface>();
		Set<INakedBehavior> contractedProcesses = new HashSet<INakedBehavior>();
		Set<INakedBehavior> allProcesses = new HashSet<INakedBehavior>();
		Set<INakedMessageStructure> tasks = new HashSet<INakedMessageStructure>();
		Set<INakedClassifier> participant = new HashSet<INakedClassifier>();
		Set<INakedEnumeration> enumerations = new HashSet<INakedEnumeration>();
		public MetaDefElementCollector(INakedModelWorkspace workspace){
			super.workspace = workspace;
		}
		@VisitBefore
		public void visitEnumeration(INakedEnumeration i){
			enumerations.add(i);
		}
		@VisitBefore
		public void visitInterface(INakedInterface i){
			interfaces.add(i);
		}
		@VisitBefore(matchSubclasses = true)
		public void visitProcess(INakedBehavior b){
			if(b.isProcess()){
				allProcesses.add(b);
				if(b.getSpecification() != null){
					contractedProcesses.add(b);
				}
			}
		}
		@VisitBefore(matchSubclasses = true)
		public void visitComponent(INakedBusinessComponent c){
			participant.add(c);
		}
		@VisitBefore(matchSubclasses = true)
		public void visitEntity(INakedBusinessRole e){
			participant.add(e);
		}
		@VisitBefore(matchSubclasses = true)
		public void visitOperation(INakedResponsibility b){
			tasks.add(b.getMessageStructure());
		}
		@VisitBefore(matchSubclasses = true)
		public void visitOpaqueAction(INakedEmbeddedSingleScreenTask a){
			tasks.add(a.getMessageStructure());
		}
		@VisitBefore(matchSubclasses = true)
		public void visitEmbeddedScreeFlowTask(INakedEmbeddedScreenFlowTask a){
			tasks.add(a.getMessageStructure());
		}
	}
	public abstract void visitWorkspace(INakedModelWorkspace root);
	public abstract void visitModel(INakedModel model);
	protected void doWorkspace(INakedModelWorkspace workspace){
		if(isIntegrationPhase()){
			OJAnnotatedPackage pkg = findOrCreatePackage(HibernateUtil.getHibernatePackage(true));
			createTextPathIfRequired(pkg, JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
			applyFilter(pkg);
			MetaDefElementCollector collector = collectElements(workspace.getOwnedElements());
			Set<INakedInterface> interfaces = collector.interfaces;
			for(INakedInterface i:interfaces){
				String metaDefName = HibernateUtil.metadefName((INakedInterface) i);
				doMetaDef(GeneralizationUtil.getConcreteEntityImplementationsOf(i, (Collection<INakedRootObject>) workspace.getOwnedElements()), metaDefName, pkg);
			}
			doMetaDef(collector.tasks, TASK_OBJECT_META_DEF, pkg);
			doMetaDef(collector.contractedProcesses, OPERATION_PROCESS_META_DEF, pkg);
			doMetaDef(collector.participant, PARTICIPANT_META_DEF, pkg);
			if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
				doTypeDefs(collector.enumerations, "Resolver", pkg);
			}
			if(transformationContext.isFeatureSelected(ProcessStepResolverImplementor.class)){
				doTypeDefs(collector.allProcesses, "StateResolver", pkg);
			}
		}
	}
	private void applyFilter(OJAnnotatedPackage ap){
		OJAnnotationValue filterDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.FilterDef"));
		filterDef.putAttribute(new OJAnnotationAttributeValue("name", "noDeletedObjects"));
		filterDef.putAttribute(new OJAnnotationAttributeValue("defaultCondition", "deleted_on > " + getCurrentTimestampSQLFunction()));
		ap.putAnnotation(filterDef);
	}
	private String getCurrentTimestampSQLFunction(){
		Dialect dialect = HibernateUtil.getHibernateDialect(this.config);
		return dialect.getCurrentTimestampSQLFunctionName();
	}
	protected void doModel(INakedModel model){
		if(!isIntegrationPhase()){
			OJAnnotatedPackage adPkg = findOrCreatePackage(HibernateUtil.getHibernatePackage(true));
			createTextPathIfRequired(adPkg, JavaSourceFolderIdentifier.ADAPTOR_GEN_TEST_SRC);
			OJAnnotatedPackage domainPkg = findOrCreatePackage(HibernateUtil.getHibernatePackage(false));
			createTextPathIfRequired(domainPkg, JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC);
			applyFilter(adPkg);
			applyFilter(domainPkg);
			Collection<INakedRootObject> selfAndDependencies = getModelInScope();
			MetaDefElementCollector collector = collectElements(selfAndDependencies);
			Set<INakedInterface> interfaces = collector.interfaces;
			for(INakedInterface i:interfaces){
				Collection<INakedBehavioredClassifier> generalizations = GeneralizationUtil.getConcreteEntityImplementationsOf(i, selfAndDependencies);
				doMetaDef(generalizations, HibernateUtil.metadefName((INakedInterface) i), adPkg);
				doMetaDef(generalizations, HibernateUtil.metadefName((INakedInterface) i), domainPkg);
			}
			if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
				doTypeDefs(collector.enumerations, "Resolver", domainPkg);
			}
			if(transformationContext.isFeatureSelected(ProcessStepResolverImplementor.class)){
				doTypeDefs(collector.allProcesses, "StateResolver", domainPkg);
			}
			if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
				doTypeDefs(collector.enumerations, "Resolver", adPkg);
			}
			if(transformationContext.isFeatureSelected(ProcessStepResolverImplementor.class)){
				doTypeDefs(collector.allProcesses, "StateResolver", adPkg);
			}
			doMetaDef(collector.tasks, TASK_OBJECT_META_DEF, adPkg);
			doMetaDef(collector.tasks, TASK_OBJECT_META_DEF, domainPkg);
			doMetaDef(collector.contractedProcesses, OPERATION_PROCESS_META_DEF, adPkg);
			doMetaDef(collector.contractedProcesses, OPERATION_PROCESS_META_DEF, domainPkg);
			doMetaDef(collector.participant, PARTICIPANT_META_DEF, adPkg);
			doMetaDef(collector.participant, PARTICIPANT_META_DEF, domainPkg);
		}
	}
	private void doTypeDefs(Set<? extends INakedClassifier> processes,String string,OJAnnotatedPackage p){
		OJAnnotationAttributeValue typeDefs = getTypeDefs(p);
		for(INakedClassifier a:processes){
			OJAnnotationValue typeDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.TypeDef"));
			typeDefs.addAnnotationValue(typeDef);
			p.putAnnotation(typeDef);
			typeDef.putAttribute("name", a.getMappingInfo().getJavaName() + string);
			typeDef.putAttribute("typeClass", new OJPathName(a.getMappingInfo().getQualifiedJavaName() + string));
		}
	}
	private OJAnnotationAttributeValue getTypeDefs(OJAnnotatedPackage p){
		OJAnnotationValue typeDefs = p.findAnnotation(new OJPathName("org.hibernate.annotations.TypeDefs"));
		if(typeDefs == null){
			typeDefs = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.TypeDefs"));
			p.putAnnotation(typeDefs);
			typeDefs.putAttribute(new OJAnnotationAttributeValue("value"));
		}
		return typeDefs.findAttribute("value");
	}
	// TODO find another place for this
	private MetaDefElementCollector collectElements(Collection<? extends INakedElement> ownedElements){
		MetaDefElementCollector collector = new MetaDefElementCollector(workspace);
		for(INakedElement e:ownedElements){
			if(e instanceof INakedModel){
				INakedModel model = (INakedModel) e;
				collector.visitRecursively(model);
			}
		}
		return collector;
	}
	private void doMetaDef(Collection<? extends INakedClassifier> impls,String metaDefName,OJAnnotatedPackage p){
		OJAnnotationValue metaDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDef"));
		OJAnnotationValue anyMetaDefs = getAnyMetaDefs(p);
		anyMetaDefs.addAnnotationValue(metaDef);
		metaDef.putAttribute("name", metaDefName + getMetaDefNameSuffix());
		metaDef.putAttribute("metaType", "integer");
		metaDef.putAttribute("idType", getIdType());
		OJAnnotationAttributeValue metaValues = new OJAnnotationAttributeValue("metaValues");
		metaDef.putAttribute(metaValues);
		for(INakedClassifier bc:impls){
			OJAnnotationValue metaValue = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.MetaValue"));
			NakedClassifierMap map = new NakedClassifierMap(bc);
			OJPathName javaTypePath = map.javaTypePath();
			metaValue.putAttribute("value", bc.getMappingInfo().getNakedUmlId().toString());
			metaValue.putAttribute("targetEntity", getTargetEntity(javaTypePath));
			metaValues.addAnnotationValue(metaValue);
		}
	}
	protected abstract OJPathName getTargetEntity(OJPathName javaTypePath);
	protected abstract String getIdType();
	protected abstract String getMetaDefNameSuffix();
	private OJAnnotationValue getAnyMetaDefs(OJAnnotatedPackage p){
		OJAnnotationValue anyMetaDefs = p.findAnnotation(new OJPathName("org.hibernate.annotations.AnyMetaDefs"));
		if(anyMetaDefs == null){
			anyMetaDefs = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDefs"));
			p.putAnnotation(anyMetaDefs);
		}
		return anyMetaDefs;
	}
}
package net.sf.nakeduml.javageneration.hibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.linkage.GeneralizationUtil;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.INakedUserInRole;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.components.INakedComponent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.hibernate.dialect.Dialect;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.runtime.domain.AbstractRequest;
import org.nakeduml.runtime.domain.ProcessRequest;
import org.nakeduml.runtime.domain.TaskRequest;

public abstract class AbstractHibernatePackageAnnotator extends AbstractJavaProducingVisitor{
	private boolean isIntegrationPhase;
	public final class MetaDeflElementCollector extends AbstractJavaProducingVisitor{
		Set<INakedInterface> interfaces = new HashSet<INakedInterface>();
		Set<INakedBehavior> processes = new HashSet<INakedBehavior>();
		Set<INakedMessageStructure> tasks = new HashSet<INakedMessageStructure>();
		Set<INakedEntity> users = new HashSet<INakedEntity>();
		Set<INakedComponent> orgUnits = new HashSet<INakedComponent>();
		@VisitBefore
		public void visitInterface(INakedInterface i){
			interfaces.add(i);
		}
		@VisitBefore(matchSubclasses = true)
		public void visitProcess(INakedBehavior b){
			if(b.isProcess() && b.getSpecification() != null){
				processes.add(b);
			}
		}
		@VisitBefore(matchSubclasses = true)
		public void visitComponent(INakedComponent c){
			orgUnits.add(c);
		}
		@VisitBefore(matchSubclasses = true)
		public void visitEntity(INakedUserInRole e){
			users.add(e);
		}
		@VisitBefore(matchSubclasses = true)
		public void visitOperation(INakedResponsibility b){
			tasks.add(b.getMessageStructure(getOclEngine().getOclLibrary()));
		}
		@VisitBefore(matchSubclasses = true)
		public void visitOpaqueAction(INakedEmbeddedSingleScreenTask a){
			tasks.add(a.getMessageStructure(getOclEngine().getOclLibrary()));
		}
		@VisitBefore(matchSubclasses = true)
		public void visitCallBehaviorAction(INakedEmbeddedScreenFlowTask a){
			tasks.add(a.getMessageStructure(getOclEngine().getOclLibrary()));
		}
	}
	public AbstractHibernatePackageAnnotator(boolean isIntegrationPhase){
		super();
		this.isIntegrationPhase = isIntegrationPhase;
	}
	public abstract void visitWorkspace(INakedModelWorkspace root);
	public abstract void visitModel(INakedModel model);
	protected void doWorkspace(INakedModelWorkspace workspace){
		if(isIntegrationPhase){
			applyFilter(true, OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC);
			Collection<? extends INakedElement> ownedElements = workspace.getOwnedElements();
			MetaDeflElementCollector collector = collectElements(ownedElements);
			Set<INakedInterface> interfaces = collector.interfaces;
			for(INakedInterface i:interfaces){
				doInterface(i, GeneralizationUtil.getConcreteEntityImplementationsOf(i, (Collection<INakedRootObject>) ownedElements), true,
						OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC);
			}
			doMetadef(collector.tasks, true, OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC, TaskRequest.TASK_META_DEF);
			doMetadef(collector.processes, true, OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC, ProcessRequest.PROCESS_META_DEF);
			doMetadef(collector.users, true, OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC, AbstractRequest.USER_ROLE_META_DEF);
			doMetadef(collector.orgUnits, true, OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC, TaskRequest.ORGANIZATION_UNIT_META_DEF);
		}
	}
	protected void applyFilter(boolean isAdaptor,OutputRootId outputRoot){
		OJAnnotatedPackage ap = findOrCreatePackage(HibernateUtil.getHibernatePackage(isAdaptor));
		OJAnnotationValue filterDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.FilterDef"));
		filterDef.putAttribute(new OJAnnotationAttributeValue("name", "noDeletedObjects"));
		filterDef.putAttribute(new OJAnnotationAttributeValue("defaultCondition", "deleted_on > " + getCurrentTimestampSQLFunction()));
		ap.putAnnotation(filterDef);
		createTextPathIfRequired(ap, outputRoot);
	}
	private String getCurrentTimestampSQLFunction(){
		Dialect dialect = HibernateUtil.getHibernateDialect(this.config);
		return dialect.getCurrentTimestampSQLFunctionName();
	}
	protected void doModel(INakedModel model){
		if(!isIntegrationPhase){
			applyFilter(true, OutputRootId.ADAPTOR_GEN_TEST_SRC);
			applyFilter(false, OutputRootId.DOMAIN_GEN_TEST_SRC);
			Collection<INakedRootObject> selfAndDependencies = getModelInScope();
			MetaDeflElementCollector collector = collectElements(selfAndDependencies);
			Set<INakedInterface> interfaces = collector.interfaces;
			for(INakedInterface i:interfaces){
				doInterface(i, GeneralizationUtil.getConcreteEntityImplementationsOf(i, selfAndDependencies), true, OutputRootId.ADAPTOR_GEN_TEST_SRC);
				doInterface(i, GeneralizationUtil.getConcreteEntityImplementationsOf(i, selfAndDependencies), false, OutputRootId.DOMAIN_GEN_TEST_SRC);
			}
			doMetadef(collector.tasks, true, OutputRootId.ADAPTOR_GEN_TEST_SRC, TaskRequest.TASK_META_DEF);
			doMetadef(collector.tasks, false, OutputRootId.DOMAIN_GEN_TEST_SRC, TaskRequest.TASK_META_DEF);
			doMetadef(collector.processes, true, OutputRootId.ADAPTOR_GEN_TEST_SRC, ProcessRequest.PROCESS_META_DEF);
			doMetadef(collector.processes, false, OutputRootId.DOMAIN_GEN_TEST_SRC, ProcessRequest.PROCESS_META_DEF);
			doMetadef(collector.users, true, OutputRootId.ADAPTOR_GEN_TEST_SRC, AbstractRequest.USER_ROLE_META_DEF);
			doMetadef(collector.users, false, OutputRootId.DOMAIN_GEN_TEST_SRC, AbstractRequest.USER_ROLE_META_DEF);
			doMetadef(collector.orgUnits, true, OutputRootId.ADAPTOR_GEN_TEST_SRC, TaskRequest.ORGANIZATION_UNIT_META_DEF);
			doMetadef(collector.orgUnits, false, OutputRootId.DOMAIN_GEN_TEST_SRC, TaskRequest.ORGANIZATION_UNIT_META_DEF);
		}
	}
	// TODO find another place for this
	private MetaDeflElementCollector collectElements(Collection<? extends INakedElement> ownedElements){
		MetaDeflElementCollector collector = new MetaDeflElementCollector();
		for(INakedElement e:ownedElements){
			if(e instanceof INakedModel){
				INakedModel model = (INakedModel) e;
				collector.visitRecursively(model);
			}
		}
		return collector;
	}
	private void doInterface(INakedClassifier i,Collection<? extends INakedClassifier> impls,boolean isAdaptor,OutputRootId outputRoot){
		String metaDefName = HibernateUtil.metadefName((INakedInterface) i);
		doMetadef(impls, isAdaptor, outputRoot, metaDefName);
	}
	protected void doMetadef(Collection<? extends INakedClassifier> impls,boolean isAdaptor,OutputRootId outputRoot,String metaDefName){
		OJAnnotationValue metaDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDef"));
		OJAnnotatedPackage p = (OJAnnotatedPackage) this.findOrCreatePackage(HibernateUtil.getHibernatePackage(isAdaptor));
		createTextPathIfRequired(p, outputRoot);
		OJAnnotationValue anyMetaDefs = getAnyMetaDefs(p);
		anyMetaDefs.addAnnotationValue(metaDef);
		metaDef.putAttribute("name", metaDefName + getMetaDefNameSuffix());
		metaDef.putAttribute("metaType", "string");
		metaDef.putAttribute("idType", getIdType());
		OJAnnotationAttributeValue metaValues = new OJAnnotationAttributeValue("metaValues");
		metaDef.putAttribute(metaValues);
		for(INakedClassifier bc:impls){
			OJAnnotationValue metaValue = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.MetaValue"));
			NakedClassifierMap map = new NakedClassifierMap(bc);
			OJPathName javaTypePath = map.javaTypePath();
			metaValue.putAttribute("value", javaTypePath.toString());
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
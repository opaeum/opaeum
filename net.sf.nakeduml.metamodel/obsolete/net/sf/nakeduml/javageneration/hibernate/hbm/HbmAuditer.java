package net.sf.nakeduml.javageneration.hibernate.hbm;

import java.util.List;
import java.util.Set;

import net.hibernatehbmmetamodel.Access;
import net.hibernatehbmmetamodel.Collection;
import net.hibernatehbmmetamodel.Generated;
import net.hibernatehbmmetamodel.HbmClass;
import net.hibernatehbmmetamodel.HbmWorkspace;
import net.hibernatehbmmetamodel.HibernateConfiguration;
import net.hibernatehbmmetamodel.Join;
import net.hibernatehbmmetamodel.ManyToOne;
import net.hibernatehbmmetamodel.ManyToOneLazy;
import net.hibernatehbmmetamodel.OneToMany;
import net.hibernatehbmmetamodel.Property;
import net.hibernatehbmmetamodel.SubClass;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.auditing.AuditImplementationStep;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = HibernateHbmPhase.class,requires = {AuditImplementationStep.class, BasicJavaModelStep.class,PersistentNameGenerator.class},after = {HbmPersistence.class})
public class HbmAuditer extends AbstractHbmTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		HbmAuditGenerator hbmAuditGenerator = new HbmAuditGenerator();
//		hbmAuditGenerator.initialize(workspace, javaModel, config, textWorkspace, this.hbmWorkspace);
		hbmAuditGenerator.startVisiting(workspace);
		
		HbmWorkspace copy = this.hbmWorkspace.makeCopy();
		rename(copy);
		addOriginal(copy);
		addRevision(copy);
		removeDeletedOnFilter(copy);
		
	}

	private void removeDeletedOnFilter(HbmWorkspace copy) {
		Set<HibernateConfiguration> configurations = copy.getHibernateConfiguration();
		for (HibernateConfiguration hibernateConfiguration : configurations) {
			Set<HbmClass> hbmClasses = hibernateConfiguration.getHbmClass();
			for (HbmClass hbmClass : hbmClasses) {
				Set<Collection> collections = hbmClass.getCollection();
				for (Collection collection : collections) {
					collection.setFilter(null);
				}
			}
			Set<SubClass> subClasses = hibernateConfiguration.getSubClass();
			for (SubClass subClass : subClasses) {
				Set<Collection> collections = subClass.getCollection();
				for (Collection collection : collections) {
					collection.setFilter(null);
				}
			}
		}
	}

	private void addOriginal(HbmWorkspace copy) {
		Set<HibernateConfiguration> configurations = copy.getHibernateConfiguration();
		for (HibernateConfiguration hibernateConfiguration : configurations) {
			Set<HbmClass> hbmClasses = hibernateConfiguration.getHbmClass();
			for (HbmClass hbmClass : hbmClasses) {
				if (!hbmClass.get_abstract()) {
					ManyToOne manyToOne = new ManyToOne(hbmClass);
					manyToOne.setAccess(Access.FIELD);
					manyToOne.setClassName(hbmClass.getEntityName());
					manyToOne.setQualifiedName(hbmClass.getQualifiedName().substring(0, hbmClass.getQualifiedName().length()-6));
					manyToOne.setEntityName(hbmClass.getEntityName().substring(0, hbmClass.getEntityName().length()-6));
					manyToOne.setName("original");
					manyToOne.setManyToOneLazy(ManyToOneLazy.PROXY);
					manyToOne.setColumn(NameConverter.decapitalize(hbmClass.getName().substring(0, hbmClass.getName().length()-6).substring(hbmClass.getName().lastIndexOf(".")+1))+"_original_id");
				}
			}
			
			Set<SubClass> subClasses = hibernateConfiguration.getSubClass();
			for (SubClass subClass : subClasses) {
				if (!subClass.get_abstract()) {
					Join join = subClass.getJoin().iterator().next();
					ManyToOne manyToOne = new ManyToOne(join);
					manyToOne.setAccess(Access.FIELD);
					manyToOne.setClassName(subClass.getEntityName().substring(0, subClass.getEntityName().length()-6));
					manyToOne.setQualifiedName(subClass.getQualifiedName().substring(0, subClass.getQualifiedName().length()-6));
					manyToOne.setEntityName(subClass.getEntityName().substring(0, subClass.getEntityName().length()-6));
					manyToOne.setName("original");
					manyToOne.setManyToOneLazy(ManyToOneLazy.PROXY);
					manyToOne.setColumn(NameConverter.decapitalize(subClass.getName().substring(0, subClass.getName().length()-6).substring(subClass.getName().lastIndexOf(".")+1))+"_original_id");
				}
			}
			
		}		
	}

	private void addRevision(HbmWorkspace copy) {
		Set<HibernateConfiguration> configurations = copy.getHibernateConfiguration();
		for (HibernateConfiguration hibernateConfiguration : configurations) {
			Set<HbmClass> hbmClasses = hibernateConfiguration.getHbmClass();
			for (HbmClass hbmClass : hbmClasses) {
				Property revisionType = new Property(hbmClass);
				revisionType.setType("util.RevisionType");
				revisionType.setName("revisionType");
				revisionType.setGenerated(Generated.NEVER);
				revisionType.setQualifiedName("");
				revisionType.setUniqueKey("");
				
				ManyToOne manyToOne = new ManyToOne(hbmClass);
				manyToOne.setAccess(Access.FIELD);
				manyToOne.setClassName("util.RevisionEntity");
				manyToOne.setQualifiedName("util.RevisionEntity");
				manyToOne.setEntityName("util.RevisionEntity");
				manyToOne.setName("revision");
				manyToOne.setManyToOneLazy(ManyToOneLazy.PROXY);
			}
		}
	}

	protected void createTextPath(HibernateConfiguration hibernateConfiguration,String outputRoot){
		try{
			SourceFolder or = textWorkspace.findOrCreateTextOutputRoot(outputRoot);
			List<String> names = hibernateConfiguration.getPath();
			or.findOrCreateTextFile(names, new HbmTextSource(hibernateConfiguration));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void rename(HbmWorkspace copy) {
		Set<HibernateConfiguration> configurations = copy.getHibernateConfiguration();
		for (HibernateConfiguration hibernateConfiguration : configurations) {
			List<String> path = hibernateConfiguration.getPath();
			String last = path.get(path.size()-1);
			int indexOf = last.indexOf(".hbm.xml");
			last = last.substring(0,indexOf) + "_Audit.hbm.xml";
			path.set(path.size()-1, last);
			createTextPath(hibernateConfiguration, HbmTextSource.GEN_RESOURCE);
			
			Set<HbmClass> hbmClasses = hibernateConfiguration.getHbmClass();
			for (HbmClass hbmClass : hbmClasses) {
				hbmClass.setName(hbmClass.getName()+"_Audit");
				hbmClass.setEntityName(hbmClass.getEntityName()+"_Audit");
				hbmClass.setTable(hbmClass.getTable()+"_audit");
				
				Set<ManyToOne> manyToOnes = hbmClass.getManyToOne();
				for (ManyToOne manyToOne : manyToOnes) {
					manyToOne.setClassName(manyToOne.getClassName()+"_Audit");
					manyToOne.setEntityName(manyToOne.getEntityName()+"_Audit");
				}
				Set<Collection> collections = hbmClass.getCollection();
				for (Collection collection : collections) {
					OneToMany oneToMany = collection.getOneToMany();
					oneToMany.setClassName(oneToMany.getClassName()+"_Audit");
					oneToMany.setQualifiedName(oneToMany.getQualifiedName()+"_Audit");
				}
			}
			
			Set<SubClass> subClasses = hibernateConfiguration.getSubClass();
			for (SubClass subClass : subClasses) {
				subClass.setName(subClass.getName()+"_Audit");
				subClass.setEntityName(subClass.getEntityName()+"_Audit");
				subClass.set_extends(subClass.get_extends() +"_Audit");
				
				Set<Join> joins = subClass.getJoin();
				for (Join join : joins) {
					
					join.setTable(join.getTable()+"_audit");
					
					Set<ManyToOne> mayToOnes = join.getManyToOne();
					for (ManyToOne manyToOne : mayToOnes) {
						manyToOne.setClassName(manyToOne.getClassName()+"_Audit");
						manyToOne.setEntityName(manyToOne.getEntityName()+"_Audit");
					}
				}
				
				Set<Collection> collections = subClass.getCollection();
				for (Collection collection : collections) {
					OneToMany oneToMany = collection.getOneToMany();
					oneToMany.setClassName(oneToMany.getClassName()+"_Audit");
					oneToMany.setQualifiedName(oneToMany.getQualifiedName()+"_Audit");
				}				
				
			}
		}
	}
}

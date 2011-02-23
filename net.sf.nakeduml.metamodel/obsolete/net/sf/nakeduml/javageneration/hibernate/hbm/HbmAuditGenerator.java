package net.sf.nakeduml.javageneration.hibernate.hbm;

import java.util.List;
import java.util.Set;

import net.hibernatehbmmetamodel.HbmClass;
import net.hibernatehbmmetamodel.HbmWorkspace;
import net.hibernatehbmmetamodel.HibernateConfiguration;
import net.hibernatehbmmetamodel.Join;
import net.hibernatehbmmetamodel.ManyToOne;
import net.hibernatehbmmetamodel.ManyToOneLazy;
import net.hibernatehbmmetamodel.SubClass;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.textmetamodel.SourceFolder;

public class HbmAuditGenerator extends HbmJavaProducingVisitor {
	public static final boolean DEVELOPMENT_MODE = true;
	private HbmWorkspace hbmWorkspace;
	
	public void createTextPath(String outputRoot){
		try{
			textWorkspace.findOrCreateTextOutputRoot(outputRoot);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	public void createTextPath(HibernateConfiguration hibernateConfiguration,String outputRoot){
		try{
			SourceFolder or = textWorkspace.findOrCreateTextOutputRoot(outputRoot);
			List<String> names = hibernateConfiguration.getPath();
			or.findOrCreateTextFile(names, new HbmTextSource(hibernateConfiguration));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@VisitAfter
	public void visitModel(INakedModel model) {
	}
	
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		Set<HibernateConfiguration> hibernateConfigurations = this.hbmWorkspace.getHibernateConfiguration();
		for (HibernateConfiguration hibernateConfiguration : hibernateConfigurations) {
			Set<HbmClass> classes = hibernateConfiguration.getHbmClass();
			for (HbmClass hbmClass : classes) {
				if (hbmClass.getName().equals(c.getMappingInfo().getQualifiedJavaName())) {
					Set<ManyToOne> manyToOnes = hbmClass.getManyToOne();
					for (ManyToOne manyToOne : manyToOnes) {
						if (manyToOne.getName().equals(c.getEndToComposite().getName())) {
							manyToOne.setManyToOneLazy(ManyToOneLazy.FALSE);
						}
					}
				}
			}
			Set<SubClass> subClasses = hibernateConfiguration.getSubClass();
			for (SubClass subClass : subClasses) {
				if (subClass.getName().equals(c.getMappingInfo().getQualifiedJavaName())) {
					
					Set<Join> joins = subClass.getJoin();
					for (Join join : joins) {
						Set<ManyToOne> manyToOnes = join.getManyToOne();
						for (ManyToOne manyToOne : manyToOnes) {
							if (manyToOne.getName().equals(c.getEndToComposite().getName())) {
								manyToOne.setManyToOneLazy(ManyToOneLazy.FALSE);
							}
						}
					}
				}
			}
		}
	}

}

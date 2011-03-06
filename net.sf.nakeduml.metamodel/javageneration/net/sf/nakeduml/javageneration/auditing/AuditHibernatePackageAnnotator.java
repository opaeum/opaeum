package net.sf.nakeduml.javageneration.auditing;

import org.nakeduml.runtime.domain.AuditId;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.hibernate.AbstractHibernatePackageAnnotator;
import net.sf.nakeduml.javageneration.hibernate.HibernateUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

public class AuditHibernatePackageAnnotator extends AbstractHibernatePackageAnnotator {
	public AuditHibernatePackageAnnotator(boolean isIntegrationPhase) {
		super(isIntegrationPhase);
	}

	public AuditHibernatePackageAnnotator() {
		super(true);
	}

	protected String getMetaDefName(INakedInterface i) {
		return HibernateUtil.metadefName(i) + "Audit";
	}

	protected String getIdType() {
		return AuditId.class.getName();
	}

	protected OJPathName getTargetEntity(OJPathName javaTypePath) {
		return new OJPathName(javaTypePath + "_Audit");
	}

	@Override
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace root) {
		doWorkspace(root);
	}

	@VisitBefore
	@Override
	public void visitModel(INakedModel model) {
		doModel(model);
	}
}

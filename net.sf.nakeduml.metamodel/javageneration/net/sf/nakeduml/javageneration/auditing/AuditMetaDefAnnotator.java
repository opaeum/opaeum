package net.sf.nakeduml.javageneration.auditing;


import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.hibernate.AbstractMetaDefAnnotator;
import net.sf.nakeduml.javageneration.hibernate.HibernateUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.core.INakedInterface;

public class AuditMetaDefAnnotator extends AbstractMetaDefAnnotator {
	public AuditMetaDefAnnotator(boolean isIntegrationPhase) {
		super(isIntegrationPhase);
	}

	public AuditMetaDefAnnotator() {
		super(true);
	}


	protected String getMetaDefName(INakedInterface i) {
		return HibernateUtil.metadefName(i)+"Audit";
	}

	protected String getIdType() {
		return AuditEntryMassage.AUDIT_ID_USER_TYPE;
	}

	protected OJPathName getTargetEntity(OJPathName javaTypePath) {
		return new OJPathName(javaTypePath+"_Audit");
	}

}

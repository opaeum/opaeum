package net.sf.nakeduml.javageneration.auditing;


import org.nakeduml.runtime.domain.AuditId;

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
		return AuditId.class.getName();
	}

	protected OJPathName getTargetEntity(OJPathName javaTypePath) {
		return new OJPathName(javaTypePath+"_Audit");
	}

}

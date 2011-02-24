package net.sf.nakeduml.javageneration.persistence;


import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.auditing.AuditEntryMassage;
import net.sf.nakeduml.javageneration.hibernate.HibernateUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.core.INakedInterface;

public class AuditMetaDefAnnotator extends AbstractMetaDefAnnotator {
	@VisitAfter
	public void visitInterface(INakedInterface i) {
		doInterface(i);
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

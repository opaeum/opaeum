package net.sf.nakeduml.javageneration.hibernate;


import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.auditing.AuditEntryMassage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.core.INakedInterface;

public class MetaDefAnnotator extends AbstractMetaDefAnnotator {
	public MetaDefAnnotator(boolean isIntegrationPhase) {
		super(isIntegrationPhase);
	}

	protected String getMetaDefName(INakedInterface i) {
		return HibernateUtil.metadefName(i);
	}

	protected String getIdType() {
		return "long";
	}

	protected OJPathName getTargetEntity(OJPathName javaTypePath) {
		return javaTypePath;
	}

}

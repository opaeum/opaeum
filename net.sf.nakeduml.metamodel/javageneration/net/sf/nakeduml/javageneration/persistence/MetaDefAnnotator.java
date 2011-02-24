package net.sf.nakeduml.javageneration.persistence;


import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.auditing.AuditEntryMassage;
import net.sf.nakeduml.javageneration.hibernate.HibernateUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.core.INakedInterface;

public class MetaDefAnnotator extends AbstractMetaDefAnnotator {
	protected String getMetaDefName(INakedInterface i) {
		return HibernateUtil.metadefName(i);
	}

	protected String getIdType() {
		return "long";
	}

	protected OJPathName getTargetEntity(OJPathName javaTypePath) {
		return javaTypePath;
	}
	@VisitAfter
	public void visitInterface(INakedInterface i) {
		doInterface(i);
	}

	protected String getOutputRoot() {
		return JavaTextSource.GEN_SRC;
	}
}

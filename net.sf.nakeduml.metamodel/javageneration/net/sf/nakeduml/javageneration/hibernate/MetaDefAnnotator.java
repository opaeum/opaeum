package net.sf.nakeduml.javageneration.hibernate;


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

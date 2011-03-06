package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

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

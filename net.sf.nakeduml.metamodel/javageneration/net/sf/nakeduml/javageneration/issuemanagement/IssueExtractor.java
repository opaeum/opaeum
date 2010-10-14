package net.sf.nakeduml.javageneration.issuemanagement;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractTextProducingVisitor;
import net.sf.nakeduml.metamodel.models.INakedModel;

public class IssueExtractor extends AbstractTextProducingVisitor {
	public static final String DOCUMENTS = "documents";

	@VisitBefore
	public void visitWorkspace(INakedModel m) {
		processTemplate(m, "Package/Priorities.vsl", "priorities.csv", IssueExtractor.DOCUMENTS);
		processTemplate(m, "Package/CapabilityPriorities.vsl", "capabilities.csv", IssueExtractor.DOCUMENTS);
	}
}

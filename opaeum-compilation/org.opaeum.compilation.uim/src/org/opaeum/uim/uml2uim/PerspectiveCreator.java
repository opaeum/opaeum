package org.opaeum.uim.uml2uim;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.InboxConfiguration;
import org.opaeum.uim.perspective.OutboxConfiguration;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.PositionInPerspective;
import org.opaeum.uim.perspective.PropertiesConfiguration;

public class PerspectiveCreator extends AbstractUimSynchronizer{
	@VisitBefore
	public void visitWorkspace(EmfWorkspace ws){
		URI uri = super.workspace.getDirectoryUri().appendSegment("ui").appendFragment("perspective").appendFileExtension("uml");
		Resource resource = super.uimRst.getResource(uri, false);
		if(resource == null){
			super.uimRst.createResource(uri);
		}
		resource.getContents().clear();
		PerspectiveConfiguration p = PerspectiveFactory.eINSTANCE.createPerspectiveConfiguration();
		resource.getContents().add(p);
		ExplorerConfiguration explorerPosition = PerspectiveFactory.eINSTANCE.createExplorerConfiguration();
		explorerPosition.setWidth(300);
		explorerPosition.setPosition(PositionInPerspective.LEFT);
		p.setExplorer(explorerPosition);
		EditorConfiguration editorPosition = PerspectiveFactory.eINSTANCE.createEditorConfiguration();
		editorPosition.setWidth(300);
		p.setEditor(editorPosition);
		PropertiesConfiguration propertiesPosition = PerspectiveFactory.eINSTANCE.createPropertiesConfiguration();
		propertiesPosition.setHeight(200);
		propertiesPosition.setPosition(PositionInPerspective.BOTTOM);
		p.setProperties(propertiesPosition );
		InboxConfiguration inboxConfiguration=PerspectiveFactory.eINSTANCE.createInboxConfiguration();
		inboxConfiguration.setPosition(PositionInPerspective.LEFT_TOP);
		p.setInbox(inboxConfiguration);
		OutboxConfiguration outboxConfiguration=PerspectiveFactory.eINSTANCE.createOutboxConfiguration();
		outboxConfiguration.setPosition(PositionInPerspective.LEFT_BOTTOM);
		p.setOutbox(outboxConfiguration);
	}
}

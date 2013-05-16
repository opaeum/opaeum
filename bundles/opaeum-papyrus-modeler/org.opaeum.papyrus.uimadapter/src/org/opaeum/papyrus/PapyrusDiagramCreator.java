package org.opaeum.papyrus;

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.newchild.IDiagramCreator;
import org.opaeum.eclipse.newchild.RelationshipDirection;

public class PapyrusDiagramCreator implements IDiagramCreator{
	@Override
	public boolean matches(IEditorPart editor){
		return editor instanceof PapyrusMultiDiagramEditor;
	}
	@Override
	public void createDiagram(String suffix,Collection<? extends Element> elements,IEditorPart editor,RelationshipDirection direction,
			EClass...relationships){
		PapyrusMultiDiagramEditor e = (PapyrusMultiDiagramEditor) editor;
		CreateClassDiagramNotationElements cmd = new CreateClassDiagramNotationElements(elements, (ModelSet) e.getEditingDomain()
				.getResourceSet(), relationships, direction, suffix);
		e.getEditingDomain().getCommandStack().execute(cmd);
		final IPageManager pageManager = (IPageManager) e.getAdapter(IPageManager.class);
		Set<Diagram> diagrams = cmd.getDiagrams();
		for(final Diagram diagram:diagrams){
			e.getEditingDomain().getCommandStack().execute(new AbstractCommand(){
				@Override
				public void redo(){
					// TODO Auto-generated method stub
				}
				@Override
				public void execute(){
					pageManager.openPage(diagram);
				}
				@Override
				public boolean canExecute(){
					return true;
				}
			});
		}
	}
}

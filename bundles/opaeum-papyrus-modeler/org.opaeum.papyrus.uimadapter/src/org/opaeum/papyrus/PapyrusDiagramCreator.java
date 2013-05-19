package org.opaeum.papyrus;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageManager;
import org.eclipse.papyrus.uml.diagram.menu.actions.ArrangeAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
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
		final PapyrusMultiDiagramEditor e = (PapyrusMultiDiagramEditor) editor;
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
					openPageAndLayout(e, pageManager, diagram);
				}
				@Override
				public boolean canExecute(){
					return true;
				}
			});
		}
	}
	private void openPageAndLayout(final PapyrusMultiDiagramEditor e,final IPageManager pageManager,final Diagram diagram){
		pageManager.openPage(diagram);
		IEditorPart activeEditor = e.getActiveEditor();
		if(activeEditor instanceof DiagramEditor){
			DiagramEditor de= (DiagramEditor) activeEditor;
			ArrangeAction aa = new ArrangeAction(ArrangeAction.ARRANGE_ALL, de.getDiagramEditPart().getChildren());
			try{
				aa.getCommand().execute();
			}catch(Exception e2){
				OpaeumEclipsePlugin.logInfo(e2.toString());
			}
		}
		
	}
}

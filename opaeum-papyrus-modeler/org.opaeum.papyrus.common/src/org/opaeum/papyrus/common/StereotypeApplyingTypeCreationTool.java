package org.opaeum.papyrus.common;

import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SemanticCreateCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.eclipse.papyrus.uml.diagram.common.service.palette.StereotypePostAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;

public class StereotypeApplyingTypeCreationTool extends AspectUnspecifiedTypeCreationTool{
	OpenUmlFile currentFile;
	public StereotypeApplyingTypeCreationTool(List<IElementType> elementTypes,final String profileName,final String stereotypeName){
		super(elementTypes);
		super.postActions.add(new StereotypePostAction(){
			public void run(final EditPart editPart){
				final Element c = (Element) editPart.getAdapter(Element.class);
				Stereotype st = ProfileApplier.getProfile(c, profileName).getOwnedStereotype(stereotypeName);
				ApplyStereotypeCommand command = new ApplyStereotypeCommand(c, st){
					@Override
					public void execute(){
						super.execute();
						currentFile = OpaeumEclipseContext.getCurrentContext().getEditingContextFor(c);
						currentFile.resumeAndCatchUp();
					}
				};
				OpaeumEclipseContext.getCurrentContext().executeAndForget(command);
			};
		});
	}
	protected void executeCurrentCommand(){
		if(currentFile != null){
			// We need this whole sequence to be processessed in a single go so that the object is only extracted once the stereotype has been
			// applied
			currentFile.suspend();
		}
		final Command curCommand = getCurrentCommand();
		if(curCommand != null && curCommand.canExecute())
			executeCommand(curCommand);
		setCurrentCommand(null);
	}
}

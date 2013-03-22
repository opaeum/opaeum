package org.opaeum.papyrus.common;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;

public class StereotypeApplyingTypeCreationTool extends AspectUnspecifiedTypeCreationTool{
	private String stereotypeName;
	EObject newElement;
	public StereotypeApplyingTypeCreationTool(List<IElementType> elementTypes,final String stereotypeName){
		super(elementTypes);
		this.stereotypeName = stereotypeName;
	}
	private void scanCommands(ICommand command){
		if(command instanceof CompositeCommand){
			final Object rv = ((CompositeCommand) command).getCommandResult().getReturnValue();
			if(rv instanceof List){
				List results = (List) rv;
				for(Object object:results){
					if(object instanceof CreateElementRequestAdapter){
						CreateElementRequest cer2 = (CreateElementRequest) ((CreateElementRequestAdapter) object).getAdapter(CreateElementRequest.class);
						newElement = cer2.getNewElement();
					}
				}
			}
		}
	}
	protected void executeCurrentCommand(){
		final CompoundCommand curCommand = (CompoundCommand) getCurrentCommand();
		if(curCommand != null && curCommand.canExecute()){
			executeCommand(curCommand);
			scanCommands(curCommand);
		}
		ApplyStereotypeCommand cmd = new ApplyStereotypeCommand(null, true, stereotypeName){
			@Override
			public void execute(){
				super.element = (Element) newElement;
				super.execute();
			}
		};
		OpenUmlFile currentFile = OpaeumEclipseContext.findOpenUmlFileFor(newElement);
		currentFile.getEditingDomain().getCommandStack().execute(cmd);
		setCurrentCommand(null);
	}
	protected void scanCommands(final CompoundCommand curCommand){
		final List<Command> commands = curCommand.getCommands();
		for(Command command:commands){
			if(command instanceof ICommandProxy){
				ICommandProxy p = (ICommandProxy) command;
				scanCommands(p.getICommand());
			}else if(command instanceof CompoundCommand){
				scanCommands((CompoundCommand) command);
			}
		}
	}
}

package org.opaeum.papyrus.common;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;

public class StereotypeApplyingTypeCreationTool extends AspectUnspecifiedTypeCreationTool{
	private String stereotypeName;
	public StereotypeApplyingTypeCreationTool(List<IElementType> elementTypes,final String stereotypeName){
		super(elementTypes);
		this.stereotypeName = stereotypeName;
	}
	protected void executeCurrentCommand(){
		final Command curCommand = getCurrentCommand();
		if(curCommand != null && curCommand.canExecute()){
			executeCommand(curCommand);
		}
		ApplyStereotypeCommand cmd = new ApplyStereotypeCommand(null,true, stereotypeName){
			@Override
			public void execute(){
				org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool.CreateAspectUnspecifiedTypeRequest tr = (org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool.CreateAspectUnspecifiedTypeRequest) getTargetRequest();
				CreateViewAndElementRequest r = (CreateViewAndElementRequest) tr.getRequestForType((IElementType) tr.getElementTypes().get(0));
				ViewDescriptor viewDescriptor = r.getViewDescriptors().get(0);
				Node adapter = (Node) viewDescriptor.getAdapter(EObject.class);
				super.element = (Element) adapter.getElement();
				super.execute();
			}
		};
		org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool.CreateAspectUnspecifiedTypeRequest tr = (org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool.CreateAspectUnspecifiedTypeRequest) getTargetRequest();
		CreateViewAndElementRequest r = (CreateViewAndElementRequest) tr.getRequestForType((IElementType) tr.getElementTypes().get(0));
		ViewDescriptor viewDescriptor = r.getViewDescriptors().get(0);
		Node adapter = (Node) viewDescriptor.getAdapter(EObject.class);
		OpenUmlFile currentFile = OpaeumEclipseContext.findOpenUmlFileFor(adapter.getElement());
		currentFile.getEditingDomain().getCommandStack().execute(cmd);
		setCurrentCommand(null);
	}
}

package org.opaeum.papyrus.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.papyrus.commands.wrappers.EMFtoGEFCommandWrapper;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.eclipse.papyrus.uml.diagram.common.service.palette.StereotypePostAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;

public class StereotypeApplyingTypeCreationTool extends AspectUnspecifiedTypeCreationTool{
	private String profileName;
	private String stereotypeName;
	public StereotypeApplyingTypeCreationTool(List<IElementType> elementTypes,final String stereotypeName){
		super(elementTypes);
		this.stereotypeName = stereotypeName;
	}
	public StereotypeApplyingTypeCreationTool(List<IElementType> elementTypes,final String profileName,final String stereotypeName){
		super(elementTypes);
		this.profileName = profileName;
		this.stereotypeName = stereotypeName;
	}
	protected void executeCurrentCommand(){
		final Command curCommand = getCurrentCommand();
		List<?> list = (List<?>) getCreateRequest().getNewObject();
		if(curCommand != null && curCommand.canExecute()){
			executeCommand(curCommand);
		}
		ApplyStereotypeCommand cmd = new ApplyStereotypeCommand(null, (Stereotype) null){
			@Override
			public void execute(){
				org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool.CreateAspectUnspecifiedTypeRequest tr = (org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool.CreateAspectUnspecifiedTypeRequest) getTargetRequest();
				CreateViewAndElementRequest r = (CreateViewAndElementRequest) tr.getRequestForType((IElementType) tr.getElementTypes().get(0));
				ViewDescriptor viewDescriptor = r.getViewDescriptors().get(0);
				Node adapter = (Node) viewDescriptor.getAdapter(EObject.class);
				super.element = (Element) adapter.getElement();
				if(profileName == null || ProfileApplier.getProfile(element, profileName) == null){
					EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
					ann.setSource(StereotypeNames)
					element.getEAnnotations().add(ann);
					// Just a keywordor profile not available;
				}else{
					Profile profile = ProfileApplier.getProfile(element, profileName);
					Stereotype st = profile.getOwnedStereotype(stereotypeName);
					super.stereotypes = Arrays.asList(st);
					super.execute();
				}
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

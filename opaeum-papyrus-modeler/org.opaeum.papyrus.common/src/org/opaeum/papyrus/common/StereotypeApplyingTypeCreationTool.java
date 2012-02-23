package org.opaeum.papyrus.common;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.eclipse.papyrus.uml.diagram.common.service.palette.StereotypePostAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class StereotypeApplyingTypeCreationTool extends AspectUnspecifiedTypeCreationTool{
	public StereotypeApplyingTypeCreationTool(List<IElementType> elementTypes,final String profileName,final String stereotypeName){
		super(elementTypes);
		super.postActions.add(new StereotypePostAction(){
			public void run(final EditPart editPart){
				Element c = (Element) editPart.getAdapter(Element.class);
				Stereotype st = ProfileApplier.getProfile(c, profileName).getOwnedStereotype(stereotypeName);
				ApplyStereotypeCommand command = new ApplyStereotypeCommand(c, st);
				OpaeumEclipseContext.getCurrentContext().executeAndForget(command);
			};
		});
	}
}

package org.opaeum.papyrus.uml.modelexplorer.handler;

import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.common.command.Command;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.eclipse.papyrus.views.modelexplorer.CommandContext;
import org.eclipse.papyrus.views.modelexplorer.ICommandContext;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class MethodHandler extends StereotypedElementHandler implements IHandler{
	private Command createCommand;
	protected IElementType getElementTypeToCreate(){
		return UMLElementTypes.ACTIVITY;
	}
	protected String getStereotype(){
		return StereotypeNames.METHOD;
	}
	protected String getProfile(){
		return StereotypeNames.OPAEUM_BPM_PROFILE;
	}
	@Override
	protected ICommandContext getCommandContext(){
		ICommandContext c = super.getCommandContext();
		return new CommandContext(c.getContainer(), UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior());
	}
}

package org.opaeum.uimodeler.modelexplorer.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.views.modelexplorer.ICommandFilter;
import org.opaeum.uimodeler.page.diagram.providers.UimElementTypes;

public class CommandFilter implements ICommandFilter {

	private List<IElementType> visibleCommands;

	public List<IElementType> getVisibleCommands() {
		if(visibleCommands == null) {
			visibleCommands = new ArrayList<IElementType>();
			visibleCommands.add(UimElementTypes.GridPanel_2001);
			visibleCommands.add(UimElementTypes.HorizontalPanel_2002);
			visibleCommands.add(UimElementTypes.HorizontalPanel_3003);
			visibleCommands.add(UimElementTypes.UimDataTable_3009);
			visibleCommands.add(UimElementTypes.UimField_3001);
			visibleCommands.add(UimElementTypes.UimField_3010);
			visibleCommands.add(UimElementTypes.UserInterface_1000);
			visibleCommands.add(UimElementTypes.VerticalPanel_2003);
			visibleCommands.add(UimElementTypes.VerticalPanel_3004);
		}

		return visibleCommands;
	}
}

package org.nakeduml.topcased.activitydiagram;

import org.topcased.modeler.editor.IConfiguration;
import org.topcased.modeler.editor.IPaletteManager;

public class ActivityConfiguration extends org.topcased.modeler.uml.activitydiagram.ActivityConfiguration implements IConfiguration{
	private IPaletteManager paletteManager;
	@Override
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new ActivityPaletteManager(getCreationUtils());
		}
		return paletteManager;
	}
}
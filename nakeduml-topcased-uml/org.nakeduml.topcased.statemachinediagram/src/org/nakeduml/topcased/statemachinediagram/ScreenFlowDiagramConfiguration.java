package org.nakeduml.topcased.statemachinediagram;

import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.uml.statemachinediagram.STMConfiguration;

public class ScreenFlowDiagramConfiguration extends STMConfiguration{
	private IPaletteManager paletteManager;
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new ScreenFlowPaletteManager(getCreationUtils());
		}
		return paletteManager;
	}
}

package org.nakeduml.topcased.statemachinediagram;

import org.eclipse.gef.EditPartFactory;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.uml.statemachinediagram.STMConfiguration;
import org.topcased.modeler.uml.statemachinediagram.STMEditPartFactory;

public class ScreenFlowDiagramConfiguration extends STMConfiguration{
	private IPaletteManager paletteManager;
	private EditPartFactory editPartFactory;
	
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new ScreenFlowPaletteManager(getCreationUtils());
		}
		return paletteManager;
	}
    public EditPartFactory getEditPartFactory()
    {
        if (editPartFactory == null)
        {
            editPartFactory = new ScreenFlowEditPartFactory();
        }

        return editPartFactory;
    }

}

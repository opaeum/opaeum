package org.opeum.topcased.activitydiagram.bpm;

import org.eclipse.gef.EditPartFactory;
import org.topcased.modeler.editor.IConfiguration;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.uml.activitydiagram.ActivityCreationUtils;

public class BusinessProcessConfiguration extends org.topcased.modeler.uml.activitydiagram.ActivityConfiguration implements IConfiguration{
	private IPaletteManager paletteManager;
	private ICreationUtils creationUtils;
	private EditPartFactory editPartFactory;
	@Override
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new BusinessProcessPaletteManager(getCreationUtils());
		}
		return paletteManager;
	}
	public ICreationUtils getCreationUtils(){
		if(creationUtils == null){
			creationUtils = new ActivityCreationUtils(getDiagramGraphConf());
		}
		return creationUtils;
	}
    public EditPartFactory getEditPartFactory()
    {
		if(editPartFactory == null)
        {
            editPartFactory = new BusinessProcessEditPartFactory();
        }

        return editPartFactory;
    }

}
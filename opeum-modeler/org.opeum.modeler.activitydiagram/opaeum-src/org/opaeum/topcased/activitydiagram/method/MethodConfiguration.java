package org.opeum.topcased.activitydiagram.method;

import org.eclipse.gef.EditPartFactory;
import org.topcased.modeler.editor.IConfiguration;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.uml.activitydiagram.ActivityCreationUtils;

public class MethodConfiguration extends org.topcased.modeler.uml.activitydiagram.ActivityConfiguration implements IConfiguration{
	private IPaletteManager paletteManager;
	private ICreationUtils creationUtils;
	private EditPartFactory editPartFactory;
	@Override
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new MethodPaletteManager(getCreationUtils());
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
            editPartFactory = new MethodEditPartFactory();
        }

        return editPartFactory;
    }

}
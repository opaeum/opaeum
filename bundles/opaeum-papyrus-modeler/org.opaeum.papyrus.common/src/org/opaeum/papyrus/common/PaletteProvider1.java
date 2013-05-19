package org.opaeum.papyrus.common;

import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.IPaletteProvider;
import org.eclipse.ui.IEditorPart;

public class PaletteProvider1 implements IPaletteProvider{
	public PaletteProvider1(){
		// TODO Auto-generated constructor stub
	}
	@Override
	public void addProviderChangeListener(IProviderChangeListener listener){
		// TODO Auto-generated method stub
	}
	@Override
	public boolean provides(IOperation operation){
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void removeProviderChangeListener(IProviderChangeListener listener){
		// TODO Auto-generated method stub
	}
	@Override
	public void contributeToPalette(IEditorPart editor,Object content,PaletteRoot root,Map predefinedEntries){
		// TODO Auto-generated method stub
	}
	@Override
	public void setContributions(IConfigurationElement configElement){
		// TODO Auto-generated method stub
	}
}

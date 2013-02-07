package org.opaeum.rap.runtime.internal.editors;

import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jface.entityeditor.EntityEditorInputJface;
import org.opaeum.runtime.rwt.OpaeumRapSession;

public class EntityEditorInput extends EntityEditorInputJface  implements IEditorInput , IValueChangeListener{

	public EntityEditorInput(IPersistentObject entity,String name,ImageDescriptor imageDescriptor,OpaeumRapSession opaeumSession){
		super(entity, name, imageDescriptor, opaeumSession);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IPersistableElement getPersistable(){
		// TODO Auto-generated method stub
		return null;
	}

}
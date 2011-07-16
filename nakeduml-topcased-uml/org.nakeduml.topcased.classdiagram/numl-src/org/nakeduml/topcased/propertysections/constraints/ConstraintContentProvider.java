package org.nakeduml.topcased.propertysections.constraints;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ConstraintContentProvider implements IStructuredContentProvider{
	public Object[] getElements(Object inputElement){
		if(inputElement instanceof Collection){
			Collection collec = (Collection) inputElement;
			return collec.toArray();
		}
		return null;
	}
	public void dispose(){
	}
	public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
	}
}

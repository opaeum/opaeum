/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.cubetree;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

@SuppressWarnings("unchecked")
public class CubeTreeContentProvider implements ITreeContentProvider{
	private List<CubeRowNode> rows;
	public CubeTreeContentProvider(){
	}
	public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
		this.rows=(List<CubeRowNode>) newInput;
	}
	public void dispose(){
	}
	public boolean hasChildren(Object element){
		return true;
	}
	public Object getParent(Object element){
		if(element instanceof CubeRowNode){
			return ((CubeRowNode) element).parent;
		}
		return null;
	}
	public Object[] getElements(Object inputElement){
		this.rows = (List<CubeRowNode>) inputElement;
		return rows.toArray();
	}
	public Object[] getChildren(Object parentElement){
		if(parentElement instanceof CubeRowNode){
			try{
				return ((CubeRowNode) parentElement).maybeLoadChildren().toArray();
			}catch(Exception e){
				return new Object[0];
			}
		}else{
			return new Object[0];
		}
	}
}
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

import java.sql.SQLException;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class CubeTreeContentProvider implements ITreeContentProvider{
	private final List<AbstractCubeNode> rows;
	public CubeTreeContentProvider(List<AbstractCubeNode> rows){
		this.rows = rows;
	}
	public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
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
		return rows.toArray();
	}
	public Object[] getChildren(Object parentElement){
		if(parentElement instanceof CubeRowNode){
			try{
				return ((CubeRowNode) parentElement).getChildren().toArray();
			}catch(Exception e){
				return new Object[0];
			}
		}else{
			return new Object[0];
		}
	}
}
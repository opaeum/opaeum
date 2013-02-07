/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.internal.editors;

import java.lang.reflect.Field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.olap4j.OlapConnection;
import org.olap4j.metadata.Cube;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.jface.cubetree.CubeTreeComposite;
import org.opaeum.runtime.rwt.MondrianSession;
import org.opaeum.uim.cube.CubeQuery;

public class CubeQueryPage extends FormPage{
	private CubeQuery cubeQuery;
	private JavaMetaInfoMap metaInfo;
	private CubeTreeComposite cubeTreeComposite;
	private Composite body;
	public CubeQueryPage(final FormEditor editor,CubeQuery opaeumPage){
		super(editor, opaeumPage.getName(), opaeumPage.getName());
		this.cubeQuery = opaeumPage;
	}
	public void init(final IEditorSite site,final IEditorInput input){
		super.init(site, input);
		setTitleToolTip(RMSMessages.get().AssignmentOverviewPage_ToolTip);
		metaInfo = getEditorInput().getOpaeumSession().getApplication().getEnvironment().getMetaInfoMap();
	}
	protected void createFormContent(final IManagedForm managedForm){
		ScrolledForm form = managedForm.getForm();
		this.body = form.getBody();
		body.setLayout(new org.eclipse.swt.layout.GridLayout(1, false));
		Button refresh = new Button(body, SWT.PUSH);
		refresh.setText("Execute");
		
//		final Text text = new Text(body, SWT.MULTI);
//		text.setText("Result");
//		text.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		refresh.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				try{
					MondrianSession mondrianSession = getEditorInput().getOpaeumSession().getMondrianSession();
					Field field = Class.forName("mondrian.olap4j.MondrianOlap4jConnection").getDeclaredField("mondrianConnection");
					field.setAccessible(true);
					OlapConnection connection = mondrianSession.getConnection();
					RolapConnection rolapConnection = (RolapConnection) field.get(connection);
					CacheControl cacheControl = rolapConnection.getCacheControl(null);
					Cube cube = connection.getOlapSchema().getCubes().get(metaInfo.getClass(cubeQuery.getUmlElementUid()).getSimpleName());
					CacheControl.CellRegion measuresRegion = cacheControl.createMeasuresRegion(rolapConnection.getSchema().lookupCube(cube.getName(),
							false));
					cacheControl.flush(measuresRegion);
					cacheControl.flushSchema(rolapConnection.getSchema());
					if(cubeTreeComposite!=null){
						cubeTreeComposite.dispose();
					}
					cubeTreeComposite=new CubeTreeComposite(body, SWT.BORDER, cubeQuery, cube,metaInfo,getEditorInput().getPersistentObject());
					cubeTreeComposite.setLayoutData(new GridData(GridData.FILL,GridData.FILL,true,true));
					body.layout();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		body.layout();
	}
	public EntityEditorInput getEditorInput(){
		EntityEditorInput eei = (EntityEditorInput) super.getEditorInput();
		return eei;
	}
}

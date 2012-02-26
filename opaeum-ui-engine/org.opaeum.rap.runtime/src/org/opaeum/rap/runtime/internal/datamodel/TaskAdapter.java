// Created on 08.10.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter.IEntityAdapter;


public class TaskAdapter implements IEntityAdapter {

  public void createNewMenu( Object element, IMenuManager menuManager ) {
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public IWizardPage createWizardPage( Object element, Class destinationType ) {
    return null;
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public String createWizardTitle( Class destinationType ) {
    return null;
  }

  public Object[] getChildren( Object parent ) {
    return null;
  }

  public ImageDescriptor getEditorImage( Object element ) {
    return null;
  }

  public String getEditorName( Object element ) {
    return null;
  }

  public FormPage[] getEditorPages( Object element, FormEditor editor ) {
    return null;
  }

  public Object[] getElements( Object parent ) {
    return null;
  }

  public Image getImage( Object element ) {
    return null;
  }

  public Object getParent( Object child ) {
    return null;
  }

  public IPropertySource getPropertySource( Object element ) {
    return null;
  }

  public String getText( Object element ) {
    return null;
  }

  public boolean hasChildren( Object parent ) {
    return false;
  }

  public void refreshAssociations( Object element, StructuredViewer viewer ) {
  }
}

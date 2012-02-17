// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.rap.rms.data.DataModelRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.opaeum.rap.runtime.internal.datamodel.EntityAdapter.IEntityAdapter;

final class RootAdapter implements IEntityAdapter {

  private static final Object[] ROOT = new Object[]{
    DataModelRegistry.getFactory()
  };

  public Object[] getChildren( final Object parent ) {
    return null;
  }

  public ImageDescriptor getEditorImage( final Object element ) {
    return null;
  }

  public String getEditorName( final Object element ) {
    return null;
  }

  public Object[] getElements( final Object parent ) {
    return ROOT;
  }

  public Image getImage( final Object element ) {
    return null;
  }

  public Object getParent( final Object child ) {
    return null;
  }

  public String getText( final Object element ) {
    return null;
  }

  public boolean hasChildren( final Object parent ) {
    return false;
  }

  public void createNewMenu( final Object element,
                             final IMenuManager menuManager )
  {
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public IWizardPage createWizardPage( final Object element, 
                                       final Class destinationType )
  {
    return null;
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public String createWizardTitle( final Class destinationType ) {
    return null;
  }

  public void refreshAssociations( final Object element,
                                   final StructuredViewer viewer )
  {
  }

  public IPropertySource getPropertySource( final Object element ) {
    return null;
 }

  public FormPage[] getEditorPages( final Object element,
                                    final FormEditor editor )
  {
    return null;
  }
}
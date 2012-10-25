// Created on 09.09.2007
package org.opaeum.rap.runtime.internal.startup;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.opaeum.rap.runtime.Constants;


public class RMSPerspective implements IPerspectiveFactory {

  public static final String ID = "org.opaeum.rap.runtime.internal.startup.RMSPerspective";

  public void createInitialLayout( final IPageLayout layout ) {
    String editorArea = layout.getEditorArea();
    IFolderLayout topLeft = layout.createFolder( "topLeft", //$NON-NLS-1$
                                                 IPageLayout.LEFT,
                                                 0.25f,
                                                 editorArea );
    topLeft.addView( Constants.NAVIGATOR_ID );
    
    IFolderLayout bottom = layout.createFolder( "bottom", //$NON-NLS-1$
                                                IPageLayout.BOTTOM,
                                                0.70f,
                                                editorArea );
    bottom.addView( "org.eclipse.ui.views.PropertySheet" ); //$NON-NLS-1$
    bottom.addPlaceholder( "org.eclipse.pde.runtime.RegistryBrowser" ); //$NON-NLS-1$
    bottom.addPlaceholder( "org.eclipse.pde.runtime.LogView" ); //$NON-NLS-1$

  }
}

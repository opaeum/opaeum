package org.opaeum.rap.runtime.internal.dialogs;

import org.eclipse.rwt.graphics.Graphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;

public class HelpDialog {
  private final static Color COLOR_WHITE
    = Display.getCurrent().getSystemColor( SWT.COLOR_WHITE );
  
  private Shell shell;

  public HelpDialog( Shell parent ) {
    int style = SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE;
    shell = new Shell( parent, style );
    String title = RMSMessages.get().HelpDialog_Help;
    shell.setText( title  );
    shell.setBounds( 200, 50, 750, 600 );
    shell.setLayout( new FillLayout() );
    Composite composite = new Composite( shell, SWT.NONE );
    createContents( composite );
  }

  private void createContents( final Composite parent ) {
    parent.setLayout( new FormLayout() );
    
    Label lblBackground = new Label( parent, SWT.NONE );
    String imgBanner = Activator.IMG_INTRO_BANNER;
    Activator activator = Activator.getDefault();
    Image banner = activator.getImage( imgBanner );
    lblBackground.setImage( banner );
    lblBackground.setBackground( COLOR_WHITE );
    FormData fdBackground = new FormData();
    fdBackground.top = new FormAttachment( 0, 0 );
    fdBackground.left = new FormAttachment( 0, 0 );
    fdBackground.right = new FormAttachment( 100, 0 );
    fdBackground.bottom = new FormAttachment( 100, 0 );
    lblBackground.setLayoutData( fdBackground );

    String imgOverview = Activator.IMG_INTRO_OVERVIEW;
    Label lblOverview = new Label( parent, SWT.NONE );
    lblOverview.setImage( activator.getImage( imgOverview ) );
    FormData fdOverview = new FormData();
    fdOverview.top = new FormAttachment( 0, 2 );
    fdOverview.left = new FormAttachment( 0, 4 );
    fdOverview.right = new FormAttachment( 0, 68 );
    fdOverview.bottom = new FormAttachment( 0, 66 );
    lblOverview.setLayoutData( fdOverview );
    lblOverview.moveAbove( lblBackground );
    lblOverview.setBackground( Graphics.getColor( 225, 234, 241 ) );

    Label lblHeadLine = new Label( parent, SWT.NONE );
    lblHeadLine.setText( RMSMessages.get().HelpDialog_RAPWorkbenchDemoHelp ); 
    lblHeadLine.setBackground( COLOR_WHITE );
    FontData fontData = parent.getFont().getFontData()[ 0 ];
    lblHeadLine.setFont( Graphics.getFont( fontData.getName(),
                                           28, 
                                           SWT.BOLD ) );
    lblHeadLine.setForeground( Graphics.getColor( 109, 126, 133 ) );
    lblHeadLine.pack();
    FormData fdLblHeadLine = new FormData();
    lblHeadLine.setLayoutData( fdLblHeadLine );
    fdLblHeadLine.right = new FormAttachment( 100, 2 );
    fdLblHeadLine.top = new FormAttachment( 0, 60 );
    lblHeadLine.moveAbove( lblBackground );
    
    Browser browser = new Browser( parent, SWT.NONE );
    browser.setUrl( RMSMessages.get().HelpDialog_ContentUrl );
    FormData fdBrowser = new FormData();
    fdBrowser.top = new FormAttachment( 0, banner.getBounds().height + 1 );
    fdBrowser.bottom = new FormAttachment( 100, 0 );
    fdBrowser.left = new FormAttachment( 0, 0 );
    fdBrowser.right = new FormAttachment( 100, 0 );
    browser.setLayoutData( fdBrowser );
    browser.moveAbove( lblBackground );
  }

  public void open() {
    shell.layout();
    shell.open();
  }
}

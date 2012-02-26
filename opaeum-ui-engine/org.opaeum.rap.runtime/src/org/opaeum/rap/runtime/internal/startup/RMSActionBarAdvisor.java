// Created on 26.09.2007
package org.opaeum.rap.runtime.internal.startup;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.handlers.IHandlerService;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.actions.HelpAction;


public class RMSActionBarAdvisor extends ActionBarAdvisor {
  private IWorkbenchAction saveAction;
  private IWorkbenchAction saveAllAction;
  private Action helpAction;
  private Action showViewDlgAction;
  private MenuManager showViewMenuMgr;
  private IContributionItem showViewMenu;

  
  private final class ShowViewDlgAction extends Action {
    private final IWorkbenchWindow window;

    private ShowViewDlgAction( IWorkbenchWindow window ) {
      super( RMSMessages.get().RMSActionBarAdvisor_OpenView );
      this.window = window;
    }

    public void run() {
      try {
        IHandlerService handlerService
          = ( IHandlerService )window.getService( IHandlerService.class );
        handlerService.executeCommand( "org.eclipse.ui.views.showView", //$NON-NLS-1$
                                       null );
      } catch( final Exception ex ) {
        ex.printStackTrace();
      }
    }

    @Override
    public String getId() {
      return "showViewDlgAction"; //$NON-NLS-1$
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
      Image img = Activator.getDefault().getImage( Activator.IMG_DIALOG );
      return ImageDescriptor.createFromImage( img );
    }
  }

  
  public RMSActionBarAdvisor( final IActionBarConfigurer configurer ) {
    super( configurer );
  }
  
  @Override
  protected void makeActions( final IWorkbenchWindow window ) {
    saveAction = ActionFactory.SAVE.create( window );
    register( saveAction );
    saveAllAction = ActionFactory.SAVE_ALL.create( window );
    register( saveAllAction );
    
    helpAction = new HelpAction( window.getShell() );
    register( helpAction );
    
    showViewDlgAction = new ShowViewDlgAction( window );
    register( showViewDlgAction );
    
    showViewMenuMgr = new MenuManager( RMSMessages.get().RMSActionBarAdvisor_ShowView, "showView" ); //$NON-NLS-2$
    showViewMenu = ContributionItemFactory.VIEWS_SHORTLIST.create( window );
    showViewMenuMgr.add( showViewMenu );
  }
  
  @Override
  protected void fillMenuBar( final IMenuManager menuBar ) {
    MenuManager windowMenu 
      = new MenuManager( RMSMessages.get().RMSActionBarAdvisor_Window, IWorkbenchActionConstants.M_WINDOW );
    windowMenu.add( showViewMenuMgr );
    menuBar.add( windowMenu );
    
    MenuManager help = new MenuManager( RMSMessages.get().RMSActionBarAdvisor_Help, "help" ); //$NON-NLS-2$
    menuBar.add(  help );
    help.add( helpAction );
  }
  
  @Override
  protected void fillCoolBar( final ICoolBarManager coolBar ) {
    IToolBarManager toolbar = new ToolBarManager( SWT.FLAT | SWT.RIGHT );
    coolBar.add( new ToolBarContributionItem( toolbar, "myAction" ) ); //$NON-NLS-1$
    toolbar.add( saveAction );
    toolbar.add( saveAllAction );
    toolbar.add( helpAction );
    toolbar.add( showViewDlgAction );
  }
}

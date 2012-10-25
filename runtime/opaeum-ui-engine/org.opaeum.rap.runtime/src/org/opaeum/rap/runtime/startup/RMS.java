package org.opaeum.rap.runtime.startup;

import java.text.MessageFormat;
import java.util.Locale;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.lifecycle.IEntryPoint;
import org.eclipse.rap.rwt.lifecycle.UICallBack;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.startup.RMSWorkbenchAdvisor;

/**
 * This class controls all aspects of the application's execution
 */
public class RMS implements IEntryPoint {

  static {
    Locale.setDefault( Locale.ENGLISH );
  }

  public int createUI() {
    String locale = RWT.getRequest().getParameter( "locale" );
    if( locale != null ) {
      if( "de".equals( locale ) ) {
        RWT.setLocale( Locale.GERMAN );
      } else if( "zh".equals( locale ) ) {
        RWT.setLocale( Locale.CHINESE );
      } else {
        String txt = "Warning: Locale parameter ''{0}'' not supported.";
        String msg = MessageFormat.format( txt, new Object[] { locale } );
        System.out.println( msg );
      }
    }
  
    Display display = PlatformUI.createDisplay();
    Activator.getDefault().initializeImageRegistry( "org.opaeum.rap.runtime" );
    UICallBack.activate( RMS.class.getName() );
    RMSWorkbenchAdvisor workbenchAdvisor = new RMSWorkbenchAdvisor();
    return PlatformUI.createAndRunWorkbench( display, workbenchAdvisor );
  }
}

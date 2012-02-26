// Created on 15.10.2007
package org.eclipse.rap.rms.data;


public class ThrowableManager {
  private static IThrowableHandler throwableHandler = new IThrowableHandler() {
    public void handle( final Throwable throwable ) {
      throwable.printStackTrace();
    }
  };
  
  
  public static interface IThrowableHandler {
    void handle( final Throwable throwable );
  }

  private ThrowableManager() {
    // prevent instance creation
  }
  
  public static void setThrowableHandler( final IThrowableHandler handler ) {
    if( handler == null ) {
      throw new NullPointerException( "Parameter handler must not be null." );
    }
    throwableHandler = handler;
  }
  
  public static void handleThrowable( final Throwable throwable ) {
    throwableHandler.handle( throwable );
  }
}

// Created on 08.09.2007
package org.eclipse.rap.rms.data;

import org.eclipse.rap.rms.internal.data.DataModel;


public class DataModelRegistry {
  public final static IDataModel DEFAULT_MODEL_TYPE = new DataModel();
  private static IDataModel factory;
  

  private DataModelRegistry() {
    // prevent instance creation
  }

  public static IDataModel getFactory() {
    return factory;
  }

  public static void register( final IDataModel factory ) {
    if( DataModelRegistry.factory != null ) {
      String msg = "An IDataFactory has already been registered.";
      throw new IllegalArgumentException( msg );
    }
    DataModelRegistry.factory = factory;
  }

  public static void deregisterFactory() {
    DataModelRegistry.factory = null;
  }
}
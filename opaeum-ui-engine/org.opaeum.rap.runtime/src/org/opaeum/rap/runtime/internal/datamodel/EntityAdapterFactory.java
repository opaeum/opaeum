// Created on 26.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.ui.views.properties.IPropertySource;


public class EntityAdapterFactory implements IAdapterFactory {

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  private static final Class[] ADAPTER_LIST
    = new Class[]
  { 
    IPropertySource.class,
    ILock.class
  };

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public Object getAdapter( final Object adaptableObject, 
                            final Class adapterType )
  {
    Object result = null;
    if( adapterType == IPropertySource.class ) {
      result = EntityAdapter.getPropertySource( adaptableObject );
    } 
    if( adapterType == ILock.class ) {
      result = new ILock() {
        public boolean lock() {
          return LockManager.lock( ( IEntity )adaptableObject );
        }
        public void unLock() {
          LockManager.unLock( ( IEntity )adaptableObject );
        }
      };
    }
    return result;
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public Class[] getAdapterList() {
    return ADAPTER_LIST;
  }
}

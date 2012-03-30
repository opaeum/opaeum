// Created on 26.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;
import org.opaeum.runtime.domain.IPersistentObject;


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
          return LockManager.lock( ( IPersistentObject )adaptableObject );
        }
        public void unLock() {
          LockManager.unLock( ( IPersistentObject )adaptableObject );
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
// Created on 26.09.2007
package org.eclipse.rap.rms.internal.data;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rap.rms.data.IEntity;

abstract class Adaptable implements IEntity, IAdaptable {

  @SuppressWarnings("unchecked")
  public Object getAdapter( final Class adapter ) {
    return Platform.getAdapterManager().getAdapter( this, adapter );
  }
}

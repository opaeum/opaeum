// Created on 07.10.2007
package org.opaeum.rap.runtime.internal.datamodel;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.rap.rms.data.IEntity;


class LockManager {
  
  private final static Set<IEntity> locks = new HashSet<IEntity>();
  
  private LockManager() {
    // prevent instance creation
  }
  
  static boolean lock( final IEntity entity ) {
    boolean result = false;
    synchronized( locks ) {
      if( !locks.contains( entity ) ) {
        locks.add( entity );
        result = true;
      }
    }
    return result;
  }
  
  static void unLock( final IEntity entity ) {
    synchronized( locks ) {
      locks.remove( entity );
    }
  }
}

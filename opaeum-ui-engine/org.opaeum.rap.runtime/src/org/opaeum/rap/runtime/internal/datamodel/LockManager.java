// Created on 07.10.2007
package org.opaeum.rap.runtime.internal.datamodel;

import java.util.HashSet;
import java.util.Set;

import org.opaeum.runtime.domain.IPersistentObject;


class LockManager {
  
  private final static Set<IPersistentObject> locks = new HashSet<IPersistentObject>();
  
  private LockManager() {
    // prevent instance creation
  }
  
  static boolean lock( final IPersistentObject entity ) {
    boolean result = false;
    synchronized( locks ) {
      if( !locks.contains( entity ) ) {
        locks.add( entity );
        result = true;
      }
    }
    return result;
  }
  
  static void unLock( final IPersistentObject entity ) {
    synchronized( locks ) {
      locks.remove( entity );
    }
  }
}

// Created on 14.09.2007
package org.eclipse.rap.rms.data;

import org.opaeum.runtime.domain.IPersistentObject;


public class ModelEvent {
  
  private final IPersistentObject entity;

  public ModelEvent( final IPersistentObject entity ) {
    this.entity = entity;
  }
  
  public IPersistentObject getEntity() {
    return entity;
  }
}

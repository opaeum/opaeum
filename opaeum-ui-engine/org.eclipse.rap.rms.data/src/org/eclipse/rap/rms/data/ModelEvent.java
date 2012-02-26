// Created on 14.09.2007
package org.eclipse.rap.rms.data;


public class ModelEvent {
  
  private final IEntity entity;

  public ModelEvent( final IEntity entity ) {
    this.entity = entity;
  }
  
  public IEntity getEntity() {
    return entity;
  }
}

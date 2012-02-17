// Created on 05.10.2007
package org.opaeum.rap.runtime.internal.datamodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.rap.rms.data.IEntity;


abstract class WorkingCopy {
  final IEntity entity;
  private final Map<String, Set<PropertyChangeListener>> listener 
    = new HashMap<String, Set<PropertyChangeListener>>();
  private Runnable dirtyNotificator;
  private boolean dirty;
  private boolean initialized;

  
  WorkingCopy( final IEntity entity ) {
    this.entity = entity;
    if( entity != null ) {
      load();
    }
  }
  
  void setDirtyNotificator( final Runnable dirtyNotificator ) {
    this.dirtyNotificator = dirtyNotificator;
  }
  
  Runnable getDirtyNotificator() {
    return dirtyNotificator;
  }
  
  final void load() {
    doLoad( entity );
    initialized = true;
  }

  abstract void doLoad( IEntity entity );

  final void save() {
    doSave( entity );
    setDirty( false );
  }


  abstract void doSave( IEntity entity );

  public boolean isDirty() {
    return dirty;
  }
  
  public void setDirty( final boolean dirty ) {
    if( dirty != this.dirty ) {
      this.dirty = dirty;
      dirtyNotificator.run();
    }
  }

  public void addPropertyChangeListener( final String property,
                                         final PropertyChangeListener lsnr )
  {
    Set<PropertyChangeListener> set = listener.get( property );
    if( set == null ) {
      set = new HashSet<PropertyChangeListener>();
      listener.put( property, set );
    }
    set.add( lsnr );
  }
  
  public void removePropertyChangeListener( final String property,
                                            final PropertyChangeListener lsnr )
  {
    Set<PropertyChangeListener> set = listener.get( property );
    if( set != null ) {
      set.remove( lsnr );
      if( set.size() == 0 ) {
        listener.remove( property );
      }
    }
  }

  Object notify( final String property,
                 final Object newValue,
                 final Object oldValue )
  {
    Set<PropertyChangeListener> set = listener.get( property );
    if( set != null ) {
      PropertyChangeEvent evt
        = new PropertyChangeEvent( this, property, oldValue, newValue );
      Iterator<PropertyChangeListener> iterator = set.iterator();
      while( iterator.hasNext() ) {
        PropertyChangeListener lsnr = iterator.next();
        if( lsnr != null ) {
          lsnr.propertyChange( evt );
        }
      }
    }
    
    if( initialized ) {
      setDirty( true );
    }
    return newValue;
  }
}

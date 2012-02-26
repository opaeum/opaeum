// Created on 14.09.2007
package org.eclipse.rap.rms.data;


public interface ModelListener {
  void entityCreated( ModelEvent evt );
  void entityChanged( ModelEvent evt );
}

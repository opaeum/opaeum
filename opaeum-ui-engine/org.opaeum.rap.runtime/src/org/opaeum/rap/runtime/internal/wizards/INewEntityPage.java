// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.wizards;

import org.eclipse.rap.rms.data.IEntity;


public interface INewEntityPage {
  boolean create();
  IEntity getEntity();
}

// Created on 14.09.2007
package org.opaeum.rap.runtime.internal.wizards;

import org.opaeum.runtime.domain.IPersistentObject;


public interface INewEntityPage {
  boolean create();
  IPersistentObject getEntity();
}

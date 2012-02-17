// Created on 07.10.2007
package org.opaeum.rap.runtime.internal.datamodel;


public interface ILock {
  boolean lock();
  void unLock();
}

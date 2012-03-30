// Created on 08.09.2007
package org.eclipse.rap.rms.data;

import org.opaeum.runtime.domain.IPersistentObject;


public interface IAssignment extends IPersistentObject {
  IProject getProject();
  IEmployee getEmployee();
}

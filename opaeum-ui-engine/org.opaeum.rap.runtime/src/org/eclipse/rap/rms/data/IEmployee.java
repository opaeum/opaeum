// Created on 08.09.2007
package org.eclipse.rap.rms.data;

import java.util.List;

import org.opaeum.runtime.domain.IPersistentObject;


public interface IEmployee extends IPersistentObject {
  String getLastName();
  String getFirstName();
  List<IAssignment> getAssignments();
}

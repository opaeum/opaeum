// Created on 08.09.2007
package org.eclipse.rap.rms.data;

import java.util.List;


public interface IEmployee extends IEntity {
  String getLastName();
  String getFirstName();
  List<IAssignment> getAssignments();
}

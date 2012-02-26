// Created on 08.09.2007
package org.eclipse.rap.rms.data;

import java.util.Date;
import java.util.List;



public interface IProject extends IEntity {
  String getName();
  IPrincipal getPrincipal();  
  List<IAssignment> getAssignments();
  IAssignment newAssignment( IEmployee employee );
 
  ITask newTask( String name );
  List<ITask> getTasks();
  
  String getDescription();
  void setDescription( String description );
  Date getStartDate();
  void setStartDate( Date startDate );
  Date getEndDate();
  void setEndDate( Date endDate );
}

// Created on 08.09.2007
package org.eclipse.rap.rms.data;

import java.util.Date;

import org.opaeum.runtime.domain.IPersistentObject;


public interface ITask extends IPersistentObject {
  String getName();
  IProject getProject();
  
  String getDescription();
  void setDescription( String description );
  Date getStartDate();
  void setStartDate( Date startDate );
  Date getEndDate();
  void setEndDate( Date endDate );
}

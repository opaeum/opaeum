// Created on 08.09.2007
package org.eclipse.rap.rms.data;

import java.util.List;

public interface IDataModel extends IEntity {
  IEmployee newEmployee( String lastName, String firstName );
  List<IEmployee> getEmployees();

  IPrincipal newPrincipal( String name );
  List<IPrincipal> getPrincipals();
  
  void addModelListener( ModelListener modelListener );
  void removeModelListener( ModelListener modelListener );

  IStorageManager getStorageManager();
}

package org.eclipse.rap.rms.internal.data;

import org.w3c.dom.*;


class DataModelReader implements IEntityReader {
  private final Document document;
  
  DataModelReader( final Document document ) {
    this.document = document;
  }

  public void load() {
    NodeList employees
      = document.getElementsByTagName( EntityConstants.EMPLOYEE );      
    for( int i = 0; i < employees.getLength(); i++ ) {
      EmployeeReader employeeReader
         = new EmployeeReader( ( Element )employees.item( i ) );
      employeeReader.load();      
    }
    NodeList principals
      = document.getElementsByTagName( EntityConstants.PRINCIPAL );
    for( int i = 0; i < principals.getLength(); i++ ) {
      PrincipalReader principalReader
        = new PrincipalReader( ( Element )principals.item( i ) );
      principalReader.load();
    }
  }
}

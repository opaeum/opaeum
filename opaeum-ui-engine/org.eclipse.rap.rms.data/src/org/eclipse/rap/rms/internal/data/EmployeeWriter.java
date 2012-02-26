package org.eclipse.rap.rms.internal.data;

import org.eclipse.rap.rms.data.IEmployee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


class EmployeeWriter implements IEntityWriter {
  private final IEmployee employee; 
  private final Element employees;

  EmployeeWriter( final IEmployee employee,
                  final Element employees )
  {
    this.employee = employee; 
    this.employees = employees;
  }

  public void save() {
    Document document = employees.getOwnerDocument();
    Element newEmployee = document.createElement( EntityConstants.EMPLOYEE );
    newEmployee.setAttribute( EntityConstants.ID, employee.getId() );
    newEmployee.setAttribute( EntityConstants.FIRST_NAME,
                              employee.getFirstName() );
    newEmployee.setAttribute( EntityConstants.LAST_NAME,
                              employee.getLastName() );    
    employees.appendChild( newEmployee );
  }
}

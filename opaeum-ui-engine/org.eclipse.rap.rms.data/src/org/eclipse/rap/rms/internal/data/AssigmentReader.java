package org.eclipse.rap.rms.internal.data;

import java.util.Iterator;
import java.util.List;

import org.eclipse.rap.rms.data.*;
import org.eclipse.rap.rms.internal.data.DataModel.Project;
import org.w3c.dom.Element;


class AssigmentReader implements IEntityReader {
  private Element element;
  private Project project;
  
  AssigmentReader( final Element element, final IProject newProject ) {
    this.element = element;
    this.project = ( Project )newProject;
  }
  
  public void load() {
    String employeeId = element.getAttribute( EntityConstants.EMPLOYEE );
    List<IEmployee> employees = DataModelRegistry.getFactory().getEmployees();
    Iterator<IEmployee> iterator = employees.iterator();
    boolean found = false;
    while( !found && iterator.hasNext() ) {
      IEmployee employee = iterator.next();
      if( employee.getId().equals( employeeId ) ) {
        found = true;
        project.newAssignment( employee ,
                               element.getAttribute( EntityConstants.ID ) );        
      }
    }
  }
}

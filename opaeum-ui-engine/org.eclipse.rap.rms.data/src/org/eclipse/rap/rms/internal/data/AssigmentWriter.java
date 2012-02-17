package org.eclipse.rap.rms.internal.data;

import org.eclipse.rap.rms.data.IAssignment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class AssigmentWriter implements IEntityWriter {
  private final IAssignment assignment;
  private final Element assignments;
  
  public AssigmentWriter( final IAssignment assignment,
                          final Element assignments )
  {
    this.assignment = assignment;
    this.assignments = assignments;
  }

  public void save() {
    Document document = assignments.getOwnerDocument();
    Element newAssignment
      = document.createElement( EntityConstants.ASSIGNMENT );
    assignments.appendChild( newAssignment );
    newAssignment.setAttribute( EntityConstants.ID, assignment.getId() );
    newAssignment.setAttribute( EntityConstants.EMPLOYEE,
                                assignment.getEmployee().getId() );
    newAssignment.setAttribute( EntityConstants.PROJECT,
                                assignment.getProject().getId() );
  }
}
package org.eclipse.rap.rms.internal.data;

import java.util.Date;
import java.util.Iterator;

import org.eclipse.rap.rms.data.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class ProjectWriter implements IEntityWriter {
  private final IProject project;
  private final StorageManager storageManager;
  private final Element projects;
  

  public ProjectWriter( final IProject project,
                        final StorageManager storageManager, 
                        final Element projects )
  {
    this.project = project;
    this.storageManager = storageManager;
    this.projects = projects;
    
  }

  public void save() {
    Document document = projects.getOwnerDocument();
    Element newProject = document.createElement( EntityConstants.PROJECT );
    projects.appendChild( newProject );    
    newProject.setAttribute( EntityConstants.ID, project.getId() );
    newProject.setAttribute( EntityConstants.NAME, project.getName() );
    newProject.setAttribute( EntityConstants.DESCRIPTION,
                             project.getDescription() );
    Date sDate = project.getStartDate();
    newProject.setAttribute( EntityConstants.STARTDATE,
                             String.valueOf( sDate.getTime() ));
    Date eDate = project.getEndDate();
    newProject.setAttribute( EntityConstants.ENDDATE,
                             String.valueOf( eDate.getTime() ));
    
    
    
    Element assigments = document.createElement( EntityConstants.ASSIGMENTS );
    newProject.appendChild( assigments );
    Iterator<IAssignment> aIterator = this.project.getAssignments().iterator();
    while( aIterator.hasNext() ) {
      IAssignment assignment = aIterator.next();
      IEntityWriter assignmentAdapter
        = storageManager.getStorageAdapter( assignment, assigments );
      assignmentAdapter.save();
    }
    Element tasks = document.createElement( EntityConstants.TASKS );
    newProject.appendChild( tasks );
    Iterator<ITask> tIterator = this.project.getTasks().iterator();
    while( tIterator.hasNext() ) {
      ITask task = tIterator.next();
      IEntityWriter taskAdapter
        = storageManager.getStorageAdapter( task, tasks );
      taskAdapter.save();
    }
  }
}

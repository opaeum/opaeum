package org.eclipse.rap.rms.internal.data;

import java.util.Date;

import org.eclipse.rap.rms.data.ITask;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class TaskWriter implements IEntityWriter {
  private final ITask task;
  private final Element tasks;
  
  public TaskWriter( final ITask task,
                          final Element tasks )
  {
    this.task = task;
    this.tasks = tasks;
  }

  public void save() {
    Document document = tasks.getOwnerDocument();
    Element newTask
      = document.createElement( EntityConstants.TASK );
    tasks.appendChild( newTask );
    newTask.setAttribute( EntityConstants.NAME, task.getName() );
    newTask.setAttribute( EntityConstants.ID, task.getId() );    
    newTask.setAttribute( EntityConstants.PROJECT,
                          task.getProject().getId() );
    newTask.setAttribute( EntityConstants.DESCRIPTION, task.getDescription() );
    Date sDate = task.getStartDate();
    newTask.setAttribute( EntityConstants.STARTDATE,
                          String.valueOf( sDate.getTime() ) );
    Date eDate = task.getEndDate();
    newTask.setAttribute( EntityConstants.ENDDATE, 
                          String.valueOf( eDate.getTime() ) );
  }
 
}

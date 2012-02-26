package org.eclipse.rap.rms.internal.data;

import java.util.Date;

import org.eclipse.rap.rms.data.IProject;
import org.eclipse.rap.rms.data.ITask;
import org.eclipse.rap.rms.internal.data.DataModel.Project;
import org.w3c.dom.Element;


public class TaskReader implements IEntityReader{
  private Element element;
  private Project project;
  
  TaskReader( final Element element, final IProject newProject ) {
    this.element = element;
    this.project = ( Project )newProject;
  }
  
  public void load() {
    ITask newTask
      = project.newTask( element.getAttribute( EntityConstants.NAME ), 
                         EntityConstants.ID);
    newTask.setDescription( element
                            .getAttribute( EntityConstants.DESCRIPTION ) );
    Date startDate = null;
    Date endDate = null;   
    String sDate = element.getAttribute( EntityConstants.STARTDATE );
    startDate = new Date( Long.parseLong( sDate ) ); 
    String eDate = element.getAttribute( EntityConstants.ENDDATE );
    endDate = new Date (Long.parseLong( eDate ));       
    newTask.setStartDate( startDate );
    newTask.setEndDate( endDate );
  }
}

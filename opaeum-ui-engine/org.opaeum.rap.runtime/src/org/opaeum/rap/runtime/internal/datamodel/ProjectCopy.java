// Created on 04.10.2007
package org.opaeum.rap.runtime.internal.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.rap.rms.data.IAssignment;
import org.eclipse.rap.rms.data.IEmployee;
import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.rap.rms.data.IPrincipal;
import org.eclipse.rap.rms.data.IProject;
import org.eclipse.rap.rms.data.ITask;

final class ProjectCopy
  extends WorkingCopy
  implements IProject
{
  static final String DESCRIPTION = "description"; //$NON-NLS-1$
  static final String START_DATE = "startDate"; //$NON-NLS-1$
  static final String END_DATE = "endDate"; //$NON-NLS-1$
  
  private String name;
  private String description;
  private Date startDate;
  private Date endDate;
  private List<TaskCopy> tasks;
  
  
  ProjectCopy( final IProject project ) {
    super( project );
  }

  void doLoad( final IEntity entity ) {
    IProject project = ( IProject )entity; 
    this.name = project.getName();
    setDescription( project.getDescription() );
    setStartDate( project.getStartDate() );
    setEndDate( project.getEndDate() );
    
    tasks = new ArrayList<TaskCopy>();
    Iterator<ITask> iterator = project.getTasks().iterator();
    while( iterator.hasNext() ) {
      ITask task = iterator.next();
      TaskCopy taskCopy = new TaskCopy( task );
      tasks.add( taskCopy );
    }
  }

  void doSave( final IEntity entity ) {
    IProject project = ( IProject )entity; 
    project.setDescription( getDescription() );
    project.setStartDate( getStartDate() );
    project.setEndDate( getEndDate() );
    Iterator<TaskCopy> iterator = tasks.iterator();
    while( iterator.hasNext() ) {
      TaskCopy next = iterator.next();
      next.save();
    }
  }
  
  @Override
  public boolean isDirty() {
    boolean result = super.isDirty();
    Iterator<TaskCopy> iterator = tasks.iterator();
    while( !result && iterator.hasNext() ) {
      result = result || iterator.next().isDirty();
    }
    return result;
  }
  
  @Override
  void setDirtyNotificator( final Runnable dirtyNotificator ) {
    super.setDirtyNotificator( dirtyNotificator );
    Iterator<TaskCopy> iterator = tasks.iterator();
    while( iterator.hasNext() ) {      
      iterator.next().setDirtyNotificator( getDirtyNotificator() );
    }
  }

  public String getDescription() {
    return description;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public String getName() {
    return name;
  }

  public List<ITask> getTasks() {
    List<ITask> result = new ArrayList<ITask>();
    result.addAll( tasks );
    return Collections.unmodifiableList( result );
  }

  public ITask newTask( final String name ) {
    TaskCopy result = new TaskCopy( name, ( IProject )entity );
    result.setDirtyNotificator( getDirtyNotificator() );
    tasks.add( result );
    setDirty( true );
    return result;
  }

  public void setDescription( final String description ) {
    this.description
      = ( String )notify( DESCRIPTION, description, this.description );
  }

  public void setStartDate( final Date startDate ) {
    this.startDate = ( Date )notify( START_DATE, startDate, this.startDate );
  }

  public void setEndDate( final Date endDate ) {
    this.endDate = ( Date )notify( END_DATE, endDate, this.endDate);
  }

  public List<IAssignment> getAssignments() {
    return null;
  }

  public IPrincipal getPrincipal() {
    return null;
  }

  public IAssignment newAssignment( final IEmployee employee ) {
    return null;
  }

  public String getId() {
    // TODO Auto-generated method stub
    return null;
  }
}
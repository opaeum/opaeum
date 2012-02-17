// Created on 05.10.2007
package org.opaeum.rap.runtime.internal.datamodel;

import java.util.Date;

import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.rap.rms.data.IProject;
import org.eclipse.rap.rms.data.ITask;


final class TaskCopy extends WorkingCopy implements ITask {
  static final String DESCRIPTION = "description"; //$NON-NLS-1$
  static final String START_DATE = "startDate"; //$NON-NLS-1$
  static final String END_DATE = "endDate"; //$NON-NLS-1$
  
  private String name;
  private String description;
  private Date startDate;
  private Date endDate;
  private final IProject project;

  TaskCopy( final IEntity entity ) {
    super( entity );
    this.project = ( ( ITask )entity ).getProject();
  }

  public TaskCopy( final String name, final IProject project ) {
    super( null );
    this.project = project;
    this.name = name;
  }

  @Override
  void doLoad( final IEntity entity ) {
    ITask task = ( ITask )entity; 
    this.name = task.getName();
    setDescription( task.getDescription() );
    setStartDate( task.getStartDate() );
    setEndDate( task.getEndDate() );

  }

  @Override
  void doSave( final IEntity entity ) {
    ITask task = ( ITask )entity;
    if( task == null ) {
      task = project.newTask( name );
    }
    task.setDescription( getDescription() );
    task.setStartDate( getStartDate() );
    task.setEndDate( getEndDate() );
  }

  public String getDescription() {
    return description;
  }

  public Date getEndDate() {
    return endDate;
  }

  public String getName() {
    return name;
  }

  public IProject getProject() {
    return null;
  }

  public Date getStartDate() {
    return startDate;
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

  public String getId() {
    // TODO Auto-generated method stub
    return null;
  }
}
// Created on 08.09.2007
package org.eclipse.rap.rms.internal.data;

import java.util.*;

import org.eclipse.rap.rms.data.*;


public class DataModel extends Adaptable implements IDataModel {

  private final Object lock = new Object();
  private final List<IEmployee> employees = new ArrayList<IEmployee>();
  private final List<IPrincipal> principals = new ArrayList<IPrincipal>();
  private final Set<ModelListener> listeners = new HashSet<ModelListener>();
  private static int idCounter = 0; 

  /////////////////////////
  // entity implementations
  
  private final class Employee extends Adaptable implements IEmployee {
    private final String firstName;
    private final String lastName;
    private final String id;
    public final List<IAssignment> assignments = new ArrayList<IAssignment>();

    private Employee( final String firstName, 
                      final String lastName,
                      final String id )
    {
      assert( firstName != null );
      assert( lastName != null );
      
      this.firstName = firstName;
      this.lastName = lastName;
      if( id != null ) {
        this.id = id;
      } else {
        this.id = newId();
      }
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public List<IAssignment> getAssignments() {
      synchronized( lock ) {
        return Collections.unmodifiableList( assignments );
      }
    }

    public String getId() {      
      return id;
    }
  }

  final class Principal extends Adaptable implements IPrincipal {
    private final String name;
    private final String id; 
    private final List<IProject> projects = new ArrayList<IProject>();
    private String city;
    private String country;
    private String email;
    private String faxNumber;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String phoneNumber;
    private String postCode;
    private String street;
    
    private Principal( final String name, final String id ) {
      assert( name != null );      
      this.name = name;     
      if( id != null ) {
        this.id = id;
      } else {
        this.id = newId();
      }
    }
    
    public String getName() {
      return name;
    }
    
    public List<IProject> getProjects() {
      synchronized( lock ) {
        return Collections.unmodifiableList( projects );          
      }
    }

    public IProject newProject( final String name ) {      
      return newProject( name, null );
    }
    
   IProject newProject( final String name, final String id ) {
      IProject result = new Project( name, this, id);
      synchronized( lock ) {
        projects.add( result );
        DataModel.this.notifyOnCreated( result );
      }
      return result;
    }
    public String getId() {     
      return id;
    }

    public String getCity() {
      return city;
    }

    public String getCountry() {
      return country;
    }

    public String getEMail() {
      return email;
    }

    public String getFaxNumber() {
      return faxNumber;
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public String getMobileNumber() {
      return mobileNumber;
    }

    public String getPhoneNumber() {
      return phoneNumber;
    }

    public String getPostCode() {
      return postCode;
    }

    public String getStreet() {
      return street;
    }

    public void setCity( final String city ) {
      this.city = ( String )notifyOnChange( city, this.city, this );
    }

    public void setCountry( final String country ) {
      this.country = ( String )notifyOnChange( country, this.country, this );
    }

    public void setEMail( final String email ) {
      this.email = ( String )notifyOnChange( email, this.email, this );
    }

    public void setFaxNumber( final String faxNumber ) {
      this.faxNumber
        = ( String )notifyOnChange( faxNumber, this.faxNumber, this );
    }

    public void setFirstName( final String firstName ) {
      this.firstName
        = ( String )notifyOnChange( firstName, this.firstName, this );
    }

    public void setLastName( final String lastName ) {
      this.lastName
        = ( String )notifyOnChange( lastName, this.lastName, this );
    }

    public void setMobileNumber( final String mobileNumber ) {
      this.mobileNumber
        = ( String )notifyOnChange( mobileNumber, this.mobileNumber, this );
    }

    public void setPhoneNumber( final String phoneNumber ) {
      this.phoneNumber
        = ( String )notifyOnChange( phoneNumber, this.phoneNumber, this );
    }

    public void setPostCode( final String postCode ) {
      this.postCode
        = ( String )notifyOnChange( postCode, this.postCode, this );
    }

    public void setStreet( final String street ) {
      this.street
        = ( String )notifyOnChange( street, this.street, this );
    }
  }
  
  final class Project extends Adaptable implements IProject {
    private final String name;
    private final String id;
    private final IPrincipal principal;
    private final List<IAssignment> assignments = new ArrayList<IAssignment>();
    private final List<ITask> tasks = new ArrayList<ITask>();
    private String description;
    private Date endDate;
    private Date startDate;
    
    private Project( final String name,
                     final IPrincipal principal,
                     final String id )
    {
      this.name = name;     
      this.principal = principal;
      if( id != null ) {
        this.id = id;
      } else {
        this.id = newId();
      }
    }
    
    public String getName() {
      return name;
    }
    
    public IPrincipal getPrincipal() {
      return principal;
    }

    public IAssignment newAssignment( final IEmployee employee ) {
      return newAssignment( employee, null );
      
    }
    IAssignment newAssignment( final IEmployee employee,
                               final String id)
    {
      IAssignment result = new Assignment( this, employee, id );
      synchronized( lock ) {
        assignments.add( result );
        ( ( Employee )employee ).assignments .add( result );
        DataModel.this.notifyOnCreated( result );
      }
      return result;
    }
    
    public List<IAssignment> getAssignments() {
      synchronized( lock ) {
        return Collections.unmodifiableList( assignments );
      }
    }

    public String getId() {      
      return id;
    }

    public List<ITask> getTasks() {
      synchronized( lock ) {
        return Collections.unmodifiableList( tasks );          
      }
    }

    public ITask newTask( final String name ) {
      return newTask( name, null );
    }
    
    ITask newTask( final String name, final String id ) {
      ITask result = new Task( name, this, id );
      synchronized( lock ) {
        tasks.add( result );
        DataModel.this.notifyOnCreated( result );
      }
      return result;
    }

    public String getDescription() {
      return description;
    }

    public Date getEndDate() {
      return endDate;
    }

    public Date getStartDate() {
      return startDate;
    }

    public void setDescription( final String description ) {
      this.description
        = ( String )notifyOnChange( description, this.description, this );
    }

    public void setEndDate( final Date endDate ) {
      this.endDate = ( Date )notifyOnChange( endDate, this.endDate, this );
    }

    public void setStartDate( final Date startDate ) {
      this.startDate
        = ( Date )notifyOnChange( startDate, this.startDate, this );
    }
  }
  
  final class Assignment extends Adaptable implements IAssignment {
    private final String id;
    private final IEmployee employee;
    private final IProject project;

    private Assignment( final IProject project,
                        final IEmployee employee,
                        final String id )
    {      
      this.employee = employee;
      this.project = project;
      if( id != null ) {
        this.id = id;
      } else {
        this.id = newId();
      }
    }

    public IProject getProject() {
      return project;
    }

    public IEmployee getEmployee() {
      return employee;
    }

    public String getId() {      
      return this.id;
    }
  }
  
  private final class Task extends Adaptable implements ITask {
    private final String id;
    private final String name;
    private final IProject project;
    private String description;
    private Date endDate;
    private Date startDate;
    
    private Task( final String name,
                  final IProject project,
                  final String id )
    {
      this.name = name;
      this.project = project;
      if( id != null ) {
        this.id = id;
      } else {
        this.id = newId();
      }
    }

    public String getName() {
      return name;
    }
    
    public IProject getProject() {
      return project;
    }

    public String getDescription() {
      return description;
    }

    public Date getEndDate() {
      return endDate;
    }

    public Date getStartDate() {
      return startDate;
    }

    public void setDescription( final String description ) {
      this.description
        = ( String )notifyOnChange( description, this.description, this );
    }

    public void setEndDate( final Date endDate ) {
      this.endDate = ( Date )notifyOnChange( endDate, this.endDate, this );
    }

    public void setStartDate( final Date startDate ) {
      this.startDate
        = ( Date )notifyOnChange( startDate, this.startDate, this );
    }

    public String getId() {      
      return this.id;
    }
  }

  
  ///////////////////////////////////////
  // interface IDataModel implementations

  public String getId() {    
    return null;
  }
  
  public IPrincipal newPrincipal( final String name ) {
    return newPrincipal( name, null );
  }
  

  IPrincipal newPrincipal( final String name, final String id ) {
    IPrincipal result = new Principal( name, id );
    synchronized( lock ) {
      principals.add( result );
      notifyOnCreated( result );
    }
    return result;
  }

  
  public List<IPrincipal> getPrincipals() {
    synchronized( lock ) {
      return Collections.unmodifiableList( principals );
    }
  }

  public IEmployee newEmployee( final String lastName, 
                                final String firstName )
  {
    return newEmployee( lastName, firstName, null );
  }
  

  IEmployee newEmployee( final String lastName, 
                         final String firstName, 
                         final String id )
  {
    IEmployee result = new Employee( firstName, lastName, id );
    synchronized( lock ) {
      employees.add( result );
      notifyOnCreated( result );
    }
    return result;
  }


  public List<IEmployee> getEmployees() {
    synchronized( lock ) {
      return Collections.unmodifiableList( employees );
    }
  }


  void clear() {
    synchronized( lock ) {
      employees.clear();
      principals.clear();
    }
  }
  
  public void addModelListener( final ModelListener modelListener ) {
    synchronized( lock ) {
      listeners.add( modelListener );
    }
  }

  public void removeModelListener( final ModelListener modelListener ) {
    synchronized( lock ) {
      listeners.remove( modelListener );
    }
  }
  
  
  //////////////////
  // helping methods

  private Object notifyOnChange( final Object newValue, 
                                 final Object oldValue,
                                 final IEntity entity )
  {
    boolean mustNotify = false;
    if( oldValue == null ) {
      if( newValue != null ) {
        mustNotify = true;
      }
    } else if( newValue == null ) {
      if( oldValue != null ) {
        mustNotify = true;
      }
    } else {
      mustNotify = !newValue.equals( oldValue );
    }
    if( mustNotify ) {
      ModelListener[] modelListeners = new ModelListener[ listeners.size() ];
      listeners.toArray( modelListeners );
      ModelEvent evt = new ModelEvent( entity );
      for( int i = 0; i < modelListeners.length; i++ ) {
        try {
          modelListeners[ i ].entityChanged( evt );
        } catch( final RuntimeException re ) {
          ThrowableManager.handleThrowable( re );
        }
      }
    }
    return newValue;
  }

  void notifyOnCreated( final IEntity entity ) {
    ModelListener[] modelListeners = new ModelListener[ listeners.size() ];
    listeners.toArray( modelListeners );
    ModelEvent evt = new ModelEvent( entity );
    for( int i = 0; i < modelListeners.length; i++ ) {
      try {
        modelListeners[ i ].entityCreated( evt );
      } catch( final RuntimeException re ) {
        ThrowableManager.handleThrowable( re );
      }
    }
  }

  public IStorageManager getStorageManager() {
    return new StorageManager( this );
  }
  
  private static synchronized String newId() {
    int result = idCounter;
    idCounter++;
    return String.valueOf( result );
  }
}

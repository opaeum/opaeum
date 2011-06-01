package org.nakeduml.workinghours;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.MetaIdentifiable;


//JDK5@CompositionFromOwningObject(attribute="holiday")
abstract public class HolidayGEN implements AbstractEntity,MetaIdentifiable{
	private Set<Object> outgoingEvents;
	private String f_name = "";
	private Date f_date = null;
	private WorkingHoursConfiguration f_workingHoursConfiguration = null;
	static private boolean usesAllInstances = false;
	static private List allInstances = new ArrayList();
	private Long id;
	private Date deletedOn;
	private int objectVersion;
	private String uid;
	/**
	 * This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject
	 */
	public HolidayGEN(WorkingHoursConfiguration owningObject){
		init(owningObject);
		addToOwningObject();
	}
	/**
	 * Default constructor for Holiday
	 */
	public HolidayGEN(){
	}
	/**
	 * Constructor for HolidayGEN
	 * 
	 * @param name
	 */
	public HolidayGEN(String name){
		super();
		this.setName(name);
		if(usesAllInstances){
			allInstances.add(this);
		}
	}
	/**
	 * Implements the getter for feature '+ name : String'
	 */
	public String getName(){
		return this.f_name;
	}
	/**
	 * Implements the setter for feature '+ name : String'
	 * 
	 * @param element
	 */
	public void setName(String element){
		if(this.f_name != element){
			this.f_name = element;
		}
	}
	/**
	 * Implements the getter for feature '+ date : Date'
	 */
	public Date getDate(){
		return this.f_date;
	}
	/**
	 * Implements the setter for feature '+ date : Date'
	 * 
	 * @param element
	 */
	public void setDate(Date element){
		if(this.f_date != element){
			this.f_date = element;
		}
	}
	/**
	 * Implements the getter for feature '+ now : Date'
	 */
	public Date getNow(){
		return new Date();
	}
	/**
	 * Implements the getter for feature '+ today : Date'
	 */
	public Date getToday(){
		return new Date();
	}
	/**
	 * Implements the setter of association end workingHoursConfiguration
	 * 
	 * @param element
	 */
	public void setWorkingHoursConfiguration(WorkingHoursConfiguration element){
		if(this.f_workingHoursConfiguration != element){
			if(this.f_workingHoursConfiguration != null){
				this.f_workingHoursConfiguration.z_internalRemoveFromHoliday((Holiday) this);
			}
			this.f_workingHoursConfiguration = element;
			if(element != null){
				element.z_internalAddToHoliday((Holiday) this);
			}
		}
	}
	/**
	 * Implements the getter for workingHoursConfiguration
	 */
	public WorkingHoursConfiguration getWorkingHoursConfiguration(){
		return this.f_workingHoursConfiguration;
	}
	/**
	 * Should NOT be used by clients! Implements the correct setting of the link for + workingHoursConfiguration : WorkingHoursConfiguration
	 * when a single element is added to it.
	 * 
	 * @param element
	 */
	public void z_internalAddToWorkingHoursConfiguration(WorkingHoursConfiguration element){
		this.f_workingHoursConfiguration = element;
	}
	/**
	 * Should NOT be used by clients! Implements the correct setting of the link for + workingHoursConfiguration : WorkingHoursConfiguration
	 * when a single element is removed to it.
	 * 
	 * @param element
	 */
	public void z_internalRemoveFromWorkingHoursConfiguration(WorkingHoursConfiguration element){
		this.f_workingHoursConfiguration = null;
	}
	/**
	 * Implements ->forAll( e : Holiday | (e <> self) implies e.date <> self.date )
	 */
	private boolean forAll1(){
		Iterator it = this.getWorkingHoursConfiguration().getHoliday().iterator();
		while(it.hasNext()){
			Holiday e = (Holiday) it.next();
			if(!(!e.equals(this) ? !e.getDate().equals(this.getDate()) : true)){
				return false;
			}
		}
		return true;
	}
	/**
	 * Implements self.workingHoursConfiguration.holiday->forAll( e : Holiday | (e <> self) implies e.date <> self.date )
	 */
	public void invariant_DuplicateFoundAHolidayAlreadExistsWithTheSameDate() throws InvariantException{
		boolean result = false;
		try{
			result = forAll1();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(!result){
			String message = "invariant DuplicateFoundAHolidayAlreadExistsWithTheSameDate ";
			message = message + "is broken in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "'";
			throw new InvariantException(this, message);
		}
	}
	/**
	 * Checks all invariants of this object and returns a list of messages about broken invariants
	 */
	public List checkAllInvariants(){
		List /* InvariantError */result = new ArrayList /* InvariantError */();
		try{
			invariant_DuplicateFoundAHolidayAlreadExistsWithTheSameDate();
		}catch(InvariantException e){
			result.add(new InvariantError(e.getInstance(), e.getMessage()));
		}
		return result;
	}
	/**
	 * Implements a check on the multiplicities of all attributes and association ends
	 */
	public List checkMultiplicities(){
		List /* InvariantError */result = new ArrayList /* InvariantError */();
		if(getName() == null){
			String message = "Mandatory feature 'name' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(this, message));
		}
		if(getDate() == null){
			String message = "Mandatory feature 'date' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(this, message));
		}
		if(getNow() == null){
			String message = "Mandatory feature 'now' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(this, message));
		}
		if(getToday() == null){
			String message = "Mandatory feature 'today' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(this, message));
		}
		if(getWorkingHoursConfiguration() == null){
			String message = "Mandatory feature 'workingHoursConfiguration' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(this, message));
		}
		return result;
	}
	/**
	 * Returns the default identifier for Holiday
	 */
	public String getIdString(){
		String result = "";
		if(this.getName() != null){
			result = result + this.getName();
		}
		return result;
	}
	/**
	 * Implements the OCL allInstances operation
	 */
	static public List allInstances(){
		if(!usesAllInstances){
			throw new RuntimeException(
					"allInstances is not implemented for this class. Set usesAllInstances to true, if you want allInstances() implemented.");
		}
		return allInstances;
	}
	public String getMetaId(){
		return "_9_5_c530259_1132751800812_758585_22";
	}
	public void setClassId(int dummy){
	}
	public int getClassId(){
		return 23;
	}
	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return this.id;
	}
	public void setDeletedOn(Date deletedOn){
		this.deletedOn = deletedOn;
	}
	public Date getDeletedOn(){
		return this.deletedOn;
	}
	public CompositionNode getOwningObject(){
		return getWorkingHoursConfiguration();
	}
	public void markDeleted(){
		setDeletedOn(new Date(System.currentTimeMillis()));
		if(getWorkingHoursConfiguration() != null){
			getWorkingHoursConfiguration().z_internalRemoveFromHoliday((Holiday) this);
		}
	}
	public void removeFromOwningObject(){
		this.markDeleted();
	}
	public void addToOwningObject(){
		getWorkingHoursConfiguration().z_internalAddToHoliday((Holiday) this);
	}
	public void init(CompositionNode owner){
		z_internalAddToWorkingHoursConfiguration((WorkingHoursConfiguration) owner);
		if(usesAllInstances){
			allInstances.add(this);
		}
		createComponents();
	}
	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}else{
			if(!(o instanceof Holiday)){
				return false;
			}else{
				AbstractEntity other = (AbstractEntity) o;
				if(getId() == null){
					return false;
				}else{
					return getId().equals(other.getId());
				}
			}
		}
	}
	public IProcessObject getAbstractStateMachine(){
		return null;
	}
	public boolean processSignal(Object signal){
		return false;
	}
	public void setObjectVersion(int objectVersion){
		this.objectVersion = objectVersion;
	}
	public int getObjectVersion(){
		return this.objectVersion;
	}
	public void startStateMachine(){
	}
	public void createComponents(){
	}
	public Holiday makeCopy(){
		Holiday result = new Holiday();
		copyState((Holiday) this, result);
		return result;
	}
	public void copyState(Holiday from,Holiday to){
		to.setWorkingHoursConfiguration(from.getWorkingHoursConfiguration());
		to.setName(from.getName());
		to.setDate(from.getDate());
	}
	public void shallowCopyState(Holiday from,Holiday to){
		to.setWorkingHoursConfiguration(from.getWorkingHoursConfiguration());
		to.setName(from.getName());
		to.setDate(from.getDate());
	}
	public boolean isGroupOwnershipValid(){
		return true;
	}
	public boolean isUserOwnershipValid(){
		return false;
	}
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}

}
package org.nakeduml.workinghours;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;

//JDK5@CompositionFromOwningObject(attribute="recurringHoliday")
abstract public class RecurringHolidayGEN implements CompositionNode, IPersistentObject{
	private int f_month = 0;
	private int f_dayOfMonth = 0;
	private String f_name = "";
	private WorkingHoursConfiguration f_workingHoursConfiguration = null;
	static private boolean usesAllInstances = false;
	static private List allInstances = new ArrayList();
	private Long id;
	private Date deletedOn;
	private int objectVersion;
	private String uid;
	/**
	 * Constructor for RecurringHolidayGEN
	 * 
	 * @param month
	 * @param dayOfMonth
	 * @param name
	 */
	public RecurringHolidayGEN(int month, int dayOfMonth, String name) {
		super();
		this.setMonth(month);
		this.setDayOfMonth(dayOfMonth);
		this.setName(name);
		if (usesAllInstances) {
			allInstances.add(this);
		}
	}
	/**
	 * Default constructor for RecurringHoliday
	 */
	public RecurringHolidayGEN() {
	}
	/**
	 * This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject
	 */
	public RecurringHolidayGEN(WorkingHoursConfiguration owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	/**
	 * Implements the getter for feature '+ month : Integer'
	 */
	public int getMonth() {
		return this.f_month;
	}
	/**
	 * Implements the setter for feature '+ month : Integer'
	 * 
	 * @param originalElement
	 */
	public void setMonth(int element) {
		if (this.f_month != element) {
			this.f_month = element;
		}
	}
	/**
	 * Implements the getter for feature '+ dayOfMonth : Integer'
	 */
	public int getDayOfMonth() {
		return this.f_dayOfMonth;
	}
	/**
	 * Implements the setter for feature '+ dayOfMonth : Integer'
	 * 
	 * @param originalElement
	 */
	public void setDayOfMonth(int element) {
		if (this.f_dayOfMonth != element) {
			this.f_dayOfMonth = element;
		}
	}
	/**
	 * Implements the getter for feature '+ name : String'
	 */
	public String getName() {
		return this.f_name;
	}
	/**
	 * Implements the setter for feature '+ name : String'
	 * 
	 * @param originalElement
	 */
	public void setName(String element) {
		if (this.f_name != element) {
			this.f_name = element;
		}
	}
	/**
	 * Implements the getter for feature '+ now : Date'
	 */
	public Date getNow() {
		return new Date();
	}
	/**
	 * Implements the getter for feature '+ today : Date'
	 */
	public Date getToday() {
		return new Date();
	}
	/**
	 * Implements the setter of association end workingHoursConfiguration
	 * 
	 * @param originalElement
	 */
	public void setWorkingHoursConfiguration(WorkingHoursConfiguration element) {
		if (this.f_workingHoursConfiguration != element) {
			if (this.f_workingHoursConfiguration != null) {
				this.f_workingHoursConfiguration.z_internalRemoveFromRecurringHoliday((RecurringHoliday) this);
			}
			this.f_workingHoursConfiguration = element;
			if (element != null) {
				element.z_internalAddToRecurringHoliday((RecurringHoliday) this);
			}
		}
	}
	/**
	 * Implements the getter for workingHoursConfiguration
	 */
	public WorkingHoursConfiguration getWorkingHoursConfiguration() {
		return this.f_workingHoursConfiguration;
	}
	/**
	 * Should NOT be used by clients! Implements the correct setting of the link for + workingHoursConfiguration :
	 * WorkingHoursConfiguration when a single originalElement is added to it.
	 * 
	 * @param originalElement
	 */
	public void z_internalAddToWorkingHoursConfiguration(WorkingHoursConfiguration element) {
		this.f_workingHoursConfiguration = element;
	}
	/**
	 * Should NOT be used by clients! Implements the correct setting of the link for + workingHoursConfiguration :
	 * WorkingHoursConfiguration when a single originalElement is removed to it.
	 * 
	 * @param originalElement
	 */
	public void z_internalRemoveFromWorkingHoursConfiguration(WorkingHoursConfiguration element) {
		this.f_workingHoursConfiguration = null;
	}
	/**
	 * Checks all invariants of this object and returns a list of messages about broken invariants
	 */
	public List checkAllInvariants() {
		List /* InvariantError */result = new ArrayList /* InvariantError */();
		return result;
	}
	/**
	 * Implements a check on the multiplicities of all attributes and association ends
	 */
	public List checkMultiplicities() {
		List /* InvariantError */result = new ArrayList /* InvariantError */();
		if (getName() == null) {
			String message = "Mandatory feature 'name' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(this, message));
		}
		if (getNow() == null) {
			String message = "Mandatory feature 'now' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(this, message));
		}
		if (getToday() == null) {
			String message = "Mandatory feature 'today' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(this, message));
		}
		if (getWorkingHoursConfiguration() == null) {
			String message = "Mandatory feature 'workingHoursConfiguration' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
			result.add(new InvariantError(this, message));
		}
		return result;
	}
	/**
	 * Returns the default identifier for RecurringHoliday
	 */
	public String getIdString() {
		String result = "";
		if (this.getName() != null) {
			result = result + this.getName();
		}
		return result;
	}
	/**
	 * Implements the OCL allInstances operation
	 */
	static public List allInstances() {
		if (!usesAllInstances) {
			throw new RuntimeException(
					"allInstances is not implemented for this class. Set usesAllInstances to true, if you want allInstances() implemented.");
		}
		return allInstances;
	}
	public String getMetaId() {
		return "_9_5_c530259_1132754136593_473172_795";
	}
	public void setClassId(int dummy) {
	}
	public int getClassId() {
		return 25;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	public CompositionNode getOwningObject() {
		return getWorkingHoursConfiguration();
	}
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if (getWorkingHoursConfiguration() != null) {
			getWorkingHoursConfiguration().z_internalRemoveFromRecurringHoliday((RecurringHoliday) this);
		}
	}
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	public void addToOwningObject() {
		getWorkingHoursConfiguration().z_internalAddToRecurringHoliday((RecurringHoliday) this);
	}
	public void init(CompositionNode owner) {
		z_internalAddToWorkingHoursConfiguration((WorkingHoursConfiguration) owner);
		if (usesAllInstances) {
			allInstances.add(this);
		}
		createComponents();
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else {
			if (!(o instanceof RecurringHoliday)) {
				return false;
			} else {
				IPersistentObject other = (IPersistentObject) o;
				if (getId() == null) {
					return false;
				} else {
					return getId().equals(other.getId());
				}
			}
		}
	}
	public IProcessObject getAbstractStateMachine() {
		return null;
	}
	public boolean processSignal(Object signal) {
		return false;
	}
	public void setObjectVersion(int objectVersion) {
		this.objectVersion = objectVersion;
	}
	public int getObjectVersion() {
		return this.objectVersion;
	}
	public void startStateMachine() {
	}
	public void createComponents() {
	}
	public RecurringHoliday makeCopy() {
		RecurringHoliday result = new RecurringHoliday();
		copyState((RecurringHoliday) this, result);
		return result;
	}
	public void copyState(RecurringHoliday from, RecurringHoliday to) {
		to.setWorkingHoursConfiguration(from.getWorkingHoursConfiguration());
		to.setDayOfMonth(from.getDayOfMonth());
		to.setName(from.getName());
		to.setMonth(from.getMonth());
	}
	public void shallowCopyState(RecurringHoliday from, RecurringHoliday to) {
		to.setWorkingHoursConfiguration(from.getWorkingHoursConfiguration());
		to.setDayOfMonth(from.getDayOfMonth());
		to.setName(from.getName());
		to.setMonth(from.getMonth());
	}
	public boolean isGroupOwnershipValid() {
		return true;
	}
	public boolean isUserOwnershipValid() {
		return false;
	}
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
}
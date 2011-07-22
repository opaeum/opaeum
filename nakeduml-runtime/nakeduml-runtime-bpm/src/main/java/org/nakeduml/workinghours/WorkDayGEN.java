package org.nakeduml.workinghours;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;

//JDK5@CompositionFromOwningObject(attribute="workDay")
abstract public class WorkDayGEN implements CompositionNode, IPersistentObject{
	private String uid = null;
	private WorkDayType f_type = WorkDayType.lookup(0);
	private int f_endMinutes = 0;
	private int f_endHours = 0;
	private int f_startHours = 0;
	private int f_startMinutes = 0;
	private WorkingHoursConfiguration f_workingHoursConfiguration = null;
	static private boolean usesAllInstances = false;
	static private List allInstances = new ArrayList();
	private Long id;
	private Date deletedOn;
	private int objectVersion;
	/**
	 * Default constructor for WorkDay
	 */
	public WorkDayGEN() {
	}
	/**
	 * This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject
	 */
	public WorkDayGEN(WorkingHoursConfiguration owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	/**
	 * Constructor for WorkDayGEN
	 * 
	 * @param endMinutes
	 * @param endHours
	 * @param startHours
	 * @param startMinutes
	 */
	public WorkDayGEN(int endMinutes, int endHours, int startHours, int startMinutes) {
		super();
		this.setEndMinutes(endMinutes);
		this.setEndHours(endHours);
		this.setStartHours(startHours);
		this.setStartMinutes(startMinutes);
		this.setEndMinutes(30);
		this.setEndHours(16);
		this.setStartHours(7);
		this.setStartMinutes(30);
		if (usesAllInstances) {
			allInstances.add(this);
		}
	}
	/**
	 * Implements the getter for feature '+ type : WorkDayType'
	 */
	public WorkDayType getType() {
		return this.f_type;
	}
	/**
	 * Implements the setter for feature '+ type : WorkDayType'
	 * 
	 * @param element
	 */
	public void setType(WorkDayType element) {
		if (this.f_type != element) {
			this.f_type = element;
		}
	}
	/**
	 * Implements the getter for feature '+ endMinutes : Integer'
	 */
	public int getEndMinutes() {
		return this.f_endMinutes;
	}
	/**
	 * Implements the setter for feature '+ endMinutes : Integer'
	 * 
	 * @param element
	 */
	public void setEndMinutes(int element) {
		if (this.f_endMinutes != element) {
			this.f_endMinutes = element;
		}
	}
	/**
	 * Implements the getter for feature '+ endHours : Integer'
	 */
	public int getEndHours() {
		return this.f_endHours;
	}
	/**
	 * Implements the setter for feature '+ endHours : Integer'
	 * 
	 * @param element
	 */
	public void setEndHours(int element) {
		if (this.f_endHours != element) {
			this.f_endHours = element;
		}
	}
	/**
	 * Implements the getter for feature '+ startHours : Integer'
	 */
	public int getStartHours() {
		return this.f_startHours;
	}
	/**
	 * Implements the setter for feature '+ startHours : Integer'
	 * 
	 * @param element
	 */
	public void setStartHours(int element) {
		if (this.f_startHours != element) {
			this.f_startHours = element;
		}
	}
	/**
	 * Implements the getter for feature '+ startMinutes : Integer'
	 */
	public int getStartMinutes() {
		return this.f_startMinutes;
	}
	/**
	 * Implements the setter for feature '+ startMinutes : Integer'
	 * 
	 * @param element
	 */
	public void setStartMinutes(int element) {
		if (this.f_startMinutes != element) {
			this.f_startMinutes = element;
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
	 * @param element
	 */
	public void setWorkingHoursConfiguration(WorkingHoursConfiguration element) {
		if (this.f_workingHoursConfiguration != element) {
			if (this.f_workingHoursConfiguration != null) {
				this.f_workingHoursConfiguration.z_internalRemoveFromWorkDay((WorkDay) this);
			}
			this.f_workingHoursConfiguration = element;
			if (element != null) {
				element.z_internalAddToWorkDay((WorkDay) this);
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
	 * Should NOT be used by clients! Implements the correct setting of the link
	 * for + workingHoursConfiguration : WorkingHoursConfiguration when a single
	 * element is added to it.
	 * 
	 * @param element
	 */
	public void z_internalAddToWorkingHoursConfiguration(WorkingHoursConfiguration element) {
		this.f_workingHoursConfiguration = element;
	}
	/**
	 * Should NOT be used by clients! Implements the correct setting of the link
	 * for + workingHoursConfiguration : WorkingHoursConfiguration when a single
	 * element is removed to it.
	 * 
	 * @param element
	 */
	public void z_internalRemoveFromWorkingHoursConfiguration(WorkingHoursConfiguration element) {
		this.f_workingHoursConfiguration = null;
	}
	/**
	 * Implements ->forAll( e : WorkDay | (e <> self) implies e.type <>
	 * self.type )
	 */
	private boolean forAll1() {
		Iterator it = this.getWorkingHoursConfiguration().getWorkDay().iterator();
		while (it.hasNext()) {
			WorkDay e = (WorkDay) it.next();
			if (!(!e.equals(this) ? (!e.getType().equals(this.getType())) : true)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Implements self.workingHoursConfiguration.workDay->forAll( e : WorkDay |
	 * (e <> self) implies e.type <> self.type )
	 */
	public void invariant_DuplicateFoundAWorkDayAlreadExistsWithTheSameType() throws InvariantException {
		boolean result = false;
		try {
			result = forAll1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!result) {
			String message = "invariant DuplicateFoundAWorkDayAlreadExistsWithTheSameType ";
			message = message + "is broken in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "'";
			throw new InvariantException(this, message);
		}
	}
	/**
	 * Checks all invariants of this object and returns a list of messages about
	 * broken invariants
	 */
	public List checkAllInvariants() {
		List /* InvariantError */result = new ArrayList /* InvariantError */();
		try {
			invariant_DuplicateFoundAWorkDayAlreadExistsWithTheSameType();
		} catch (InvariantException e) {
			result.add(new InvariantError(e.getInstance(), e.getMessage()));
		}
		return result;
	}
	/**
	 * Implements a check on the multiplicities of all attributes and
	 * association ends
	 */
	public List checkMultiplicities() {
		List /* InvariantError */result = new ArrayList /* InvariantError */();
		if (getType() == null) {
			String message = "Mandatory feature 'type' in object '";
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
	 * Returns the default identifier for WorkDay
	 */
	public String getIdString() {
		String result = "";
		result = result + this.getEndMinutes();
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
		return "_9_5_c530259_1132751800828_623489_43";
	}
	public void setClassId(int dummy) {
	}
	public int getClassId() {
		return 24;
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
	public String getName() {
		return "WorkDay" + getId();
	}
	public CompositionNode getOwningObject() {
		return getWorkingHoursConfiguration();
	}
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if (getWorkingHoursConfiguration() != null) {
			getWorkingHoursConfiguration().z_internalRemoveFromWorkDay((WorkDay) this);
		}
	}
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	public void addToOwningObject() {
		getWorkingHoursConfiguration().z_internalAddToWorkDay((WorkDay) this);
	}
	public void init(CompositionNode owner) {
		z_internalAddToWorkingHoursConfiguration((WorkingHoursConfiguration) owner);
		this.setEndMinutes(30);
		this.setEndHours(16);
		this.setStartHours(7);
		this.setStartMinutes(30);
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
			if (!(o instanceof WorkDay)) {
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
	public WorkDay makeCopy() {
		WorkDay result = new WorkDay();
		copyState((WorkDay) this, result);
		return result;
	}
	public void copyState(WorkDay from, WorkDay to) {
		to.setWorkingHoursConfiguration(from.getWorkingHoursConfiguration());
		to.setStartMinutes(from.getStartMinutes());
		to.setEndMinutes(from.getEndMinutes());
		to.setStartHours(from.getStartHours());
		to.setType(from.getType());
		to.setEndHours(from.getEndHours());
	}
	public void shallowCopyState(WorkDay from, WorkDay to) {
		to.setWorkingHoursConfiguration(from.getWorkingHoursConfiguration());
		to.setStartMinutes(from.getStartMinutes());
		to.setEndMinutes(from.getEndMinutes());
		to.setStartHours(from.getStartHours());
		to.setType(from.getType());
		to.setEndHours(from.getEndHours());
	}
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
}
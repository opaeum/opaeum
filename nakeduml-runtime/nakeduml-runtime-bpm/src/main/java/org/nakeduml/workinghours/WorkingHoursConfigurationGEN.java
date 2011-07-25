package org.nakeduml.workinghours;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.CompositionNode;

abstract public class WorkingHoursConfigurationGEN implements  IPersistentObject, CompositionNode {
	private int f_businessDaysPerMonth = 0;
	private int f_businessHoursPerWeek = 0;
	private int f_businessHoursPerDay = 0;
	private Set /* (RecurringHoliday) */f_holiday = new HashSet( /* Holiday */);
	private Set /* (RecurringHoliday) */f_workDay = new HashSet( /* WorkDay */);
	private Set /* (RecurringHoliday) */f_recurringHoliday = new HashSet( /* RecurringHoliday */);
	static private boolean usesAllInstances = false;
	static private List allInstances = new ArrayList();
	private Long id;
	private Date deletedOn;
	private int objectVersion;
	private String uid;
	/**
	 * Default constructor for WorkingHoursConfiguration
	 */
	public WorkingHoursConfigurationGEN() {
	}
	/**
	 * Constructor for WorkingHoursConfigurationGEN
	 * 
	 * @param businessDaysPerMonth
	 * @param businessHoursPerWeek
	 * @param businessHoursPerDay
	 */
	public WorkingHoursConfigurationGEN(int businessDaysPerMonth, int businessHoursPerWeek, int businessHoursPerDay) {
		super();
		this.setBusinessDaysPerMonth(businessDaysPerMonth);
		this.setBusinessHoursPerWeek(businessHoursPerWeek);
		this.setBusinessHoursPerDay(businessHoursPerDay);
		this.setBusinessDaysPerMonth(21);
		this.setBusinessHoursPerWeek(40);
		this.setBusinessHoursPerDay(8);
		if (usesAllInstances) {
			allInstances.add(this);
		}
	}

	// JDK5@BaseType(baseType=double.class)

	/**
	 * Implements the user defined operation '+ addTimeTo( fromDate: Date, timeUnit: TimeUnit, numberOfUnits: Real ) :
	 * Date'
	 * 
	 * @param fromDate
	 * @param timeUnit
	 * @param numberOfUnits
	 */
	public Date addTimeTo(Date fromDate, TimeUnit timeUnit, double numberOfUnits) {
		Date result = null;
		return result;
	}
	/**
	 * Implements the user defined operation '+ calculateDifference( fromDate: Date, toDate: Date, timeUnit: TimeUnit ) :
	 * Real'
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param timeUnit
	 */
	public double calculateDifference(Date fromDate, Date toDate, TimeUnit timeUnit) {
		double result = 0;
		return result;
	}
	/**
	 * Implements the getter for feature '+ businessDaysPerMonth : Integer'
	 */
	public int getBusinessDaysPerMonth() {
		return this.f_businessDaysPerMonth;
	}
	/**
	 * Implements the setter for feature '+ businessDaysPerMonth : Integer'
	 * 
	 * @param originalElement
	 */
	public void setBusinessDaysPerMonth(int element) {
		if (this.f_businessDaysPerMonth != element) {
			this.f_businessDaysPerMonth = element;
		}
	}
	/**
	 * Implements the getter for feature '+ businessHoursPerWeek : Integer'
	 */
	public int getBusinessHoursPerWeek() {
		return this.f_businessHoursPerWeek;
	}
	/**
	 * Implements the setter for feature '+ businessHoursPerWeek : Integer'
	 * 
	 * @param originalElement
	 */
	public void setBusinessHoursPerWeek(int element) {
		if (this.f_businessHoursPerWeek != element) {
			this.f_businessHoursPerWeek = element;
		}
	}
	/**
	 * Implements the getter for feature '+ businessHoursPerDay : Integer'
	 */
	public int getBusinessHoursPerDay() {
		return this.f_businessHoursPerDay;
	}
	/**
	 * Implements the setter for feature '+ businessHoursPerDay : Integer'
	 * 
	 * @param originalElement
	 */
	public void setBusinessHoursPerDay(int element) {
		if (this.f_businessHoursPerDay != element) {
			this.f_businessHoursPerDay = element;
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
	 * Implements the setter for feature '+ holiday : Set(Holiday)'
	 * 
	 * @param elements
	 */
	public void setHoliday(Set elements) {
		if (this.f_holiday != elements) {
			Iterator it = this.f_holiday.iterator();
			while (it.hasNext()) {
				Holiday x = (Holiday) it.next();
				x.z_internalRemoveFromWorkingHoursConfiguration((WorkingHoursConfiguration) this);
			}
			this.f_holiday = elements;
			if (this.f_holiday != null) {
				it = this.f_holiday.iterator();
				while (it.hasNext()) {
					Holiday x = (Holiday) it.next();
					x.z_internalAddToWorkingHoursConfiguration((WorkingHoursConfiguration) this);
				}
			}
		}
	}
	/**
	 * Implements addition of a single originalElement to feature '+ holiday : Set(Holiday)'
	 * 
	 * @param originalElement
	 */
	public void addToHoliday(Holiday element) {
		if (element == null) {
			return;
		}
		if (this.f_holiday.contains(element)) {
			return;
		}
		this.f_holiday.add(element);
		if (element.getWorkingHoursConfiguration() != null) {
			element.getWorkingHoursConfiguration().z_internalRemoveFromHoliday(element);
		}
		element.z_internalAddToWorkingHoursConfiguration((WorkingHoursConfiguration) this);
	}
	/**
	 * Implements removal of a single originalElement from feature '+ holiday : Set(Holiday)'
	 * 
	 * @param originalElement
	 */
	public void removeFromHoliday(Holiday element) {
		if (element == null) {
			return;
		}
		this.f_holiday.remove(element);
		element.z_internalRemoveFromWorkingHoursConfiguration((WorkingHoursConfiguration) this);
	}
	// JDK5@BaseType(baseType=Holiday.class)
	/**
	 * Implements the getter for + holiday : Set(Holiday)
	 */
	public Set getHoliday() {
		if (this.f_holiday != null) {
			return Collections.unmodifiableSet(this.f_holiday);
		} else {
			return null;
		}
	}
	/**
	 * This operation should NOT be used by clients. It implements the correct addition of an originalElement in an association.
	 * 
	 * @param originalElement
	 */
	public void z_internalAddToHoliday(Holiday element) {
		this.f_holiday.add(element);
	}
	/**
	 * This operation should NOT be used by clients. It implements the correct removal of an originalElement in an association.
	 * 
	 * @param originalElement
	 */
	public void z_internalRemoveFromHoliday(Holiday element) {
		this.f_holiday.remove(element);
	}
	/**
	 * Implements the addition of a number of elements to holiday
	 * 
	 * @param newElems
	 */
	public void addToHoliday(Collection newElems) {
		Iterator it = newElems.iterator();
		while ((it.hasNext())) {
			Object item = it.next();
			if (item instanceof Holiday) {
				this.addToHoliday((Holiday) item);
			}
		}
	}
	/**
	 * Implements the removal of a number of elements from holiday
	 * 
	 * @param oldElems
	 */
	public void removeFromHoliday(Collection oldElems) {
		Iterator it = oldElems.iterator();
		while ((it.hasNext())) {
			Object item = it.next();
			if (item instanceof Holiday) {
				this.removeFromHoliday((Holiday) item);
			}
		}
	}
	/**
	 * Implements the removal of all elements from holiday
	 */
	public void removeAllFromHoliday() {
		/*
		 * make a copy of the collection in order to avoid a ConcurrentModificationException
		 */
		Iterator it = new HashSet(getHoliday()).iterator();
		while ((it.hasNext())) {
			Object item = it.next();
			if (item instanceof Holiday) {
				this.removeFromHoliday((Holiday) item);
			}
		}
	}
	/**
	 * Implements the setter for feature '+ workDay : Set(WorkDay)'
	 * 
	 * @param elements
	 */
	public void setWorkDay(Set elements) {
		if (this.f_workDay != elements) {
			Iterator it = this.f_workDay.iterator();
			while (it.hasNext()) {
				WorkDay x = (WorkDay) it.next();
				x.z_internalRemoveFromWorkingHoursConfiguration((WorkingHoursConfiguration) this);
			}
			this.f_workDay = elements;
			if (this.f_workDay != null) {
				it = this.f_workDay.iterator();
				while (it.hasNext()) {
					WorkDay x = (WorkDay) it.next();
					x.z_internalAddToWorkingHoursConfiguration((WorkingHoursConfiguration) this);
				}
			}
		}
	}
	/**
	 * Implements addition of a single originalElement to feature '+ workDay : Set(WorkDay)'
	 * 
	 * @param originalElement
	 */
	public void addToWorkDay(WorkDay element) {
		if (element == null) {
			return;
		}
		if (this.f_workDay.contains(element)) {
			return;
		}
		this.f_workDay.add(element);
		if (element.getWorkingHoursConfiguration() != null) {
			element.getWorkingHoursConfiguration().z_internalRemoveFromWorkDay(element);
		}
		element.z_internalAddToWorkingHoursConfiguration((WorkingHoursConfiguration) this);
	}
	/**
	 * Implements removal of a single originalElement from feature '+ workDay : Set(WorkDay)'
	 * 
	 * @param originalElement
	 */
	public void removeFromWorkDay(WorkDay element) {
		if (element == null) {
			return;
		}
		this.f_workDay.remove(element);
		element.z_internalRemoveFromWorkingHoursConfiguration((WorkingHoursConfiguration) this);
	}
	// JDK5@BaseType(baseType=WorkDay.class)
	/**
	 * Implements the getter for + workDay : Set(WorkDay)
	 */
	public Set getWorkDay() {
		if (this.f_workDay != null) {
			return Collections.unmodifiableSet(this.f_workDay);
		} else {
			return null;
		}
	}
	/**
	 * This operation should NOT be used by clients. It implements the correct addition of an originalElement in an association.
	 * 
	 * @param originalElement
	 */
	public void z_internalAddToWorkDay(WorkDay element) {
		this.f_workDay.add(element);
	}
	/**
	 * This operation should NOT be used by clients. It implements the correct removal of an originalElement in an association.
	 * 
	 * @param originalElement
	 */
	public void z_internalRemoveFromWorkDay(WorkDay element) {
		this.f_workDay.remove(element);
	}
	/**
	 * Implements the addition of a number of elements to workDay
	 * 
	 * @param newElems
	 */
	public void addToWorkDay(Collection newElems) {
		Iterator it = newElems.iterator();
		while ((it.hasNext())) {
			Object item = it.next();
			if (item instanceof WorkDay) {
				this.addToWorkDay((WorkDay) item);
			}
		}
	}
	/**
	 * Implements the removal of a number of elements from workDay
	 * 
	 * @param oldElems
	 */
	public void removeFromWorkDay(Collection oldElems) {
		Iterator it = oldElems.iterator();
		while ((it.hasNext())) {
			Object item = it.next();
			if (item instanceof WorkDay) {
				this.removeFromWorkDay((WorkDay) item);
			}
		}
	}
	/**
	 * Implements the removal of all elements from workDay
	 */
	public void removeAllFromWorkDay() {
		/*
		 * make a copy of the collection in order to avoid a ConcurrentModificationException
		 */
		Iterator it = new HashSet(getWorkDay()).iterator();
		while ((it.hasNext())) {
			Object item = it.next();
			if (item instanceof WorkDay) {
				this.removeFromWorkDay((WorkDay) item);
			}
		}
	}
	/**
	 * Implements the setter for feature '+ recurringHoliday : Set(RecurringHoliday)'
	 * 
	 * @param elements
	 */
	public void setRecurringHoliday(Set elements) {
		if (this.f_recurringHoliday != elements) {
			Iterator it = this.f_recurringHoliday.iterator();
			while (it.hasNext()) {
				RecurringHoliday x = (RecurringHoliday) it.next();
				x.z_internalRemoveFromWorkingHoursConfiguration((WorkingHoursConfiguration) this);
			}
			this.f_recurringHoliday = elements;
			if (this.f_recurringHoliday != null) {
				it = this.f_recurringHoliday.iterator();
				while (it.hasNext()) {
					RecurringHoliday x = (RecurringHoliday) it.next();
					x.z_internalAddToWorkingHoursConfiguration((WorkingHoursConfiguration) this);
				}
			}
		}
	}
	/**
	 * Implements addition of a single originalElement to feature '+ recurringHoliday : Set(RecurringHoliday)'
	 * 
	 * @param originalElement
	 */
	public void addToRecurringHoliday(RecurringHoliday element) {
		if (element == null) {
			return;
		}
		if (this.f_recurringHoliday.contains(element)) {
			return;
		}
		this.f_recurringHoliday.add(element);
		if (element.getWorkingHoursConfiguration() != null) {
			element.getWorkingHoursConfiguration().z_internalRemoveFromRecurringHoliday(element);
		}
		element.z_internalAddToWorkingHoursConfiguration((WorkingHoursConfiguration) this);
	}
	/**
	 * Implements removal of a single originalElement from feature '+ recurringHoliday : Set(RecurringHoliday)'
	 * 
	 * @param originalElement
	 */
	public void removeFromRecurringHoliday(RecurringHoliday element) {
		if (element == null) {
			return;
		}
		this.f_recurringHoliday.remove(element);
		element.z_internalRemoveFromWorkingHoursConfiguration((WorkingHoursConfiguration) this);
	}
	// JDK5@BaseType(baseType=RecurringHoliday.class)
	/**
	 * Implements the getter for + recurringHoliday : Set(RecurringHoliday)
	 */
	public Set getRecurringHoliday() {
		if (this.f_recurringHoliday != null) {
			return Collections.unmodifiableSet(this.f_recurringHoliday);
		} else {
			return null;
		}
	}
	/**
	 * This operation should NOT be used by clients. It implements the correct addition of an originalElement in an association.
	 * 
	 * @param originalElement
	 */
	public void z_internalAddToRecurringHoliday(RecurringHoliday element) {
		this.f_recurringHoliday.add(element);
	}
	/**
	 * This operation should NOT be used by clients. It implements the correct removal of an originalElement in an association.
	 * 
	 * @param originalElement
	 */
	public void z_internalRemoveFromRecurringHoliday(RecurringHoliday element) {
		this.f_recurringHoliday.remove(element);
	}
	/**
	 * Implements the addition of a number of elements to recurringHoliday
	 * 
	 * @param newElems
	 */
	public void addToRecurringHoliday(Collection newElems) {
		Iterator it = newElems.iterator();
		while ((it.hasNext())) {
			Object item = it.next();
			if (item instanceof RecurringHoliday) {
				this.addToRecurringHoliday((RecurringHoliday) item);
			}
		}
	}
	/**
	 * Implements the removal of a number of elements from recurringHoliday
	 * 
	 * @param oldElems
	 */
	public void removeFromRecurringHoliday(Collection oldElems) {
		Iterator it = oldElems.iterator();
		while ((it.hasNext())) {
			Object item = it.next();
			if (item instanceof RecurringHoliday) {
				this.removeFromRecurringHoliday((RecurringHoliday) item);
			}
		}
	}
	/**
	 * Implements the removal of all elements from recurringHoliday
	 */
	public void removeAllFromRecurringHoliday() {
		/*
		 * make a copy of the collection in order to avoid a ConcurrentModificationException
		 */
		Iterator it = new HashSet(getRecurringHoliday()).iterator();
		while ((it.hasNext())) {
			Object item = it.next();
			if (item instanceof RecurringHoliday) {
				this.removeFromRecurringHoliday((RecurringHoliday) item);
			}
		}
	}
	public Holiday createHoliday() {
		Holiday newInstance = new Holiday();
		newInstance.setWorkingHoursConfiguration((WorkingHoursConfiguration) this);
		newInstance.init(this);
		return newInstance;
	}
	public WorkDay createWorkDay() {
		WorkDay newInstance = new WorkDay();
		newInstance.setWorkingHoursConfiguration((WorkingHoursConfiguration) this);
		newInstance.init(this);
		return newInstance;
	}
	public RecurringHoliday createRecurringHoliday() {
		RecurringHoliday newInstance = new RecurringHoliday();
		newInstance.setWorkingHoursConfiguration((WorkingHoursConfiguration) this);
		newInstance.init(this);
		return newInstance;
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
		if (getWorkDay().size() < 1) {
			String message = "Lower bound of feature 'workDay' in object '";
			message = message + this.getIdString();
			message = message + "' of type '" + this.getClass().getName() + "' is 1" + ", yet it has size "
					+ getWorkDay().size() + ".";
			result.add(new InvariantError(this, message));
		}
		return result;
	}
	/**
	 * Returns the default identifier for WorkingHoursConfiguration
	 */
	public String getIdString() {
		String result = "";
		result = result + this.getBusinessDaysPerMonth();
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
		return "_9_5_c530259_1132751800828_425588_46";
	}
	public void setClassId(int dummy) {
	}
	public int getClassId() {
		return 26;
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
		return "WorkingHoursConfiguration" + getId();
	}
	public CompositionNode getOwningObject() {
		return null;
	}
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		Iterator holidayIter = new java.util.ArrayList(getHoliday()).iterator();
		while (holidayIter.hasNext()) {
			Holiday child = (Holiday) holidayIter.next();
			child.markDeleted();
		}
		Iterator workDayIter = new java.util.ArrayList(getWorkDay()).iterator();
		while (workDayIter.hasNext()) {
			WorkDay child = (WorkDay) workDayIter.next();
			child.markDeleted();
		}
		Iterator recurringHolidayIter = new java.util.ArrayList(getRecurringHoliday()).iterator();
		while (recurringHolidayIter.hasNext()) {
			RecurringHoliday child = (RecurringHoliday) recurringHolidayIter.next();
			child.markDeleted();
		}
	}
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	public void addToOwningObject() {
	}
	public void init(CompositionNode owner) {
		this.setBusinessDaysPerMonth(21);
		this.setBusinessHoursPerWeek(40);
		this.setBusinessHoursPerDay(8);
		if (usesAllInstances) {
			allInstances.add(this);
		}
		createComponents();
		java.util.Iterator iterworkDay = getWorkDay().iterator();
		while (iterworkDay.hasNext()) {
			((CompositionNode) iterworkDay.next()).init(this);
		}
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else {
			if (!(o instanceof WorkingHoursConfiguration)) {
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
		Iterator holidayIter = new java.util.ArrayList(getHoliday()).iterator();
		while (holidayIter.hasNext()) {
			Holiday child = (Holiday) holidayIter.next();
			child.startStateMachine();
		}
		Iterator workDayIter = new java.util.ArrayList(getWorkDay()).iterator();
		while (workDayIter.hasNext()) {
			WorkDay child = (WorkDay) workDayIter.next();
			child.startStateMachine();
		}
		Iterator recurringHolidayIter = new java.util.ArrayList(getRecurringHoliday()).iterator();
		while (recurringHolidayIter.hasNext()) {
			RecurringHoliday child = (RecurringHoliday) recurringHolidayIter.next();
			child.startStateMachine();
		}
	}
	public void createComponents() {
		if (getWorkDay().isEmpty()) {
			WorkDay newworkDay;
			newworkDay = new WorkDay();
			addToWorkDay(newworkDay);
			newworkDay.setType(WorkDayType.WEEKDAY);
			newworkDay = new WorkDay();
			addToWorkDay(newworkDay);
			newworkDay.setType(WorkDayType.SATURDAY);
			newworkDay = new WorkDay();
			addToWorkDay(newworkDay);
			newworkDay.setType(WorkDayType.SUNDAY);
		}
	}
	public WorkingHoursConfiguration makeCopy() {
		WorkingHoursConfiguration result = new WorkingHoursConfiguration();
		copyState((WorkingHoursConfiguration) this, result);
		return result;
	}
	public void copyState(WorkingHoursConfiguration from, WorkingHoursConfiguration to) {
		java.util.Iterator holidayIter = from.getHoliday().iterator();
		while (holidayIter.hasNext()) {
			Holiday child = (Holiday) holidayIter.next();
			to.addToHoliday(child.makeCopy());
		}
		to.setBusinessHoursPerDay(from.getBusinessHoursPerDay());
		java.util.Iterator workDayIter = from.getWorkDay().iterator();
		while (workDayIter.hasNext()) {
			WorkDay child = (WorkDay) workDayIter.next();
			to.addToWorkDay(child.makeCopy());
		}
		to.setBusinessDaysPerMonth(from.getBusinessDaysPerMonth());
		java.util.Iterator recurringHolidayIter = from.getRecurringHoliday().iterator();
		while (recurringHolidayIter.hasNext()) {
			RecurringHoliday child = (RecurringHoliday) recurringHolidayIter.next();
			to.addToRecurringHoliday(child.makeCopy());
		}
		to.setBusinessHoursPerWeek(from.getBusinessHoursPerWeek());
	}
	public void shallowCopyState(WorkingHoursConfiguration from, WorkingHoursConfiguration to) {
		to.setBusinessHoursPerDay(from.getBusinessHoursPerDay());
		to.setBusinessDaysPerMonth(from.getBusinessDaysPerMonth());
		to.setBusinessHoursPerWeek(from.getBusinessHoursPerWeek());
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
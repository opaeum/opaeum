package org.nakeduml.workinghours;
//JDK5@CompositionFromOwningObject(attribute = "workDay")
public class WorkDay extends WorkDayGEN {
	public boolean isWorkingDay() {
		return getMinutesPerDay() > 0;
	}
	public int getStartOfDayInMinutes() {
		return getStartHours() * 60 + getStartMinutes();
	}
	public int getEndOfDayInMinutes() {
		return getEndHours() * 60 + getEndMinutes();
	}
	public int getMinutesPerDay() {
		if (getEndOfDayInMinutes() < getStartOfDayInMinutes()) {
			// Robustness logic
			super.setStartHours(8);
			super.setEndHours(17);
		}
		return getEndOfDayInMinutes() - getStartOfDayInMinutes();
	}
	// Robustness logic
	// JDK5@Override
	@Override
	public int getEndHours() {
		if (super.getEndHours() > 23 || super.getEndHours() < 0) {
			if (getType().isWeekday()) {
				super.setEndHours(17);
			} else {
				super.setEndHours(0);
			}
		}
		return super.getEndHours();
	}
	// JDK5@Override
	@Override
	public int getEndMinutes() {
		if (super.getEndMinutes() > 59 || super.getEndMinutes() < 0) {
			super.setEndMinutes(0);
		}
		return super.getEndMinutes();
	}
	// JDK5@Override
	@Override
	public int getStartHours() {
		if (super.getStartHours() > 23 || super.getStartHours() < 0) {
			if (getType().isWeekday()) {
				super.setStartHours(8);
			} else {
				super.setStartHours(0);
			}
		}
		return super.getStartHours();
	}
	// JDK5@Override
	@Override
	public int getStartMinutes() {
		if (super.getStartMinutes() > 59 || super.getStartMinutes() < 0) {
			super.setStartMinutes(0);
		}
		return super.getStartMinutes();
	}
	public boolean isGroupOwnershipValid() {
		return true;
	}
	public boolean isUserOwnershipValid() {
		return false;
	}
}

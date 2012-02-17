package raptest;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

import raptest.View.Gender;

public class Person implements PropertyChangeListener {
	private String firstName;
	private String lastName;
	private boolean married;
	private Gender gender;
	private Integer age;
	private Address address;
	private Date dateOfBirth;
	private Integer height;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public Person() {
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public String getFirstName() {
		return firstName;
	}

	public Gender getGender() {
		return gender;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean isMarried() {
		return married;
	}

	public void setFirstName(String firstName) {
		propertyChangeSupport.firePropertyChange("firstName", this.firstName,
				this.firstName = firstName);
	}

	public void setGender(Gender gender) {
		propertyChangeSupport.firePropertyChange("gender", this.gender,
				this.gender = gender);
	}

	public void setLastName(String lastName) {
		propertyChangeSupport.firePropertyChange("lastName", this.lastName,
				this.lastName = lastName);
	}

	public void setMarried(boolean isMarried) {
		propertyChangeSupport.firePropertyChange("married", this.married,
				this.married = isMarried);
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		propertyChangeSupport.firePropertyChange("age", this.age,
				this.age = age);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		address.addPropertyChangeListener("country", this);
		propertyChangeSupport.firePropertyChange("address", this.address,
				this.address = address);
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		propertyChangeSupport.firePropertyChange("address", null, address);
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		propertyChangeSupport.firePropertyChange("dateOfBirth",
				this.dateOfBirth, this.dateOfBirth = dateOfBirth);
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		propertyChangeSupport.firePropertyChange("height",
				this.height, this.height = height);
	}
}

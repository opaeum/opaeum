// Created on 04.10.2007
package org.opaeum.rap.runtime.internal.datamodel;

import java.util.List;

import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.rap.rms.data.IPrincipal;
import org.eclipse.rap.rms.data.IProject;

final class PrincipalCopy
  extends WorkingCopy
  implements IPrincipal
{
  static final String STREET = "street"; //$NON-NLS-1$
  static final String POST_CODE = "postCode"; //$NON-NLS-1$
  static final String PHONE_NUMBER = "phoneNumber"; //$NON-NLS-1$
  static final String MOBILE_NUMBER = "mobileNumber"; //$NON-NLS-1$
  static final String LAST_NAME = "lastName"; //$NON-NLS-1$
  static final String FIRST_NAME = "firstName"; //$NON-NLS-1$
  static final String FAX_NUMBER = "faxNumber"; //$NON-NLS-1$
  static final String EMAIL = "EMail"; //$NON-NLS-1$
  static final String COUNTRY = "country"; //$NON-NLS-1$
  static final String CITY = "city"; //$NON-NLS-1$

  private String city;
  private String country;
  private String eMail;
  private String faxNumber;
  private String firstName;
  private String lastName;
  private String mobileNumber;
  private String name;
  private String phoneNumber;
  private String postCode;
  private String street;
  
  
  public PrincipalCopy( final IPrincipal principal ) {
    super( principal );
  }

  void doLoad( final IEntity entity ) {
    IPrincipal principal = ( IPrincipal )entity;
    this.name = principal.getName();
    setCity( principal.getCity() );
    setCountry( principal.getCountry() );
    setEMail( principal.getEMail() );
    setFaxNumber( principal.getFaxNumber() );
    setFirstName( principal.getFirstName() );
    setLastName( principal.getLastName() );
    setMobileNumber( principal.getMobileNumber() );
    setPhoneNumber( principal.getPhoneNumber() );
    setPostCode( principal.getPostCode() );
    setStreet( principal.getStreet() );
  }
  
  void doSave( final IEntity entity ) {
    IPrincipal principal = ( IPrincipal )entity;
    principal.setCity( getCity() );
    principal.setCountry( getCountry() );
    principal.setEMail( getEMail() );
    principal.setFaxNumber( getFaxNumber() );
    principal.setFirstName( getFirstName() );
    principal.setLastName( getLastName() );
    principal.setMobileNumber( getMobileNumber() );
    principal.setPhoneNumber( getPhoneNumber() );
    principal.setPostCode( getPostCode() );
    principal.setStreet( getStreet() );
    setDirty( false );
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public String getEMail() {
    return eMail;
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

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getPostCode() {
    return postCode;
  }

  public List<IProject> getProjects() {
    return null;
  }

  public String getStreet() {
    return street;
  }

  public IProject newProject( final String name ) {
    return null;
  }

  public void setCity( final String city ) {
    this.city = ( String )notify( CITY, city, this.city );
  }

  public void setCountry( final String country ) {
    this.country = ( String )notify( COUNTRY, country, this.country );
  }

  public void setEMail( final String eMail ) {
    this.eMail = ( String )notify( EMAIL, eMail, this.eMail );
  }

  public void setFaxNumber( final String faxNumber ) {
    this.faxNumber
      = ( String )notify( FAX_NUMBER, faxNumber, this.faxNumber );
  }

  public void setFirstName( final String firstName ) {
    this.firstName
      = ( String )notify( FIRST_NAME, firstName, this.firstName );
  }

  public void setLastName( final String lastName ) {
    this.lastName
      = ( String )notify( LAST_NAME, lastName, this.lastName );
  }

  public void setMobileNumber( final String mobileNumber ) {
    this.mobileNumber
      = ( String )notify( MOBILE_NUMBER, mobileNumber, this.mobileNumber );
  }

  public void setPhoneNumber( final String phoneNumber ) {
    this.phoneNumber
      = ( String )notify( PHONE_NUMBER, phoneNumber, this.phoneNumber );
  }

  public void setPostCode( final String postCode ) {
    this.postCode
      = ( String )notify( POST_CODE, postCode, this.postCode );
  }

  public void setStreet( final String street ) {
    this.street = ( String )notify( STREET, street, this.street );
  }

  public String getId() {
    // TODO Auto-generated method stub
    return null;
  }
}
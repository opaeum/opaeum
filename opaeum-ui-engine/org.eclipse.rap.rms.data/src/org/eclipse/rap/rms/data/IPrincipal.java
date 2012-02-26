// Created on 08.09.2007
package org.eclipse.rap.rms.data;

import java.util.List;

public interface IPrincipal extends IEntity {
  String getName();
  IProject newProject( String name );
  List<IProject> getProjects();
  
  String getStreet();
  void setStreet( String street );
  String getCity();
  void setCity( String city );
  String getPostCode();
  void setPostCode( String postCode );
  String getCountry();
  void setCountry( String country );
  
  String getLastName();
  void setLastName( String lastName );
  String getFirstName();
  void setFirstName( String firstName );
  String getEMail();
  void setEMail( String eMail );
  String getPhoneNumber();
  void setPhoneNumber( String phoneNumber );
  String getFaxNumber();
  void setFaxNumber( String faxNumber );
  String getMobileNumber();
  void setMobileNumber( String mobileNumber );
}

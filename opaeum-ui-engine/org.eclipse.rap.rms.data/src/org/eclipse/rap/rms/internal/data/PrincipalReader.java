package org.eclipse.rap.rms.internal.data;

import org.eclipse.rap.rms.data.DataModelRegistry;
import org.eclipse.rap.rms.data.IPrincipal;
import org.w3c.dom.*;


class PrincipalReader implements IEntityReader {
  private Element element;

  PrincipalReader( final Element element ) {
    this.element = element;
  }
  
  public void load() {
    DataModel model = ( DataModel )DataModelRegistry.getFactory();
    IPrincipal newPrincipal 
      = model.newPrincipal( element.getAttribute( EntityConstants.NAME ),
                            element.getAttribute( EntityConstants.ID ) );
    newPrincipal.setCity( element.getAttribute( EntityConstants.CITY ) );
    newPrincipal.setStreet( element.getAttribute( EntityConstants.STREET ) );
    String postCode = element.getAttribute( EntityConstants.POSTCODE );
    newPrincipal.setPostCode( postCode );
    newPrincipal.setCountry( element.getAttribute( EntityConstants.COUNTRY ) );
    
    String lastName = element.getAttribute( EntityConstants.LAST_NAME );
    newPrincipal.setLastName( lastName );
    String firstName = element.getAttribute( EntityConstants.FIRST_NAME );
    newPrincipal.setFirstName( firstName );
    newPrincipal.setEMail( element.getAttribute( EntityConstants.EMAIL ) );
    String phoneNumber = element.getAttribute( EntityConstants.PHONENUMBER );
    newPrincipal.setPhoneNumber( phoneNumber );
    String faxNumber = element.getAttribute( EntityConstants.FAXNUMBER );
    newPrincipal.setFaxNumber( faxNumber );
    String mobileNumber = element.getAttribute( EntityConstants.MOBILENUMBER );
    newPrincipal.setMobileNumber( mobileNumber );   
   
    Node firstChild = element.getFirstChild();
    NodeList childNodes = firstChild.getChildNodes();
    int projectNum = childNodes.getLength();
    for( int i = 0; i < projectNum; i++ ) {
      ProjectReader projectReader
        = new ProjectReader( ( Element )childNodes.item( i ), newPrincipal );
      projectReader.load();
    }    
  }
}

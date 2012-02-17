package org.eclipse.rap.rms.internal.data;

import java.util.Iterator;

import org.eclipse.rap.rms.data.IPrincipal;
import org.eclipse.rap.rms.data.IProject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


class PrincipalWriter implements IEntityWriter {
  private final IPrincipal principal;
  private final StorageManager storageManager;
  private final Element principals;
 

  PrincipalWriter( final IPrincipal principal,
                   final StorageManager storageManager, 
                   final Element principals )
  {
    this.principal = principal;
    this.storageManager = storageManager;  
    this.principals = principals;
  }

  public void save() {
    Document document = principals.getOwnerDocument();
    Element newPrincipal = document.createElement( EntityConstants.PRINCIPAL );
    principals.appendChild( newPrincipal );
    newPrincipal.setAttribute( EntityConstants.ID, principal.getId() );
    newPrincipal.setAttribute( EntityConstants.NAME, principal.getName() );
    newPrincipal.setAttribute( EntityConstants.STREET, principal.getStreet() );
    newPrincipal.setAttribute( EntityConstants.CITY, principal.getCity() );
    newPrincipal.setAttribute( EntityConstants.POSTCODE,
                               principal.getPostCode() );
    newPrincipal.setAttribute( EntityConstants.COUNTRY,
                               principal.getCountry() );
    newPrincipal.setAttribute( EntityConstants.LAST_NAME,
                               principal.getLastName() );
    newPrincipal.setAttribute( EntityConstants.FIRST_NAME,
                               principal.getFirstName() );
    newPrincipal.setAttribute( EntityConstants.EMAIL,
                               principal.getEMail() );
    newPrincipal.setAttribute( EntityConstants.PHONENUMBER,
                               principal.getPhoneNumber() );
    newPrincipal.setAttribute( EntityConstants.FAXNUMBER,
                               principal.getFaxNumber() );
    newPrincipal.setAttribute( EntityConstants.MOBILENUMBER,
                               principal.getMobileNumber() );  
    
    Element projects = document.createElement( EntityConstants.PROJECTS );
    newPrincipal.appendChild( projects );
    Iterator<IProject> pIterator = principal.getProjects().iterator();
    while( pIterator.hasNext()) {
      IProject project = pIterator.next();
      IEntityWriter projectAdapter
        = storageManager.getStorageAdapter( project, projects);
      projectAdapter.save();
    }
  }
}

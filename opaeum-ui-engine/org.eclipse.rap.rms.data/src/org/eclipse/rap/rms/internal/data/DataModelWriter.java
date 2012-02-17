package org.eclipse.rap.rms.internal.data;

import java.util.Iterator;

import org.eclipse.rap.rms.data.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class DataModelWriter implements IEntityWriter {
  private final IDataModel dataModel;
  private final StorageManager storageManager;
  private final Element root;
  
  DataModelWriter( final IDataModel dataModel,
                   final Element root, 
                   final StorageManager storageManager )
  {
    this.dataModel = dataModel;
    this.storageManager = storageManager;
    this.root = root;
  }

  public void save() {
    Document document = root.getOwnerDocument();
    Element dataModel = document.createElement( EntityConstants.DATA_MODEL );
    root.appendChild( dataModel );
    Element employees = document.createElement( EntityConstants.EMPLOYEES );
    dataModel.appendChild( employees );
    Iterator<IEmployee> eIterator = this.dataModel.getEmployees().iterator();
    while( eIterator.hasNext() ) {
      IEmployee employee = eIterator.next();
      IEntityWriter employeeAdapter
        = storageManager.getStorageAdapter( employee, employees );
      employeeAdapter.save();
    }
    
    Element principals = document.createElement( EntityConstants.PRINCIPALS );
    dataModel.appendChild( principals );
    Iterator<IPrincipal> pIterator = this.dataModel.getPrincipals().iterator();
    while( pIterator.hasNext() ) {
      IPrincipal principal = pIterator.next();
      IEntityWriter principalAdapter 
        = storageManager.getStorageAdapter( principal, principals );
      principalAdapter.save();
    }
  }
}

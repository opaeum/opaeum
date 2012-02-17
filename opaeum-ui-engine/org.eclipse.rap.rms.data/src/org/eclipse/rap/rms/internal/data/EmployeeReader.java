package org.eclipse.rap.rms.internal.data;

import org.eclipse.rap.rms.data.DataModelRegistry;
import org.w3c.dom.Element;


class EmployeeReader implements IEntityReader {
  private final Element element;
  
  EmployeeReader( final Element element ) {
    this.element = element;
  }

  public void load() {
    DataModel model = ( DataModel )DataModelRegistry.getFactory();
    model.newEmployee( element.getAttribute( EntityConstants.LAST_NAME ), 
                       element.getAttribute( EntityConstants.FIRST_NAME ),
                       element.getAttribute( EntityConstants.ID ) );
  }
}

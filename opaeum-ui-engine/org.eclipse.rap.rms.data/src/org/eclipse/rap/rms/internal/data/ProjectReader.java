package org.eclipse.rap.rms.internal.data;

import java.util.Date;

import org.eclipse.rap.rms.data.IPrincipal;
import org.eclipse.rap.rms.internal.data.DataModel.Principal;
import org.eclipse.rap.rms.internal.data.DataModel.Project;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class ProjectReader implements IEntityReader {

  private Element element;
  private Principal principal;

  public ProjectReader( final Element element, final IPrincipal principal ) {
    this.element = element;
    this.principal = ( Principal )principal;
  }

  public void load() {
    String attName = element.getAttribute( EntityConstants.NAME );
    String attId = element.getAttribute( EntityConstants.ID );
    Project newProject = ( Project )principal.newProject( attName, attId );
    String desc = element.getAttribute( EntityConstants.DESCRIPTION );
    newProject.setDescription( desc );
    Date startDate = null;
    Date endDate = null;
   
      String sDate = element.getAttribute( EntityConstants.STARTDATE );
      startDate = new Date(Long.parseLong( sDate ));  
    
      String eDate = element.getAttribute( EntityConstants.ENDDATE );
      endDate = new Date(Long.parseLong( eDate ));
   
    newProject.setStartDate( startDate );
    newProject.setEndDate( endDate );
   
    Node firstChild = element.getFirstChild();
    Node lastChild = element.getLastChild();
    NodeList firstChildNodes = firstChild.getChildNodes();
    int assigmentNum = firstChildNodes.getLength();
    for( int i = 0; i < assigmentNum; i++ ) {
      Element item = ( Element )firstChildNodes.item( i );
      AssigmentReader assigmentReader = new AssigmentReader( item, newProject );
      assigmentReader.load();
    }
    NodeList lastChildNodes = lastChild.getChildNodes();
    int taskNum = lastChildNodes.getLength();
    for( int i = 0; i < taskNum; i++ ) {
      Node item = lastChildNodes.item( i );
      TaskReader taskReader = new TaskReader( ( Element )item, newProject );
      taskReader.load();
    }
  }
}

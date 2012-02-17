package org.eclipse.rap.rms.internal.data;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.rap.rms.data.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

class StorageManager implements IStorageManager {
  private final DataModel dataModel;
  
  public StorageManager( final DataModel dataModel ) {
    this.dataModel = dataModel;
  }

  public void save( final OutputStream out ) throws IOException {
    String msg = "Could not write the data model.";
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.newDocument();
      Element root = document.createElement( "Root" );
      document.appendChild( root );
      IEntityWriter dataModelAdapter = getStorageAdapter( dataModel, root );
      dataModelAdapter.save();
      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer transformer = tFactory.newTransformer();
      transformer.setOutputProperty( OutputKeys.VERSION, "1.0");
      transformer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
      DOMSource source = new DOMSource( document );
      transformer.transform( source, new StreamResult( out ) );
    } catch( final ParserConfigurationException pce ) {
      throw new IllegalStateException( msg, pce );
    } catch( final TransformerConfigurationException tce ) {
      throw new IllegalStateException( msg, tce );
    } catch( final TransformerException te ) {
      throw new IllegalStateException( msg, te );
    }
  }
  
  public IEntityWriter getStorageAdapter( final IEntity entity, 
                                          final Element element )
  {
    IEntityWriter result = null;
    if( entity instanceof IEmployee ) {
      result = new EmployeeWriter( ( IEmployee )entity, element);
    } else if( entity instanceof IDataModel ) {
      result = new  DataModelWriter( ( IDataModel )entity, element, this );
    } else if(entity instanceof IPrincipal){
      result = new PrincipalWriter( ( IPrincipal )entity, this, element);
    } else if(entity instanceof IProject){
      result = new ProjectWriter( ( IProject )entity, this, element);
    } else if( entity instanceof IAssignment ) {
      result = new AssigmentWriter( ( IAssignment )entity, element);
    } else if( entity instanceof ITask ) {
      result = new TaskWriter( ( ITask )entity, element);
    }  
    return result;
  } 
 
  public void load( final InputStream in ) throws IOException {
    dataModel.clear(); 
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating( false ); 
    DocumentBuilder builder;
    String msg = "The input stream parameter is not a valid data model file.";
    try {
      builder = factory.newDocumentBuilder();
      Document document = builder.parse( in );
      document.normalize();
      DataModelReader modelReader = new DataModelReader( document );
      modelReader.load();
    } catch( final ParserConfigurationException pce ) {
      new IllegalArgumentException( msg, pce );
    } catch( final SAXException se ) {
      new IllegalArgumentException( msg, se );
    }
  }
}

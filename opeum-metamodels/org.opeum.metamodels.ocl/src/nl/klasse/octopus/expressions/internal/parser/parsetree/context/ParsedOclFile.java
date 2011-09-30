package nl.klasse.octopus.expressions.internal.parser.parsetree.context;

import java.util.ArrayList;

import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedName;
import nl.klasse.tools.common.StringHelpers;


public class ParsedOclFile {
	ParsedName 						packageName 	= null;
	ArrayList<ParsedOclContext> 	contents 		= new ArrayList<ParsedOclContext>();
	String 								filename			= ""; 	// holds the name of the file that is represented by this PasedOClFile instance
	
	public ParsedOclFile(String filename) {
		this.filename = filename;
	}
	
	public void setPackageName( ParsedName name ) {
		packageName = name;
	}
	
	public ParsedName getPackageName() {
		return packageName;
	}
	
	@SuppressWarnings("unchecked")
	public void setContents( ArrayList<ParsedOclContext> c ){
		contents = (ArrayList<ParsedOclContext>) c.clone();
	}
	
	public void addContents( ParsedOclContext c ){
		contents.add(c);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ParsedOclContext> getContents( ){
		return (ArrayList<ParsedOclContext>) contents.clone();
	}
			
	public String toString( ){
		String result = "";
		if (packageName != null) {
			result = "package " + packageName.toString() + StringHelpers.newLine;
		}
		if (contents != null) {
			result = result + contents.toString(); 
		}
		return result;
	}
	/**
	 * Returns the filename.
	 * @return String
	 */
	public String getFilename() {
		return filename;
	}

}

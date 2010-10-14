/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.model.internal.types;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.VisibilityKind;


/** @author  Jos Warmer
 * @version $Id: ModelElementImpl.java,v 1.1 2006/03/01 19:13:34 jwarmer Exp $
 */
public class ModelElementImpl implements IModelElement {
    private String 			name		= "";    // name of this element
	private String 			filename	= "";    //name of the input file that contains this context
	private int 			line		= 1;     //the line number of this context in the input file
	private int 			column		= 1;     //the column number of this context in the input file
    private VisibilityKind 	visibility 	= VisibilityKind.NONE;

    /** Creates new IModelElement */
    public ModelElementImpl(String n) {
        name = n;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        name = n;
    }   
    
    public PathName getPathName(){
        return new PathName(name);
    }    
    
	/**
	 * Method setLineAndColumn.
	 * @param i
	 * @param i1
	 */
	public void setLineAndColumn(int line, int col) {
		this.line = line;
		this.column = col;
	}

	/**
	 * Returns the filename.
	 * @return String
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename.
	 * @param filename The filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * Returns the column.
	 * @return int
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns the line.
	 * @return int
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Returns the visibility.
	 * @return VisibilityKind
	 */
	public VisibilityKind getVisibility() {
		return visibility;
	}

	/**
	 * Sets the visibility.
	 * @param visibility The visibility to set
	 */
	public void setVisibility(VisibilityKind visibility) {
		this.visibility = visibility;
	}
}

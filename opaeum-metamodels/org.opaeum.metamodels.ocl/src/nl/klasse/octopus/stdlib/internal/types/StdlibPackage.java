/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IAssociation;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.modelVisitors.IPackageVisitor;


/**
 * 
 * @author  Jos Warmer
 * @version $Id: StdlibPackage.java,v 1.2 2008/01/19 13:31:09 annekekleppe Exp $
 */
public class StdlibPackage implements IPackage {
    private String name = null;
    private PathName pathName;		// path of this element as Part1::part2::part3::part4

    public StdlibPackage(String name){
        this.name = name;
    }

    public void addSubpackage(IPackage sub) {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public PathName getPathName(){
        return pathName;
    }    
    
    public void setPathName(PathName pn){
        pathName = pn;
    }

    public void removeSubpackage(IPackage sub) {
    }

    public Collection<IPackage> getSubpackages() {
        return null;
    }

    public int getMetaName() {
        return 0;
    }

    public void setParent(IPackage p) {
    }

    public IPackage getParent() {
        return null;
    }

    public IPackage getRoot() {
        return null;
    }

    public boolean isReference() {
        return false;
    }

    public void addClassifier(IClassifier c) {
    }

    public void removeClassifier(IClassifier c) {
    }

    public Collection<IClassifier> getClassifiers() {
        return null;
    }

    public IClassifier getOwnedClassifier(String name, boolean create) {
        return null;
    }

    public Collection<IAssociation> getAssociations() {
        return null;
    }

    public void addImport(IImportedElement elem) {
    }

    public void removeImport(IImportedElement elem) {
    }

    public Collection<IImportedElement> getImports() {
        return null;
    }

    public void addAspect(String aspect) {
    }

    public void removeAspect(String aspect) {
    }

    public Iterator getAspects() {
        return null;
    }

    public IModelElement lookup(PathName path) {
        return null;
    }

    public IModelElement lookupRecursive(PathName path) {
        return null;
    }
    
    public IModelElement localLookup(String name) {
        return null;
    }

    public IClassifier resolveClassifier(IClassifier c) {
        return null;
    }

    public void addOclAny() {
    }

    public void addAssociationsToClassifiers() {
    }

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.IPackage#getInterfaces()
	 */
	public Collection<IInterface> getInterfaces() {
		return new ArrayList<IInterface>();
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.oclengine.modelinterface.INameSpace#lookupOperation(nl.klasse.octopus.oclengine.general.PathName, java.util.List)
	 */
	public IOperation lookupOperation(PathName path, List types) {
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.IPackage#accept(nl.klasse.octopus.modelVisitors.IPackageVisitor)
	 */
	public void accept(IPackageVisitor visitor) {
	}
}

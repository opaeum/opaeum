/*
 * Created on Mar 5, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.model.internal.types;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.INameSpace;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;


/**
 * NameSpaceImpl : 
 */
public class NameSpaceImpl extends ModelElementImpl implements INameSpace{

	/**
	 * 
	 */
	public NameSpaceImpl(String n) {
		super(n);
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.INameSpace#lookup(nl.klasse.octopus.expressions.internal.types.PathName)
	 */
	public IModelElement lookup(PathName path) {
		if (path == null) return null;
		if( path.isSingleName() ){
			return lookupLocal( path.getLast() );
		} else {
			IModelElement first = lookupLocal( path.getFirst() );
			if( first != null) {
				if( first instanceof INameSpace ) {
					return ((INameSpace)first).lookup(path.getTail());
				}
		   }
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.model.INameSpace#lookupOperation(nl.klasse.octopus.expressions.internal.types.PathName, java.util.List)
	 */
	public IOperation lookupOperation(PathName path, List<IClassifier> types) {
		if (path == null) return null;
		if( path.isSingleName() ){
			return null; // there are no operations directly in a package
		} else {
			IModelElement first = lookupLocal( path.getFirst() );
			if( first != null) {
				if( first instanceof INameSpace ) {
					return ((INameSpace)first).lookupOperation(path.getTail(), types);
				}
			}
		}
		return null;
	}

	/**
	 * @param string
	 * @return
	 */
	protected IModelElement lookupLocal(String string) {
		System.out.println("Internal error: this operation should be implemented by subclasses of NameSpaceImpl");
		return null;
	}

	@Override
	public Collection<IImportedElement> getImports() {
		return Collections.emptySet();
	}

	@Override
	public Collection<IClassifier> getClassifiers() {
		return Collections.emptySet();
	}

	@Override
	public Collection<IPackage> getSubpackages() {
		return Collections.emptySet();
	}


}

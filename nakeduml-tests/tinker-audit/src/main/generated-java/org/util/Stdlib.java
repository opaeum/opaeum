package org.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/** Class ...
 */
public class Stdlib {



	/** Implements the equals operation for bags
	 * 
	 * @param source 
	 * @param arg 
	 */
	static public <T> boolean bagEquals(List<T> source, List<T> arg) {
		/* make copy of arguments in order to manipulate the collection*/
		if ( source.size() != arg.size() ) {
			return false;
		}
		List<T> myArg = new ArrayList<T>( arg );
		for ( T elem : source ) {
			if ( myArg.contains(elem) ) {
				myArg.remove(elem);
			} else {
				return false;
			}
		}
		return myArg.isEmpty();
	}
	
	/** Implements the standard flatten operation on Bags.
	 * Because Java generic types are not checked during run-time this operation 
	 * cannot be implemented without compiler warnings.
	 * 
	 * @param source 
	 */
	@SuppressWarnings("unchecked")
	static public List bagFlatten(Object source) {
		List result = new ArrayList();
		if ( source instanceof Collection ) {
			Iterator it = ((Collection)source).iterator();
			while ( it.hasNext() ) {
				Object elem = it.next();
				if ( elem instanceof Collection ) {
					result.addAll( bagFlatten(elem));
				} else {
					result.add(elem);
				}
			}
		}
		return result;
	}
	
	static public List<Boolean> boolAsBag(boolean myBool) {
		List<Boolean> result = new ArrayList<Boolean>();
		result.add( new Boolean(myBool) );
		return result;
	}
	
	static public List<Boolean> boolAsOrderedSet(boolean myBool) {
		List<Boolean> result = new ArrayList<Boolean>();
		result.add( new Boolean(myBool) );
		return result;
	}
	
	static public List<Boolean> boolAsSequence(boolean myBool) {
		List<Boolean> result = new ArrayList<Boolean>();
		result.add( new Boolean(myBool) );
		return result;
	}
	
	static public Set<Boolean> boolAsSet(boolean myBool) {
		Set<Boolean> result = new HashSet<Boolean>();
		result.add( new Boolean(myBool) );
		return result;
	}
	
	static public <T> List<T> collectionAsBag(Collection<T> myCollection) {
		List<T> result = new ArrayList<T>();
		if ( myCollection != null ) {
			result.addAll( myCollection );
		}
		return result;
	}
	
	static public <T> List<T> collectionAsOrderedSet(Collection<T> myCollection) {
		List<T> result = new ArrayList<T>();
		if ( myCollection != null ) {
			result.addAll( myCollection );
		}
		return result;
	}
	
	static public <T> List<T> collectionAsSequence(Collection<T> myCollection) {
		List<T> result = new ArrayList<T>();
		if ( myCollection != null ) {
			result.addAll( myCollection );
		}
		return result;
	}
	
	static public <T> Set<T> collectionAsSet(Collection<T> myCollection) {
		Set<T> result = new HashSet<T>();
		if ( myCollection != null ) {
			result.addAll( myCollection );
		}
		return result;
	}
	
	/** Implements the excludesAll operation for sets
	 * 
	 * @param source 
	 * @param arg 
	 */
	static public boolean excludesAll(Collection source, Collection arg) {
		Iterator it = arg.iterator();
		while ( it.hasNext() ) {
			Object elem = it.next();
			if ( source.contains(elem) ) {
				return false;
			}
		}
		return true;
	}
	
	static public <T> Set<T> excluding(Set<T> mySource, T myElem) {
		Set<T> result = new HashSet<T>(mySource);
		if ( myElem != null ) {
			result.remove(myElem);
		}
		return result;
	}
	
	static public <T> List<T> excluding(List<T> mySource, T myElem) {
		List<T> result = new ArrayList<T>(mySource);
		if ( myElem != null ) {
			result.remove(myElem);
		}
		return result;
	}
	
	static public <T> Set<T> including(Set<T> mySource, T myElem) {
		Set<T> result = new HashSet<T>(mySource);
		if ( myElem != null ) {
			result.add(myElem);
		}
		return result;
	}
	
	static public <T> List<T> including(List<T> mySource, T myElem) {
		List<T> result = new ArrayList<T>(mySource);
		if ( myElem != null ) {
			result.add(myElem);
		}
		return result;
	}
	
	static public <T> List<T> insertAt(List<T> mySource, int myIndex, T myElem) {
		if ( mySource == null ) {
			return null;
		}
		if ( myElem != null ) {
			mySource.add(myIndex, myElem);
		}
		return mySource;
	}
	
	static public List<Integer> intAsBag(int myInt) {
		List<Integer> result = new ArrayList<Integer>();
		result.add( new Integer(myInt) );
		return result;
	}
	
	static public List<Integer> intAsOrderedSet(int myInt) {
		List<Integer> result = new ArrayList<Integer>();
		result.add( new Integer(myInt) );
		return result;
	}
	
	static public List<Integer> intAsSequence(int myInt) {
		List<Integer> result = new ArrayList<Integer>();
		result.add( new Integer(myInt) );
		return result;
	}
	
	static public Set<Integer> intAsSet(int myInt) {
		Set<Integer> result = new HashSet<Integer>();
		result.add( new Integer(myInt) );
		return result;
	}
	
	static public <T> Set<T> intersection(Collection<T> mySource, Collection<T> myElem) {
		Set<T> result = new TreeSet<T>(mySource);
		if ( myElem != null ) {
			result.retainAll(myElem);
		}
		return result;
	}
	
	static public <E> List<E> objectAsBag(E myObject) {
		List<Object> result = new ArrayList<Object>();
		if ( myObject != null ) {
			result.add( myObject );
		}
		return (List<E>)result;
	}
	
	static public <E> List<E> objectAsOrderedSet(E myObject) {
		List<Object> result = new ArrayList<Object>();
		if ( myObject != null ) {
			result.add( myObject );
		}
		return (List<E>)result;
	}
	
	static public <E> List<E> objectAsSequence(E myObject) {
		List<Object> result = new ArrayList<Object>();
		if ( myObject != null ) {
			result.add( myObject );
		}
		return (List<E>)result;
	}
	
	static public <E> Set<E> objectAsSet(E myObject) {
		Set<Object> result = new HashSet<Object>();
		if ( myObject != null ) {
			result.add( myObject );
		}
		return (Set<E>)result;
	}
	
	/** Implements the equals operation for orderedsets
	 * 
	 * @param source 
	 * @param arg 
	 */
	static public boolean orderedsetEquals(List source, List arg) {
		if ( source.size() != arg.size() ) {
			return false;
		}
		Iterator it1 = source.iterator();
		Iterator it2 = arg.iterator();
		while ( it1.hasNext() ) {
			Object elem1 = it1.next();
			Object elem2 = it2.next();
			if ( elem1 instanceof Integer ) {
				if ( ((Integer)elem1).intValue() != ((Integer)elem2).intValue() ) {
					return false;
				}
			} else {
				if ( elem1 instanceof Float ) {
					if ( ((Float)elem1).floatValue() != ((Float)elem2).floatValue() ) {
						return false;
					}
				} else {
					if ( elem1 instanceof Boolean ) {
						if ( ((Boolean)elem1).booleanValue() != ((Boolean)elem2).booleanValue() ) {
							return false;
						}
					} else {
						if ( !elem1.equals(elem2) ) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/** Implements the standard flatten operation on OrderedSets.
	 * Because Java generic types are not checked during run-time this operation 
	 * cannot be implemented without compiler warnings.
	 * 
	 * @param source 
	 */
	@SuppressWarnings("unchecked")
	static public List orderedsetFlatten(Object source) {
		List result = new ArrayList();
		if ( source instanceof Collection ) {
			Iterator it = ((Collection)source).iterator();
			while ( it.hasNext() ) {
				Object elem = it.next();
				if ( elem instanceof Collection ) {
					result.addAll( orderedsetFlatten(elem));
				} else {
					result.add(elem);
				}
			}
		}
		return result;
	}
	
	static public List<Double> realAsBag(double myReal) {
		List<Double> result = new ArrayList<Double>();
		result.add( new Double(myReal) );
		return result;
	}
	
	static public List<Double> realAsOrderedSet(double myReal) {
		List<Double> result = new ArrayList<Double>();
		result.add( new Double(myReal) );
		return result;
	}
	
	static public List<Double> realAsSequence(double myReal) {
		List<Double> result = new ArrayList<Double>();
		result.add( new Double(myReal) );
		return result;
	}
	
	static public Set<Double> realAsSet(double myReal) {
		Set<Double> result = new HashSet<Double>();
		result.add( new Double(myReal) );
		return result;
	}
	
	/** Implements the equals operation for sequences
	 * 
	 * @param source 
	 * @param arg 
	 */
	static public boolean sequenceEquals(List source, List arg) {
		if ( source.size() != arg.size() ) {
			return false;
		}
		Iterator it1 = source.iterator();
		Iterator it2 = arg.iterator();
		while ( it1.hasNext() ) {
			Object elem1 = it1.next();
			Object elem2 = it2.next();
			if ( elem1 instanceof Integer ) {
				if ( ((Integer)elem1).intValue() != ((Integer)elem2).intValue() ) {
					return false;
				}
			} else {
				if ( elem1 instanceof Float ) {
					if ( ((Float)elem1).floatValue() != ((Float)elem2).floatValue() ) {
						return false;
					}
				} else {
					if ( elem1 instanceof Boolean ) {
						if ( ((Boolean)elem1).booleanValue() != ((Boolean)elem2).booleanValue() ) {
							return false;
						}
					} else {
						if ( !elem1.equals(elem2) ) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/** Implements the standard flatten operation on Sequences.
	 * Because Java generic types are not checked during run-time this operation 
	 * cannot be implemented without compiler warnings.
	 * 
	 * @param source 
	 */
	@SuppressWarnings("unchecked")
	static public List sequenceFlatten(Object source) {
		List result = new ArrayList();
		if ( source instanceof Collection ) {
			Iterator it = ((Collection)source).iterator();
			while ( it.hasNext() ) {
				Object elem = it.next();
				if ( elem instanceof Collection ) {
					result.addAll( sequenceFlatten(elem));
				} else {
					result.add(elem);
				}
			}
		}
		return result;
	}
	
	/** Implements the equals operation for sets
	 * 
	 * @param source 
	 * @param arg 
	 */
	static public boolean setEquals(Set source, Set arg) {
		if ( source.size() != arg.size() ) {
			return false;
		}
		Iterator it = arg.iterator();
		while ( it.hasNext() ) {
			Object elem = it.next();
			if ( !source.contains(elem) ) {
				return false;
			}
		}
		return true;
	}
	
	/** Implements the standard flatten operation on Sets.
	 * Because Java generic types are not checked during run-time this operation 
	 * cannot be implemented without compiler warnings.
	 * 
	 * @param source 
	 */
	@SuppressWarnings("unchecked")
	static public Set setFlatten(Object source) {
		Set result = new HashSet();
		if ( source instanceof Collection ) {
			Iterator it = ((Collection)source).iterator();
			while ( it.hasNext() ) {
				Object elem = it.next();
				if ( elem instanceof Collection ) {
					result.addAll( setFlatten(elem));
				} else {
					result.add(elem);
				}
			}
		}
		return result;
	}
	
	static public List<String> stringAsBag(String myString) {
		List<String> result = new ArrayList<String>();
		if ( myString != null ) {
			result.add( myString );
		}
		return result;
	}
	
	static public List<String> stringAsOrderedSet(String myString) {
		List<String> result = new ArrayList<String>();
		if ( myString != null ) {
			result.add( myString );
		}
		return result;
	}
	
	static public List<String> stringAsSequence(String myString) {
		List<String> result = new ArrayList<String>();
		if ( myString != null ) {
			result.add( myString );
		}
		return result;
	}
	
	static public Set<String> stringAsSet(String myString) {
		Set<String> result = new HashSet<String>();
		if ( myString != null ) {
			result.add( myString );
		}
		return result;
	}

}
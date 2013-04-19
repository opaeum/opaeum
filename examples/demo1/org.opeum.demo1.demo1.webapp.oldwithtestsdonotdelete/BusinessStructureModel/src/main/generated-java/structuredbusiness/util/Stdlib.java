package structuredbusiness.util;

import java.sql.Timestamp;
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
	static final public Timestamp FUTURE = new Timestamp(1000L*60*60*24*365*1000);


	/** Implements the equals operation for bags
	 * 
	 * @param source 
	 * @param arg 
	 */
	static public <T> boolean bagEquals(Collection<T> source, Collection<T> arg) {
		/* make copy of arguments in order to manipulate the collection*/
		if ( source.size() != arg.size() ) {
			return false;
		}
		Collection<T> myArg = new ArrayList<T>( arg );
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
	static public Collection bagFlatten(Object source) {
		Collection result = new ArrayList();
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
	
	static public Collection<Boolean> boolAsBag(Boolean myBool) {
		Collection<Boolean> result = new ArrayList<Boolean>();
		if ( myBool != null ) {
			result.add( myBool );
		}
		return result;
	}
	
	static public List<Boolean> boolAsOrderedSet(Boolean myBool) {
		List<Boolean> result = new ArrayList<Boolean>();
		if ( myBool != null ) {
			result.add( myBool );
		}
		return result;
	}
	
	static public List<Boolean> boolAsSequence(Boolean myBool) {
		List<Boolean> result = new ArrayList<Boolean>();
		if ( myBool != null ) {
			result.add( myBool );
		}
		return result;
	}
	
	static public Set<Boolean> boolAsSet(Boolean myBool) {
		Set<Boolean> result = new HashSet<Boolean>();
		if ( myBool != null ) {
			result.add( myBool );
		}
		return result;
	}
	
	static public <T> Collection<T> collectionAsBag(Collection<T> myCollection) {
		Collection<T> result = new ArrayList<T>();
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
	
	static public <T> T collectionAsSingleObject(Collection<T> in) {
		if ( in.size()==0 ) {
			return null;
		} else {
			if ( in.size()==1 ) {
				return in.iterator().next();
			} else {
				throw new IllegalArgumentException("Input collection contains more than one object: "+ in.size());
			}
		}
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
	
	static public <T> Collection<T> excluding(Collection<T> mySource, T myElem) {
		Collection<T> result = new ArrayList<T>(mySource);
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
	
	static public <T> Set<T> excluding(Set<T> mySource, T myElem) {
		Set<T> result = new HashSet<T>(mySource);
		if ( myElem != null ) {
			result.remove(myElem);
		}
		return result;
	}
	
	static public <T> Collection<T> including(Collection<T> mySource, T myElem) {
		Collection<T> result = new ArrayList<T>(mySource);
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
	
	static public <T> Set<T> including(Set<T> mySource, T myElem) {
		Set<T> result = new HashSet<T>(mySource);
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
	
	static public Collection<Integer> intAsBag(Integer myInt) {
		Collection<Integer> result = new ArrayList<Integer>();
		if ( myInt != null ) {
			result.add( myInt );
		}
		return result;
	}
	
	static public List<Integer> intAsOrderedSet(Integer myInt) {
		List<Integer> result = new ArrayList<Integer>();
		if ( myInt != null ) {
			result.add( myInt );
		}
		return result;
	}
	
	static public List<Integer> intAsSequence(Integer myInt) {
		List<Integer> result = new ArrayList<Integer>();
		if ( myInt != null ) {
			result.add( myInt );
		}
		return result;
	}
	
	static public Set<Integer> intAsSet(Integer myInt) {
		Set<Integer> result = new HashSet<Integer>();
		if ( myInt != null ) {
			result.add( myInt );
		}
		return result;
	}
	
	static public <T> Set<T> intersection(Collection<T> mySource, Collection<T> myElem) {
		Set<T> result = new TreeSet<T>(mySource);
		if ( myElem != null ) {
			result.retainAll(myElem);
		}
		return result;
	}
	
	static public <T> Collection<T> objectAsBag(T myObject) {
		Collection<T> result = new ArrayList<T>();
		if ( myObject != null ) {
			result.add( myObject );
		}
		return result;
	}
	
	static public <T> List<T> objectAsOrderedSet(T myObject) {
		List<T> result = new ArrayList<T>();
		if ( myObject != null ) {
			result.add( myObject );
		}
		return result;
	}
	
	static public <T> List<T> objectAsSequence(T myObject) {
		List<T> result = new ArrayList<T>();
		if ( myObject != null ) {
			result.add( myObject );
		}
		return result;
	}
	
	static public <T> Set<T> objectAsSet(T myObject) {
		Set<T> result = new HashSet<T>();
		if ( myObject != null ) {
			result.add( myObject );
		}
		return result;
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
	
	static public Collection<Double> realAsBag(Double myReal) {
		Collection<Double> result = new ArrayList<Double>();
		if ( myReal != null ) {
			result.add( myReal );
		}
		return result;
	}
	
	static public List<Double> realAsOrderedSet(Double myReal) {
		List<Double> result = new ArrayList<Double>();
		if ( myReal != null ) {
			result.add( myReal );
		}
		return result;
	}
	
	static public List<Double> realAsSequence(Double myReal) {
		List<Double> result = new ArrayList<Double>();
		if ( myReal != null ) {
			result.add( myReal );
		}
		return result;
	}
	
	static public Set<Double> realAsSet(Double myReal) {
		Set<Double> result = new HashSet<Double>();
		if ( myReal != null ) {
			result.add( myReal );
		}
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
	
	static public Collection<String> stringAsBag(String myString) {
		Collection<String> result = new ArrayList<String>();
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
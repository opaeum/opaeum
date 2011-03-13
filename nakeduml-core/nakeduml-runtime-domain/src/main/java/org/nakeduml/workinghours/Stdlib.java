package org.nakeduml.workinghours;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
public class Stdlib {
	static public Set stringAsSet(String myString) {
		Set result = new HashSet();
		if (myString != null) {
			result.add(myString);
		}
		return result;
	}
	static public Set intAsSet(int myInt) {
		Set result = new HashSet();
		if (new Integer(myInt) != null) {
			result.add(new Integer(myInt));
		}
		return result;
	}
	static public Set realAsSet(double myReal) {
		Set result = new HashSet();
		if (new Double(myReal) != null) {
			result.add(new Double(myReal));
		}
		return result;
	}
	static public Set boolAsSet(boolean myBool) {
		Set result = new HashSet();
		if (new Boolean(myBool) != null) {
			result.add(new Boolean(myBool));
		}
		return result;
	}
	static public Set collectionAsSet(Collection myCollection) {
		Set result = new HashSet();
		if (myCollection != null) {
			result.addAll(myCollection);
		}
		return result;
	}
	static public Set objectAsSet(Object myObject) {
		Set result = new HashSet();
		if (myObject != null) {
			result.add(myObject);
		}
		return result;
	}
	static public List stringAsBag(String myString) {
		List result = new ArrayList();
		if (myString != null) {
			result.add(myString);
		}
		return result;
	}
	static public List intAsBag(int myInt) {
		List result = new ArrayList();
		if (new Integer(myInt) != null) {
			result.add(new Integer(myInt));
		}
		return result;
	}
	static public List realAsBag(double myReal) {
		List result = new ArrayList();
		if (new Double(myReal) != null) {
			result.add(new Double(myReal));
		}
		return result;
	}
	static public List boolAsBag(boolean myBool) {
		List result = new ArrayList();
		if (new Boolean(myBool) != null) {
			result.add(new Boolean(myBool));
		}
		return result;
	}
	static public List collectionAsBag(Collection myCollection) {
		List result = new ArrayList();
		if (myCollection != null) {
			result.addAll(myCollection);
		}
		return result;
	}
	static public List objectAsBag(Object myObject) {
		List result = new ArrayList();
		if (myObject != null) {
			result.add(myObject);
		}
		return result;
	}
	static public List stringAsSequence(String myString) {
		List result = new ArrayList();
		if (myString != null) {
			result.add(myString);
		}
		return result;
	}
	static public List intAsSequence(int myInt) {
		List result = new ArrayList();
		if (new Integer(myInt) != null) {
			result.add(new Integer(myInt));
		}
		return result;
	}
	static public List realAsSequence(double myReal) {
		List result = new ArrayList();
		if (new Double(myReal) != null) {
			result.add(new Double(myReal));
		}
		return result;
	}
	static public List boolAsSequence(boolean myBool) {
		List result = new ArrayList();
		if (new Boolean(myBool) != null) {
			result.add(new Boolean(myBool));
		}
		return result;
	}
	static public List collectionAsSequence(Collection myCollection) {
		List result = new ArrayList();
		if (myCollection != null) {
			result.addAll(myCollection);
		}
		return result;
	}
	static public List objectAsSequence(Object myObject) {
		List result = new ArrayList();
		if (myObject != null) {
			result.add(myObject);
		}
		return result;
	}
	static public List stringAsOrderedSet(String myString) {
		List result = new ArrayList();
		if (myString != null) {
			result.add(myString);
		}
		return result;
	}
	static public List intAsOrderedSet(int myInt) {
		List result = new ArrayList();
		if (new Integer(myInt) != null) {
			result.add(new Integer(myInt));
		}
		return result;
	}
	static public List realAsOrderedSet(double myReal) {
		List result = new ArrayList();
		if (new Double(myReal) != null) {
			result.add(new Double(myReal));
		}
		return result;
	}
	static public List boolAsOrderedSet(boolean myBool) {
		List result = new ArrayList();
		if (new Boolean(myBool) != null) {
			result.add(new Boolean(myBool));
		}
		return result;
	}
	static public List collectionAsOrderedSet(Collection myCollection) {
		List result = new ArrayList();
		if (myCollection != null) {
			result.addAll(myCollection);
		}
		return result;
	}
	static public List objectAsOrderedSet(Object myObject) {
		List result = new ArrayList();
		if (myObject != null) {
			result.add(myObject);
		}
		return result;
	}
	static public Set including(Set mySource, Object myElem) {
		Set result = new HashSet(mySource);
		result.add(myElem);
		return result;
	}
	static public Set excluding(Set mySource, Object myElem) {
		Set result = new HashSet(mySource);
		result.remove(myElem);
		return result;
	}
	static public List including(List mySource, Object myElem) {
		List result = new ArrayList(mySource);
		result.add(myElem);
		return result;
	}
	static public List excluding(List mySource, Object myElem) {
		List result = new ArrayList(mySource);
		result.remove(myElem);
		return result;
	}
	static public Set intersection(Collection mySource, Collection myElem) {
		Set intersection = new TreeSet(mySource);
		intersection.retainAll(myElem);
		return intersection;
	}
	static public List insertAt(List mySource, int myIndex, Object myElem) {
		if (mySource == null)
			return null;
		mySource.add(myIndex, myElem);
		return mySource;
	}
	/**
	 * Implements the standard flatten operation on Sets
	 * 
	 * @param source
	 */
	static public Set setFlatten(Object source) {
		java.util.Set result = new HashSet();
		if (source instanceof java.util.Collection) {
			Iterator it = ((java.util.Collection) source).iterator();
			while (it.hasNext()) {
				Object elem = it.next();
				if (elem instanceof java.util.Collection) {
					result.addAll(setFlatten(elem));
				} else {
					result.add(elem);
				}
			}
		}
		return result;
	}
	/**
	 * Implements the standard flatten operation on Sets
	 * 
	 * @param source
	 */
	static public List sequenceFlatten(Object source) {
		java.util.List result = new ArrayList();
		if (source instanceof java.util.Collection) {
			Iterator it = ((java.util.Collection) source).iterator();
			while (it.hasNext()) {
				Object elem = it.next();
				if (elem instanceof java.util.Collection) {
					result.addAll(sequenceFlatten(elem));
				} else {
					result.add(elem);
				}
			}
		}
		return result;
	}
	/**
	 * Implements the standard flatten operation on Sets
	 * 
	 * @param source
	 */
	static public List bagFlatten(Object source) {
		java.util.List result = new ArrayList();
		if (source instanceof java.util.Collection) {
			Iterator it = ((java.util.Collection) source).iterator();
			while (it.hasNext()) {
				Object elem = it.next();
				if (elem instanceof java.util.Collection) {
					result.addAll(bagFlatten(elem));
				} else {
					result.add(elem);
				}
			}
		}
		return result;
	}
	/**
	 * Implements the standard flatten operation on Sets
	 * 
	 * @param source
	 */
	static public List orderedsetFlatten(Object source) {
		java.util.List result = new ArrayList();
		if (source instanceof java.util.Collection) {
			Iterator it = ((java.util.Collection) source).iterator();
			while (it.hasNext()) {
				Object elem = it.next();
				if (elem instanceof java.util.Collection) {
					result.addAll(orderedsetFlatten(elem));
				} else {
					result.add(elem);
				}
			}
		}
		return result;
	}
	/**
	 * Implements the equals operation for sets
	 * 
	 * @param source
	 * @param arg
	 */
	static public boolean setEquals(Set source, Set arg) {
		if (source.size() != arg.size()) {
			return false;
		}
		Iterator it = arg.iterator();
		while (it.hasNext()) {
			Object elem = it.next();
			if (!source.contains(elem)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Implements the equals operation for sequences
	 * 
	 * @param source
	 * @param arg
	 */
	static public boolean sequenceEquals(List source, List arg) {
		if (source.size() != arg.size()) {
			return false;
		}
		Iterator it1 = source.iterator();
		Iterator it2 = arg.iterator();
		while (it1.hasNext()) {
			Object elem1 = it1.next();
			Object elem2 = it2.next();
			if (elem1 instanceof Integer) {
				if (((Integer) elem1).intValue() != ((Integer) elem2).intValue()) {
					return false;
				}
			} else {
				if (elem1 instanceof Double) {
					if (((Double) elem1).doubleValue() != ((Double) elem2).doubleValue()) {
						return false;
					}
				} else {
					if (elem1 instanceof Boolean) {
						if (((Boolean) elem1).booleanValue() != ((Boolean) elem2).booleanValue()) {
							return false;
						}
					} else {
						if (!elem1.equals(elem2)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	/**
	 * Implements the equals operation for bags
	 * 
	 * @param source
	 * @param arg
	 */
	static public boolean bagEquals(List source, List arg) {
		if (source.size() != arg.size()) {
			return false;
		}
		java.util.List mySource = new ArrayList(source);
		java.util.List myArg = new ArrayList(arg);
		Iterator it1 = mySource.iterator();
		while (it1.hasNext()) {
			Object elem1 = it1.next();
			if (myArg.contains(elem1)) {
				myArg.remove(elem1);
			} else {
				return false;
			}
		}
		return myArg.isEmpty();
	}
	/**
	 * Implements the equals operation for orderedsets
	 * 
	 * @param source
	 * @param arg
	 */
	static public boolean orderedsetEquals(List source, List arg) {
		if (source.size() != arg.size()) {
			return false;
		}
		Iterator it1 = source.iterator();
		Iterator it2 = arg.iterator();
		while (it1.hasNext()) {
			Object elem1 = it1.next();
			Object elem2 = it2.next();
			if (elem1 instanceof Integer) {
				if (((Integer) elem1).intValue() != ((Integer) elem2).intValue()) {
					return false;
				}
			} else {
				if (elem1 instanceof Double) {
					if (((Double) elem1).doubleValue() != ((Double) elem2).doubleValue()) {
						return false;
					}
				} else {
					if (elem1 instanceof Boolean) {
						if (((Boolean) elem1).booleanValue() != ((Boolean) elem2).booleanValue()) {
							return false;
						}
					} else {
						if (!elem1.equals(elem2)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	/**
	 * Implements the equals operation for sets
	 * 
	 * @param source
	 * @param arg
	 */
	static public boolean excludesAll(Collection source, Collection arg) {
		Iterator it = arg.iterator();
		while (it.hasNext()) {
			Object elem = it.next();
			if (source.contains(elem)) {
				return false;
			}
		}
		return true;
	}
}
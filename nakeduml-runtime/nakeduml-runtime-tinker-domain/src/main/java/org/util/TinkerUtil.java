package org.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TinkerUtil {

	public static Collection<?> convertEnumsForPersistence(Collection<? extends Enum<?>> multiEmbeddedReason) {
		Collection<String> persistentCollection;
		if (multiEmbeddedReason instanceof Set) {
			persistentCollection = new HashSet<String>();
		} else {
			persistentCollection = new ArrayList<String>();
		}
		for (Enum<?> e : multiEmbeddedReason) {
			persistentCollection.add(e.toString());
		}
		return persistentCollection;
	}

	public static Collection convertEnumsFromPersistence(Collection<String> multiEmbeddedReason, Class<? extends Enum> e, boolean isOrdered) {
		if (multiEmbeddedReason != null) {
			Collection<Enum> persistentCollection;
			if (!isOrdered) {
				persistentCollection = new HashSet<Enum>();
			} else {
				persistentCollection = new ArrayList<Enum>();
			}
			for (String s : multiEmbeddedReason) {
				persistentCollection.add(Enum.valueOf(e, s));
			}
			return persistentCollection;
		} else {
			return isOrdered ? new ArrayList(): new HashSet();
		}
	}

}

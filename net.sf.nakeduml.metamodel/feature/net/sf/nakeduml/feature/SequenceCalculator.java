package net.sf.nakeduml.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SequenceCalculator<T> {
	public class BeforeAndAfter {
		T executableUnit;
		Set<BeforeAndAfter> predecessors = new HashSet<BeforeAndAfter>();
		Class<? extends T>[] before;
		Class<? extends T>[] after;

		public BeforeAndAfter(T phase, Class<? extends T>[] before, Class<? extends T>[] after) {
			super();
			this.executableUnit = phase;
			this.before = before;
			this.after = after;
		}

		public BeforeAndAfter(T phase) {
			super();
		}

		public T getExecutableUnit() {
			return this.executableUnit;
		}

		public Set<BeforeAndAfter> getPredecessors() {
			return this.predecessors;
		}

		public void addPredecessor(BeforeAndAfter p) {
			this.predecessors.add(p);
		}

		public Class<? extends T>[] before() {
			return before;
		}

		public Class<? extends T>[] after() {
			return after;
		}
	}

	Map<Class<? extends T>, BeforeAndAfter> classMap = new HashMap<Class<? extends T>, BeforeAndAfter>();
	List<T> executionUnits = new ArrayList<T>();

	protected abstract BeforeAndAfter createStepAndPredecessor(T step);

	public void initializeFromClasses(Set<Class<? extends T>> selectedExecutionUnits) {
		populateClassToBeforeAndAfterMap(selectedExecutionUnits);
		resolvePredecessors();
		sort();
	}

	public void populateClassToBeforeAndAfterMap(Set<Class<? extends T>> selectedExecutionUnits) {
		for (Class<? extends T> t : selectedExecutionUnits) {
			BeforeAndAfter beforeAndAfter;
			try {
				beforeAndAfter = createStepAndPredecessor(t.newInstance());
				classMap.put(t, beforeAndAfter);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void resolvePredecessors() {
		for (BeforeAndAfter me : classMap.values()) {
			addMyPredecessors(me);
			addMeToMySuccessors(me);
		}
	}

	private void addMeToMySuccessors(BeforeAndAfter me) {
		for (Class<? extends T> c : me.before()) {
			if (classMap.containsKey(c)) {
				classMap.get(c).addPredecessor(me);
			}
		}
	}

	private void addMyPredecessors(BeforeAndAfter me) {
		for (Class<? extends T> c : me.after()) {
			if (classMap.containsKey(c)) {
				me.addPredecessor(classMap.get(c));
			}
		}
	}

	private void sort() throws CircularPrecessionException {
		List<T> traversalPath = new ArrayList<T>();
		for (BeforeAndAfter tp : classMap.values()) {
			addPredecessorsThenMyself(traversalPath, tp);
		}
	}

	private void addPredecessorsThenMyself(List<T> traversalPath, BeforeAndAfter tp) throws CircularPrecessionException {
		if (!executionUnits.contains(tp.getExecutableUnit())) {
			traversalPath.add(tp.getExecutableUnit());
			for (BeforeAndAfter pf : tp.getPredecessors()) {
				if (traversalPath.contains(pf.getExecutableUnit())) {
					throw new CircularPrecessionException(traversalPath, pf.getExecutableUnit());
				} else {
					addPredecessorsThenMyself(traversalPath, pf);
				}
			}
			traversalPath.remove(tp.getExecutableUnit());
			executionUnits.add(tp.getExecutableUnit());
		}
	}

	public List<T> getExecutionUnits() {
		return this.executionUnits;
	}
}

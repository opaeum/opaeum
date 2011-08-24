package net.sf.nakeduml.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SequenceCalculator<T>{
	public class BeforeAndAfter{
		Class<? extends T> executableUnit;
		Set<BeforeAndAfter> predecessors = new HashSet<BeforeAndAfter>();
		Class<? extends T>[] before;
		Class<? extends T>[] after;
		public BeforeAndAfter(Class<? extends T> phase,Class<? extends T>[] before,Class<? extends T>[] after){
			super();
			this.executableUnit = phase;
			this.before = before;
			this.after = after;
		}
		public Class<? extends T> getExecutableUnit(){
			return this.executableUnit;
		}
		public Set<BeforeAndAfter> getPredecessors(){
			return this.predecessors;
		}
		public void addPredecessor(BeforeAndAfter p){
			this.predecessors.add(p);
		}
		public Class<? extends T>[] before(){
			return before;
		}
		public Class<? extends T>[] after(){
			return after;
		}
	}
	Map<Class<? extends T>,BeforeAndAfter> classMap = new HashMap<Class<? extends T>,BeforeAndAfter>();
	Set<Class<? extends T>> selectedSteps = new HashSet<Class<? extends T>>();
	List<T> executionUnits = new ArrayList<T>();
	protected abstract BeforeAndAfter createStepAndPredecessor(Class<? extends T> step);
	public void initializeFromClasses(Set<Class<? extends T>> selectedExecutionUnits){
		this.selectedSteps= selectedExecutionUnits;
		resolvePredecessors();
		sort();
	}
	private void resolvePredecessors(){
		for(Class<? extends T> clzz:selectedSteps){
			BeforeAndAfter me = getBeforeAndAfter(clzz);
			addMyPredecessors(me);
			addMeToMySuccessors(me);
		}
	}
	private void addMeToMySuccessors(BeforeAndAfter me){
		for(Class<? extends T> c:me.before()){
			BeforeAndAfter successor = getBeforeAndAfter(c);
			addMeToMySuccessors(successor);
			successor.addPredecessor(me);
		}
	}
	public BeforeAndAfter getBeforeAndAfter(Class<? extends T> c){
		BeforeAndAfter beforeAndAfter = classMap.get(c);
		if(beforeAndAfter==null){
			classMap.put(c,beforeAndAfter=createStepAndPredecessor(c));
		}
		return beforeAndAfter;
	}
	private void addMyPredecessors(BeforeAndAfter me){
		for(Class<? extends T> c:me.after()){
			BeforeAndAfter predecessor = getBeforeAndAfter(c);
			addMyPredecessors(predecessor);
			me.addPredecessor(predecessor);
		}
	}
	private void sort() throws CircularPrecessionException{
		List<Class<? extends T>> traversalPath = new ArrayList<Class<? extends T>>();
		for(BeforeAndAfter tp:classMap.values()){
			addPredecessorsThenMyself(traversalPath, tp);
		}
	}
	private void addPredecessorsThenMyself(List<Class<? extends T>> traversalPath,BeforeAndAfter tp) throws CircularPrecessionException{
		if(!hasAlreadyBeenAdded(tp)){
			traversalPath.add(tp.getExecutableUnit());
			for(BeforeAndAfter pf:tp.getPredecessors()){
				if(traversalPath.contains(pf.getExecutableUnit())){
					throw new CircularPrecessionException(traversalPath, pf.getExecutableUnit());
				}else{
					addPredecessorsThenMyself(traversalPath, pf);
				}
			}
			traversalPath.remove(tp.getExecutableUnit());
			if(selectedSteps.contains(tp.getExecutableUnit())){
				try{
					executionUnits.add(tp.getExecutableUnit().newInstance());
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			}
		}
	}
	public boolean hasAlreadyBeenAdded(BeforeAndAfter tp){
		for(T t:executionUnits){
			if(tp.getExecutableUnit().isInstance(t)){
				return true;
			}
		}
		return false;
	}
	public List<T> getExecutionUnits(){
		return this.executionUnits;
	}
	public Set<Class<? extends T>> getExecutionUnitClasses(){
		return this.classMap.keySet();
	}
}

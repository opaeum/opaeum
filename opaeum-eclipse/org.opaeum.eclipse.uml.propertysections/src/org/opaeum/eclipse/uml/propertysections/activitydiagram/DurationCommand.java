package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.DurationObservation;

public class DurationCommand extends AbstractCommand{
	private int index = 0;
	private DurationObservation obs;
	private Object value;
	public DurationCommand(DurationObservation obs,EStructuralFeature feature,Object value,int index){
		super();
		this.obs = obs;
		this.feature = feature;
		this.value = value;
		this.index = index;
	}
	private Object oldValue;
	private EStructuralFeature feature;
	@Override
	public boolean canExecute(){
		return index <= 1 && value!=null;
	}
	@Override
	public void execute(){
		EList list = (EList) obs.eGet(feature);
		oldValue = list.size() <= index ? null : list.get(index);
		redo();
	}
	@Override
	public void redo(){
		BasicEList list = (BasicEList) obs.eGet(feature);
		Object[] data = new Object[2];
		if(list.isEmpty()){
			data[0] = value;
			data[1] = value;
		}else if(list.size() == 1){
			data[0] = list.get(0);
			data[1] = value;
		}else{
			data=list.data();
		}
		data[index] = value;
		list.setData(2, data);
	}
	@Override
	public boolean canUndo(){
		return true;
	}
	@Override
	public void undo(){
		EList list = (EList) obs.eGet(feature);
		list.set(index, oldValue);
	}
}

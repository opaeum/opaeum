package org.opaeum.runtime.jface.ui;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;

public class OpaeumValidationRealm extends Realm{
	private Display display;
	private Realm realm;
	public OpaeumValidationRealm(){
		setDefault(this);
	}
	public void asyncExec(Runnable runnable){
		getRealm().asyncExec(runnable);
	}
	public boolean equals(Object obj){
		return getRealm().equals(obj);
	}
	public void exec(Runnable runnable){
		getRealm().exec(runnable);
	}
	public int hashCode(){
		return getRealm().hashCode();
	}
	public void timerExec(int milliseconds,Runnable runnable){
		getRealm().timerExec(milliseconds, runnable);
	}
	public String toString(){
		return getRealm().toString();
	}
	@Override
	public boolean isCurrent(){
		return true;
	}
	Realm getRealm(){
		if(display!=Display.getCurrent()|| realm==null){
			realm=SWTObservables.getRealm( Display.getCurrent() );
		}
		return realm;
	}
}

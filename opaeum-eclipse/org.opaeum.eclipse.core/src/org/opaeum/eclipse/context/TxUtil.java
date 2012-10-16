package org.opaeum.eclipse.context;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Display;

public class TxUtil{
	public static void executeAndWait(final Command command,EditingDomain editingDomain2){
		if(Display.getCurrent() != null){
			throw new IllegalStateException("ExecuteAndWait can only be called from background threads");
		}
		if(editingDomain2 instanceof TransactionalEditingDomain){
			try{
				Object lock = new Object();
				synchronized(lock){
					executeInOwnTransaction(command, lock, editingDomain2);
					lock.wait(3 * 60 * 1000);
				}
			}catch(InterruptedException e){
				throw new RuntimeException(e);
			}
		}else if(editingDomain2 == null){
			command.execute();
		}else{
			editingDomain2.getCommandStack().execute(command);
		}
	}
	public static void performExecute(final Command command,final Object lock,final EditingDomain editingDomain2){
		if(editingDomain2 instanceof TransactionalEditingDomain){
			executeInOwnTransaction(command, lock, editingDomain2);
		}else if(editingDomain2 == null){
			command.execute();
		}else{
			editingDomain2.getCommandStack().execute(command);
		}
	}
	public static void executeInOwnTransaction(final Command command,final Object lock,final EditingDomain editingDomain2){
		// !!!!! Papyrus requires all this shit
		Display.getDefault().asyncExec(new Runnable(){
			public void run(){
				try{
					((TransactionalEditingDomain) editingDomain2).runExclusive(new Runnable(){
						public void run(){
							Display.getCurrent().asyncExec(new Runnable(){
								public void run(){
									try{
										editingDomain2.getCommandStack().execute(command);
									}finally{
										if(lock != null){
											synchronized(lock){
												lock.notifyAll();
											}
										}
									}
								}
							});
						}
					});
				}catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}

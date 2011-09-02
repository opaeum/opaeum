package org.nakeduml.environment.seam2;

import org.nakeduml.runtime.persistence.UmtPersistence;

public class Seam2UmtPersistence extends AbstractSeam2Persistence implements UmtPersistence{
	@Override
	public void beginTransaction(){
		// TODO Auto-generated method stub
	}
	@Override
	public boolean isActive(){
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void rollbackTransaction(){
		// TODO Auto-generated method stub
	}
	@Override
	public void commitTransaction(){
		// TODO Auto-generated method stub
	}
	@Override
	public boolean isRolledBack(){
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setTransactionTimeout(int i){
		// TODO Auto-generated method stub
	}
}

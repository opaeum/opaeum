package org.opaeum.runtime.event;

public interface UserInterfaceService{
	public <T> T getUserFormById(String id);
	public <T> void registerUserForm(String id, String content); 
}

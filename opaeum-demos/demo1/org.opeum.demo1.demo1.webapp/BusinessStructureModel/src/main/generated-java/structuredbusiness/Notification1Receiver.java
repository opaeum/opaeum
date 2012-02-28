package structuredbusiness;

public interface Notification1Receiver {
	public boolean consumeNotification1Event(Notification1 signal);
	
	public void receiveNotification1(Notification1 signal);

}
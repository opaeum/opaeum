package structuredbusiness;

public interface ProductAnnouncementReceiver {
	public boolean consumeProductAnnouncementEvent(ProductAnnouncement signal);
	
	public void receiveProductAnnouncement(ProductAnnouncement signal);

}
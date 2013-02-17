package org.opaeum.demo1.structuredbusiness;

public interface ProductAnnouncementReceiver {
	public boolean consumeProductAnnouncementEvent(ProductAnnouncement signal);
	
	public void receiveProductAnnouncement(ProductAnnouncement signal);

}
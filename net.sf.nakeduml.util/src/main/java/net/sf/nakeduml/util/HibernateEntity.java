package net.sf.nakeduml.util;

import java.util.Date;

public interface HibernateEntity {
	public void setDeletedOn(Date d);

	public Date getDeletedOn();
}

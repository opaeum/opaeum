package org.util.metainfo.adaptor;

import org.nakeduml.environment.JavaMetaInfoMap;
import org.nakeduml.runtime.domain.AbstractEntity;

public class JavaMetaInfoMapImpl extends JavaMetaInfoMap {


	/** Constructor for JavaMetaInfoMapImpl
	 */
	public JavaMetaInfoMapImpl() {
		putClass(org.activitytest.Root.class);
		putClass(org.activitytest.Customer.class);
		putClass(org.activitytest.customer.SendEmailActivity.class);
	}


}
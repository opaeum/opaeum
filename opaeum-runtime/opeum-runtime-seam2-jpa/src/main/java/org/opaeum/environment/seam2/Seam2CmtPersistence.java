package org.opaeum.environment.seam2;

import org.jboss.seam.annotations.Name;
import org.opaeum.runtime.persistence.CmtPersistence;

@Name("cmtPersistence")
public class Seam2CmtPersistence extends AbstractSeam2Persistence implements CmtPersistence{
}

package org.opeum.environment.seam2;

import org.jboss.seam.annotations.Name;
import org.opeum.runtime.persistence.CmtPersistence;

@Name("cmtPersistence")
public class Seam2CmtPersistence extends AbstractSeam2Persistence implements CmtPersistence{
}

package org.opaeum.runtime.persistence;

/**
 * This class assumes that a transaction already exists when it is created. It will flush before transaction commit and close afterwards. Should ideally only be used
 * inside containers with an active JTA transaction
 * @author ampie
 *
 */
public interface CmtPersistence extends AbstractPersistence{
}

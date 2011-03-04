package net.sf.nakeduml.audit;

import java.util.Map;
import java.util.WeakHashMap;

import org.hibernate.cfg.Configuration;

public class AuditConfiguration {

	private AuditSyncManager auditSyncManager;
	private static Map<Configuration, AuditConfiguration> cfgs = new WeakHashMap<Configuration, AuditConfiguration>();

    public AuditConfiguration(Configuration cfg) {
        auditSyncManager = new AuditSyncManager();
    }
    
    public AuditSyncManager getSyncManager() {
        return auditSyncManager;
    }    
    
	public synchronized static AuditConfiguration getFor(Configuration cfg) {
		AuditConfiguration verCfg = cfgs.get(cfg);
		if (verCfg == null) {
			verCfg = new AuditConfiguration(cfg);
			cfgs.put(cfg, verCfg);
		}
		return verCfg;
	}

}

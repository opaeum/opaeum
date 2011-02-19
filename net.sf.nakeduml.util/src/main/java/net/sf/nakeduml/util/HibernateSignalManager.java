package net.sf.nakeduml.util;

import java.util.List;

import org.drools.common.InternalKnowledgeRuntime;
import org.drools.runtime.EnvironmentName;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.jbpm.process.instance.event.DefaultSignalManager;

public class HibernateSignalManager extends DefaultSignalManager {

    public HibernateSignalManager(InternalKnowledgeRuntime kruntime) {
        super(kruntime);
    }
    
    public void signalEvent(String type,
                            Object event) {
        for ( long id : getProcessInstancesForEvent( type ) ) {
            getKnowledgeRuntime().getProcessInstance( id );
        }
        super.signalEvent( type,
                           event );
    }

    @SuppressWarnings("unchecked")
    private List<Long> getProcessInstancesForEvent(String type) {
        Session em = (Session) getKnowledgeRuntime().getEnvironment().get( EnvironmentName.CMD_SCOPED_ENTITY_MANAGER );
        
        org.hibernate.Query processInstancesForEvent = em.createQuery("select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)" );
        processInstancesForEvent.setFlushMode(FlushMode.COMMIT);
        processInstancesForEvent.setParameter( "type",
                                               type );
        List<Long> list = (List<Long>) processInstancesForEvent.list();
        return list;
    }
}

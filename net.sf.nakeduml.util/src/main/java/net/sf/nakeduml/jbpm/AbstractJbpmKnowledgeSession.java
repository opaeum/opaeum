package net.sf.nakeduml.jbpm;


//TODO Weld
public abstract class AbstractJbpmKnowledgeSession /*implements Synchronization*/ {
//	private StatefulKnowledgeSession knowledgeSession;
//	@In
//	UserTransaction transaction;
//
//	protected abstract EntityManager getEntityManager();
//
//	protected abstract AbstractJbpmKnowledgeBase getJbpmKnowledgeBase();
//
//	public StatefulKnowledgeSession getKnowledgeSession() {
//		if (this.knowledgeSession == null) {
//			this.knowledgeSession = createKnowledgeSession();
//		}
//		return this.knowledgeSession;
//	}
//
//	protected StatefulKnowledgeSession createKnowledgeSession()  {
//		KnowledgeBase kbase = getJbpmKnowledgeBase().getKnowledgeBase();
//		if (Contexts.isEventContextActive()) {
//			try {
//				if (transaction.isActive()) {
//					transaction.registerSynchronization(this);
//				}else{
//					throw new IllegalStateException("Processes must be accessed from a transactional context");
//				}
//			} catch (SystemException e) {
//				throw new RuntimeException(e);
//			}
//			Properties properties = new Properties();
//			properties.setProperty("drools.commandService", "org.drools.persistence.session.SingleSessionCommandService");
//			properties.setProperty("drools.processInstanceManagerFactory",
//					"org.drools.persistence.processinstance.JPAProcessInstanceManagerFactory");
//			properties.setProperty("drools.workItemManagerFactory", "org.drools.persistence.processinstance.JPAWorkItemManagerFactory");
//			properties.put("drools.processInstanceManagerFactory", "org.jbpm.persistence.processinstance.JPAProcessInstanceManagerFactory");
//			properties.put("drools.processSignalManagerFactory", "org.jbpm.persistence.processinstance.JPASignalManagerFactory");
//			SessionConfiguration config = new SessionConfiguration(properties);
//			final Environment environment = EnvironmentFactory.newEnvironment();
//	        environment.set(EnvironmentName.OBJECT_MARSHALLING_STRATEGIES, new ObjectMarshallingStrategy[]{
//                    new JPAPlaceholderResolverStrategy(environment){
//
//						@Override
//						public Object read(ObjectInputStream is) throws IOException, ClassNotFoundException {
//					        String canonicalName = is.readUTF();
//					        Object id = is.readObject();
//					        EntityManager em =(EntityManager) environment.get(EnvironmentName.CMD_SCOPED_ENTITY_MANAGER);
//					        return em.find(Class.forName(canonicalName), id);
//						}
//                    	
//                    },
//                    new SerializablePlaceholderResolverStrategy( ClassObjectMarshallingStrategyAcceptor.DEFAULT  )
//                     });
//
//			environment.set(EnvironmentName.CMD_SCOPED_ENTITY_MANAGER, getEntityManager());
//			return kbase.newStatefulKnowledgeSession(config, environment);
//		} else {
//			Properties props = new Properties();
//			props.setProperty("drools.processInstanceManagerFactory", "org.jbpm.process.instance.impl.DefaultProcessInstanceManagerFactory");
//			props.setProperty("drools.processSignalManagerFactory", "org.jbpm.process.instance.event.DefaultSignalManagerFactory");
//			SessionConfiguration cfg = new SessionConfiguration(props);
//			EnvironmentImpl env = new EnvironmentImpl();
//			return kbase.newStatefulKnowledgeSession(cfg, env);
//		}
//	}
//
//	@Override
//	public void afterCompletion(int arg0) {
//		if (Contexts.isEventContextActive()) {
//			Contexts.getEventContext().remove("jbpmKnowledgeSession");
//		}
//	}
//
//	@Override
//	public void beforeCompletion() {
//	}
}
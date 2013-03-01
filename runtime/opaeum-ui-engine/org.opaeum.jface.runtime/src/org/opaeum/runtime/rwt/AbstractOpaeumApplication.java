package org.opaeum.runtime.rwt;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.opaeum.ecore.UimReader;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.Query;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.osgi.framework.Bundle;

public abstract class AbstractOpaeumApplication implements IOpaeumApplication{
	Bundle bundle;
	private Validator validator;
	protected ConversationalPersistence applicationPersistence;
	private UimResourceSet uimResourceSet;
	protected AbstractOpaeumApplication(Bundle bundle){
		super();
		this.bundle = bundle;
	}
	protected abstract IBusinessNetwork newBusinessNetwork();
	protected abstract IPersonNode newPersonNode(IBusinessNetwork bn,String emailAddress);
	protected abstract IBusinessCollaborationBase newBusinessCollaboration(IBusinessNetwork findOrCreateBusinessNetwork);
	@Override
	public void init(){
		getEnvironment().register();
		String path = getEnvironment().getProperty(Environment.DEV_UI_DIR);
		if(path != null && path.length() > 0){
			File file = new File(path);
			if(file.exists() && file.isDirectory()){
				uimResourceSet=new UimResourceSet(file);
			}
		}
		if(uimResourceSet==null){
			uimResourceSet=new UimResourceSet(bundle);
		}
	}
	@Override
	public synchronized IBusinessCollaborationBase createRootBusinessCollaboration(){
		IBusinessCollaborationBase result = newBusinessCollaboration(findOrCreateBusinessNetwork());
		getApplicationPersistence().persist(result);
		return result;
	}
	@Override
	public Validator getValidator(){
		if(validator == null){
			ClassLoader ccl=Thread.currentThread().getContextClassLoader();
			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			validator = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory().getValidator();
			Thread.currentThread().setContextClassLoader(ccl);
		}
		return validator;
	}
	protected IBusinessNetwork findOrCreateBusinessNetwork(){
		ConversationalPersistence persistence = getApplicationPersistence();
		Collection<IBusinessNetwork> readAll = readAllBusinessNetworks();
		for(Class<?> clss:getEnvironment().getMetaInfoMap().getAllClasses()){
			if(clss.isEnum()){
				try{
					Class<?> eec = clss.getClassLoader().loadClass(clss.getName() + "Entity");
					Object[] enumConstants = clss.getEnumConstants();
					for(Object object:enumConstants){
						if(object instanceof IEnum){
							if(persistence.find(eec, ((IEnum) object).getOpaeumId()) == null){
								Object newInstance = eec.getConstructor(clss).newInstance(object);
								persistence.persist(newInstance);
							}
						}
					}
				}catch(Exception e){
					System.out.println(e);
				}
			}
			persistence.flush();
		}
		if(readAll.isEmpty()){
			IBusinessNetwork bn = newBusinessNetwork();
			persistence.persist(bn);
			persistence.flush();
			return bn;
		}else{
			return readAll.iterator().next();
		}
	}
	protected Collection<IBusinessNetwork> readAllBusinessNetworks(){
		Query q = getApplicationPersistence().createQuery("from BusinessNetwork");
		Collection<IPersistentObject> people = q.executeQuery();
		Collection<IBusinessNetwork> result = new ArrayList<IBusinessNetwork>();
		for(IPersistentObject p:people){
			result.add((IBusinessNetwork) p);
		}
		return result;
	}
	@Override
	public PerspectiveConfiguration getPerspectiveConfiguration(){
		return (PerspectiveConfiguration) getResourceWrapper("perspective").getRoot();
	}
	@Override
	public synchronized IPersonNode findOrCreatePersonByEMailAddress(String id){
		// TODO find email addresses too
		Query q = getApplicationPersistence().createQuery("from PersonNode where username=:username");
		q.setParameter("username", id);
		Collection<IPersistentObject> people = q.executeQuery();
		for(IPersistentObject p:people){
			return (IPersonNode) p;
		}
		IPersonNode newPerson = newPersonNode(findOrCreateBusinessNetwork(), id);
		getApplicationPersistence().flush();
		return newPerson;
	}
	private ConversationalPersistence getApplicationPersistence(){
		if(applicationPersistence == null){
			applicationPersistence = getEnvironment().createConversationalPersistence();
		}
		return applicationPersistence;
	}
	@Override
	public AbstractUserInteractionModel getUserInteractionModel(String id){
		AbstractUserInteractionModel result = (AbstractUserInteractionModel) getResourceWrapper(id).getRoot();
		return result;
	}
	@Override
	public String getIdentifier(){
		return getEnvironment().getApplicationIdentifier();
	}
	@Override
	public URL getCubeUrl(){
		throw new IllegalStateException("getCubeUrl not implemented yet");
	}
	@Override
	public void dispose(){
		getEnvironment().unregister();
		if(this.applicationPersistence != null && this.applicationPersistence.isOpen()){
			this.applicationPersistence.close();
		}
		this.uimResourceSet = null;
		this.validator = null;
	}
	private AbstractUimResource getResourceWrapper(String id){
		return uimResourceSet.getResource(id + ".uim");
	}
}

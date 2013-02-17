package org.opaeum.runtime.rwt;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.rap.rwt.application.EntryPoint;
import org.opaeum.ecore.EObject;
import org.opaeum.org.opaeum.rap.metamodels.uim.UimInstantiator;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.Query;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public abstract class AbstractOpaeumApplication implements IOpaeumApplication{
	Bundle bundle;
	Map<String,URL> uimFiles;
	Map<String,AbstractUserInteractionModel> userInteractionModels = Collections
			.synchronizedMap(new HashMap<String,AbstractUserInteractionModel>());
	private Validator validator;
	protected ConversationalPersistence applicationPersistence;
	protected AbstractOpaeumApplication(Bundle bundle){
		super();
		this.bundle = bundle;
	}
	protected abstract IBusinessNetwork newBusinessNetwork();
	protected abstract IPersonNode newPersonNode(IBusinessNetwork bn,String emailAddress);
	protected abstract IBusinessCollaborationBase newBusinessCollaboration(IBusinessNetwork findOrCreateBusinessNetwork);
	@Override
	public abstract Environment getEnvironment();
	@Override
	public abstract IBusinessCollaborationBase getRootBusinessCollaboration();
	@Override
	public synchronized IBusinessCollaborationBase createRootBusinessCollaboration(){
		IBusinessCollaborationBase result = newBusinessCollaboration(findOrCreateBusinessNetwork());
		getApplicationPersistence().persist(result);
		return result;
	}
	@Override
	public abstract Class<? extends EntryPoint> getEntryPointType();
	@Override
	public Validator getValidator(){
		if(validator == null){
			validator = Validation.buildDefaultValidatorFactory().getValidator();
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
	private  ConversationalPersistence getApplicationPersistence(){
		if(applicationPersistence == null){
			applicationPersistence = getEnvironment().createConversationalPersistence();
		}
		return applicationPersistence;
	}
	@Override
	public AbstractUserInteractionModel getUserInteractionModel(String id){
		try{
			AbstractUserInteractionModel result = userInteractionModels.get(id);
			if(result == null){
				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document doc = db.parse(getUimFiles().get(id).openStream());
				Element xml = doc.getDocumentElement();
				EObject e = UimInstantiator.INSTANCE.newInstance(xml.getNodeName());
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put(xml.getAttribute("xmi:id"), e);
				e.buildTreeFromXml(xml, map);
				e.populateReferencesFromXml(xml, map);
				result = (AbstractUserInteractionModel) e;
				userInteractionModels.put(id, result);
			}
			return result;
		}catch(ParserConfigurationException e){
			throw new RuntimeException(e);
		}catch(SAXException e){
			throw new RuntimeException(e);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	protected Map<String,URL> getUimFiles(){
		if(uimFiles == null){
			Enumeration<URL> entries = bundle.findEntries("/", "*.uim", true);
			uimFiles = new HashMap<String,URL>();
			while(entries.hasMoreElements()){
				URL url = (URL) entries.nextElement();
				uimFiles.put(url.getFile().substring(0, url.getFile().length() - 4), url);
			}
		}
		return uimFiles;
	}
	@Override
	public String getIdentifier(){
		return getEnvironment().getApplicationIdentifier();
	}
	@Override
	public URL getCubeUrl(){
		throw new IllegalStateException("getCubeUrl not implemented yet");
	}
}

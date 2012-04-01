package org.opaeum.demo.demo1;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.Query;
import org.opaeum.uim.util.UimResourceImpl;
import org.opeum.demo1.util.Demo1JavaMetaInfoMap;

import structuredbusiness.Structuredbusiness;

public class Demo1OpaeumApplication implements IOpaeumApplication{
	ResourceSetImpl uimResourceSet = new ResourceSetImpl();
	Map<String,Resource> tempResources = new HashMap<String,Resource>();
	private EmfWorkspace emfWorkspace;
	// /TODO regularly close and reopen;
	private ConversationalPersistence applicationPersistence;
	private Validator validator;
	public Demo1OpaeumApplication(){
	}
	@Override
	public String getIdentifier(){
		return "demo1";
	}
	@Override
	public Environment getEnvironment(){
		Demo1JpaEnvironment i = Demo1JpaEnvironment.getInstance();
		return i;
	}
	@Override
	public Validator getValidator(){
		if(validator == null){
			validator = Validation.buildDefaultValidatorFactory().getValidator();
		}
		return validator;
	}
	private IBusinessNetwork findOrCreateBusinessNetwork(){
		Collection<BusinessNetwork> readAll = getApplicationPersistence().readAll(BusinessNetwork.class);
		if(readAll.isEmpty()){
			BusinessNetwork bn = new BusinessNetwork();
			getApplicationPersistence().persist(bn);
			getApplicationPersistence().flush();
			return bn;
		}else{
			return readAll.iterator().next();
		}
	}
	@Override
	public IBusinessCollaborationBase createRootBusinessCollaboration(){
		Structuredbusiness result = new Structuredbusiness((BusinessNetwork) findOrCreateBusinessNetwork());
		getEnvironment().createConversationalPersistence().flush();
		return result;
	}
	@Override
	public IBusinessCollaborationBase getRootBusinessCollaboration(){
		BusinessNetwork bn = (BusinessNetwork) findOrCreateBusinessNetwork();
		Set<Structuredbusiness> bcs = bn.getStructuredbusiness();
		for(IBusinessCollaboration bc:bcs){
			return bc;
		}
		return null;
	}
	@Override
	public EmfWorkspace getEmfWorkspace(){
		if(this.emfWorkspace == null){
			ResourceSetImpl resourceSet = new ResourceSetImpl();
			URI uri = URI.createPlatformPluginURI("/org.opaeum.demo.models/src/BusinessStructureModel.uml", true);
			org.eclipse.uml2.uml.Package model = null;
			try{
				Resource rs = resourceSet.createResource(uri);
				rs.load(resourceSet.getURIConverter().createInputStream(uri), null);
				model = (org.eclipse.uml2.uml.Package) rs.getContents().get(0);
				emfWorkspace = new EmfWorkspace(model, null, "john");
			}catch(IOException e){
				throw new RuntimeException(e);
			}
		}
		return this.emfWorkspace;
	}
	@Override
	public IPersonNode findOrCreatePersonByEMailAddress(String id){
		// TODO find email addresses too
		Query q = getApplicationPersistence().createQuery("from PersonNode where username=:username");
		q.setParameter("username", id);
		Collection<IPersistentObject> people = q.executeQuery();
		for(IPersistentObject p:people){
			return (IPersonNode) p;
		}
		PersonNode newPerson = new PersonNode((BusinessNetwork) findOrCreateBusinessNetwork(), id);
		getApplicationPersistence().flush();
		return newPerson;
	}
	public ConversationalPersistence getApplicationPersistence(){
		if(applicationPersistence == null){
			applicationPersistence = getEnvironment().createConversationalPersistence();
		}
		return applicationPersistence;
	}
	@Override
	public Resource getUimResource(String id){
		URI uri = URI.createPlatformPluginURI("/org.opaeum.demo.models/src/ui/" + id + ".uml", true);
		try{
			Resource resource = uimResourceSet.getResource(uri, false);
			if(resource == null || true){
				if(uimResourceSet.getURIConverter().exists(uri, Collections.emptyMap())){
					resource = uimResourceSet.createResource(uri);
					resource.load(uimResourceSet.getURIConverter().createInputStream(uri), null);
				}else{
					resource = tempResources.get(id);
					if(resource == null){
						resource = new UimResourceImpl(uri);
						tempResources.put(id, resource);
					}
				}
			}
			return resource;
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args){
		JavaTypedElement typedElement = new Demo1JavaMetaInfoMap().getTypedElement("914890@_MwHicHvJEeGIOPhylek76A");
		System.out.println(typedElement.getDeclaringClass());
	}
}

package org.opaeum.demo.demo1;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.Validator;

import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.graphics.Resource;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.rwt.AbstractOpaeumApplication;
import org.opeum.demo1.util.Demo1JavaMetaInfoMap;

public class Demo1OpaeumApplication extends AbstractOpaeumApplication{
	Map<String,Resource> tempResources = new HashMap<String,Resource>();
	// /TODO regularly close and reopen;
	private ConversationalPersistence applicationPersistence;
	private Validator validator;
	public Demo1OpaeumApplication(){
	}
	public Environment getEnvironment(){
		Demo1JpaEnvironment i = Demo1JpaEnvironment.getInstance();
		return i;
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
	@Override
	public URL getCubeUrl(){
		return getClass().getClassLoader().getResource("cube.xml");
	}
	public Class<? extends EntryPoint> getEntryPointType(){
		return null;
	}
	@Override
	protected IBusinessNetwork newBusinessNetwork(){
		return new BusinessNetwork();
	}

	@Override
	protected IPersonNode newPersonNode(IBusinessNetwork findOrCreateBusinessNetwork,String id){
		return new PersonNode((BusinessNetwork) findOrCreateBusinessNetwork, id);
	}
	@Override
	protected IBusinessCollaborationBase newBusinessCollaboration(IBusinessNetwork bn){
		return new Structuredbusiness((BusinessNetwork)bn);
	}
}

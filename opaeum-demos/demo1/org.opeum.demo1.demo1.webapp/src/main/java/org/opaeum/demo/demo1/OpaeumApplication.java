package org.opaeum.demo.demo1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.internal.resource.UMLResourceImpl;
import org.opaeum.emf.load.EmfWorkspaceLoader;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.DefaultTransformationLog;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.osgi.framework.Bundle;

import structuredbusiness.Structuredbusiness;

public class OpaeumApplication implements IOpaeumApplication{
	private EmfWorkspace emfWorkspace;
	public OpaeumApplication(){
	}
	@Override
	public Environment getEnvironment(){
		Demo1JpaEnvironment i = Demo1JpaEnvironment.getInstance();
		return i;
	}
	@Override
	public IBusinessNetwork getBusinessNetwork(){
		Collection<BusinessNetwork> readAll = getEnvironment().getPersistence().readAll(BusinessNetwork.class);
		if(readAll.isEmpty()){
			BusinessNetwork bn = new BusinessNetwork();
			getEnvironment().getPersistence().persist(bn);
			getEnvironment().getPersistence().flush();
			return bn;
		}else{
			return readAll.iterator().next();
		}
	}
	@Override
	public IBusinessCollaborationBase createRootBusinessCollaboration(){
		return new Structuredbusiness();
	}
	@Override
	public EmfWorkspace getEmfWorkspace(){
		if(this.emfWorkspace == null){
			ResourceSetImpl resourceSet = new ResourceSetImpl();
			URI uri = URI.createPlatformPluginURI("/org.opaeum.demo.models/src/BusinessStructureModel.uml", true);
			org.eclipse.uml2.uml.Package model=null;
			try{
				Resource rs = resourceSet.createResource(uri);
				rs.load(resourceSet.getURIConverter().createInputStream(uri), null);
				model=(org.eclipse.uml2.uml.Package)rs.getContents().get(0);
			}catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Resource r = resourceSet.getResource(uri, false);
			Bundle bundle = Platform.getBundle("org.opaeum.demo.models");
			// TODO check if this works in deployed mode
			int indexOf = bundle.getLocation().indexOf("file:");
			IPath location = Platform.getLocation();
			String substring = bundle.getLocation().substring(indexOf + 5);
			File dir = new File(location.toFile(), substring + "/src");
			emfWorkspace = new EmfWorkspace(model, null, "john");
		}
		return this.emfWorkspace;
	}
}

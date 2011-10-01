package org.opeum.eclipse;

import java.io.File;
import java.net.URL;

import net.sf.opeum.emf.workspace.UriToFileConverter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.osgi.framework.internal.core.BundleURLConnection;
import org.osgi.framework.Bundle;

public final class EclipseUriToFileConverter implements UriToFileConverter{
	@Override
	public File resolveUri(URI uri){
		if(uri.isFile()){
			return new File(uri.toFileString());
		}else if(uri.isPlatformPlugin()){
			Bundle bundle = Platform.getBundle(uri.segment(1));
			try{
				String[] segments = uri.segments();
				StringBuilder sb = new StringBuilder();
				for(int i = 2;i < segments.length;i++){
					sb.append("/");
					sb.append(segments[i]);
				}
				URL resource = bundle.getResource(sb.toString());
				if(resource == null){
					return null;
				}else{
					BundleURLConnection openConnection = (BundleURLConnection) resource.openConnection();
					String location = openConnection.getFileURL().toString().substring("file:".length());
					File file = new File(location);
					return file;
				}
			}catch(Exception e){
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}else{
			String platformString2 = uri.toPlatformString(true);
			try{
				IFile diFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString2));
				return diFile.getLocation().toFile();
			}catch(IllegalArgumentException a){
				try{
					IFolder diFile = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(platformString2));
					return diFile.getLocation().toFile();
				}catch(IllegalArgumentException a2){
					IProject diFile = ResourcesPlugin.getWorkspace().getRoot().getProject(platformString2);
					return diFile.getLocation().toFile();
				}
			}
		}
	}
}
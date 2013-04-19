package org.opaeum.eclipse.compiler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.opaeum.eclipse.javasync.JavaSourceSynchronizer;
import org.opaeum.eclipse.javasync.JavaTransformationProcessManager;

public class OpaeumIncrementalProjectBuilder extends IncrementalProjectBuilder{
	public OpaeumIncrementalProjectBuilder(){
	}
	@Override
	protected IProject[] build(int kind,Map<String,String> args,IProgressMonitor monitor) throws CoreException{
		if(kind == IncrementalProjectBuilder.FULL_BUILD){
			fullBuild(monitor);
		}else{
			IResourceDelta delta = getDelta(getProject());
			if(delta == null){
				fullBuild(monitor);
			}else{
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}
	private void incrementalBuild(IResourceDelta delta,IProgressMonitor monitor){
		Set<IFile> files = new HashSet<IFile>();
		addUmlFiles(files, delta);
		for(IFile f:files){
			JavaSourceSynchronizer s= JavaTransformationProcessManager.getSynchronizerFor(f);
			if(s!=null){
				s.synchronizeNow(monitor);
			}
		}
	}
	private void addUmlFiles(Set<IFile> files,IResourceDelta d){
		if(d.getResource() instanceof IFile){
			if(d.getResource().getFileExtension().equals("uml")){
				files.add((IFile) d.getResource());
			}
		}
		for(IResourceDelta c:d.getAffectedChildren()){
			addUmlFiles(files, c);
		}
	}
	private void fullBuild(IProgressMonitor monitor){
		// TODO REbuild directories
	}
}

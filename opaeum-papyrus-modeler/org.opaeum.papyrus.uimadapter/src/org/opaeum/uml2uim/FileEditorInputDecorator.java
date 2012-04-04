package org.opaeum.uml2uim;

import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.part.FileEditorInput;

public class FileEditorInputDecorator extends FileEditorInput{

	private FileEditorInput delegate;
	private String name;

	public FileEditorInputDecorator(FileEditorInput file, String name){
		super(file.getFile());
		this.delegate=file;
		this.name=name;
	}

	public int hashCode(){
		return delegate.hashCode();
	}

	public boolean equals(Object obj){
		return delegate.equals(obj);
	}

	public boolean exists(){
		return delegate.exists();
	}

	public String getFactoryId(){
		return delegate.getFactoryId();
	}

	public IFile getFile(){
		return delegate.getFile();
	}

	public ImageDescriptor getImageDescriptor(){
		return delegate.getImageDescriptor();
	}

	public String getName(){
		return name;
	}

	public IPersistableElement getPersistable(){
		return delegate.getPersistable();
	}

	public IStorage getStorage(){
		return delegate.getStorage();
	}

	public String getToolTipText(){
		return delegate.getToolTipText();
	}

	public void saveState(IMemento memento){
		delegate.saveState(memento);
	}

	public URI getURI(){
		return delegate.getURI();
	}

	public IPath getPath(){
		return delegate.getPath();
	}

	public String toString(){
		return delegate.toString();
	}

	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter){
		return delegate.getAdapter(adapter);
	}
}

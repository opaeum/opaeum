package org.opaeum.runtime.rwt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUimResourceWrapper extends AbstractUimResourceWrapper{
	private File file;
	private long lastModified;
	public FileUimResourceWrapper(File bundle,String id){
		super();
		this.file = new File(bundle, id + ".uim");
		lastModified=file.lastModified();
	}
	@Override
	protected boolean shouldReload(){
		return file.lastModified()>lastModified;
	}
	@Override
	protected InputStream createInputStream(){
		try{
			FileInputStream result = new FileInputStream(file);
			lastModified=file.lastModified();
			return result;
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}

package org.opaeum.runtime.rwt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUimResource extends AbstractUimResource{
	private File file;
	private long lastModified;
	public FileUimResource(File bundle,String fileName){
		super(fileName);
		this.file = new File(bundle, fileName);
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

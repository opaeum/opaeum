package net.sf.nakeduml.emf.workspace;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;

public class DefaultUriToFileConverter implements UriToFileConverter{
	@Override
	public File resolveUri(URI uri){
		if(!uri.isFile()){
			throw new IllegalStateException();
		}else{
			try{
				return new File(uri.toFileString()).getCanonicalFile();
			}catch(IOException e){
				throw new IllegalStateException(e);
			}
		}
	}
}

package net.sf.nakeduml.jbpm5;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.nakeduml.textmetamodel.TextSource;

import org.eclipse.emf.ecore.resource.Resource;

public class EmfTextSource implements TextSource {
	Resource resource;
	private String prefixToDelete;

	public EmfTextSource(Resource r) {
		this.resource=r;
	}
	public EmfTextSource(Resource r,String prefixToDelete) {
		this.resource=r;
		this.prefixToDelete=prefixToDelete;
	}

	@Override
	public char[] toCharArray() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			resource.save(baos, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String string = new String(baos.toByteArray());
		if(prefixToDelete!=null){
			string=string.replaceAll(":" + prefixToDelete, "");
			string=string.replaceAll(prefixToDelete+":" , "");
		}
		return string.toCharArray();
	}

	@Override
	public boolean hasContent() {
		return resource.getContents().size()>0;
	}
}

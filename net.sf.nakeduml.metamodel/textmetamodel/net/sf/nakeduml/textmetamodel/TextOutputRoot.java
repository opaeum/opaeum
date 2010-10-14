package net.sf.nakeduml.textmetamodel;


public class TextOutputRoot extends TextFileDirectory {
	protected TextOutputRoot(TextWorkspace parent, String name) {
		super(parent, name);
	}

	@Override
	protected void appendNameToRelativePath(StringBuilder sb) {
		sb.append("/");
	}
	@Override
	public TextOutputRoot getOutputRoot() {
		return this;
	
	}

}

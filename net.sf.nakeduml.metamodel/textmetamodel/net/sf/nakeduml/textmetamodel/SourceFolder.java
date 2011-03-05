package net.sf.nakeduml.textmetamodel;

public class SourceFolder extends TextDirectory {
	private boolean shouldClean;

	protected SourceFolder(TextProject textProject, String name, boolean shouldClean) {
		super(textProject, name);
		this.shouldClean = shouldClean;
	}

	public boolean shouldClean() {
		return shouldClean;
	}

	@Override
	protected void appendNameToRelativePath(StringBuilder sb) {
		sb.append("/");
		sb.append(name);
	}

	@Override
	public SourceFolder getSourceFolder() {
		return this;
	}
}

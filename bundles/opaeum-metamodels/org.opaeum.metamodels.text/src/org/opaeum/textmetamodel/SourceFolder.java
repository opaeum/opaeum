package org.opaeum.textmetamodel;

public class SourceFolder extends TextDirectory {
	private boolean shouldClean;
	private boolean isRegenerated=true;
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

	void shouldClean(boolean b){
		this.shouldClean=b;
	}

	public TextProject getProject(){
		// TODO Auto-generated method stub
		return (TextProject)getParent();
	}

	public boolean isRegenerated(){
		return isRegenerated;
	}

	public void setRegenerated(boolean isRegenerated){
		this.isRegenerated = isRegenerated;
	}
}

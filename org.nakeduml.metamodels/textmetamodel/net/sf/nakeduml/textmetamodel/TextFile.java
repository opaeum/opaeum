package net.sf.nakeduml.textmetamodel;

public class TextFile extends TextOutputNode {
	private TextSource textSource;
	private char[] content;
	private boolean overwrite;

	public TextSource getTextSource() {
		return this.textSource;
	}

	public TextFile(TextDirectory parent, String name, TextSource source, boolean overwrite) {
		super(parent, name);
		this.textSource = source;
		this.overwrite = overwrite;
	}

	public boolean overwrite() {
		return overwrite;
	}

	public char[] getContent() {
		if (content == null) {
			content = textSource.toCharArray();
		}
		return content;
	}

	public SourceFolder getOutputRoot() {
		TextDirectory parent = getParent();
		while (true) {
			if (parent instanceof SourceFolder) {
				return (SourceFolder) parent;
			} else if (parent.getParent() instanceof TextDirectory) {
				parent = (TextDirectory) parent.getParent();
			} else {
				throw new IllegalStateException("No TextOutputRoot found in file hierarchy");
			}
		}
	}

	@Override
	public TextDirectory getParent() {
		return (TextDirectory) super.getParent();
	}

	@Override
	public boolean hasContent() {
		return textSource.hasContent();
	}
}
package net.sf.nakeduml.textmetamodel;

public class TextFile extends TextFileNode {
	private TextSource textSource;
	private char[] content;

	public TextSource getTextSource() {
		return this.textSource;
	}

	public TextFile(TextFileDirectory parent, String name, TextSource source) {
		super(parent, name);
		this.textSource = source;
	}

	public char[] getContent() {
		if (content == null) {
			content = textSource.toCharArray();
		}
		return content;
	}

	public TextOutputRoot getOutputRoot() {
		TextFileDirectory parent = getParent();
		while (true) {
			if (parent instanceof TextOutputRoot) {
				return (TextOutputRoot) parent;
			} else if (parent.getParent() instanceof TextFileDirectory) {
				parent = (TextFileDirectory) parent.getParent();
			} else {
				throw new IllegalStateException("No TextOutputRoot found in file hierarchy");
			}
		}
	}

	@Override
	public TextFileDirectory getParent() {
		return (TextFileDirectory) super.getParent();
	}
	@Override
	public boolean hasContent() {
		return textSource.hasContent();
	}
}

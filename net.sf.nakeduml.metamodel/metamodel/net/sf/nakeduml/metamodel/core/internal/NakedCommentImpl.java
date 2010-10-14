package net.sf.nakeduml.metamodel.core.internal;

import net.sf.nakeduml.metamodel.core.INakedComment;

public class NakedCommentImpl extends NakedModelElementImpl implements INakedComment {
	private static final long serialVersionUID = -2277029928805037846L;
	private String body;

	@Override
	public String getMetaClass() {
		return "comment";
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}

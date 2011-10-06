package org.opeum.metamodel.core.internal;

import org.opeum.metamodel.core.INakedComment;

public class NakedCommentImpl extends NakedElementImpl implements INakedComment {
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

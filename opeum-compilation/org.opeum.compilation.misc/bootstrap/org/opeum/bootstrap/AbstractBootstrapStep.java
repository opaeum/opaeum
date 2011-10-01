package org.opeum.bootstrap;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.opeum.feature.ITransformationStep;
import org.opeum.metamodel.models.INakedModel;
import org.opeum.textmetamodel.ISourceFolderIdentifier;
import org.opeum.textmetamodel.TextSourceFolderIdentifier;
import org.opeum.velocity.AbstractTextProducingVisitor;

public abstract class AbstractBootstrapStep extends AbstractTextProducingVisitor implements ITransformationStep {
	protected INakedModel currentModel;


	protected void createConfig(String name, TextSourceFolderIdentifier outputRootId) {
		CharArrayWriter outputBuilder = copyResource(name);
		findOrCreateTextFile(outputBuilder, outputRootId, name);
	}

	protected void createDefaultHtmlPages(String name) {
		final CharArrayWriter outputBuilder = copyResource(name);
		findOrCreateTextFile(outputBuilder, TextSourceFolderIdentifier.WEBAPP_RESOURCE, name);
	}

	protected void createConfig(String name, ISourceFolderIdentifier outputRootId, String dir) {
		CharArrayWriter outputBuilder = copyResource(name);
		findOrCreateTextFile(outputBuilder, outputRootId, dir, name);
	}

	protected CharArrayWriter copyResource(String name) {
		final InputStream inputStream = this.getClass().getResourceAsStream("/" + name);
		String string;
		final CharArrayWriter outputBuilder = new CharArrayWriter();
		if (inputStream != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			try {
				while (null != (string = reader.readLine())) {
					outputBuilder.append(string).append('\n');
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return outputBuilder;
	}

}

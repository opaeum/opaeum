package net.sf.nakeduml.ocl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.internal.NakedPackageImpl;
import nl.klasse.octopus.oclengine.IOclEngine;
import nl.klasse.octopus.oclengine.internal.OclError;
public class ExpressionAssimilator {
	private File projectRoot;
	private NakedUmlConfig config;
	public ExpressionAssimilator(NakedUmlConfig config, File projectRoot) {
		this.projectRoot = projectRoot;
		this.config = config;
	}
	public void readConstraints(INakedPackage model, IOclEngine myEngine) {
		List filenames = new ArrayList();
		List streams = new ArrayList();
		// TODO make "expression" configurable
		File expressionDir = new File(this.projectRoot, "expressions");
		if (expressionDir.exists()) {
			readDirectory(expressionDir, filenames, streams);
			if (!filenames.isEmpty()) {
				List errors = new ArrayList();
				List results = myEngine.parseAndAnalyze(filenames, streams, model, errors);
				handleErrors(errors);
				if (model instanceof NakedPackageImpl) {
					((NakedPackageImpl) model).mergeExpressions(results);
				}
				closeStreams(streams);
			}
		}
	}
	private void closeStreams(List streams) {
		Iterator it = streams.iterator();
		while (it.hasNext()) {
			FileInputStream fis = (FileInputStream) it.next();
			try {
				fis.close();
			} catch (IOException e) {
			}
		}
	}
	private void readDirectory(File oclFolder, List<String> filenames, List<FileInputStream> streams) {
		File[] oclContents = oclFolder.listFiles();
		for (File element : oclContents) {
			// search for folders - these should be included also
			if (element.isDirectory()) {
				readDirectory(element, filenames, streams);
			} else if (element.isFile()) {
				addFileForAnalysis(element, filenames, streams);
			}
		}
	}
	private void addFileForAnalysis(File oclFile, List<String> filenames, List<FileInputStream> streams) {
		try {
			if (oclFile.getName().endsWith(".ocl")) {
				filenames.add(oclFile.getAbsolutePath());
				streams.add(new FileInputStream(oclFile));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	private void handleErrors(List errors) {
		Iterator it = errors.iterator();
		while (it.hasNext()) {
			OclError err = (OclError) it.next();
			// TODO show this to the user
			System.out.println(err);
		}
	}
}

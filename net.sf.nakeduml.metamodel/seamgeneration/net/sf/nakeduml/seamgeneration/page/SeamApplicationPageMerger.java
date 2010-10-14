package net.sf.nakeduml.seamgeneration.page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.seamgeneration.UserInteractionElementVisitor;
import net.sf.nakeduml.textmetamodel.TextFile;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

@StepDependency(phase = SeamTransformationPhase.class, after = SeamPageBuilder.class)
public class SeamApplicationPageMerger extends UserInteractionElementVisitor {

	// public static final String VIEW_DIR = "gen-resources";
	public static final String VIEW_DIR = "gen-view";

	@VisitAfter
	public void visitAfterWorkspace(UserInteractionWorkspace e) {
		TextOutputRoot outputRoot = textWorkspace.findOrCreateTextOutputRoot(VIEW_DIR);
		List<String> path = new ArrayList<String>();
		path.add("WEB-INF");
		path.add("pages.xml");
		TextFile t = outputRoot.findOrCreateTextFile(path, null);

		StringBuffer sb = new StringBuffer();
		File pages = new File(this.config.getApplicationPagesXmlPath() + "/pages.xml");
		if (pages.exists()) {
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(pages));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append("\n");
					sb.append(line);
				}
				sb.append("\n");
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			
			SeamPageSource seamPagesSource = (SeamPageSource) t.getTextSource();
			seamPagesSource.setApplicationPages(sb);
		}
	}
}
